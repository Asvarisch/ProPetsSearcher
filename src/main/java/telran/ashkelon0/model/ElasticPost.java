package telran.ashkelon0.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.geo.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@Document(indexName = "#{@esConfig.getIndexName()}")
@Document(indexName = "lost-found")
public class ElasticPost {
	@Id
	String id;
	boolean typePost;
	String userLogin;
	String type;
	Set<String> tags;
	@GeoPointField
	Point location;
}
