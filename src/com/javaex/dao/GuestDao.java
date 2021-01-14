package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {
	
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
			List<GuestVo> gList;
		
		// --각 메소드 결과처리 위한 필드--
			int count=0;
			
			
		//<생성자>
		
		
		//<g/s>
		
		
		//<메서드>
		
		//--DB와 연결관련 메서드--
		
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
		
		
		////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////
		
		
		// DB에서 리스트 가져오기
		
		public List<GuestVo> getList(){
			
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
			    	
			    	gList.add(new GuestVo(no, name, pw, content, date));
			    }
			   
			    
			    
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return gList;
		}
		
		//DB에서 데이터 하나 가져오기
		public GuestVo getGuest(int no){
			
			getConnection();
			
			GuestVo gv=null;
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "select   no, ";
					   query += "        name, ";
					   query += "        password, ";
					   query += "        content, ";
					   query += "        reg_date ";
					   query += " from    guestbook ";
					   query += " where no = ? ";
					
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, no);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					int number = rs.getInt(1);
					String name = rs.getString(2);
					String password = rs.getString(3);
					String content = rs.getString(4);
					String reg_date = rs.getString(5);
					
					gv = new GuestVo(number,name,password,content,reg_date);
					
				}
				
			    
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return gv;
			
		}
		

		//DB에서 삭제
		public int delete(int no, String password){
			
			getConnection();
			
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			
					String query = "delete from guestbook ";
			    	   	   query += "where no = ? and password = ? ";
			    	   	   
			    	   	   pstmt = conn.prepareStatement(query);	   
			    	   	   pstmt.setInt(1, no);			    	 
			    	   	   pstmt.setString(2, password);
			    	   	
			    	   	   count = pstmt.executeUpdate();
			    	   	  
			    	   	   conn.commit(); 
			    	   	  
			    	   	  
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
		
		
		//DB 수정
		public int update(GuestVo gv){
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "update guestbook ";
			    	   query += "set   content = ?, ";			   
					   query += "      reg_date = sysdate ";
					   query +="where no = ? and name = ? and password = ? ";

			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setString(1, gv.getContent());
			    pstmt.setInt(2, gv.getNo()); 
			    pstmt.setString(3, gv.getName());
			    pstmt.setString(4, gv.getPw());
			    
			    // 4. 결과처리
			   count = pstmt.executeUpdate();

			   conn.commit();
			   
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
		
		

		
		
		//DB 등록
		public int insert(GuestVo gv){
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "insert into guestbook ";
			    	   query += "values(seq_no.nextval, ? , ? , ? , sysdate ) ";
					 

			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setString(1, gv.getName());
			    pstmt.setString(2, gv.getPw());
			    pstmt.setString(3, gv.getContent());
			   
			    
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
	
	
	

