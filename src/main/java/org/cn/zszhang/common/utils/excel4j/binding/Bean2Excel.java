package org.cn.zszhang.common.utils.excel4j.binding;

import java.io.File;
import java.util.List;


/**
 * 将Java Bean序列化到excel文件的接口
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 15:26:34
 */
public interface Bean2Excel {

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
	public void addDataValidation(String[] sequence, int sheetNo, int firstRow, int lastRow, int firstCol, int lastCol);

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
	public void addDataValidation(String formula, int sheetNo, int firstRow, int lastRow, int firstCol, int lastCol);

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
	public void addDataValidation(String formula, String sheetName, int firstRow, int lastRow, int firstCol, int lastCol);

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
	public void addDataValidation(String[] sequence, String sheetName, int firstRow, int lastRow, int firstCol, int lastCol);

	/**
	 * 写入到文件
	 * 
	 * @param filename    filename
	 */
	public void write2File(String filename);

	/**
	 * 打开一个excel工作薄对象，多次调用createExcelBook同名方法将关闭上次打开的工作薄对象。
	 * 
	 * @param type    2003-excel2003格式； other - excel2007以上格式
	 */
	public void createExcelBook(int type);

	/**
	 * 打开一个excel工作薄对象，多次调用createExcelBook同名方法将关闭上次打开的工作薄对象。
	 * 
	 * @param file    file
	 */
	public void createExcelBook(File file);

	/**
	 * 将beans写入到指定的sheet页的指定位置
	 * 
	 * @param beans
	 * @param sheetNo
	 * @param firstRow
	 * @param rowMapper    -- 行映射信息， 必须实现其中的row2Bean方法
	 */
	public <T> void toSheet(List<T> beans, int sheetNo, int firstRow, Bean2ExcelRowMapper<T> rowMapper);

	/**
	 * 将beans写入到指定的sheet页的指定位置
	 * 
	 * @param beans
	 * @param sheetName
	 * @param firstRow
	 * @param rowMapper    -- 行映射信息，必须实现其中的bean2Row方法
	 */
	public <T> void toSheet(List<T> beans, String sheetName, int firstRow, Bean2ExcelRowMapper<T> rowMapper);

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetIndex
	 * @param hidden    0 - visible. 1 - hidden. 2 - very hidden.
	 */
	public void setSheetHidden(int sheetIndex, int hidden);

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetName
	 * @param hidden    0 - visible. 1 - hidden. 2 - very hidden.
	 */
	public void setSheetHidden(String sheetName, int hidden);

}