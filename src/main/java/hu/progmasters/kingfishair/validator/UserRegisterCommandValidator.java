package hu.progmasters.kingfishair.validator;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.incoming.EmailForRequestPasswordReset;
import hu.progmasters.kingfishair.dto.incoming.UserRegisterCommand;
import hu.progmasters.kingfishair.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegisterCommandValidator implements Validator {

    private UserRepository userRepository;

    public UserRegisterCommandValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterCommand.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterCommand userRegisterCommand = (UserRegisterCommand) target;

        String email = userRegisterCommand.getEmail();
        RegisteredUser customUser = userRepository.findByEmail(email).orElse(null);
        if (customUser!=null) {
            errors.rejectValue("email", "registerCommand.user.exists");
        }
        if (!email.contains("@")) {
            errors.rejectValue("email", "registerCommand.email.not.contains.at");
        }
        if (!email.contains(".")) {
            errors.rejectValue("email", "registerCommand.email.not.contains.dot");
        }
        if (!userRegisterCommand.getName().contains(" ")){
            errors.rejectValue("name", "registerCommand.name.not.fullname");
        }
        if (!userRegisterCommand.getPhoneNumber().matches("[0-9+]+")){
            errors.rejectValue("phoneNumber", "registerCommand.phone.number.string");
        }
        if (!userRegisterCommand.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")){
            errors.rejectValue("password", "registerCommand.password.short");
        }


    }

}
