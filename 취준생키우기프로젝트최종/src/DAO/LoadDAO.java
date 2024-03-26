package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.PlayerDTO;

public class LoadDAO {

    PreparedStatement psmt = null;
    ResultSet rs = null;

    public PlayerDTO loadPlayer(PlayerDTO player) {
        CommonDAO common = new CommonDAO();
        common.getConn();

        PlayerDTO loadedPlayer = null;

        String sql = "SELECT * FROM game where player_no = ?";

        try {
            psmt = common.conn.prepareStatement(sql);
            psmt.setInt(1, player.getplayer_no());

            rs = psmt.executeQuery();

            if (rs.next()) {
                loadedPlayer = new PlayerDTO();
                loadedPlayer.setId(rs.getString("id"));
                loadedPlayer.setnickname(rs.getString("nickname"));
                loadedPlayer.setpassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            common.close();
        }

        return loadedPlayer;
    }
}
