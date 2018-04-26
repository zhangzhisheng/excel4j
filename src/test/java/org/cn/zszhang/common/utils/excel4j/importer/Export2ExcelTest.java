package org.cn.zszhang.common.utils.excel4j.importer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cn.zszhang.common.utils.excel4j.importer.DefaultImpExpExcel;
import org.cn.zszhang.common.utils.excel4j.importer.Export2Excel;
import org.testng.annotations.Test;

public class Export2ExcelTest {

	@Test
	public void write2File() {
		List<Object> users = new ArrayList<Object>();
		users.add(new TestUser(1, "格格巫", true));
		users.add(new TestUser(2, "红精灵", false));
		users.add(new TestUser(3, "蓝精灵", true));
		
		Export2Excel exp = new DefaultImpExpExcel();
		exp.openExcelFile(this.getClass().getResource("/user.xlsx").getPath());
		
		String sheetName = "sheet01";
		exp.addSheetData(sheetName, 1, users);
		
		exp.write2File(this.getClass().getResource("/user2.xlsx").getPath());
	}
}
