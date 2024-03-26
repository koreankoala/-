package DTO;

public class ActionCount {
	private int attendCount;
	private int absenceCount;
	private int studyCount;
	private int solvingCount;
	private int restCount;
	private int leaveCount;

	public boolean attend() {
		if (this.attendCount > 0) {
			return false;
		}
		this.attendCount += 1;
		return true;
	}

	public boolean absence() {
		if (this.absenceCount > 0) {
			return false;
		}
		this.absenceCount += 1;
		return true;
	}

	public boolean study() {
		if (this.studyCount > 1) {
			return false;
		}
		this.studyCount += 1;
		return true;
	}

	public boolean solving() {
		if (this.solvingCount > 2) {
			return false;
		}
		this.solvingCount += 1;
		return true;
	}

	public boolean rest() {
		if (this.restCount > 1) {
			return false;
		}
		this.restCount += 1;
		return true;
	}

	public boolean leave() {
		if (this.leaveCount > 0) {
			return false;
		}
		this.leaveCount += 1;
		return true;
	}

	public int getAttendCount() {
		return attendCount;
	}

	public int getAbsenceCount() {
		return absenceCount;
	}

	public int getStudyCount() {
		return studyCount;
	}

	public int getSolvingCount() {
		return solvingCount;
	}

	public int getRestCount() {
		return restCount;
	}

	public int getLeaveCount() {
		return leaveCount;
	}

}
