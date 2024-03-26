package DTO;

// Option 클래스
public class ActionDTO {
	private int action_no;
	private String action_title;
	private int limit_num;
	
	public ActionDTO(int action_no, String action_title, int limit_num) {
		this.action_no = action_no;
		this.action_title = action_title;
		this.limit_num = limit_num;
	}
	public int getAction_no() {
		return action_no;
	}
	public void setAction_no(int action_no) {
		this.action_no = action_no;
	}
	public String getAction_title() {
		return action_title;
	}
	public void setAction_title(String action_title) {
		this.action_title = action_title;
	}
	public int getlimit_num() {
		return limit_num;
	}
	public void setlimit_num(int limit_num) {
		this.limit_num = limit_num;
	}

}
