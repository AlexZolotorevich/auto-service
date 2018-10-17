package by.htp.entity;

import java.io.Serializable;

public class PageInformation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int COUNT_ITEMS_PER_PAGE = 5;
	private int countOfPage;
	private int start;
	private int currentPage;
	
	
	public PageInformation(){
		
	}

	public int getCOUNT_ITEMS_PER_PAGE() {
		return COUNT_ITEMS_PER_PAGE;
	}

	public int getCountOfPage() {
		return countOfPage;
	}

	public void setCountOfPage(int countOfPage) {
		this.countOfPage = countOfPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int curretPage) {
		this.currentPage = curretPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "PageInformation [COUNT_ITEMS_PER_PAGE=" + COUNT_ITEMS_PER_PAGE + ", countOfPage=" + countOfPage
				+ ", start=" + start + ", currentPage=" + currentPage + "]";
	}

}
