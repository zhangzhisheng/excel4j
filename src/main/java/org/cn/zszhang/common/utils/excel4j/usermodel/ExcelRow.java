package org.cn.zszhang.common.utils.excel4j.usermodel;

import java.util.Iterator;

/**
 * 工作表Sheet中的行对象接口
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 15:25:22
 */
public interface ExcelRow extends Iterable<ExcelCell>  {
	/**
	 * Use this to create new cells within the row and return it.The type can be
	 * changed either through calling setCellValue or setCellType.
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * 
	 * @param column    column
	 */
	public ExcelCell createCell(int column);
	/**
	 * Use this to create new cells within the row and return it.The type can be
	 * changed either through calling setCellValue or setCellType.
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * 
	 * @param colLabel    colLabel
	 */
	public ExcelCell createCell(String colLabel);

	/**
	 * Use this to create new cells within the row and return it.The cell that is
	 * returned will be of the requested type.The type can be changed either through
	 * calling setCellValue or setCellType.
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * 
	 * @param colLabel
	 * @param type    type
	 */
	public ExcelCell createCell(String colLabel, int type);
	/**
	 * Use this to create new cells within the row and return it.The cell that is
	 * returned will be of the requested type.The type can be changed either through
	 * calling setCellValue or setCellType.
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * 
	 * @param column
	 * @param type    type
	 */
	public ExcelCell createCell(int column, int type);

	/**
	 * Get the cell representing a given column (logical
	 * cell) 0-<font
	 * color="#3f5fbf">based.  If you ask for a
	 * cell that is not defined....return null.
	 * 
	 * @param cellnum
	 */
	public ExcelCell getCell(int cellnum);

	/**
	 * Get the cell representing a given column label If you ask for a cell that is
	 * not defined....return null.
	 * @param cellLabel -- Excel列标，如a,b,aa,ab等
	 * 
	 * @param colLabel
	 */
	public ExcelCell getCell(String colLabel);

	/**
	 * Get the number of the first cell contained in this row.
	 * 
	 */
	public int getFirstCellNum();

	/**
	 * Gets the index of the last cell contained in this row
	 * PLUS
	 * ONE.
	 * The result also happens to be the
	 * 1-based
	 * column number of the last cell.  This value can be used as a standard upper
	 * bound when iterating over cells.
	 */
	public int getLastCellNum();

	/**
	 * Get row number this row represents
	 */
	public int getRowNum();

	/**
	 * Returns the Sheet this row belongs to
	 */
	public ExcelSheet getSheet();

	/**
	 * Remove the Cell from this row.
	 * 
	 * @param cell
	 */
	public void removeCell(ExcelCell cell);

	/**
     * @return Cell iterator of the physically defined cells.  Note element 4 may
     * actually be row cell depending on how many are defined!
     */
    Iterator<ExcelCell> cellIterator();

}