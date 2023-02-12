package io.com.open.javaopenvalid.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private boolean capitalLetter = true;
    private boolean smallLetter = true;
    private boolean specialSymbol = true;
    private boolean number = true;

    private int mixedNum = 0;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (passwordCheck(value)) {
            return true;
        }
        return false;
    }

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.capitalLetter = constraintAnnotation.capitalLetter();
        mixedNum += this.capitalLetter ? 1 : 0;
        this.smallLetter = constraintAnnotation.smallLetter();
        mixedNum += this.smallLetter ? 1 : 0;
        this.specialSymbol = constraintAnnotation.specialSymbol();
        mixedNum += this.specialSymbol ? 1 : 0;
        this.number = constraintAnnotation.number();
        mixedNum += this.number ? 1 : 0;
    }

    private boolean passwordCheck(String password) {
        if (password == null || password.equals("")) {
            return false;
        }
        boolean checked = false;
        try {
            int checkNum = 0;
            String numberRedExp = "^(?=.*[0-9])(?=.\\S+$).{0,}$";
            String lowerRedExp = "^(?=.*[a-z])(?=.\\S+$).{0,}$";
            String upperRedExp = "^(?=.*[A-Z])(?=.\\S+$).{0,}$";
            String specialRedExp = "^(?=.*\\W)(?=.\\S+$).{0,}$";
            if (password.matches(numberRedExp) && this.number) {
                checkNum++;
            }
            if (password.matches(lowerRedExp) && this.smallLetter) {
                checkNum++;
            }
            if (password.matches(upperRedExp) && this.capitalLetter) {
                checkNum++;
            }
            if (password.matches(specialRedExp) && this.specialSymbol) {
                checkNum++;
            }
            if (checkNum >= this.mixedNum) {
                checked = true;
            }
        } catch (Exception e) {
            return false;
        }
        return checked;
    }
}
