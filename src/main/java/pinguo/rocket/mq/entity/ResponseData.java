package pinguo.rocket.mq.entity;

import java.util.Map;

public class ResponseData {
	private int status;
	private String message;
	private Map<String, Object> data;
	private long serverTime;
	
	public ResponseData(){
		this.serverTime = System.currentTimeMillis();
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getServerTime() {
		return serverTime;
	}
	public void setServerTime(long l) {
		this.serverTime = l;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
