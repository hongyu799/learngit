package com.exp.model;

public class PageBean {

	private int pageNum = 1; // 当前是第几页
	private int maxPage = 0; // 一共有多少页
	private int maxRowCount = 0; // 一共有多少行
	private int rowsPage = 3; // 每页多少行

	private String tableName;// 表名

	private String sqlWhere;// 查询条件

	private int startNum;
	private int stopNum;

	public int getStartNum() {
		return (pageNum - 1) * rowsPage;
	}

	public int getStopNum() {
		return pageNum * rowsPage;
	}

	public int getPageNum() {
		if (pageNum <= 0)
			pageNum = 1;
		else if (maxPage != 0 && pageNum >= maxPage)
			pageNum = maxPage;
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getMaxRowCount() {
		return maxRowCount;
	}

	public void setMaxRowCount(int maxRowCount) {
		if (maxRowCount == 0) {
			maxPage = 1;
		} else {
			if (maxRowCount % rowsPage == 0)
				maxPage = maxRowCount / rowsPage;
			else
				maxPage = maxRowCount / rowsPage + 1;
		}

		this.maxRowCount = maxRowCount;
	}

	public int getRowsPage() {
		return rowsPage;
	}

	public void setRowsPage(int rowsPage) {
		this.rowsPage = rowsPage;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public static void main(String[] args) {
		System.out.println(10 % 3);
	}
}