package org.cn.zszhang.common.utils.excel4j.normal.reader;

import java.util.List;

import org.cn.zszhang.common.utils.excel4j.normal.reader.SheetData;
import org.cn.zszhang.common.utils.excel4j.normal.reader.SimpleReader;
import org.cn.zszhang.common.utils.excel4j.normal.reader.SimpleReaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class SimpleReaderTest {
	private SimpleReader sr = new SimpleReaderImpl();
	private final Logger logger = LoggerFactory.getLogger(getClass());

  @Test
  public void readBook() {
	  List<SheetData> lsd = sr.read(this.getClass().getResource("/user.xlsx").getPath());
	  logger.info(lsd.toString());
  }

  @Test
  public void readOneSheet() {
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void readManySheet() {
    //throw new RuntimeException("Test not implemented");
  }

  @Test
  public void readListSheet() {
   // throw new RuntimeException("Test not implemented");
  }
}
