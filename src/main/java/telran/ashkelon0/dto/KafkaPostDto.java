package telran.ashkelon0.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KafkaPostDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2476308943428998682L;
	String id;
	boolean typePost;
	String userLogin;
	String type;
	Set<String> tags;
	LocationDto location;
}
