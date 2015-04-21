package org.cn.zszhang.common.utils.excel4j.usermodel;

import java.util.Date;


/**
 * 工作表中的格cell接口
 * @author zszhang
 * @version 1.0
 * @updated 17-四月-2015 9:35:33
 */
public interface ExcelCell {

	/**
	 * 空值类型。
	 */
	public static int CELL_TYPE_BLANK = 3;
	
	public static int CELL_TYPE_BOOLEAN = 4;
	public static int CELL_TYPE_DATE = 2;
	public static int CELL_TYPE_NUMERIC = 0;
	public static int CELL_TYPE_STRING = 1;
	public static int CELL_TYPE_ERROR = 5;

	/**
	 * Get the value of the cell as a boolean.
	 */
	public boolean getBooleanCellValue();

	/**
	 * Return a formula for the cell, for example, SUM(C4:
	 * E4)
	 */
	public String getCellFormula();

	public int getCellType();

	/**
	 * Returns column index of this cell
	 */
	public int getColumnIndex();

	/**
	 * Get the value of the cell as a date.
	 */
	public Date getDateCellValue();

	/**
	 * Get the value of the cell as a number.
	 */
	public double getNumericCellValue();

	/**
	 * Returns the Row this cell belongs to
	 */
	public ExcelRow getRow();

	/**
	 * Returns row index of a row in the sheet that contains
	 * this cell
	 */
	public int getRowIndex();

	/**
	 * Returns the sheet this cell belongs to
	 */
	public ExcelSheet getSheet();

	/**
	 * Get the value of the cell as a string
	 */
	public String getStringCellValue();

	/**
	 * Sets formula for this cell.
	 * 
	 * @param formula
	 */
	public void setCellFormula(String formula);

	/**
	 * Set the cells type.If the cell currently contains a
	 * value, the value will be converted to match the new type, if possible.
	 * 
	 * 
	 * @param cellType
	 */
	public void setCellType(int cellType);

	/**
	 * Set a numeric value for the cell
	 * 
	 * @param value
	 */
	public void setCellValue(double value);

	/**
	 * Set a boolean value for the cell
	 * 
	 * @param value
	 */
	public void setCellValue(boolean value);

	/**
	 * Converts the supplied date to its equivalent Excel
	 * numeric value and sets that into the cell.
	 * 
	 * @param value
	 */
	public void setCellValue(Date value);

	/**
	 * Set a string value for the cell.
	 * 
	 * @param value
	 */
	public void setCellValue(String value);

	/**
	 * 不管什么类型都按照字符串形式返回值
	 * @return 返回字符串形式的值,BLANK返回"NULL"，ERROR返回"ERROR"
	 */
	public String getCellValueAsString() ;

	/**
	 * 通过object获取cell的值，只支持基本数据类型和其封装对象。
	 */
	public Object getCellValue();

	/**
	 * 通过object设置cell的值，只支持基本数据类型和其封装对象。
	 * 
	 * @param value    value
	 */
	public void setCellValue(Object value);
}