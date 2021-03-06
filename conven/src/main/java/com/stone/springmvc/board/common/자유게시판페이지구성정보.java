package com.stone.springmvc.board.common;

import java.util.List;

public class 자유게시판페이지구성정보 {

	private int totalRecordCount; // DB에 저장된 삭제 되지 않은 게시글의 총 갯수
	private int currentPage;
	private List<자유게시글> contents;
	private int totalPageCount; // 총 페이지의 갯수 = 출력할 수 있는 마지막 페이지
	private int startPage; // 현재 보이는 페이지상에서의 시작 페이지 ([예] 현재 페이지 3 -> 시작페이지 1, 현재페이지 66->시작:65)
	private int endPage; // 현재 보이는 페이지상에서의 끝 페이지  ([예] 현재 페이지 3 -> 끝페이지 5, 현재페이지 66->끝:70)
	private final int SHOW_PAGE_COUNT = 5; // 보여줄 페이지 갯수
	private final int SHOW_RECORD_COUNT = 5; // 최대 10
		
	public 자유게시판페이지구성정보() {	}
	
	public 자유게시판페이지구성정보(int totalRecordCount, int currentPage, List<자유게시글> contents) {
		
		this.totalRecordCount = totalRecordCount;
		this.currentPage = currentPage;
		this.contents = contents;
		
		if(this.totalRecordCount == 0) {
			this.totalPageCount = 0;
			startPage = 0;
			endPage = 0;
		}
		else {
			this.totalPageCount = this.totalRecordCount / SHOW_RECORD_COUNT;
			
			if(this.totalRecordCount % SHOW_RECORD_COUNT != 0) {
				this.totalPageCount++;
			}
			
			int modVal = this.currentPage % SHOW_PAGE_COUNT;
			startPage = this.currentPage / SHOW_PAGE_COUNT * SHOW_PAGE_COUNT + 1;
			if(modVal == 0) startPage -= SHOW_PAGE_COUNT;
			
			endPage = startPage + SHOW_PAGE_COUNT - 1;
			if(endPage > this.totalPageCount) endPage = this.totalPageCount;
		}
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<자유게시글> getContents() {
		return contents;
	}

	public void setContents(List<자유게시글> contents) {
		this.contents = contents;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}	

	public int getSHOW_PAGE_COUNT() {
		return SHOW_PAGE_COUNT;
	}
	
	public int getSHOW_RECORD_COUNT() {
		return SHOW_RECORD_COUNT;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public boolean hasNoRecords() {
		return totalRecordCount == 0;
	}
	
	public boolean hasRecords() {
		return totalRecordCount > 0;
	}
	
	
}
