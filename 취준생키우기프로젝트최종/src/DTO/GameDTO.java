package DTO;

// 게임저장용 클래스
public class GameDTO {
	private int save_no;
	private int status_no;
	private int player_no;
	private int scenario_no;
	
	public GameDTO(int save_no, int status_no, int player_no, int scenario_no) {
		this.save_no = save_no;
		this.status_no = status_no;
		this.player_no = player_no;
		this.scenario_no = scenario_no;
	}
	public int getSave_no() {
		return save_no;
	}
	public void setSave_no(int save_no) {
		this.save_no = save_no;
	}
	public int getStatus_no() {
		return status_no;
	}
	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}
	public int getPlayer_no() {
		return player_no;
	}
	public void setPlayer_no(int player_no) {
		this.player_no = player_no;
	}
	public int getScenario_no() {
		return scenario_no;
	}
	public void setScenario_no(int scenario_no) {
		this.scenario_no = scenario_no;
	}
	
}
