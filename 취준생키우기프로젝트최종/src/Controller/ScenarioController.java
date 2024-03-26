package Controller;
import java.awt.Desktop.Action;

import DAO.ActionDAO;
import DTO.ActionDTO;

public class ScenarioController {
	int result=0;
	ActionDAO dao=new ActionDAO();
	
	public int Con_join(int action_no,String action_title,int limit_num) {
		ActionDTO dto=new ActionDTO(action_no,action_title,limit_num);
		ActionDAO dao=new ActionDAO();
		result=dao.join(dto);
		return result;
	}
	
	// 나머지는 다른 분들 코드 합치기!
}


