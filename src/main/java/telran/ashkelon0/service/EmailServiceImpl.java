package telran.ashkelon0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import telran.ashkelon0.model.ElasticPost;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
    private JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(String to, SearchHits<ElasticPost> returnedPosts) {
		String baseUrl = "http://localhost:8080/lostfound/en/v1/";
		String links = "";
		for (SearchHit<ElasticPost> searchHit : returnedPosts) {
			String link = baseUrl.concat(searchHit.getId());
			links = links.concat(link).concat("\n");
		}
		
		System.out.println(links);
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("propetsnotif@gmail.com");
        message.setTo(to); 
        message.setSubject("We probably found your pet!"); 
        message.setText(links);
        emailSender.send(message);
		
	}

}
