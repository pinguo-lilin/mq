package pinguo.rocket.mq.entity;

import java.util.List;

public class Consumer {
	private int id;
	private String name;
	private String type;
	private List<Routing> routings;
	private List<Subscribe> subscribes;
	private int pollNameServerInteval;
	private int heartbeatBrokerInterval;
	private int persistConsumerOffsetInterval;
	private int consumeMessageBatchMaxSize;
	
	public int getPollNameServerInteval() {
		return pollNameServerInteval;
	}

	public void setPollNameServerInteval(int pollNameServerInteval) {
		this.pollNameServerInteval = pollNameServerInteval;
	}

	public int getHeartbeatBrokerInterval() {
		return heartbeatBrokerInterval;
	}

	public void setHeartbeatBrokerInterval(int heartbeatBrokerInterval) {
		this.heartbeatBrokerInterval = heartbeatBrokerInterval;
	}

	public int getConsumeMessageBatchMaxSize() {
		return consumeMessageBatchMaxSize;
	}

	public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
		this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
	}

	public int getPersistConsumerOffsetInterval() {
		return persistConsumerOffsetInterval;
	}

	public void setPersistConsumerOffsetInterval(int persistConsumerOffsetInterval) {
		this.persistConsumerOffsetInterval = persistConsumerOffsetInterval;
	}

	public List<Routing> getRoutings() {
		return routings;
	}

	public void setRoutings(List<Routing> routings) {
		this.routings = routings;
	}

	public List<Subscribe> getSubscribes() {
		return subscribes;
	}

	public void setSubscribes(List<Subscribe> subscribes) {
		this.subscribes = subscribes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
