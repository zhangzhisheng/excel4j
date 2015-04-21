package org.cn.zszhang.common.utils.excel4j.normal.reader;

import java.util.ArrayList;
import java.util.List;


/**
 * 工作表数据，有行数据构成
 * @author zszhang
 * @version 1.0
 * @created 12-四月-2015 19:25:34
 */
public class SheetDataImpl implements SheetData {

	/**
	 * 工作表对应的结果集；
	 */
	private List<List<String>> rs;
	private final int initColCnt = 16;
	private final int initRowCnt = 256;
	
	protected void addRow() {
		List<String> e = new ArrayList<String>(initColCnt);
		rs.add(e);
	}

	protected void addColData(int rowNo, String value) {
		List<String> row = rs.get(rowNo);
		if(row != null) {
			row.add(value);
		}
	}
	public SheetDataImpl(){
		rs = new ArrayList<List<String>>(initRowCnt);
	}

	public void finalize() throws Throwable {
		rs = null;  // release memory.
	}

	/**
	 * 通过列索引取数据
	 * 
	 * @param columnIndex
	 */
	public String get(int rowNo, int columnIndex){
		String v = null;
		
		if( rs != null && rs.get(rowNo)  != null) {
			v = rs.get(rowNo).get(columnIndex);
		}
		
		return v;
	}

	/**
	 * 通过Excel列名(如A,B,C,..,FF)取数据;
	 * 通过调用get(int)方法进行实现。
	 * 
	 * @param columnLabel
	 */
	public String get(int rowNo, String columnLabel){
		if(columnLabel == null || columnLabel.equals("")) {
			return null;
		}
		int columnIndex =  excel2Int(columnLabel);
		return this.get(rowNo, columnIndex);
	}
	
	// 实现excel列号转换为从0开始的序号
	private int excel2Int(String str) {
		int radix = 36;
		int t = Integer.parseInt(str, radix);
		int v=0,i=1;
		
		for(int a=t/radix,r=t%radix; a != 0 || r !=0  ;r=a%radix,a=a/radix) {
			v = (r-9)*i + v;
			i *= 26;
		}
		
		return v-1; // 为了表示从0开始
	}

	public int getRowCount() {
		return rs.size();
	}
	
	public String toString() {
		return rs.toString();
	}
}