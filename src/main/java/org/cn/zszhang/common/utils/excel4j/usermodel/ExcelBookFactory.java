package org.cn.zszhang.common.utils.excel4j.usermodel;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cn.zszhang.common.utils.excel4j.config.Excel4JavaConfiguration;
import org.cn.zszhang.common.utils.excel4j.poiAdapter.ExcelBookPoiImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zszhang
 * @version 1.0
 * @created 13-����-2015 17:59:25
 */
public class ExcelBookFactory {
	private final static Logger logger = LoggerFactory.getLogger(ExcelBookFactory.class);

	/**
	 * 通过已有的Excel文件创建工作薄对象，此方法长用于读取excel文件时。
	 * 
	 * @param file    file
	 */
	public static ExcelBook openBook(File file) {
		if( null == file ) {
			logger.warn("Open NULL file Exception!");
			return null;
		}
		
		if( !file.exists() ) {
			return null;
		}
		
		try {
			Workbook wb = WorkbookFactory.create(file);
			return new ExcelBookPoiImpl(wb);
		} catch (IOException ioe) {
			logger.warn("打开Excel文件'" + file.getName() + "'时发生IOException:"	+ ioe.getMessage());
			return null;
		} catch (InvalidFormatException ife) {
			logger.warn("打开Excel文件'" + file.getName() + "'时发生InvalidFormatException:"	+ ife.getMessage());
			return null;
		}
	}

	/**
	 * 内存中创建工作薄对象，此方法长用于写入excel文件前。
	 * 
	 * @param type    -- 2003表示excel2003格式；2007表示2007及以上格式; 其它取值按缺省情况。
	 */
	public static ExcelBook create(int type) {
		Workbook wb = null; 
		switch (type) {
		case 2003: 
			wb = new HSSFWorkbook();
			break;
		case 2007:
			wb = new XSSFWorkbook();
			break;
		default:
			return create(Excel4JavaConfiguration.getDefaultExcelFromat());
		}
		
		return new ExcelBookPoiImpl(wb);
	}

}