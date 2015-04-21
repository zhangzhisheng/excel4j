package org.cn.zszhang.common.utils.excel4j.importer;

public class TestUser {

	private int id;
	private String name;
	private boolean sex;
	public TestUser(int id, String name, boolean sex) {
		this.id = id; this.name = name; this.sex = sex;
	}
	public TestUser() {
		
	}
	public String toString() {
		return "[id="+id+",name="+name+",sex="+sex+"]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}

}
