package org.cn.zszhang.common.utils.excel4j.binding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBookFactory;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author zszhang
 * @version 1.0
 * @created 15-四月-2015 0:32:36
 */
public class DefaultExcel4Java implements Excel4Java {
	private final static Logger logger = LoggerFactory.getLogger(DefaultExcel4Java.class);
	
	private ExcelBook book;
	private boolean isOpen = false;

	public DefaultExcel4Java(){
		book = null;
	}

	/**
	 * 设置数据有效性序列，本方法支持的为输入序列，采用公式引用方式的，请采用下一个setDataValidation方法。
	 * 
	 * @param sequence    -- 字符串序列
	 * @param sheetName
	 * @param firstRow    -- 开始行
	 * @param lastRow    -- 结束行
	 * @param firstCol    -- 开始列
	 * @param lastCol    --  结束列
	 */
	public void addDataValidation(String[] sequence, String sheetName, int firstRow, int lastRow, int firstCol, int lastCol){
		ExcelSheet sh = book.getSheet(sheetName);
		sh.addDataValidation(sequence, firstRow, lastRow, firstCol, lastCol);
	}

	/**
	 * 设置数据有效性序列，本方法采用公式引用方式，直接输入值方式的请使用上一个setDataValidation方法。
	 * 
	 * @param formula    -- 引用数据源的公式
	 * @param sheetName
	 * @param firstRow    -- 开始行
	 * @param lastRow    -- 结束行
	 * @param firstCol    -- 开始列
	 * @param lastCol    --  结束列
	 */
	public void addDataValidation(String formula, String sheetName, int firstRow, int lastRow, int firstCol, int lastCol){
		ExcelSheet sh = book.getSheet(sheetName);
		sh.addDataValidation(formula, firstRow, lastRow, firstCol, lastCol);
	}

	/**
	 * 设置数据有效性序列，本方法采用公式引用方式，直接输入值方式的请使用上一个setDataValidation方法。
	 * 
	 * @param formula    -- 引用数据源的公式
	 * @param sheetNo
	 * @param firstRow    -- 开始行
	 * @param lastRow    -- 结束行
	 * @param firstCol    -- 开始列
	 * @param lastCol    --  结束列
	 */
	public void addDataValidation(String formula, int sheetNo, int firstRow, int lastRow, int firstCol, int lastCol){
		ExcelSheet sh = book.getSheetAt(sheetNo);
		sh.addDataValidation(formula, firstRow, lastRow, firstCol, lastCol);
	}

	/**
	 * 设置数据有效性序列，本方法支持的为输入序列，采用公式引用方式的，请采用下一个setDataValidation方法。
	 * 
	 * @param sequence    -- 字符串序列
	 * @param sheetNo
	 * @param firstRow    -- 开始行
	 * @param lastRow    -- 结束行
	 * @param firstCol    -- 开始列
	 * @param lastCol    --  结束列
	 */
	public void addDataValidation(String[] sequence, int sheetNo, int firstRow, int lastRow, int firstCol, int lastCol){
		ExcelSheet sh = book.getSheetAt(sheetNo);
		sh.addDataValidation(sequence, firstRow, lastRow, firstCol, lastCol);
	}

	/**
	 * 写入到文件
	 * 
	 * @param filename
	 */
	public void write2File(String filename){
		if( !isOpen ) {
			logger.warn("在打开Excel文件前，无法写入！");
			return;
		}
		book.write(filename);
		isOpen = false;
	}

	/**
	 * 打开一个excel工作薄对象，多次调用将关闭上次打开的工作薄对象。
	 * 
	 * @param type 2003-excel2003格式； other - excel2007以上格式
	 */
	public void createExcelBook(int type) {
		if( isOpen ) {
			logger.warn("前面打开的文件还没有执行写入操作，不能打开下一个文件！！");
			return;
		}
		book = ExcelBookFactory.create(type);
		isOpen = true;
	}

	/**
	 * 将beans写入到指定的sheet页的指定位置
	 * 
	 * @param beans
	 * @param sheetName
	 * @param firstRow
	 * @param rowMapper -- 行映射信息，必须实现其中的bean2Row方法
	 */
	public <T> void toSheet(List<T> beans, String sheetName, int firstRow, Bean2ExcelRowMapper<T> rowMapper) {
		if( null == book ) return;
		ExcelSheet sh = book.getSheet(sheetName);
		if( sh == null ) return;
		toSheet(beans, sh, firstRow, rowMapper);
	}

