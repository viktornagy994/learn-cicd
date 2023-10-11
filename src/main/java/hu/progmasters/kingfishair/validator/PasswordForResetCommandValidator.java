package hu.progmasters.kingfishair.validator;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.incoming.PasswordForResetCommand;
import hu.progmasters.kingfishair.dto.incoming.UserRegisterCommand;
import hu.progmasters.kingfishair.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordForResetCommandValidator implements Validator {



    public PasswordForResetCommandValidator() {

    }


    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordForResetCommand.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        PasswordForResetCommand passwordForResetCommand = (PasswordForResetCommand) target;

        if (passwordForResetCommand.getPassword().length()<6){
            errors.rejectValue("password", "registerCommand.password.short");
        }

    }
}
