package pinguo.rocket.mq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pinguo.rocket.mq.dao.IsubscribeDao;
import pinguo.rocket.mq.entity.Consumer;
import pinguo.rocket.mq.entity.Subscribe;

@Service
public class SubscribeService {

	@Resource
	IsubscribeDao isubscribeDao;
	
	public int addSubscribe(Subscribe subscribe){
		return isubscribeDao.add(subscribe);
	}
	
	public List<Subscribe> findByCid(int cid){
		return isubscribeDao.findByCid(cid);
	}
	
	public Map<String, List<Subscribe>> listByConsumers(List<Consumer> consumers) {
		Map<String, List<Subscribe>> subsribeInfos = new HashMap<String, List<Subscribe>>();
		for (Consumer consumer : consumers) {
			List<Subscribe> subscribes = isubscribeDao.findByCid(consumer.getId());
			subsribeInfos.put(consumer.getName(), subscribes);
		}
		return subsribeInfos;
	}
}
