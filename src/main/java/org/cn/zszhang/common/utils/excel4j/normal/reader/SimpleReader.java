package org.cn.zszhang.common.utils.excel4j.normal.reader;

import java.util.List;


/**
 * 从excel读取数据的简单接口，适合小文件的处理
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 15:46:55
 */
public interface SimpleReader {

	/**
	 * 将文件中读出所有sheet的数据
	 * 
	 * @param filename
	 */
	public List<SheetData> read(String filename);

	/**
	 * 将文件中读出第sheetNo页的数据
	 * 
	 * @param filename
	 * @param sheetNo
	 */
	public SheetData read(String filename, int sheetNo);

	/**
	 * 从beginSheetNo页开始，共读count页
	 * 
	 * @param filename
	 * @param beginSheetNo
	 * @param count
	 */
	public List<SheetData> read(String filename, int beginSheetNo, int count);

	/**
	 * 将sheets数组中的所有sheetNo页读入进来
	 * 
	 * @param filename
	 * @param sheets
	 */
	public List<SheetData> read(String filename, int[] sheets);

}