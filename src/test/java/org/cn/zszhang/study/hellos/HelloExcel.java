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
		
		// impExp 在spring容器初始化时已经注入了一些数据，这些数据可以直接导出。 
		ImpExpExcel exp = (ImpExpExcel)ac.getBean("impExp");
		// 获取指定以配置的rowmapper，针对一种javabean进行转入转出
		Excel4JavaRowMapper<TestUser> mapper = (Excel4JavaRowMapper<TestUser>)ac.getBean("tumap");
		// 打开excel文件
		exp.openExcelFile("f:/test/user.xlsx");
		// 读取excel中的数据
		List<TestUser> users = exp.read(0, Integer.MAX_VALUE, "所有妖精", TestUser.class, mapper);
		// 写入excel中数据
		exp.addSheetData(users, "testconfig", 1, mapper);
		users = exp.read(-1, 1, "所有妖精", TestUser.class, mapper);
		System.out.println(users);
		// 写入excel文件
		exp.write2File("f:/test/user3.xlsx");
		// 关闭打开的excel文件
		exp.closeExcelFile();
	}

}
