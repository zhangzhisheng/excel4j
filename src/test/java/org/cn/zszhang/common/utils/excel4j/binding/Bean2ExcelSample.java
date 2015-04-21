package org.cn.zszhang.common.utils.excel4j.binding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cn.zszhang.common.utils.excel4j.binding.DefaultExcel4JavaRowMapper;
import org.cn.zszhang.common.utils.excel4j.binding.Excel4Java;
import org.cn.zszhang.common.utils.excel4j.binding.DefaultExcel4Java;
import org.cn.zszhang.common.utils.excel4j.binding.Excel4JavaRowMapper;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelCell;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bean2ExcelSample {
	private final static Logger  logger = LoggerFactory.getLogger(Bean2ExcelSample.class);

	class User {
		int id;
		String name;
		boolean sex;
		public User(int id, String name, boolean sex) {
			this.id = id; this.name = name; this.sex = sex;
		}
		public User() {
			
		}
		public String toString() {
			return "[id="+id+",name="+name+",sex="+sex+"]";
		}
	}

	public static void main(String[] args) {
		Excel4Java e4j = new DefaultExcel4Java();
		e4j.createExcelBook(new File("f:/test/user.xlsx"));
		//e4j.createExcelBook(2007);
		List<User> users = new ArrayList<User>();
		Bean2ExcelSample x = new Bean2ExcelSample();
		//UserRowMapper mapper = x.new UserRowMapper();
		//ExcelRowMapper<User> mapper = new DefaultExcelRowMapper<User>();
		Excel4JavaRowMapper<User> mapper =  new DefaultExcel4JavaRowMapper<User>();
		
		users.add(x.new User(1, "格格巫", true));
		users.add(x.new User(2, "红精灵", false));
		users.add(x.new User(3, "蓝精灵", true));
		
		String sheetName = "sheet01";
		e4j.toSheet(users, sheetName, 1, mapper);
		
		String[] sequence = {"男", "女"};
		e4j.addDataValidation(sequence, sheetName, 1, 200, 2, 2);

		sheetName = "sheet02";
		e4j.toSheet(users, sheetName, 1,mapper);
		String formula = "=sheet02!$B:$B";
		e4j.addDataValidation(formula, "sheet01", 1, 200, 1, 1);
		e4j.addDataValidation(formula, "sheet01", 1, 200, 4, 4);
		
		e4j.setSheetHidden(sheetName, ExcelBook.SHEET_STATE_HIDDEN);
		
		e4j.write2File("f:/test/user2.xlsx");
		
		List<User> readUsers = e4j.toBeans(sheetName, 1, 3, mapper, User.class);
		System.out.println(readUsers);
	}
	
	public boolean checkNotNull(Class clazz, Object o) {
		//clazz.get
		return true;
	}
	
	class UserRowMapper implements Excel4JavaRowMapper<User>  {

		public ExcelRow bean2Row(User bean, ExcelRow row) {
			row.createCell("a").setCellValue(bean.id);
			row.createCell("b").setCellValue(bean.name);
			if( bean.sex )
				row.createCell("c").setCellValue("男");
			else
				row.createCell("c").setCellValue("女");
			
			return row;
		}

		public User row2Bean(ExcelRow row, Class<User> clazz) {
			User u = new User();
			u.id = (int)row.getCell("a").getNumericCellValue();
			u.name = row.getCell("b").getStringCellValue();
			String sex = row.getCell("c").getStringCellValue();
			if( "男".equals(sex) ) u.sex = true;
			else if( "女".equals(sex) ) u.sex = false;
			
			return u;
		}
		
	}
}
