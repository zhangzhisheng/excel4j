package org.cn.zszhang.common.utils.excel4j.poiAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zszhang
 * @version 1.0
 * @created 13-四月-2015 17:34:38
 */
public class ExcelBookPoiImpl implements ExcelBook {
	private final static Logger logger = LoggerFactory.getLogger(ExcelBookPoiImpl.class);
	
	private final Workbook _book;
 
	/**
     * Sheets of this Book keyed by their sheet indexes.
     * The TreeMap ensures that the sheets are ordered by sheetIndex in the ascending order.
     */
	private final List<ExcelSheet> sheets = new ArrayList<ExcelSheet>();

	public ExcelBookPoiImpl(Workbook book){
		_book = book;
		if( null == book ) {
			logger.error("初始化ExcelBook失败，传入空的PoiWorkbook对象");
			return;
		}
		for(int i=0; i<_book.getNumberOfSheets(); i++) {
			Sheet sh = _book.getSheetAt(i);
			ExcelSheet es = new ExcelSheetPoiImpl(sh, this);
			sheets.add(es);
		}
	}

	/**
	 * Create an Sheet from an existing sheet in the Workbook.
	 * 
	 * 
	 * @param sheetNum
	 */
	public ExcelSheet cloneSheet(int sheetNum){
		Sheet cs = _book.cloneSheet(sheetNum);
		if( null == cs ) return null;
		
		ExcelSheet es = new ExcelSheetPoiImpl(cs,this);
		sheets.add(es);
		
		return es;
	}

	/**
	 * Use this to create new sheets.
	 */
	public ExcelSheet createSheet(){
		Sheet s = _book.createSheet();
		if(null == s) return null;
		
		ExcelSheet es = new ExcelSheetPoiImpl(s,this);
		sheets.add(es);
		
		return es;
	}

	/**
	 * Use this to create new sheets.<font
	 * color="#3f5fbf">Note that Excel allows sheet names up to 31 chars in length
	 * but other applications (such as OpenOffice) allow more. Some versions of Excel
	 * crash with names longer than 31 chars,others <font
	 * color="#7f7f9f">- truncate such names to
	 * 31 character.
	 * 
	 * @param sheetname
	 */
	public ExcelSheet createSheet(String sheetname){
		Sheet s = _book.createSheet(sheetname);
		if(null == s) return null;
		
		ExcelSheet es = new ExcelSheetPoiImpl(s,this);
		sheets.add(es);

		return es;
	}

	/**
	 * 获取当前活动的sheet页编号(0-based)
	 */
	public int getActiveSheetIndex(){
		return _book.getActiveSheetIndex();
	}

	/**
	 * Get the number of spreadsheets in the
	 * workbook
	 */
	public int getNumberOfSheets(){
		return _book.getNumberOfSheets();
	}

	/**
	 * Get sheet with the given name
	 * 
	 * @param name
	 */
	public ExcelSheet getSheet(String name){
		for(ExcelSheet sh : sheets) {
			if(sh.getSheetName().equals(name)) return sh;
		}
		return null;
	}

	/**
	 * Get the Sheet object at the given index.
	 * 
	 * @param index
	 */
	public ExcelSheet getSheetAt(int index){
		if( index < 0 || index >= sheets.size()) return null;
		return sheets.get(index);
	}

	/**
	 * Returns the index of the given sheet
	 * @retrun -1 if not found
	 * @param sheet
	 */
	public int getSheetIndex(ExcelSheet sheet){
		int index = 0; 
		for(ExcelSheet sh : sheets) {
			if(sh == sheet) return index;
			index++;
		}
		return -1;
	}

	/**
	 * Returns the index of the sheet by his name
	 * 
	 * @param name
	 * @retrun -1 if not found
	 */
	public int getSheetIndex(String name){
		int index=0;
		for(ExcelSheet sh : sheets) {
			if(sh.getSheetName().equals(name)) return index;
			index++;
		}
		return -1;
	}

	/**
	 * Get the sheet name
	 * 
	 * @param sheet
	 */
	public String getSheetName(int sheet){
		ExcelSheet sh= sheets.get(sheet);
		if( null == sh ) return null;
		return sh.getSheetName();
	}

	/**
	 * Check whether a sheet is hidden.Note that a sheet
	 * could instead be set to be very hidden, which is different
	 * 
	 * @param sheetIx
	 */
	public boolean isSheetHidden(int sheetIx){
		return _book.isSheetHidden(sheetIx);
	}

	/**
	 * Check whether a sheet is very hidden.This is different
	 * from the normal hidden status.
	 * 
	 * @param sheetIx
	 */
	public boolean isSheetVeryHidden(int sheetIx){
		return _book.isSheetVeryHidden(sheetIx);
	}

	/**
	 * Removes sheet at the given index.
	 * 
	 * @param index
	 */
	public void removeSheetAt(int index){
		_book.removeSheetAt(index);
		sheets.remove(index);
	}

	/**
	 * 设置活动Sheet页
	 * 
	 * @param sheetIndex
	 */
	public void setActiveSheet(int sheetIndex){
		_book.setActiveSheet(sheetIndex);
	}

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetIx
	 * @param hidden    0 - visible. 1 - hidden. 2 - very hidden.
	 */
	public void setSheetHidden(int sheetIx, int hidden){
		_book.setSheetHidden(sheetIx, hidden);
	}

	/**
	 * Hide or unhide a sheet
	 * 
	 * @param sheetIx
	 * @param hidden
	 */
	public void setSheetHidden(int sheetIx, boolean hidden){
		_book.setSheetHidden(sheetIx, hidden);
	}

	/**
	 * Set the sheet name. 
	 * if the name is null or invalid, do nothing.
	 * 
	 * @param sheet
	 * @param name
	 */
	public void setSheetName(int sheet, String name){
		_book.setSheetName(sheet, name);
	}

	/**
	 * Write out this workbook to an Outputstream.
	 * 
	 * Throw IOException if anything can't be written.
	 * 
	 * 
	 * @exception  -- 异常情况发生时日志中记录，不向业务层抛异常。
	 * @return  -- 失败返回false
	 * @param filename
	 */
	public boolean write(String filename) {
		try {
			OutputStream out = new FileOutputStream(filename);
			_book.write(out);
			out.close();
		} catch (FileNotFoundException fne) {
			logger.error("写入excel时发现文件不存在:"+filename);
			logger.error(fne.getMessage());			
			return false;
		} catch (IOException ioe) {
			logger.error("写入excel时发生IO异常:"+filename);
			logger.error(ioe.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 写入到文件流
	 * @return  -- 失败返回false
	 */
	public boolean write(OutputStream os)  {
		try {
			_book.write(os);
			return true;
		} catch (IOException e) {
			logger.warn("写入文件流失败" + os.toString());
			return false;
		}
	}

	/**
     * Alias for {@link #sheetIterator()} to allow  foreach loops:
     * 
     * for(ExcelCell cell : row){
     *     ...
     * }
     * 
     *
     * @return an iterator over cells in this row.
     */
	public Iterator<ExcelSheet> iterator() {
		return sheetIterator();
	}
	
    /**
     * Cell iterator over the physically defined cells:
     * 
     * for (Iterator<Cell> it = row.cellIterator(); it.hasNext(); ) {
     *     Cell cell = it.next();
     *     ...
     * }
     * 
     *
     * @return an iterator over cells in this row.
     */
    @SuppressWarnings("unchecked")
	public Iterator<ExcelSheet> sheetIterator() {
    	return (Iterator<ExcelSheet>)(Iterator<? extends ExcelSheet>)sheets.iterator();
	}

}