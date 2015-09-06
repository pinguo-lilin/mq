package pinguo.rocket.mq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pinguo.rocket.mq.entity.Consumer;

@Repository
public interface IconsumerDao {
	public List<Consumer> findList();
	public int add(Consumer consumer);
}
