package com.vti.service;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailForgotPassword {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IAccountRepository accountRepository;

    // ham gui mail
    public void sendMail(String toEmail, String subject, String body){ // toEmail: email nhan, subject: tieu de gui mail, body: noi dung gui mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Tranquangtu487@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    // ham tao ra token
    public void linktoChangePassword(String email){
        // tao ra token
        UUID uuid = UUID.randomUUID();
        Account account =  accountRepository.findByEmail(email);
        account.setToken(uuid.toString());
        account.setTokenCreated(LocalDateTime.now());
        accountRepository.save(account);

        // ham gui mail
        String subject = "Thay doi mat khau";
        String body = "Click vao link de thay doi mat khau: http://127.0.0.1:5500/ChangePassword.html?token=" + uuid.toString();
        sendMail(email, subject, body);
    }
    // ham check thoi gian token
    public Boolean checkTimeToken(LocalDateTime token){
        LocalDateTime now = LocalDateTime.now();  // thoi gian hien tai
        Duration diff = Duration.between(token, now); // durration: ham so sanh thoi gian
        return diff.toMinutes() <= 10; // < 10p
        // now: 10h34 ong gui mail , Time_token = 10h34 => nhung den 11h, time_now = 11h, time_token = 10h34
    }

    // ham thay doi mat khau
    public void changePassword(String newPassword, String token) throws Exception {
       Account account  = accountRepository.findByToken(token);//tim va kiem tra xem token nhap la cua account nao
       if (checkTimeToken(account.getTokenCreated())){
           account.setPassword(new BCryptPasswordEncoder().encode(newPassword));// thay doi password
           account.setToken("");//sau khi thay doi thi xoa token
           accountRepository.save(account);
       }else {
           throw  new Exception("Token het han");
       }

    }



}
