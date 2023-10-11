package hu.progmasters.kingfishair.service;

import de.triology.recaptchav2java.ReCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReCaptchaService {

    @Value("6LcTQvMnAAAAADJBpJ-QkwPnpUmbh6EbHPnRszII")
    private String recaptchaKey;

    public boolean isValidToken(String token) {
        return new ReCaptcha(recaptchaKey).isValid(token);
    }
}
