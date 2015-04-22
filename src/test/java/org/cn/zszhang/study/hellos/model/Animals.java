package org.cn.zszhang.study.hellos.model;

public class Animals {
	private int id;
	private String petName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	
	public Animals(int id, String petName) {
		this.id = id;
		this.petName = petName;
	}
	@Override
	public String toString() {
		return "Animals [id=" + id + ", petName=" + petName + "]";
	}
	
}
