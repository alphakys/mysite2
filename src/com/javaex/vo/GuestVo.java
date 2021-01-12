package com.javaex.vo;

public class GuestVo {
	public int no;
	public String name, pw, content, date;
	
	
	//생성자
	public GuestVo() {}
	
	public GuestVo(int no, String name, String pw, String content, String date) {
		this.no = no;
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.date = date;
	}
	
	public GuestVo(int no, String name, String pw, String content) {
		this.no = no;
		this.name = name;
		this.pw = pw;
		this.content = content;
	
	}
	
	public GuestVo(String name, String pw, String content, String date) {
	
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.date = date;
	}

	public GuestVo(String name, String pw, String content) {
		
		this.name = name;
		this.pw = pw;
		this.content = content;
		
	}
	
	//g/s
	
	
	
	//메서드
	
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + pw + ", content=" + content
				+ ", date=" + date + "]";
	}
	
	
}


