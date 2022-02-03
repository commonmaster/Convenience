package com.stone.springmvc.product.common;

import java.util.List;

public class 매니저제품페이지구성정보 {

	private int totalRecordCount; // DB에 저장된 게시글의 총 갯수
	private int currentPage;
	private List<제품> contents;
	private int totalPageCount; // 총 페이지의 갯수 = 출력할 수 있는 마지막 페이지
	private int startPage; 
	private int endPage;
	private final int SHOW_PAGE_COUNT = 5; // 보여줄 페이지 갯수
	private final int SHOW_RECORD_COUNT = 5; //보여줄 레코드수(3의 배수)(최대 9)
	
	public 매니저제품페이지구성정보() {	}
	
	public 매니저제품페이지구성정보(int totalRecordCount, int currentPage, List<제품> contents) {
		
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

	public List<제품> getContents() {
		return contents;
	}

	public void setContents(List<제품> contents) {
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
