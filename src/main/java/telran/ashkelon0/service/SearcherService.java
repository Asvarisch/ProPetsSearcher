package telran.ashkelon0.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.ashkelon0.dao.LostFoundRepository;
import telran.ashkelon0.dto.KafkaPostDto;
import telran.ashkelon0.model.ElasticPost;


@Component
public class SearcherService {
	
	private static final String LOST_FOUND_TOPIC = "ir4jztza-lostfound";

//	@Autowired
//	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	LostFoundRepository repository;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EmailService emailService;
	
	
	
	@KafkaListener(topics = LOST_FOUND_TOPIC)
	public void reciveLFpostsFromKarafka(String postJson) {
		try {
			KafkaPostDto postDto =  mapper.readValue(postJson, KafkaPostDto.class);
			Point location = new Point(postDto.getLocation().getLatitude(), postDto.getLocation().getLongitude());
			Distance distance = new Distance(1000.0); //, Metrics.KILOMETERS
			ElasticPost post = modelMapper.map(postDto, ElasticPost.class);
			post.setLocation(location);
			repository.save(post);
			
			if (post.isTypePost()) {
				SearchHits<ElasticPost> returnedPosts = repository.findByTypePostAndTypeAndTagsInAndLocationNear(false, post.getType(), post.getTags(), location, distance);
				emailService.sendSimpleMessage(post.getUserLogin(), returnedPosts);
				returnedPosts.forEach(System.out::println);
				System.out.println("///////////////////////////");
				
			} else {
				SearchHits<ElasticPost> returnedPosts = repository.findByTypePostAndTypeAndTagsInAndLocationNear(true, post.getType(), post.getTags(), location, distance);
				emailService.sendSimpleMessage(post.getUserLogin(), returnedPosts);
				returnedPosts.forEach(System.out::println);
				System.out.println("///////////////////////////");
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}

}





//if (post.isTypePost()) {
//	kafkaTemplate.send(FOUND_TOPIC, postJson);
//} else {
//	kafkaTemplate.send(LOST_TOPIC, postJson);
//}
