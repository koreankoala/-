package Controller;


import java.sql.SQLException;

import DAO.StatDAO;
import DTO.StatDTO;

public class StatController {

	StatDAO dao = new StatDAO();

	public StatDTO getStatus(int player_no) throws SQLException {
//		System.out.println("con>>dao:"+player_no);
		return dao.getStatus(player_no);
	}
	
	public boolean saveStatus(StatDTO statusDTO) throws SQLException {
		return dao.saveStatus(statusDTO);
	}
}
