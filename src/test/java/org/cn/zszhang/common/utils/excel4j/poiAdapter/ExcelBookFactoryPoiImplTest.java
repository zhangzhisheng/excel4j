package org.cn.zszhang.common.utils.excel4j.poiAdapter;

import java.io.File;

import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBookFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExcelBookFactoryPoiImplTest {

  @Test
  public void create() {
	  File file = new File("f:/test/tt.xlsx");
	  ExcelBook eb = ExcelBookFactory.openBook(file);
	  Assert.assertNotNull(eb);
  }
}
