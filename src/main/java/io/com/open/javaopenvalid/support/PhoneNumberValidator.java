package io.com.open.javaopenvalid.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (phoneNumberValid(value)) {
            return true;
        }
        return false;
    }

    private static boolean phoneNumberValid(String value) {
        String phone = toPhoneNumber(value.replaceAll("-", ""));
        if (phone.matches("^\\d{3}-\\d{3,4}-\\d{4}$")) {
            return true;
        } else if (phone.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$")) {
            return true;
        } else if (phone.matches("^\\d{4}-\\d{4}$")) {
            return true;
        } else {
            return false;
        }
    }

    private static String toPhoneNumber(String value) {
        if (value.startsWith("02")) {
            return value.replaceAll("([0-9]{2})([0-9]+)([0-9]{4})", "$1-$2-$3");
        } else if (value.length() == 8) {
            return value.replaceAll("([0-9]{4})([0-9]{4})", "$1-$2");
        } else {
            return value.replaceAll("([0-9]{3})([0-9]+)([0-9]{4})", "$1-$2-$3");
        }
    }
}
