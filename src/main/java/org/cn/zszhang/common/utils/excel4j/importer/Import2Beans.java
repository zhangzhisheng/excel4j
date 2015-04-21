package org.cn.zszhang.common.utils.excel4j.importer;

import java.util.List;

import org.cn.zszhang.common.utils.excel4j.binding.Excel2BeanRowMapper;


/**
 * 读取excel文件转换到java bean的接口
 * @author zszhang
 * @version 1.0
 * @updated 18-四月-2015 21:15:57
 */
public interface Import2Beans {


	/**
	 * 关闭打开的Excel文件和释放内存中的ExcelBook对象
	 */
	public void closeExcelFile();

	/**
	 * 打开Excel文件，准备读取数据
	 * 
	 * @param fileName
	 */
	public void openExcelFile(String fileName);

	/**
	 * 读取内存中指定的sheet页内容，并将数据转为list返回。
	 * lastRow-firstRow=0,读取firstRow当前行，并返回。
	 * lastRow-firstRow<0,从firstRow逆向读取差值+1行。
	 * lastRow-firstRow>0,从firstRow正向读取差值+1行。
	 * 
	 * @param lastRow    读到此行结束。
	 * @param firstRow    从此行开始读起。
	 * @param sheetName    要打开的sheet页名称， null和空串返回null。
	 * @param clazz	要转换到的bean的类型
	 */
	public <T> List<T> read(int lastRow, int firstRow, String sheetName, Class<T> clazz);
	
	/**
	 * 读取内存中指定的sheet页内容，并将数据转为list返回。
	 * lastRow-firstRow=0,读取firstRow当前行，并返回。
	 * lastRow-firstRow<0,从firstRow逆向读取差值+1行。
	 * lastRow-firstRow>0,从firstRow正向读取差值+1行。
	 * 
	 * @param lastRow    读到此行结束。
	 * @param firstRow    从此行开始读起。
	 * @param sheetName    要打开的sheet页名称， null和空串返回null。
	 * @param clazz	要转换到的bean的类型
	 * @param mapper 读取时使用的mapper
	 */
	public <T> List<T> read(int lastRow, int firstRow, String sheetName, Class<T> clazz, Excel2BeanRowMapper<T> mapper);
}