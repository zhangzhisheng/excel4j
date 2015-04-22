package org.cn.zszhang.study.hellos.svc;

import java.util.ArrayList;
import java.util.List;

import org.cn.zszhang.study.hellos.model.TestUser;
import org.springframework.stereotype.Component;

@Component
public class TestUserSvc extends AbastractSvc {

	public TestUserSvc () {
		users.add(new TestUser(1, "格格巫", true));
		users.add(new TestUser(2, "红精灵", false));
		users.add(new TestUser(3, "蓝精灵", true));		
		users.add(new TestUser(4, "小红帽", true));		
		users.add(new TestUser(5, "大灰狼", true));		
	}
	public List<TestUser> getAll() {
		return users;
	}
	
	
	private TestUser getByName(String name) {
		for(TestUser user: users) {
			if( user.getName().equals(name))	return user;
		}
		return null;
	}
	public List<TestUser> getAllElf(String sql) {
		if( null == sql || sql.isEmpty() ) return null;
		
		List<TestUser> lu = new ArrayList<TestUser>();
		for(TestUser u : users) {
			if(u.getName().matches(".*" + sql + ".*")) lu.add(u);
		}
		
		return lu;
	}
}