	private <T> void toSheet( List<T> beans, ExcelSheet sh, int firstRow, Bean2ExcelRowMapper<T> rowMapper) {
		int rownum = firstRow;
		for(T bean : beans) {
			ExcelRow row = sh.createRow(rownum++);
			rowMapper.bean2Row(bean, row);
		}
	}
	/**
	 * 将beans写入到指定的sheet页的指定位置
	 * 
	 * @param beans
	 * @param sheetNo
	 * @param firstRow
	 * @param rowMapper -- 行映射信息， 必须实现其中的row2Bean方法
	 */
	public <T> void toSheet(List<T> beans, int sheetNo, int firstRow, Bean2ExcelRowMapper<T> rowMapper) {
		if( null == book ) return;
		ExcelSheet sh = book.getSheetAt(sheetNo);
		if( sh == null ) return;
		toSheet(beans, sh, firstRow, rowMapper);
	}
	/**
	 * 读取一个excel文件, 多次调用将丢弃上次读取的文件。
	 * 
	 * @param file
	 */
	public void createExcelBook(File file)  {
		if( isOpen ) {
			logger.warn("前面打开的文件还没有执行写入操作，不能打开下一个文件！！");
			return;
		}
		book = ExcelBookFactory.openBook(file);
		if( null == book ) createExcelBook(2007);
		isOpen = true;
	}

	/**
	 * 读取指定页面中指定范围的数据
	 * 
	 * @return  -- 如果指定的sheet页不存在，返回NULL
	 * @param sheetNo
	 * @param firstRow
	 * @param lastRow
	 * @param rowMapper -- 行映射信息，必须实现row2Bean方法
	 * @param clazz	要转换到的bean的类型
	 */
	public <T> List<T> toBeans(int sheetNo, int firstRow, int lastRow, Excel2BeanRowMapper<T> rowMapper, Class<T> clazz) {
		if ( null == book ) {
			logger.warn("调用本方法前，要先调用read(file)方法将Excel文件内容读取进来！");
			return null;
		}
		ExcelSheet sh = book.getSheetAt(sheetNo);
		if( null == sh ) {
			logger.warn("指定的sheetNo不存在:"+ sheetNo);
			return null;
		}
		return toBeans(sh, firstRow, lastRow, rowMapper, clazz);
	}

	/**
	 * 读取指定页面中指定范围的数据
	 * 
	 * @return  -- 如果指定的sheet页不存在，返回NULL
	 * @param sheetName
	 * @param firstRow
	 * @param lastRow
	 * @param rowMapper -- 行映射信息，必须实现row2Bean方法
	 * @param clazz	要转换到的bean的类型
	 */
	public <T> List<T> toBeans(String sheetName, int firstRow, int lastRow, Excel2BeanRowMapper<T> rowMapper, Class<T> clazz) {
		if ( null == book ) {
			logger.warn("调用本方法前，要先调用read(file)方法将Excel文件内容读取进来！");
			return null;
		}
		ExcelSheet sh = book.getSheet(sheetName);
		if( null == sh ) {
			logger.warn("指定的sheetName不存在:"+ sheetName);
			return null;
		}
		return toBeans(sh, firstRow, lastRow, rowMapper, clazz);
		
	}

	private <T> List<T> toBeans(ExcelSheet sheet, int firstRow, int lastRow, Excel2BeanRowMapper<T> rowMapper, Class<T> clazz) {
		lastRow = lastRow <= sheet.getLastRowNum() ? lastRow : sheet.getLastRowNum();
		if( firstRow > lastRow ) return null;
		
		List<T> beans  = new ArrayList<T>(lastRow-firstRow+1);
		
		for(int i = firstRow; i <= lastRow ; i++) {
			ExcelRow row = sheet.getRow(i);
			if( null == row ) continue;
			T bean = rowMapper.row2Bean(row, clazz);
			if( null == bean ) continue;
			beans.add(bean);
		}
		
		return beans;
	}	
	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetIndex
	 * @param hidden    0 - visible. 1 - hidden. 2 - very hidden.
	 */
	public void setSheetHidden(int sheetIndex, int hidden) {
		book.setSheetHidden(sheetIndex, hidden);
	}

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetName
	 * @param hidden    0 - visible. 1 - hidden. 2 - very hidden.
	 */
	public void setSheetHidden(String sheetName, int hidden) {
		int sheetIx = book.getSheetIndex(sheetName);
		if( sheetIx < 0 ) 	return;
		book.setSheetHidden(sheetIx, hidden);
	}
}