package com.sqa.onlinepizzastore.validations;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.*;

import com.google.common.base.Joiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public void initialize(ValidPassword arg0) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
//		// rules to be checked
//		Integer uppercaseLetters = 1;
//		Integer digitsNumber = 1;
//		Integer specialCharacters = 1;
//		Integer minlength = 8;
//		Integer maxLength = 30;
//
//		// add counters for each rule
//		Integer foundUppercaseLetters = 0;
//		Integer foundDigitsNumber = 0;
//		Integer foundSpecialCharacters = 0;
//
//		// check UppercaseCharacterRule, DigitCharacterRule and specialCharacters
//		for (int i = 0; i < password.length(); i += 1) {
//			if (Character.isDigit(password.charAt(i))) {
//				foundDigitsNumber++;
//				continue;
//			}
//
//			if (password.substring(i, i + 1).matches("[^A-Za-z0-9 ]")) {
//				foundSpecialCharacters++;
//				continue;
//			}
//
//			if (Character.isUpperCase(password.charAt(i))) {
//				foundUppercaseLetters++;
//				continue;
//			}
//		}
//
//		// check if there are enough digits
//		if (foundDigitsNumber < digitsNumber) {
//			System.out.println(" Password must contain at least " + digitsNumber + " digit characters. ");
//			return false;
//		}
//
//		// check if there are enough special characters
//		if (foundSpecialCharacters < specialCharacters) {
//			System.out.println(" Password must contain at least " + specialCharacters + " special characters. ");
//			return false;
//		}
//
//		// check if there are enough uppercase letters
//		if (foundUppercaseLetters < uppercaseLetters) {
//			System.out.println(" Password must contain at least " + uppercaseLetters + " uppercase characters. ");
//			return false;
//		}
//
//		// check if password length is at least of length 8
//		if (minlength > password.length()) {
//			System.out.println("Password must be at least 8 characters in length.");
//			return false;
//		}
//		// check if password length is at most of length 30
//		if (maxLength < password.length()) {
//			System.out.println("Password must be maximum 30 characters in length.");
//			return false;
//		}
		
		// ===========================================================================
		
		PasswordValidator validator = new PasswordValidator(
				Arrays.asList(new UppercaseCharacterRule(1), new DigitCharacterRule(1), new SpecialCharacterRule(1),
						new NumericalSequenceRule(3, false), new AlphabeticalSequenceRule(3, false),
						new QwertySequenceRule(3, false), new WhitespaceRule(), new LengthRule(8, 30)));

		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Joiner.on(" ").join(validator.getMessages(result)))
				.addConstraintViolation();
		return false;
	}
}

























