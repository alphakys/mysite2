package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class BoardDao {
	
	
	
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
			List<BoardVo> bList;
		
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
		public int insert(BoardVo bv){
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "insert into board ";
			    	   query += "values(seq_board_no.nextval, ? , ? , ? , sysdate ) ";
					 
		
			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setString(1, bv.getName());
			    pstmt.setString(2, bv.getTitle());
			    pstmt.setString(3, bv.getContent());
			    
			    // 4. 결과처리
			   count = pstmt.executeUpdate();
			   
			   conn.commit();
		
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
	
		
		//모든 리스트 정보 가져오기
		public List<BoardVo> getList(){
			
			getConnection();
			bList = new ArrayList<>();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "select   no, ";
			    	   query += "        name, ";
			    	   query += "        title, ";
			    	   query += "        content, ";
			    	   query += "        to_char(reg_date, 'yyyy-mm-dd') ";
			    	   query += "from    board ";
			    
			    pstmt = conn.prepareStatement(query);	   
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	int no = rs.getInt(1);
			    	String name = rs.getString(2);
			    	String title = rs.getString(3);
			    	String content = rs.getString(4);
			    	String date = rs.getString(5);
			    	
			    	bList.add(new BoardVo(no, name, title, content, date));
			    }
			   
			    
			    
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			
			close();
			
			return bList;
		}
	
	
	
}
