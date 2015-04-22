package org.cn.zszhang.study.hellos.svc;

import java.util.ArrayList;
import java.util.List;

import org.cn.zszhang.study.hellos.model.TestUser;

public abstract class AbastractSvc {
	protected final List<TestUser> users = new ArrayList<TestUser>();
	public TestUser getById(int id) {
		for(TestUser user: users) {
			if( user.getId() == id)	return user;
		}
		return null;
	}

}
