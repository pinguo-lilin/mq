package pinguo.rocket.mq.dao;

import java.util.List;

import pinguo.rocket.mq.entity.Routing;

public interface IroutingDao {
	public List<Routing> findList();
	public int add(Routing routing);
	public List<Routing> findByCid(int cid);
}
