package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.PasswordResetToken;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.outgoing.PasswordResetTokenListItem;
import hu.progmasters.kingfishair.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
@Service
@Transactional
public class PasswordResetTokenService {


    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }


    public static Date addDays(Date d, int days){
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
        return d;
    }


    public void savePasswordResetToken(PasswordResetToken passwordResetToken){
        this.passwordResetTokenRepository.save(passwordResetToken);
    }


    public RegisteredUser findUserByPasswordResetToken(String token) {
        return passwordResetTokenRepository.findUserByToken(token);
    }


    public PasswordResetTokenListItem checkStatus(String token) {
        PasswordResetTokenListItem passwordResetTokenListItem =new PasswordResetTokenListItem( passwordResetTokenRepository.findPasswordResetTokenByToken(token));
        return passwordResetTokenListItem;
    }
}
