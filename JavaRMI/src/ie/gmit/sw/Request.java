package ie.gmit.sw;

public class Request {
	String algorithim;
	String str1;
	String str2;
	String taskNumber;
	
	public String getAlgorithim() {
		return algorithim;
	}
	
	public void setAlgorithim(String algorithim) {
		this.algorithim = algorithim;
	}
	
	public String getStr1() {
		return str1;
	}
	
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	
	public String getStr2() {
		return str2;
	}
	
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	public String getTaskNumber() {
		return taskNumber;
	}
	
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	
	public Request(String algorithim, String str1, String str2, String taskNumber) {
		super();
		this.algorithim = algorithim;
		this.str1 = str1;
		this.str2 = str2;
		this.taskNumber = taskNumber;
	}
}
