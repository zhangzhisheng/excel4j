package org.cn.zszhang.common.utils.excel4j.importer;

import java.util.List;

import org.cn.zszhang.common.utils.excel4j.binding.Bean2ExcelRowMapper;


/**
 * java bean导出到excel的接口
 * @author zszhang
 * @version 1.0
 * @updated 18-四月-2015 21:19:11
 */
public interface Export2Excel {

	/**
	 * 写入到文件
	 * 
	 * @param filename    filename
	 */
	public void write2File(String filename);

	/**
	 * 关闭打开的Excel文件和释放内存中的ExcelBook对象
	 */
	public void closeExcelFile();

	/**
	 * 增加一页sheet数据
	 * 
	 * @param sheetName
	 * @param firstRow
	 * @param data
	 */
	public <T> void addSheetData(String sheetName, int firstRow, List<T> data);

	/**
	 * 写入到sheet页中指定的类对象
	 * 
	 * @param beans    要写入的对象List
	 * @param sheetName    要写入的sheet页名称，如果为空，直接返回
	 * @param firstRow    从第几行开始写，从0行开始计算。
	 * @param mapper 将一个bean映射到一行excel的mapper类
	 */
	public <T> void addSheetData(List<T>beans, String sheetName, int firstRow, Bean2ExcelRowMapper<T> mapper);

	/**
	 * 打开Excel文件，准备读取数据
	 * 
	 * @param fileName
	 */
	public void openExcelFile(String fileName);

}