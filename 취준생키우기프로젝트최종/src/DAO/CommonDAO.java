package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommonDAO {
	// DAO
	Connection conn = null;
	PreparedStatement psmt = null;

	// 동적로딩 및 DB 접속을 위한 메소드
	public Connection getConn() {
		try {
			// 동적로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB접속
			String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String db_url = "jdbc:oracle:thin:@58.123.161.218:1521:xe";
			String db_user = "jobseeker";
			String db_pw = "12345";

			conn = DriverManager.getConnection(db_url, db_user, db_pw);

		} catch (Exception e) {
			System.out.println("getConn 메소드 오류...!");
		}
		
		return conn;
	}

	// 객체를 반납 할수있는 메소드 생성 !
	public void close() {
		try {
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
