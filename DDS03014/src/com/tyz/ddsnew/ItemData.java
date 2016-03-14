package com.tyz.ddsnew;

public class ItemData {
	String   time;
	String   name;
	String   carNo;
	public ItemData(){}
	public ItemData(String time, String name, String carNo) {
		super();
		this.time = time;
		this.name = name;
		this.carNo = carNo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name="+name+",carNo="+carNo+",time="+time;
	}
}
