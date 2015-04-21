package org.cn.zszhang.common.utils.excel4j.importer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cn.zszhang.common.utils.excel4j.binding.Bean2ExcelRowMapper;
import org.cn.zszhang.common.utils.excel4j.binding.DefaultExcel4JavaRowMapper;
import org.cn.zszhang.common.utils.excel4j.binding.Excel2BeanRowMapper;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBookFactory;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缺省的导入导出
 * @author zszhang
 * @version 1.0
 * @updated 18-四月-2015 0:03:45
 */
public class DefaultImpExpExcel implements ImpExpExcel {
	private final static Logger logger = LoggerFactory.getLogger(DefaultImpExpExcel.class);
	
	private ExcelBook book;
	private boolean isOpen = false;
	// 此处放入通过spring注入的数据
	private Map<String, Object> defaultData;
	
	public DefaultImpExpExcel(){
		
	}

	/**
	 * 写入到文件
	 * 
	 * @param filename    filename
	 */
	public void write2File(String filename){
		if( !isOpen ) {
			logger.warn("在打开Excel文件前，无法写入！");
			return;
		}
		writeDefaultData();
		book.write(filename);
	}
	/**
	 * 关闭打开的Excel文件和释放内存中的ExcelBook对象
	 */
	public void closeExcelFile() {
		book = null;
		isOpen = false;
	}
	/**
	 * 增加一页sheet数据。 使用缺省的mapper。
	 * 
	 * @param sheetName
	 * @param firstRow
	 * @param data
	 */
	public <T> void addSheetData(String sheetName, int firstRow, List<T> data) {
		addSheetData(data, sheetName, firstRow, new DefaultExcel4JavaRowMapper<T>());
	}

	/**
	 * 写入到sheet页中指定的类对象
	 * 
	 * @param beans    要写入的对象List
	 * @param sheetName    要写入的sheet页名称，如果为空，直接返回
	 * @param firstRow    从第几行开始写，从0行开始计算。
	 * @param mapper 将一个bean映射到一行excel的mapper类
	 */
	public <T> void addSheetData(List<T>beans, String sheetName, int firstRow, Bean2ExcelRowMapper<T> mapper) {
		if( null == book  || null == beans || null == sheetName || null == mapper ) return;
		ExcelSheet sh = book.getSheet(sheetName);
		if( null == sh ) {
			book.createSheet(sheetName);
			sh = book.getSheet(sheetName);
		}
		int rownum = firstRow;
		for(T o : beans) {
			ExcelRow row = sh.createRow(rownum++);
			mapper.bean2Row(o, row);
		}
	}
	
	/**
	 * 打开Excel文件，准备读取数据
	 * 
	 * @param fileName
	 */
	public void openExcelFile(String fileName) {
		if( isOpen ) {
			logger.warn("前面打开的文件还没有执行写入操作，不能打开下一个文件！！");
			return;
		}
		book = ExcelBookFactory.openBook(new File(fileName));
		if( null == book ) {
			logger.warn("打开文件失败：" + fileName);
			return;
		}
		isOpen = true;
		
	}

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
	public <T> List<T> read(int lastRow, int firstRow, String sheetName, Class<T> clazz, Excel2BeanRowMapper<T> mapper) {
		if( null == sheetName || null == mapper  || null == clazz || sheetName.isEmpty() ) return null;
		if ( null == book ) {
			logger.warn("调用本方法前，要先调用openExcelFile(fileName)方法将Excel文件内容读取进来！");
			return null;
		}
		ExcelSheet sh = book.getSheet(sheetName);
		if( null == sh ) {
			logger.warn("指定的sheetName不存在:"+ sheetName);
			return null;
		}
		// firstRow < 0 时，从最下面取倒数第几行。 0行仍表示最上面第一行。
		if( firstRow < 0 ) 
			firstRow = sh.getLastRowNum() + 1 + firstRow ;
		else if(firstRow >sh.getLastRowNum() ) // > 最大行数时，取最大行数。
			firstRow = sh.getLastRowNum() ;
		// firstRow < 0 时，从最下面取倒数第几行。 0行仍表示最上面第一行。
		if( lastRow < 0 ) 
			lastRow = sh.getLastRowNum() + 1 + lastRow ;
		else if(lastRow >sh.getLastRowNum() ) // > 最大行数时，取最大行数。
			lastRow = sh.getLastRowNum() ;
		
		if( firstRow > lastRow )
			return toBeans(sh, lastRow, firstRow, clazz, mapper);
		else
			return toBeans(sh, firstRow, lastRow, clazz, mapper);
	}
	
	/**
	 * 读取内存中指定的sheet页内容，并将数据转为list返回。
	 * lastRow-firstRow=0,读取firstRow当前行，并返回。
	 * lastRow-firstRow<0,从firstRow逆向读取差值+1行。
	 * lastRow-firstRow>0,从firstRow正向读取差值+1行。
	 * 
	 * @param lastRow    读到此行结束。
	 * @param firstRow    从此行开始读起
	 * @param sheetName    要打开的sheet页名称， null和空串返回null。
	 * @param clazz	要转换到的bean的类型
	 */
	public <T> List<T> read(int lastRow, int firstRow, String sheetName, Class<T> clazz) {
		Excel2BeanRowMapper<T> mapper = new DefaultExcel4JavaRowMapper<T>();
		return read(lastRow, firstRow, sheetName, clazz, mapper);
	}
	
	private <T> List<T> toBeans(ExcelSheet sheet, int firstRow, int lastRow, Class<T> clazz, Excel2BeanRowMapper<T> mapper ) {
		List<T> beans = new ArrayList<T>(lastRow-firstRow+1);
		ExcelRow row = null;
		T bean = null;
		for(int i = firstRow; i <= lastRow ; i++) {
			row = sheet.getRow(i);
			if( null == row ) continue;
			bean = mapper.row2Bean(row, clazz);
			if( null == bean ) continue;
			beans.add(bean);
		}
		
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	private <T> void writeDefaultData() {
		if( null == defaultData ) return;
		Bean2ExcelRowMapper<T> mapper = new DefaultExcel4JavaRowMapper<T>();
		for(Entry<String, Object> entry : defaultData.entrySet() ) {
			logger.debug("开始写入数据页:" +entry.getKey());
			addSheetData((List<T>)entry.getValue(),entry.getKey(), 0, mapper);
		}
	}
	public Map<String, Object> getDefaultData() {
		return defaultData;
	}

	public void setDefaultData(Map<String, Object> defaultData) {
		this.defaultData = defaultData;
	}

}