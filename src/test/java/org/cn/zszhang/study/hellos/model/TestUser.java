package org.cn.zszhang.study.hellos.model;

public class TestUser extends BaseUser {

	private boolean sex;
	public TestUser(int id, String name, boolean sex) {
		super.id = id; super.name = name; this.sex = sex;
	}
	public TestUser() {
		
	}
	public String toString() {
		return "[id="+id+",name="+name+",sex="+sex+"]";
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}

}
