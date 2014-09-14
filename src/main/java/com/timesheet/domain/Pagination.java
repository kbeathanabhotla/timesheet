package com.timesheet.domain;

public class Pagination {

	private int offset=1;
	private int count=50;
	
	public Pagination(int offset, int count) {
		this.offset = offset;
		this.count = count;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
