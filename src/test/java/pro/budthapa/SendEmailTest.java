/**
 * 
 */
package pro.budthapa;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

/**
 * @author budthapa
 * Mar 19, 2017
 * 
 */
@Service
@SpringBootTest
public class SendEmailTest {
	@Autowired
    private EmailService emailService;

    public void sendEmail() throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("kasturitech16@gmail.com",
                        "Kasturi Technologies"))
                .to(Lists.newArrayList(
                        new InternetAddress("budthapa@gmail.com",
                        "Buddhi Bal Thapa")))
                .subject("You shall die! It's not me, it's Psychohistory")
                .body("Hello Planet!")
                .encoding("UTF-8").build();

        emailService.send(email);
    }
}
