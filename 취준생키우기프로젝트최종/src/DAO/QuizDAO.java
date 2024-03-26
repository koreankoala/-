package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.QuizDTO;

public class QuizDAO {
	CommonDAO common = new CommonDAO();
	ResultSet rs = null;

	public int join(QuizDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<QuizDTO> getQuiz(String type) throws SQLException {
		Connection conn = common.getConn();

		String sql = "select * from quiz where quiz_type = ?";
		PreparedStatement psmt = conn.prepareStatement(sql);

		psmt.setString(1, type);
		rs = psmt.executeQuery();
		ArrayList<QuizDTO> quizList = new ArrayList<QuizDTO>();
		while (rs.next()) {
			quizList.add(QuizDTO.getQuiz(rs.getInt("quiz_no"), type, rs.getString("question"), rs.getString("answer")));
		}
		conn.close();
		

		return quizList;
	}

}
