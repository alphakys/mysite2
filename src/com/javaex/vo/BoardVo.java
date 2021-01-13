package com.javaex.vo;

public class BoardVo {
	
	//필드
	private int no;
	private String name;
	private String title;
	private String content;
	private String date;
	
	
	
	//생성자
	public BoardVo(int no, String name, String title, String content, String date) {
		super();
		this.no = no;
		this.name = name;
		this.title = title;
		this.content = content;
		this.date = date;
	}



	public BoardVo(String name, String title, String content) {
		super();
		this.name = name;
		this.title = title;
		this.content = content;
	}

	//Getter/Setter

	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}


	
	//메서드

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", name=" + name + ", title=" + title + ", content=" + content + ", date=" + date
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
