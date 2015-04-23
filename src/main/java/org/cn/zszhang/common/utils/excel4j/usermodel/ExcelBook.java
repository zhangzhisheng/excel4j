package org.cn.zszhang.common.utils.excel4j.usermodel;

import java.io.OutputStream;
import java.util.Iterator;


/**
 * 工作表
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 15:25:07
 */
public interface ExcelBook extends Iterable<ExcelSheet> {

	/**
	 * Indicates the book window is hidden, but can be shown by the user via the user
	 * interface.
	 */
	public static int SHEET_STATE_HIDDEN = 1;
	/**
	 * Indicates the sheet is hidden and cannot be shown in the user interface (UI).In
	 * Excel this state is only available programmatically in VBA:
	 * ThisWorkbook.Sheets("MySheetName").Visible = xlSheetVeryHidden
	 */
	public static int SHEET_STATE_VERY_HIDDEN = 2;
	/**
	 * Indicates the sheet is visible.
	 */
	public static int SHEET_STATE_VISIBLE = 0;

	/**
	 * Create an Sheet from an existing sheet in the Workbook.
	 * 
	 * @param sheetNum    sheetNum
	 */
	public ExcelSheet cloneSheet(int sheetNum);

	/**
	 * Use this to create new sheets.Note that Excel allows sheet names up to 31 chars
	 * in length but other applications (such as OpenOffice) allow more. Some versions
	 * of Excel crash with names longer than 31 chars,others - truncate such names to
	 * 31 character.
	 * 
	 * @param sheetname    sheetname
	 */
	public ExcelSheet createSheet(String sheetname);

	/**
	 * Use this to create new sheets.
	 */
	public ExcelSheet createSheet();

	/**
	 * 获取当前活动的sheet页编号(0-based)
	 */
	public int getActiveSheetIndex();

	/**
	 * Get the number of spreadsheets in the workbook
	 */
	public int getNumberOfSheets();

	/**
	 * Get sheet with the given name
	 * 
	 * @param name    name
	 */
	public ExcelSheet getSheet(String name);

	/**
	 * Get the Sheet object at the given index.
	 * 
	 * @param index    index
	 */
	public ExcelSheet getSheetAt(int index);

	/**
	 * Returns the index of the given sheet
	 * @retrun -1 if not found
	 * 
	 * @param sheet    sheet
	 */
	public int getSheetIndex(ExcelSheet sheet);

	/**
	 * Returns the index of the sheet by his name
	 * @retrun -1 if not found
	 * 
	 * @param name    name
	 */
	public int getSheetIndex(String name);

	/**
	 * Get the sheet name
	 * 
	 * @param sheet    sheet
	 */
	public String getSheetName(int sheet);

	/**
	 * Check whether a sheet is hidden.Note that a sheet could instead be set to be
	 * very hidden, which is different
	 * 
	 * @param sheetIx    sheetIx
	 */
	public boolean isSheetHidden(int sheetIx);

	/**
	 * Check whether a sheet is very hidden.This is different from the normal hidden
	 * status.
	 * 
	 * @param sheetIx    sheetIx
	 */
	public boolean isSheetVeryHidden(int sheetIx);

	/**
	 * Removes sheet at the given index.
	 * 
	 * @param index    index
	 */
	public void removeSheetAt(int index);

	/**
	 * 设置活动Sheet页
	 * 
	 * @param sheetIndex    sheetIndex
	 */
	public void setActiveSheet(int sheetIndex);

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetIx
	 * @param hidden    hidden
	 */
	public void setSheetHidden(int sheetIx, boolean hidden);

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetIx
	 * @param hidden    0 - visible. 1 - hidden. 2 - very hidden.
	 */
	public void setSheetHidden(int sheetIx, int hidden);

	/**
	 * Set the sheet name.
	 * if the name is null or invalid, do nothing.
	 * 
	 * @param sheet
	 * @param name    name
	 */
	public void setSheetName(int sheet, String name);

	/**
	 * Write out this workbook to a file.
	 * @return  -- 失败返回false
	 */
	public boolean write(String filename) ;

	/**
	 * 写入到文件流
	 * @return  -- 失败返回false
	 */
	public boolean write(OutputStream os) ;

    /**
     *  Returns an iterator of the sheets. 
     *
     * @return an iterator of the sheets.  
     */
    Iterator<ExcelSheet> sheetIterator();

}