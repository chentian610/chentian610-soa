package com.chentian610.common.vo;

import java.util.ArrayList;

public class resultListVO {
	/**
	 * 数据
	 */	
	private ArrayList<?> list;
	/**
	 * 总数
	 */
	private int total;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<?> getList() {
		return list;
	}
	public void setList(ArrayList<?> list) {
		this.list = list;
	}
}
