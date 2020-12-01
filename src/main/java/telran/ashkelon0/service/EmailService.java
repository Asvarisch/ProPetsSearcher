package telran.ashkelon0.service;

import org.springframework.data.elasticsearch.core.SearchHits;

import telran.ashkelon0.model.ElasticPost;

public interface EmailService {
	
	public void sendSimpleMessage(String to, SearchHits<ElasticPost> returnedPosts);

}
