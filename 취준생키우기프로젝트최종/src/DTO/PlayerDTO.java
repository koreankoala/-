package DTO;

//회원가입용 DTO
public class PlayerDTO {
	// PlayerDTO 필드
	private int player_no; // pk
	private String id; 
	private String password;
	private String nickname;
	
	// PlayerDTO 생성자
	public PlayerDTO(int player_no, String id, String password, String nickname) {
		this.player_no = player_no; //pk
		this.id = id;
		this.password = password;
		this.nickname = nickname;
	}
	
	public PlayerDTO(String id, String password, String nickname) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
	}
	
	public PlayerDTO() {
		// TODO Auto-generated constructor stub
	}
	
	// PlayerDTO getter setter
	public int getplayer_no() {
		return player_no;
	}

	public void setplayer_no(int player_no) {
		this.player_no = player_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getnickname() {
		return nickname;
	}

	public void setnickname(String nickname) {
		this.nickname = nickname;
	}


	
	
}
