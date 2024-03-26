package DTO;

// 시나리오용 DTO
public class ScenarioDTO {
	private int scenario_no;
	private String scenario_title;
	public ScenarioDTO(int scenario_no, String scenario_title) {
		this.scenario_no = scenario_no;
		this.scenario_title = scenario_title;
	}
	public int getScenario_no() {
		return scenario_no;
	}
	public void setScenario_no(int scenario_no) {
		this.scenario_no = scenario_no;
	}
	public String getscenario_title() {
		return scenario_title;
	}
	public void setscenario_title(String scenario_title) {
		this.scenario_title = scenario_title;
	}
}
