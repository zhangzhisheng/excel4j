package org.cn.zszhang.common.utils.excel4j.normal.reader;


/**
 * 工作表结果集，对应从工作表读取数据时的结果。
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 15:46:52
 */
public interface SheetData {

	/**
	 * 通过列索引取数据
	 * 
	 * @param rowNo
	 * @param columnIndex
	 */
	public String get(int rowNo, int columnIndex);

	/**
	 * 通过Excel列名(如A,B,C,..,FF)取数据
	 * 
	 * @param rowNo
	 * @param columnLabel
	 */
	public String get(int rowNo, String columnLabel);

	// ����sheetҳ����������
	public int getRowCount() ;
	
	public String toString();
}