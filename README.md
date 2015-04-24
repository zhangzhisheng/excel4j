# excel4j
本工具类旨在封装一种在java项目中简单、快速接入excel的方式。系统本身没有编写任何excel实质操作内容，当前这些内容都交给了poi来完成。
未来，基于本工具或者本工具自己会演进到一种可配置的快速接入方式，可能会开发一种领域语言来解决，还没有想好。欢迎有兴趣的兄弟参加，英文不好，大家还是中国话交流吧。
本文的例子在test目录下面，用起来还是比较简单的，以后会再增加几个例子，用以说明使用的方便性。
下面是一个例子，几行代码做的事情，从此java操作excel很简单了吧：

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