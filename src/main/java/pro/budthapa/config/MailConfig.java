/**
 * 
 */
package pro.budthapa.config;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.ImageType;
import it.ozimov.springboot.mail.model.InlinePicture;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultInlinePicture;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import pro.budthapa.domain.Registration;

/**
 * @author budthapa
 * Mar 19, 2017
 * 
 */
@Configuration
public class MailConfig {
	@Autowired
	EmailService emailService;
	
	public Email sendEmailWithTemplate(Registration registration) throws Exception{
		final Email email=DefaultEmail.builder()
				.from(new InternetAddress("kasturitech@gmail.com", "Buddhi Bal Thapa"))
				.to(Lists.newArrayList(new InternetAddress(registration.getEmail(), registration.getName())))
				.subject("HisabKitab : New User Sign UP")
				.body("") //this will be replaced by thymeleaf
				.encoding("UTF-8")
				.build();
		return email;
	}
	
	public InlinePicture createInlinePicture(){
			System.out.println("checikg pics");
	        ClassLoader classLoader = getClass().getClassLoader();
	        File pictureFile = new File(classLoader.getResource("img/icon" + File.separator + "48x48.png").getFile());
	        Preconditions.checkState(pictureFile.exists(), "There is not picture %s", pictureFile.getName());

	        return DefaultInlinePicture.builder()
	                .file(pictureFile)
	                .imageType(ImageType.JPG)
	                .templateName("48x48.png").build();
	}
	
	/**
	 * @param register
	 * @param activationLink
	 * @return
	 */
	public Map<String, Object> objectMap(Registration register, String activationLink) {
		Map<String, Object> object=ImmutableMap.of(
				 "name", register.getName() ,
				 "email", register.getEmail(),
				 "activationLink", activationLink
				 );
		return object;
	}

	/**
	 * @param register
	 * @throws Exception 
	 * @throws CannotSendEmailException 
	 */
	public void sendVerificationEmail(Registration register, String activationLink) throws CannotSendEmailException, Exception {
		String template= "registrationEmail.html";

		emailService.send(sendEmailWithTemplate(register), template, 
				objectMap(register,activationLink), createInlinePicture());
		
	}
	

	public void sendEmail(Registration register,String plainPassword,String activationLink) throws UnsupportedEncodingException {
		final Email email = DefaultEmail.builder()
				.from(new InternetAddress("kasturitech16@gmail.com",
						"Kasturi Technologies"))
				.to(Lists.newArrayList(
						new InternetAddress(register.getEmail(),
								register.getName())))
				.subject("HisabKitab: New User Sign up")
				.body("Hello "+register.getName()+"!. Welcome to HisabKitab. Please activate your account by clicking this link "
				+activationLink+" Username: "+register.getEmail()+" Password: "+plainPassword)
				.encoding("UTF-8").build();
		
		emailService.send(email);
	}
}
