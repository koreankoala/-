package Controller;

import DAO.PlayerDAO;
import DTO.PlayerDTO;

public class PlayerController {
	int result=0;
	PlayerDAO dao=new PlayerDAO();
	
	
	//회원가입관련
	public int Con_join(int player_no, String id, String password, String nickname) {
		PlayerDTO dto=new PlayerDTO(id, password, nickname);
		
		PlayerDAO dao=new PlayerDAO();
		
		result=dao.join(dto);
		
		return result;
	}//회원가입 끝

	
	// 로그인 기능
	
	// 로그인
    public PlayerDTO Con_login(int menu, String id, String pw) {
        PlayerDTO player = dao.login(id, pw);

        if (player != null) {
            System.out.println("로그인 성공! 환영합니다, "+ player.getnickname() + "님!");
        } else {
            System.out.println("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
        }
        return player;
    }
	
	
	
 // 회원 탈퇴
    public int Con_delete(String id) {
    	PlayerDAO dao = new PlayerDAO();
		
		
		//DTO에 무엇을받을지 모르니 기본타입으로 만들어놓고 쓰자!
		PlayerDTO dto = new PlayerDTO();
		dto.setId(id);
		
		result = dao.delete(dto);		
		return result;
    }
}
    



