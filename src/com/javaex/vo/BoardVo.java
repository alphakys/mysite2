package com.javaex.vo;

public class BoardVo {
	
	//<<필드>>
	
	private int no;
	private String title;
	private String content;
	private int hit;
	private String date;
	private int userNo;
	// users에서 가져온 이름(게시판 리스트에 보여줄 userName)
	private String name;
	
	
	
	//<<생성자>>
	
	
	
	//for 리스트
	public BoardVo(int no, String title, String name, int hit, String date, int userNo) {
		super();
		this.no = no;
		this.title = title;
		this.name = name;
		this.hit = hit;
		this.date = date;
		this.userNo = userNo;
	}
	
	

	
	//for insert
	public BoardVo(int userNo, String title, String content) {
			super();
			this.title = title;
			this.content = content;
			this.userNo = userNo;
		}
	
	
	//for getPost
	public BoardVo(String name, int hit, String date, String title, String content, int userNo) {
		super();
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.date = date;
		this.userNo = userNo;
		this.name = name;
	}
	
	
	
	//Getter/Setter
	


	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	

	
	
	
	
	
	
}
