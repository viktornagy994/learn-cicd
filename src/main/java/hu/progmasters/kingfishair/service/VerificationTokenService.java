package hu.progmasters.kingfishair.service;

import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.domain.VerificationToken;
import hu.progmasters.kingfishair.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
public class VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }


    public static Date addDays(Date d, int days){
        d.setTime(d.getTime() + days * 1000L * 60L * 60L * 24L);
        return d;
    }


    public void saveVerificationToken(VerificationToken verificationToken){
        this.verificationTokenRepository.save(verificationToken);
    }


    public RegisteredUser findUserByVerificationToken(String token) {
       return verificationTokenRepository.findUserByToken(token);
    }
}
