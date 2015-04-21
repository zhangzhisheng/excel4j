package org.cn.zszhang.common.utils.excel4j.poiAdapter;

import java.util.Iterator;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelCell;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zszhang
 * @version 1.0
 * @created 13-四月-2015 17:34:39
 */
public class ExcelRowPoiImpl implements ExcelRow {
	private final static Logger logger = LoggerFactory.getLogger(ExcelRowPoiImpl.class);
	
	private final Row _row;
	private final ExcelSheet _sheet;
    /**
     * Cells of this row keyed by their column indexes.
     * The TreeMap ensures that the cells are ordered by columnIndex in the ascending order.
     */
	private final TreeMap<Integer, ExcelCell> cells = new TreeMap<Integer, ExcelCell>();

	public ExcelRowPoiImpl(Row row, ExcelSheet _sheet){
		_row = row;
		this._sheet = _sheet;
		if(null == row || null == _sheet) {
			logger.error("初始化ExcelRow失败，传入空的PoiRow对象！");
			return;
		}
		for(Cell c : row) {
			ExcelCell e = new ExcelCellPoiImpl(c, this);
			cells.put(c.getColumnIndex(), e);
		}
	}

	protected Row getPoiRow() {
		return _row;
	}
	/**
	 * Use this to create new cells within the row and return
	 * it.The cell that is returned will be of the
	 * requested type.The type can be changed either through calling setCellValue or
	 * setCellType.
	 * 
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * @param column
	 * @param type
	 */
	public ExcelCell createCell(int column, int type){
		ExcelCell existCell = cells.get(column);
		if( existCell != null ) return existCell;
		Cell cell = _row.createCell(column, type);
		if( null == cell ) return null;
		
		ExcelCell ec = new ExcelCellPoiImpl(cell, this);
		cells.put(cell.getColumnIndex(), ec);
		return ec;
	}
	/**
	 * Use this to create new cells within the row and return
	 * it.The type can be changed either through
	 * calling setCellValue or setCellType.
	 * 
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * @param colLabel
	 */
	public ExcelCell createCell(String colLabel) {
		int cellnum = label2Index(colLabel);
		return createCell(cellnum);
	}

	/**
	 * Use this to create new cells within the row and return
	 * it.The cell that is returned will be of the
	 * requested type.The type can be changed either through calling setCellValue or
	 * setCellType.
	 * 
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * @param colLabel
	 * @param type
	 */
	public ExcelCell createCell(String colLabel, int type) {
		int cellnum = label2Index(colLabel);
		return createCell(cellnum, type);
	}

	/**
	 * Use this to create new cells within the row and return
	 * it.The type can be changed either through
	 * calling setCellValue or setCellType.
	 * 
	 * @return -- 如果已经存在该单元格，直接返回已经存在的单元格，不再重新创建。
	 * @param column
	 */
	public ExcelCell createCell(int column){
		ExcelCell existCell = cells.get(column);
		if( existCell != null ) return existCell;
		Cell cell = _row.createCell(column);
		if( null == cell ) return null;
		
		ExcelCell ec = new ExcelCellPoiImpl(cell, this);
		cells.put(cell.getColumnIndex(), ec);
		return ec;
	}

	/**
	 * Get the cell representing a given column (logical
	 * cell) 0-<font
	 * color="#3f5fbf">based.  If you ask for a
	 * cell that is not defined....return null.
	 * 
	 * @param cellnum
	 */
	public ExcelCell getCell(int cellnum) {
		if(cellnum < 0 || cellnum >= _row.getLastCellNum()) return null;
		
		ExcelCell cell =  cells.get(cellnum);
		
		return cell;
	}

	/**
	 * Get the number of the first cell contained in this row.
	 * 
	 */
	public int getFirstCellNum(){
		return _row.getFirstCellNum();
	}

	/**
	 * Gets the index of the last cell contained in this row
	 * PLUS
	 * ONE.
	 * The result also happens to be the
	 * 1-based
	 * column number of the last cell.  This value can be used as a standard upper
	 * bound when iterating over cells.
	 */
	public int getLastCellNum(){
		return _row.getLastCellNum();
	}

	/**
	 * Get row number this row represents
	 */
	public int getRowNum(){
		return _row.getRowNum();
	}

	/**
	 * Returns the Sheet this row belongs to
	 */
	public ExcelSheet getSheet(){
		return _sheet;
	}

	/**
	 * Remove the Cell from this row.
	 * 
	 * @param cell
	 */
	public void removeCell(ExcelCell cell){
		_row.removeCell(((ExcelCellPoiImpl)cell).getPoiCell());
		cells.remove(cell.getColumnIndex());
	}

    /**
     * Alias for {@link #cellIterator()} to allow  foreach loops:
     * <pre>
     * for(Cell cell : row){
     *     ...
     * }
     * 
     *
     * @return an iterator over cells in this row.
     */
	public Iterator<ExcelCell> iterator() {
		return cellIterator();
	}

    /**
     * Cell iterator over the physically defined cells:
     * <pre>
     * for (Iterator<Cell> it = row.cellIterator(); it.hasNext(); ) {
     *     Cell cell = it.next();
     *     ...
     * }
     * 
     *
     * @return an iterator over cells in this row.
     */
	public Iterator<ExcelCell> cellIterator() {
		return cells.values().iterator();
	}

	/**
	 * Get the cell representing a given column label
	 * If you ask for a
	 * cell that is not defined....return null.
	 * 
	 * @param colLabel -- Excel列标，如a,b,aa,ab等
	 */
	public ExcelCell getCell(String colLabel) {
		int cellnum = label2Index(colLabel);
		return getCell(cellnum);
	}
	// 实现excel列号转换为从0开始的序号
	private int label2Index(String str) {
		int radix = 36;
		int t = Integer.parseInt(str, radix);
		int v=0,i=1;
		
		for(int a=t/radix,r=t%radix; a != 0 || r !=0  ;r=a%radix,a=a/radix) {
			v = (r-9)*i + v;
			i *= 26;
		}
		
		return v-1; // 为了表示从0开始
	}

}