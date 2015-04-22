package org.cn.zszhang.study.hellos.svc;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GetDataFromConfig {
	private Map<String, Object> data;
	public GetDataFromConfig() {
		
	}
	public void printAllData() {
		if( null == data ) return;
		
		for(Entry<String, Object> entry : data.entrySet() ) {
			System.out.println("--------------got Data for " + entry.getKey());
			List<Object> list = (List<Object>) entry.getValue();
			for(Object e : list) {
				System.out.println(e);
			}
		}
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
