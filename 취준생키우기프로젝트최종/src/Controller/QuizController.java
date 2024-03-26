package Controller;


import java.sql.SQLException;
import java.util.ArrayList;

import DAO.QuizDAO;
import DTO.QuizDTO;

public class QuizController {

	public ArrayList<QuizDTO> getQuiz(String type) throws SQLException {
		QuizDAO dao = new QuizDAO();
		return dao.getQuiz(type);
	}

	// 나머지는 다른 분들 코드 합치기!

}
