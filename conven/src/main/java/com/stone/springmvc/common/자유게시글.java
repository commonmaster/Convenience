package com.stone.springmvc.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class 자유게시글 {

	private long no;
	private String title;
	private String authorId;
	private String contents;
	private String regDate;
	private String modifyDate;
	private int readCount;	
		
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	public String printDate() {
		
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String s = f.format(now);
		
		String ymd = this.regDate.substring(0, 10);
		String time = this.regDate.substring(11,16);
		
		if(s.equals(ymd)) {
			return time;
		}
		return ymd;
	}
	
	public String printContentText() {
		
		return contents.replaceAll("\n", "<br>");
	}
	
}
