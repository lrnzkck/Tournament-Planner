package com.agil.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.agil.model.Member;
import com.agil.service.MemberService;

@Component
public class MemberValidator implements Validator {

	@Autowired
	private MemberService memberService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (member.getUsername().length() < 6 || member.getUsername().length() > 32) {
			errors.reject("username", "Size.memberForm.username");
		}

		if (memberService.findByUsername(member.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.memberForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (member.getPassword().length() < 8 || member.getPassword().length() > 32) {
			errors.reject("password", "Size.memberForm.password");
		}

		if (!member.getPasswordConfirm().equals(member.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.memberForm.passwordConfirm");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if(memberService.findByEmail(member.getEmail()) != null) {
			errors.reject("email", "Duplicate.memberForm.username");
		}
		
		
		
	}

}
