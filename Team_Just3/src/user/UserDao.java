package user;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DataSource;

/*
이름       널?       유형            
-------- -------- ------------- 
USERID   NOT NULL VARCHAR2(100) 
PASSWORD          VARCHAR2(100) 
NAME              VARCHAR2(100) 
ADDRESS           VARCHAR2(100) 
PHONE             VARCHAR2(50)  
GENDER            VARCHAR2(10) 
*/

public class UserDao {
	private DataSource dataSource;
	
	public UserDao() throws Exception{
		dataSource = new DataSource();
	}
	
	
	public int insert(User user) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(UserSQL.USER_INSERT);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getAddress());
		pstmt.setString(5, user.getPhone());
		pstmt.setString(6, user.getGender());
		int rowCount = pstmt.executeUpdate();
		
		pstmt.close();
		dataSource.close(con);
		
		return rowCount;
	}
	
	public User selectById(String userId) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(UserSQL.USER_SELECT_BY_ID);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		User user = null;
		while(rs.next()) {
			String userid = rs.getString("userid");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String phone = rs.getString("phone");
			String gender = rs.getString("gender");
			return user = new User(userid, password, name, address, phone, gender);
		}
		
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return user;
	}

	public int update(User user) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(UserSQL.USER_UPDATE);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getAddress());
		pstmt.setString(4, user.getPhone());
		pstmt.setString(5, user.getGender());
		pstmt.setString(6, user.getUserId());
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
		
	}
	
	public int delete(String userid)throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(UserSQL.USER_DELETE);
		pstmt.setString(1, userid);
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	
	
	//아이디 중복체크메서드
	public int countByUserId(String userId) throws Exception {
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(UserSQL.USER_SELECT_BY_ID_COUNT);
		pstmt.setString(1, userId);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		int userCount = rs.getInt(1);
		
		return userCount;
	}
	
	//아이디 찾기
		public User findUserId(String name, String phone) throws Exception {
			Connection con=dataSource.getConnection();
			PreparedStatement pstmt=con.prepareStatement(UserSQL.USER_FIND_BY_ID);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			ResultSet rs=pstmt.executeQuery();
			User user = null;
			while(rs.next()) {
				String userid = rs.getString("userid");
				String password = rs.getString("password");
				String name1 = rs.getString("name");
				String address = rs.getString("address");
				String phone2 = rs.getString("phone");
				String gender = rs.getString("gender");
				return user = new User(userid, password, name1, address, phone2, gender);
			}
			pstmt.close();
			dataSource.close(con);
			return user;
		}
		
		//아이디 찾기
				public User findUserPassword(String userid, String name, String phone) throws Exception {
					Connection con=dataSource.getConnection();
					PreparedStatement pstmt=con.prepareStatement(UserSQL.USER_FIND_BY_ID);
					pstmt.setString(1, userid);
					pstmt.setString(2, name);
					pstmt.setString(3, phone);
					ResultSet rs=pstmt.executeQuery();
					User user = null;
					while(rs.next()) {
						String userid1 = rs.getString("userid");
						String password = rs.getString("password");
						String name1 = rs.getString("name");
						String address = rs.getString("address");
						String phone2 = rs.getString("phone");
						String gender = rs.getString("gender");
						return user = new User(userid, password, name1, address, phone2, gender);
					}
					pstmt.close();
					dataSource.close(con);
					return user;
				}
		

		
	
	
}
