package org.cn.zszhang.common.utils.excel4j.usermodel;

import java.util.Iterator;

/**
 * 工作表对象接口
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 15:25:31
 */
public interface ExcelSheet extends Iterable<ExcelRow>  {

	/**
	 * Create a new row within the sheet and return the high level representation.
	 * 
	 * @param rownum    rownum
	 */
	public ExcelRow createRow(int rownum);

	/**
	 * Return the parent workbook
	 */
	public ExcelBook getBook();

	/**
	 * Gets the first row on the sheet.return the number of the first logical row on
	 * the sheet (0-
	 * based).
	 */
	public int getFirstRowNum();

	/**
	 * Gets the last row on the sheet.
	 */
	public int getLastRowNum();

	/**
	 * The left col in the visible view when the sheet is first viewed after opening
	 * it in a viewer
	 */
	public int getLeftCol();

	/**
	 * Returns the logical row (not physical) 0-based. If you ask for a row that is
	 * not defined you get a null.
	 * 
	 * @param rownum    rownum
	 */
	public ExcelRow getRow(int rownum);

	/**
	 * Returns the name of this sheet
	 */
	public String getSheetName();

	/**
	 * The top row in the visible view when the sheet is first viewed after opening it
	 * in a viewer
	 */
	public int getTopRow();

	/**
	 * Get the hidden state for a given column
	 * 
	 * @param columnIndex
	 */
	public boolean isColumnHidden(int columnIndex);

	/**
	 * Remove a row from this sheet.  All cells contained in
	 * the row are removed as well.
	 * 
	 * @param row
	 */
	public void removeRow(ExcelRow row);

	/**
	 * Set the visibility state for a given column
	 * 
	 * @param columnIndex
	 * @param hidden
	 */
	public void setColumnHidden(int columnIndex, boolean hidden);

    /**
     *  Returns an iterator of the physical rows
     *
     * @return an iterator of the PHYSICAL rows.  Meaning the 3rd element may not
     * be the third row if say for instance the second row is undefined.
     */
    Iterator<ExcelRow> rowIterator();

	/**
	 * 设置数据有效性序列，本方法支持的为输入序列，采用公式引用方式的，请采用下一个setDataValidation方法。
	 * 
	 * @param sequence    -- 字符串序列
	 * @param firstRow    -- 开始行
	 * @param lastRow    -- 结束行
	 * @param firstCol    -- 开始列
	 * @param lastCol    --  结束列
	 */
    public void addDataValidation(String[] sequence, int firstRow, int lastRow, int firstCol, int lastCol);
	/**
	 * 设置数据有效性序列，本方法采用公式引用方式，直接输入值方式的请使用上一个setDataValidation方法。
	 * 
	 * @param formula    -- 引用数据源的公式
	 * @param firstRow    -- 开始行
	 * @param lastRow    -- 结束行
	 * @param firstCol    -- 开始列
	 * @param lastCol    --  结束列
	 */
    public void addDataValidation(String formula, int firstRow, int lastRow, int firstCol, int lastCol);
}