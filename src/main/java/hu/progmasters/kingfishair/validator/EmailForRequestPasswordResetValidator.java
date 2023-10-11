package hu.progmasters.kingfishair.validator;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.incoming.EmailForRequestPasswordReset;
import hu.progmasters.kingfishair.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpServletRequest;

@Component
public class EmailForRequestPasswordResetValidator implements Validator {

    private UserRepository userRepository;

    public EmailForRequestPasswordResetValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EmailForRequestPasswordReset.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        EmailForRequestPasswordReset emailForRequestPasswordReset = (EmailForRequestPasswordReset) target;

        String email = emailForRequestPasswordReset.getUsername();
        RegisteredUser customUser = userRepository.findByEmail(email).orElse(null);
        if (!email.contains("@")) {
            errors.rejectValue("username", "email.not.contains.at");
        }
        if (!email.contains(".")) {
            errors.rejectValue("username", "email.not.contains.dot");
        }

    }
}
