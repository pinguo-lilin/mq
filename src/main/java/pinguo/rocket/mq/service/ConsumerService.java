package pinguo.rocket.mq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pinguo.rocket.mq.dao.IconsumerDao;
import pinguo.rocket.mq.entity.Consumer;

@Service
public class ConsumerService {
	
	@Resource
	IconsumerDao iconsumerDao;
	
	public int addConsumer(Consumer consumer) {
		return iconsumerDao.add(consumer);
	}
	
	public List<Consumer> list(){
		return iconsumerDao.findList();
	}
	
}
