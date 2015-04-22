package org.cn.zszhang.study.hellos.svc;

import org.springframework.stereotype.Component;

@Component
public class TestTemplate<T> {
	private String comm_content;
	
	public void print(T o) {
		System.out.println("----------------------------");
		System.out.println("comm:" + comm_content);
		System.out.println("other:" + o);
		System.out.println("----------------------------");
	}

	public String getComm_content() {
		return comm_content;
	}

	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
}
