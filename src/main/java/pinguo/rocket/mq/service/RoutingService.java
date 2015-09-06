package pinguo.rocket.mq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pinguo.rocket.mq.dao.IroutingDao;
import pinguo.rocket.mq.entity.Consumer;
import pinguo.rocket.mq.entity.Routing;

@Service
public class RoutingService {

	@Resource
	IroutingDao iroutingDao;
	
	public int addRouting(Routing routing){
		return iroutingDao.add(routing);
	}
	
	public List<Routing> findByCid(int cid){
		return iroutingDao.findByCid(cid);
	}
	
	public Map<String, Map<String, String>> listByConsumers(List<Consumer> consumers) {
		Map<String, Map<String, String>> routingsInfos = new HashMap<String, Map<String, String>>();
		for (Consumer consumer : consumers) {
			Map<String, String> routingMap = new HashMap<String, String>();
			List<Routing> routings = iroutingDao.findByCid(consumer.getId());
			for (Routing routing : routings) {
				routingMap.put(routing.getTag(), routing.getUrl());
			}
			routingsInfos.put(consumer.getName(), routingMap);
		}
		return routingsInfos;
	}
}
