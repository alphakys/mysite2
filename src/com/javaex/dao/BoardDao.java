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
			    	   query += "values(seq_board_no.nextval, ? , ? , 0 , sysdate, ? ) ";
					 
		
			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setString(1, bv.getTitle());
			    pstmt.setString(2, bv.getContent());
			    pstmt.setInt(3, bv.getUserNo());
			    
			    // 4. 결과처리
			   count = pstmt.executeUpdate();
			   
			   conn.commit();
		
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
	
		
		
		//페이징을 위한 리스트 정보 가져오기
		//모든 리스트 가져와서 뿌리면 리스트 수가 많아지면 시간이 오래 걸릴것 같아 DB에서 필요한 정보 수만큼 뿌리는 방향으로 바꾼 쿼리
				public List<BoardVo> getList(int page){
					
					getConnection();
					
					bList = new ArrayList<>();
					
					try {
					    // 3. SQL문 준비 / 바인딩 / 실행
					    String query = "select   b.no, ";			   
					    	   query += "        title, ";
					    	   query += "        u.name, ";		    
					    	   query += "        hit, ";
					    	   query += "        to_char(reg_date, 'yyyy-mm-dd hh24:mi'), ";
					    	   query += "        user_no ";
					    	   query += "from    board b left outer join users u ";
					    	   query += "on   	 b.user_no = u.no ";
					    	   query += "		 inner join (select rownum r, ";
					    	   query += "		 						   no ";
					    	   query += "		 			 from	(select no,	";
					    	   query += "		 						    rownum ";
					    	   query += "		 				     from   board ";
					    	   query += "		 						    order by no desc) ";
					    	   query += "		 			 ) rn ";
					    	   query += "		 			 on rn.no = b.no ";
					    	   query += "		 			 where rn.r >= ? and rn.r <= ? ";

					    	   
					    pstmt = conn.prepareStatement(query);
					    
					    pstmt.setInt(1, ((page-1)*10) +1);
					    pstmt.setInt(2, page*10);
					    
					    rs = pstmt.executeQuery();
					    
					    while(rs.next()) {
					    	
					    	int no = rs.getInt(1);		
					    	String title = rs.getString(2);
					    	String name = rs.getString(3);
					    	int hit = rs.getInt(4);
					    	String date = rs.getString(5);
					    	int userNo = rs.getInt(6);
					    	
					    	bList.add(new BoardVo(no, title, name, hit, date, userNo));
					    }
					   
					    
					    
					}  catch (SQLException e) {
					    System.out.println("error:" + e);
					} 
					
					
					close();
					
					return bList;
				}
		
		
				
				
				
				
				
		//모든 리스트 정보 가져오기
		public List<BoardVo> getList(){
			
			getConnection();
			
			bList = new ArrayList<>();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "select   b.no, ";			   
			    	   query += "        title, ";
			    	   query += "        u.name, ";		    
			    	   query += "        hit, ";
			    	   query += "        to_char(reg_date, 'yyyy-mm-dd hh24:mi'), ";
			    	   query += "        user_no ";
			    	   query += "from    board b left outer join users u ";
			    	   query += "on   	 b.user_no = u.no ";
			    	   query += "order by  no desc ";
			    	   
			    	   
			    pstmt = conn.prepareStatement(query);	   
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	int no = rs.getInt(1);		
			    	String title = rs.getString(2);
			    	String name = rs.getString(3);
			    	int hit = rs.getInt(4);
			    	String date = rs.getString(5);
			    	int userNo = rs.getInt(6);
			    	
			    	bList.add(new BoardVo(no, title, name, hit, date, userNo));
			    }
			   
			    
			    
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			
			close();
			
			return bList;
		}
	
		
		//이름으로 검색한 리스트 가져오기
		public List<BoardVo> getList(String keyName){
				
				getConnection();
				
				bList = new ArrayList<>();
				
				try {
				    // 3. SQL문 준비 / 바인딩 / 실행
				    String query = "select   b.no, ";			   
				    	   query += "        title, ";
				    	   query += "        u.name, ";		    
				    	   query += "        hit, ";
				    	   query += "        to_char(reg_date, 'yyyy-mm-dd hh24:mm'), ";
				    	   query += "        user_no ";
				    	   query += "from    board b left outer join users u ";
				    	   query += "on   	 b.user_no = u.no ";
				    	   query += "where   u.name = ? ";
				    	   query += "order by  no desc ";
				    	   
				    	   
				    pstmt = conn.prepareStatement(query);
				    //1.18 12:21분 수정!! 검색기능 테스트중 오류 발견해서 이 부분 수정하고 커밋했습니다
				    //**************************
				    pstmt.setString(1, keyName);
				    //**************************
				    rs = pstmt.executeQuery();
				    
				    while(rs.next()) {
				    	
				    	int no = rs.getInt(1);		
				    	String title = rs.getString(2);
				    	String name = rs.getString(3);
				    	int hit = rs.getInt(4);
				    	String date = rs.getString(5);
				    	int userNo = rs.getInt(6);
				    	
				    	bList.add(new BoardVo(no, title, name, hit, date, userNo));
				    }
				   
				    
				    
				}  catch (SQLException e) {
				    System.out.println("error:" + e);
				} 
				
				
				close();
				
				return bList;
			}
		
		
		//페이징을 위한 총 게시글 개수 가져오는 메소드
		public int count() {
			
			getConnection();
			
			int totalPost=0;
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "select   count(no) ";
			    	   query += "from 	 board ";
		
			    pstmt = conn.prepareStatement(query);	   
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	totalPost = rs.getInt(1);
			    }
			      
			   
			   conn.commit();
		
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return totalPost;
		}
		
		
		
		//검색한 리스트의 페이징을 위한 게시글 수
		public int seachCount(String keyword) {
			
			getConnection();
			
			int totalPost=0;
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "select   count(b.no) ";
			    	   query += "from 	 board b, users u ";
			    	   query += "where   b.user_no = u.no and u.name = ? ";

		
			    pstmt = conn.prepareStatement(query);
			    pstmt.setString(1,keyword);
			    
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	totalPost = rs.getInt(1);
			    }
			      
			   
			   conn.commit();
		
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return totalPost;
		}
		
		
		
		
		//선택된 게시글을 뿌려줄 데이터 가져오는 메소드
		public BoardVo getPost(int num){
			
			getConnection();
			
			BoardVo bv = null;
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "select   name, ";			   
			    	   query += "        hit, ";	    			    	 
			    	   query += "        to_char(reg_date, 'yyyy-mm-dd'), ";
			    	   query += "        title, ";
			    	   query += "        content, ";
			    	   query += "        user_no ";
			    	   query += "from    board b, users u ";
			    	   query += "where   b.user_no = u.no and b.no = ? ";
			    	 
			    	   
			    	   
			    pstmt = conn.prepareStatement(query);
			    pstmt.setInt(1, num);
			    
			    rs = pstmt.executeQuery();
			    
			    while(rs.next()) {
			    	
			    	String name = rs.getString(1);		
			    	int hit = rs.getInt(2);    	
			    	String date = rs.getString(3);
			    	String title = rs.getString(4); 
			    	String content = rs.getString(5);
			    	int userNo = rs.getInt(6);
			    	
			    	bv = new BoardVo(name, hit, date, title, content, userNo );
			    }
			   
			    conn.commit();
			    
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			
			close();
			
			return bv;
		}
		
		////////////////////////////////////////////////////////
		//오라클에서 바로 hit을 불러서 사용할 수 있다 !!!!!!!!!!메모!!!!!!!!!!!!
		
		//조회수 업데이트 1번 방식
				public int updateHit(int no){
					
					getConnection();
					
					try {
					    // 3. SQL문 준비 / 바인딩 / 실행
					    String query = "update board ";
					    	   query += "set hit = hit+1 ";
					    	   query += "where no = ? ";
				
					    pstmt = conn.prepareStatement(query);	   
					   
					    pstmt.setInt(1, no);
					    
					    
					    // 4. 결과처리
					   count = pstmt.executeUpdate();
					   
					   conn.commit();
				
					}  catch (SQLException e) {
					    System.out.println("error:" + e);
					} 
					
					close();
					
					return count;
				}
		
		
		
		/*
		//조회수 업데이트 2번 방식
		public int updateHit(int no, int hit){
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "update board ";
			    	   query += "set hit = ? ";
			    	   query += "where no = ? ";
		
			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setInt(1, hit+1);
			    pstmt.setInt(2, no);
			    
			    
			    // 4. 결과처리
			   count = pstmt.executeUpdate();
			   
			   conn.commit();
		
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
		*/
		
		//게시글 수정 메소드
		public int update(int no, String title, String content ){
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "update board ";
			    	   query += "set title = ? , ";
			    	   query += "	 content = ? ";
			    	   query += "where no = ? ";
		
			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setString(1, title);
			    pstmt.setString(2, content);
			    pstmt.setInt(3, no);
			    
			    
			    // 4. 결과처리
			   count = pstmt.executeUpdate();
			   
			   conn.commit();
		
			}  catch (SQLException e) {
			    System.out.println("error:" + e);
			} 
			
			close();
			
			return count;
		}
		
		
		//게시글 삭제 메소드
		public int delete(int no) {
			
			getConnection();
			
			try {
			    // 3. SQL문 준비 / 바인딩 / 실행
			    String query = "delete board ";
			    	   query += "where no = ? ";
		
			    pstmt = conn.prepareStatement(query);	   
			    pstmt.setInt(1, no);
			    
			    
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
