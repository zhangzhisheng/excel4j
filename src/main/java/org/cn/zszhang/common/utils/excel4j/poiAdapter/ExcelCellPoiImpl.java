package org.cn.zszhang.common.utils.excel4j.poiAdapter;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
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
public class ExcelCellPoiImpl  implements ExcelCell {
	private final static Logger logger = LoggerFactory.getLogger(ExcelCellPoiImpl.class);
	
	private final Cell _cell;
	private final ExcelRow _row;

	public ExcelCellPoiImpl(Cell cell, ExcelRow _row){
		if( null == cell || null == _row ) {
			logger.error("初始化ExcelCell失败，传入空的PoiCell对象！");
		}
		_cell = cell;
		this._row = _row;
	}

	protected Cell getPoiCell() {
		return _cell;
	}
	/**
	 * Get the value of the cell as a boolean.
	 */
	public boolean getBooleanCellValue(){
		return _cell.getBooleanCellValue();
	}

	/**
	 * Return a formula for the cell, for example, SUM(C4:
	 * E4)
	 */
	public String getCellFormula(){
		return _cell.getCellFormula();
	}

	public int getCellType(){
		switch (_cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK: return ExcelCell.CELL_TYPE_BLANK;
		case Cell.CELL_TYPE_BOOLEAN: return ExcelCell.CELL_TYPE_BOOLEAN;
		case Cell.CELL_TYPE_STRING: return ExcelCell.CELL_TYPE_STRING;
		case Cell.CELL_TYPE_NUMERIC:
				if(DateUtil.isCellDateFormatted(_cell)) 
					return ExcelCell.CELL_TYPE_DATE;
				else
					return ExcelCell.CELL_TYPE_NUMERIC;
		case Cell.CELL_TYPE_FORMULA:	return getFormulaCellValueType();
		default: return ExcelCell.CELL_TYPE_ERROR;
		}
	}

	private int getFormulaCellValueType() {
		switch(_cell.getCachedFormulaResultType()) {
		case Cell.CELL_TYPE_BLANK: return ExcelCell.CELL_TYPE_BLANK;
		case Cell.CELL_TYPE_BOOLEAN: return ExcelCell.CELL_TYPE_BOOLEAN;
		case Cell.CELL_TYPE_STRING: return ExcelCell.CELL_TYPE_STRING;
		case Cell.CELL_TYPE_NUMERIC:
				if(DateUtil.isCellDateFormatted(_cell)) 
					return ExcelCell.CELL_TYPE_DATE;
				else
					return ExcelCell.CELL_TYPE_NUMERIC;
		default: return ExcelCell.CELL_TYPE_ERROR; // 因公式的值类型不可能还是公式，所以将其算在default里面做错误处理
		}
	}
	/**
	 * Returns column index of this cell
	 */
	public int getColumnIndex(){
		return _cell.getColumnIndex();
	}

	/**
	 * Get the value of the cell as a date.
	 */
	public Date getDateCellValue(){
		return _cell.getDateCellValue();
	}

	/**
	 * Get the value of the cell as a number.
	 */
	public double getNumericCellValue(){
		return _cell.getNumericCellValue();
	}

	/**
	 * Returns the Row this cell belongs to
	 */
	public ExcelRow getRow(){
		return _row;
	}

	/**
	 * Returns row index of a row in the sheet that contains
	 * this cell
	 */
	public int getRowIndex(){
		return _cell.getRowIndex();
	}

	/**
	 * Returns the sheet this cell belongs to
	 */
	public ExcelSheet getSheet(){
		return _row.getSheet();
	}

	/**
	 * Get the value of the cell as a string
	 */
	public String getStringCellValue(){
		return _cell.getStringCellValue();
	}

	/**
	 * Sets formula for this cell.
	 * 
	 * @param formula
	 */
	public void setCellFormula(String formula){
		//_cell.setCellType(Cell.CELL_TYPE_FORMULA);
		_cell.setCellFormula(formula);
	}

	/**
	 * Set the cells type.If the cell currently contains a
	 * value, the value will be converted to match the new type, if possible.
	 * 
	 * 
	 * @param cellType
	 */
	public void setCellType(int cellType){
		switch (cellType) {
		case CELL_TYPE_BLANK: 
			_cell.setCellType(Cell.CELL_TYPE_BLANK);
			break;
		case CELL_TYPE_BOOLEAN:
			_cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
			break;
		case CELL_TYPE_STRING:
			_cell.setCellType(Cell.CELL_TYPE_STRING);
			break;
		case CELL_TYPE_DATE:
		case CELL_TYPE_NUMERIC:
			_cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			break;
		default:
			_cell.setCellType(Cell.CELL_TYPE_ERROR);
		}
	}

	/**
	 * Set a string value for the cell.
	 * 
	 * @param value
	 */
	public void setCellValue(String value){
		_cell.setCellValue(value);
	}

	/**
	 * Converts the supplied date to its equivalent Excel
	 * numeric value and sets that into the cell.
	 * 
	 * @param value
	 */
	public void setCellValue(Date value){
		_cell.setCellValue(value);
	}

	/**
	 * Set a boolean value for the cell
	 * 
	 * @param value
	 */
	public void setCellValue(boolean value){
		_cell.setCellValue(value);
	}

	/**
	 * Set a numeric value for the cell
	 * 
	 * @param value
	 */
	public void setCellValue(double value){
		_cell.setCellValue(value);
	}

	/**
	 * 不管什么类型都按照字符串形式返回值
	 * 
	 * @return 返回字符串形式的值,BLANK返回"NULL"，ERROR返回"ERROR"
	 */
	public String getCellValueAsString() {
		String v = getCellValue().toString();
		return v;	
	}

	public Object getCellValue() {
		Cell cell = _cell;
		Object v = null;
		int type = cell.getCellType() == Cell.CELL_TYPE_FORMULA ?  cell.getCachedFormulaResultType() : cell.getCellType();
		switch (type) {
		case Cell.CELL_TYPE_STRING:
			v = cell.getRichStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				v = cell.getDateCellValue();
			} else {
				v = cell.getNumericCellValue();
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			v = cell.getBooleanCellValue();
			break;
		default:
			// do nothing.
			logger.debug("cell " + cell.getColumnIndex() + "convert fail...");
		}
		return v;
	}

	public void setCellValue(Object value) {
		if( null == value ) return;
		if( value instanceof Date ) setCellValue(Date.class.cast(value));
		else if( value instanceof String ) setCellValue(String.class.cast(value));
		else if( value instanceof Boolean ) setCellValue(Boolean.class.cast(value).booleanValue());
		else if ( value instanceof Integer ) setCellValue(Integer.class.cast(value).doubleValue());
	}

}