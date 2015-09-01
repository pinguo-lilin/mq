package pinguo.rocket.mq.entity;

public class Subscribe {
	private String topic;
	private String tags;
	
	public Subscribe(String topic, String tags)
	{
		this.topic = topic;
		this.tags = tags;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
}
