package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.PlayerDTO;

public class PlayerDAO {
   
   
   CommonDAO common = new CommonDAO();
   
   Connection conn = null;
   
   PreparedStatement psmt = null;
 
   ResultSet rs = null;
   
   int result =0;
   
   
   
//   public CommonDAO getCommon() {
//	return common;
//   }
   
   
   //회원가입
   public int join(PlayerDTO dto) {
      //접속
      common.getConn();
      
      //sql문장
      
      
      String sql = "INSERT INTO PLAYER (id, password, nickname) VALUES (?, ?, ?)";
      try {
         psmt = common.conn.prepareStatement(sql);
         psmt.setString(1, dto.getId());
         psmt.setString(2, dto.getpassword());
         psmt.setString(3, dto.getnickname());
         
         //결과 처리
         result = psmt.executeUpdate();
         
     
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return result;
      
   }//회원가입 join끝 

   //로그인
   public PlayerDTO login(String id, String pw) {
	 //접속
	   common.getConn();
	   
	   PlayerDTO player = null;
	   //sql
	   String sql = "SELECT * FROM player WHERE id = ? AND password = ?";
	   
	   try {
	       psmt = common.conn.prepareStatement(sql);
	       psmt.setString(1, id);
	       psmt.setString(2, pw);
	       rs = psmt.executeQuery();
	       
	       if (rs.next()) {
	           player = new PlayerDTO();
	           player.setplayer_no(rs.getInt("player_no"));
	           player.setId(rs.getString("id"));
	           player.setnickname(rs.getString("nickname"));
	       }
	       
	   } catch (Exception e) {
	       e.printStackTrace();
	       System.out.println("로그인중 오류발생!");
	   }
	  
	   return player;
	}





   
   
   // 회원 탈퇴
   public int delete(PlayerDTO dto ) {
	   common.getConn();
       String sql = "DELETE FROM player WHERE id = ?";
       try {
    	   psmt = common.conn.prepareStatement(sql);
    	   psmt.setString(1, dto.getId());
           result =psmt.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
           
       }finally {
		common.close();
	}
       return result;
   }
   
   
   
   //해당 아이디를 가진 회원을 가져오는 메소드 (회원탈퇴에 필요)
  // public PlayerDTO getbyId(String id) {
	  // return dao.getbyId(id); 
  // }


}//DAO의 끝
	

