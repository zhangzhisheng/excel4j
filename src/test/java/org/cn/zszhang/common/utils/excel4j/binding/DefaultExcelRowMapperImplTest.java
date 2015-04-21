package org.cn.zszhang.common.utils.excel4j.binding;

import java.util.Date;

import org.cn.zszhang.common.utils.excel4j.binding.DefaultExcel4JavaRowMapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DefaultExcelRowMapperImplTest {
	
	private User u;
	private DefaultExcel4JavaRowMapper<User> mapper = new DefaultExcel4JavaRowMapper<User>();

	@BeforeClass
	public void setUp() {
		u = new User(1, "zszhang", true);
	}
	
	@AfterClass
	public void tearDown() {
		u = null;
	}

  @Test
  public void bean2Row() {
	  //mapper.bean2Row(u, null);
  }

  @Test
  public void row2Bean() {
    //throw new RuntimeException("Test not implemented");
  }
  
	class User {
		int id;
		String name;
		boolean sex;
		short age;
		byte b;
		Date today;
		float f;
		double d;
		
		public User(int id, String name, boolean sex) {
			this.id = id; this.name = name; this.sex = sex;
		}
		public User() {
			
		}
		public String toString() {
			return "[id="+id+",name="+name+",sex="+sex+"]";
		}
	}
}
