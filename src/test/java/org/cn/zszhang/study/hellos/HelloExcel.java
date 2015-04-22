package org.cn.zszhang.study.hellos;

import java.util.List;

import org.cn.zszhang.common.utils.excel4j.binding.Excel4JavaRowMapper;
import org.cn.zszhang.common.utils.excel4j.importer.ImpExpExcel;
import org.cn.zszhang.study.hellos.model.TestUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloExcel {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("reject.xml");
		
		ImpExpExcel exp = (ImpExpExcel)ac.getBean("impExp");
		Excel4JavaRowMapper<TestUser> mapper = (Excel4JavaRowMapper<TestUser>)ac.getBean("tumap");
		
		exp.openExcelFile("f:/test/user.xlsx");

		List<TestUser> users = exp.read(0, Integer.MAX_VALUE, "所有妖精", TestUser.class, mapper);
		exp.addSheetData(users, "testconfig", 1, mapper);
		users = exp.read(-1, 1, "所有妖精", TestUser.class, mapper);
		System.out.println(users);

		System.out.println("==========其它妖精[0,1]=======");
		users = exp.read(0, 1, "其它妖精", TestUser.class);
		System.out.println(users);
		System.out.println("==========其它妖精[1,0]=======");
		users = exp.read(1, 0, "其它妖精", TestUser.class);
		System.out.println(users);
		System.out.println("==========其它妖精[-1,-2]=======");
		users = exp.read(-1, -2, "其它妖精", TestUser.class);
		System.out.println(users);
		
		exp.write2File("f:/test/user3.xlsx");
		
		exp.closeExcelFile();
	}

}
