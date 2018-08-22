package by.htp.entity;

public class PageInformation {
	
	private int COUNT_ITEMS_PER_PAGE = 5;
	/** general number of pages */
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

}
