package Controller;
import java.awt.Desktop.Action;

import DAO.GameDAO;
import DTO.GameDTO;

public class GameController {
	int result=0;
	GameDAO dao=new GameDAO();
	
	public int Con_join(int save_no, int status_no, int player_no, int scenario_no) {
		GameDTO dto=new GameDTO(save_no, status_no, player_no, scenario_no);
		GameDAO dao=new GameDAO();
		result=dao.join(dto);
		return result;
	}
	
	// 나머지는 다른 분들 코드 합치기!
}

