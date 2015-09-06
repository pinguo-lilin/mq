package pinguo.rocket.mq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pinguo.rocket.mq.entity.Subscribe;

@Repository
public interface IsubscribeDao {
	public List<Subscribe> findList();
	public int add(Subscribe subscribe);
	public List<Subscribe> findByCid(int cid);
}
