package pinguo.rocket.mq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pinguo.rocket.mq.dao.IuserDao;
import pinguo.rocket.mq.entity.User;

@Service
public class UserService {
	
	@Resource
	IuserDao dao;
	
	@Value("${jdbc.driver}")
	String url;
	
	public List<User> listUsers() {
		List<User> userList = dao.findList();
		return userList;
	}
	
	public int addUser(User user){
		int result = dao.add(user);
		System.out.println("Service->name:" + user.getName());
		return result;
	}
	
}

