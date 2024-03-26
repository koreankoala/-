package DTO;

// status 확인용 DTO
public class StatDTO {
	// StatDTO 필드
	private int status_no; // pk

	@Override
	public String toString() {
		return "action : " + action + ", health : " + health + ", stress : " + stress + ", intelligence : "
				+ intelligence;
	}

	private int player_no;
	private int health;
	private int action;
	private int stress;
	private int intelligence;
	private int stage;

	public StatDTO() {
	}

	public StatDTO(int health, int action, int stress, int intelligence, int stage) {
		super();
		this.health = health;
		this.action = action;
		this.stress = stress;
		this.intelligence = intelligence;
		this.stage = stage;
	}

	// StatDTO getter setter
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getStress() {
		return stress;
	}

	public void setStress(int stress) {
		this.stress = stress;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public ResultStatus attend() {
		this.action -= 5;
		this.health -= 5;
		this.stress += 5;
		return calculateStatus();
	}

	public ResultStatus absence() {
		this.action -= 20;
		this.intelligence -= 30;
		this.health += 40;
		this.stress -= 40;
		return calculateStatus();
	}

	public ResultStatus study() {
		this.action -= 5;
		this.intelligence += 5;
		this.health -= 5;
		this.stress += 10;
		return calculateStatus();
	}

	public ResultStatus nomalPass() {
		this.action -= 5;
		this.intelligence += 10;
		this.health -= 5;
		this.stress -= 5;
		return calculateStatus();
	}

	public ResultStatus fail() {
		this.action -= 5;
		this.intelligence -= 5;
		this.health -= 10;
		this.stress += 10;
		return calculateStatus();
	}

	public ResultStatus bonusPass() {
		this.intelligence += 5;
		this.health += 15;
		this.stress -= 10;
		return calculateStatus();
	}

	public ResultStatus rest() {
		this.action -= 5;
		this.health += 10;
		this.stress -= 5;
		return calculateStatus();
	}

	public ResultStatus leave() {
		this.action -= 5;
		this.intelligence -= 15;
		this.health += 5;
		this.stress -= 20;
		return calculateStatus();
	}

	public ResultStatus calculateStatus() {

		if (this.health <= 0 || this.stress >= 100) {
			return ResultStatus.HOSPITAL;
		}
		if (this.intelligence <= 0) {
			return ResultStatus.C_LEVEL;
		}
		if (this.stage <= 30) {
			return ResultStatus.PROCESS;
		}
		if (this.intelligence <= 10) {
			return ResultStatus.NO_JOB;
		}
		if (this.intelligence <= 20) {
			return ResultStatus.FAMILLY;
		}
		if (this.intelligence <= 30) {
			return ResultStatus.PART_TIME;
		}
		if (this.intelligence <= 70) {
			return ResultStatus.START_UP;
		}
		if (this.intelligence <= 85) {
			return ResultStatus.NO_IT;
		}
		return ResultStatus.BIG_TECH;
	}
}
