package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.StatDTO;

public class StatDAO {
	CommonDAO common = new CommonDAO();
	ResultSet rs = null;
	int result = 0;

	public int join(StatDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	public StatDTO getStatus(int player_no) throws SQLException {
		Connection conn = common.getConn();

		String sql = "select * from status where player_no = ? ";
		PreparedStatement psmt = conn.prepareStatement(sql);

		psmt.setInt(1, player_no);
		rs = psmt.executeQuery();
		StatDTO dto = new StatDTO();

		while (rs.next()) {
			int status_no = rs.getInt("status_no");
			int health = rs.getInt("health");
			int action = rs.getInt("action");
			int stress = rs.getInt("stress");
			int intelligence = rs.getInt("intelligence");
			int stage = rs.getInt("stage");
			dto.setStatus_no(status_no);
			dto.setPlayer_no(player_no);
			dto.setHealth(health);
			dto.setAction(action);
			dto.setStress(stress);
			dto.setIntelligence(intelligence);
			dto.setStage(stage);
		}
		conn.close();

		return dto;
	}

	public boolean saveStatus(StatDTO statusDTO) throws SQLException {
		Connection conn = common.getConn();

		String sql = "update status set health = ?, action = ? , stress =?, intelligence = ?, stage =?  where player_no = ? ";
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, statusDTO.getHealth());
		psmt.setInt(2, statusDTO.getAction());
		psmt.setInt(3, statusDTO.getStress());
		psmt.setInt(4, statusDTO.getIntelligence());
		psmt.setInt(5, statusDTO.getStage());
		psmt.setInt(6, statusDTO.getPlayer_no());
		result = psmt.executeUpdate();
		if(result != 1) {
			return false;
		} 
		return true;
	}
	public StatDTO loadStat(StatDTO stat) {
        PreparedStatement psmt = null;
        ResultSet rs = null;

        CommonDAO common = new CommonDAO();
        common.getConn();

        StatDTO loadedStat = null;

        String sql = "SELECT * FROM game WHERE status_no = ?";

        try {
            psmt = common.conn.prepareStatement(sql);
            psmt.setInt(1, stat.getStatus_no());

			rs = psmt.executeQuery();

			if (rs.next()) {
				loadedStat = new StatDTO();
				loadedStat.setHealth(rs.getInt("health"));
				loadedStat.setAction(rs.getInt("action"));
				loadedStat.setIntelligence(rs.getInt("intelligence"));
				loadedStat.setStress(rs.getInt("stress"));
				loadedStat.setStage(rs.getInt("stage"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			common.close();
		}

		return loadedStat;
	}
}
