package com.dbc.pokesuits.config.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            //pelo menos 8 caracteres e maximo 30
            new LengthRule(8, 30),

            //pelo menos um caracter em maiusculo
            new CharacterRule(EnglishCharacterData.UpperCase, 1),

            // pelo menos um caracter minusculo
            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            //pelo menos um numero
            new CharacterRule(EnglishCharacterData.Digit, 1),

            // pelo menos um caracter especial
            new CharacterRule(EnglishCharacterData.Special, 1),

            // sem espaços brancos
            new WhitespaceRule()

        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
            .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
        return false;
    }
}