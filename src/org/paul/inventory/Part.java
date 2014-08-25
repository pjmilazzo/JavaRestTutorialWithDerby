package org.paul.inventory;

public class Part {
	
	private String primaryKey;
	private String title;
	private int code;
	private String maker;
	private int avail;
	private String desc;
	
	public Part() {
		// Empty Constructor
	}
	
	public Part(String primaryKey, String title, int code, String maker, int avail, String desc) {
		this.primaryKey = primaryKey;
		this.title = title;
		this.code = code;
		this.maker = maker;
		this.avail = avail;
		this.desc = desc;
		
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getAvail() {
		return avail;
	}
	public void setAvail(int avail) {
		this.avail = avail;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
