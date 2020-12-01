package telran.ashkelon0.dao;

import java.util.Set;

import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import telran.ashkelon0.model.ElasticPost;

public interface LostFoundRepository extends ElasticsearchRepository<ElasticPost, String> {

	SearchHits<ElasticPost> findByTypePostAndTypeAndTagsInAndLocationNear(boolean typePost, String type, Set<String> tags, Point location, Distance distance);
//	SearchHits<ElasticPost> findByLocationNear(Point location, Distance distance);
//	SearchHits<ElasticPost> findByTypePostAndTypeAndTagsIn(boolean typePost, String type, Set<String> tags);

}
