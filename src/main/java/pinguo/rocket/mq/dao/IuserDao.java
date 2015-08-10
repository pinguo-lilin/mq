package pinguo.rocket.mq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import pinguo.rocket.mq.entity.User;

@Repository
public interface IuserDao {
	public List<User> findList();

	public int add(User user);
}
