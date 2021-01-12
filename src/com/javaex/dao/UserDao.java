package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.UserVo;

public class UserDao {
	
	
	//<필드>
	
		// --연결관련 필드--
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		private String id = "webdb"; 
		private String pw = "webdb";
		
		// --리스트 필드--	
		List<UserVo> gList;
	
		// --각 메소드 결과처리 위한 필드--
		int count=0;
	
	//<생성자>
		
		
	//<g/s>
	
	
	//<<메서드>>
	
		//DB 연결
	
	public void getConnection() {
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, id , pw);

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}

	}
	
		//자원정리 메서드
	public void close() {
	    try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	
	
	//DB 등록
	public int insert(UserVo uv){
		
		getConnection();
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "insert into users ";
		    	   query += "values(seq_users_no.nextval, ? , ? , ? , ?) ";
				 
	
		    pstmt = conn.prepareStatement(query);	   
		    pstmt.setString(1, uv.getId());
		    pstmt.setString(2, uv.getPw());
		    pstmt.setString(3, uv.getName());
		    pstmt.setString(4, uv.getGender());
		    
		    // 4. 결과처리
		   count = pstmt.executeUpdate();
		   
		   conn.commit();
	
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return count;
	}

	
	
	//DB에서 아이디, 패스워드 확인용 user 받기
	public UserVo getUser(String id, String password){
		
		getConnection();
		
		UserVo authUser=null;
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "select   no , ";
				   query += "        name ";
				   query += "from   users ";
				   query += "where id = ? and password = ? ";
				   
			pstmt = conn.prepareStatement(query);	   
			pstmt.setString(1, id);	   
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();			
			
			while(rs.next()) {
		
				int no = rs.getInt("no");
				String name = rs.getString("name");
				
				authUser = new UserVo(no, name);
				
			}
			
		    
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return authUser;
		
	}

	
	//DB에서 no로 user 받기(목적 : 새로운 세션 생성용) || 오버로딩? 
	public UserVo getUser(int num){
		
		getConnection();
		
		UserVo newUser=null;
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "select   no , ";
				   query += "        name ";
				   query += "from   users ";
				   query += "where no = ? ";
				   
			pstmt = conn.prepareStatement(query);	   
			pstmt.setInt(1, num);	   
			
			rs = pstmt.executeQuery();			
			
			while(rs.next()) {
		
				int no = rs.getInt("no");
				String name = rs.getString("name");
				
				newUser = new UserVo(no, name);
				
			}
			
		    
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return newUser;
		
	}
	
	
	public List<UserVo> getList(){
		
		getConnection();
		gList = new ArrayList<>();
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "select   no, ";
		    	   query += "        name, ";
		    	   query += "        password, ";
		    	   query += "        content, ";
		    	   query += "        reg_date ";
		    	   query += "from    guestbook ";
		    
		    pstmt = conn.prepareStatement(query);	   
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()) {
		    	
		    	int no = rs.getInt(1);
		    	String name = rs.getString(2);
		    	String pw = rs.getString(3);
		    	String content = rs.getString(4);
		    	String date = rs.getString(5);
		    	
		    	gList.add(new UserVo(no, name, pw, content, date));
		    }
		   
		    
		    
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return gList;
	}
	
	//DB에서 삭제
	public int delete(int no, String name, String password){
		
		getConnection();
		
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
		
				String query = "delete from guestbook ";
		    	   	   query += "where no = ? and name = ? and password = ? ";
		    	   	   
		    	   	   pstmt = conn.prepareStatement(query);	   
		    	   	   pstmt.setInt(1, no);
		    	   	   pstmt.setString(2, name);
		    	   	   pstmt.setString(3, password);
		    	   	
		    	   	   count = pstmt.executeUpdate();
		    	   	  
		    	   	   conn.commit(); 
		    	   	  
		    	   	  
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return count;
	}
	
	
	//DB 수정
	public int update(UserVo uv){
		
		getConnection();
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "update users ";
		    	   query += "set   password = ?, ";			   
				   query += "      name = ?, ";
				   query += "      gender = ? ";
				   query +="where no = ? ";

		    pstmt = conn.prepareStatement(query);	
		    
		    pstmt.setString(1, uv.getPw());
		    pstmt.setString(2, uv.getName()); 
		    pstmt.setString(3, uv.getGender());
		    pstmt.setInt(4, uv.getNo());
		    
		    // 4. 결과처리
		   count = pstmt.executeUpdate();

		   conn.commit();
		   
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return count;
	}
	
	
	

	
}
