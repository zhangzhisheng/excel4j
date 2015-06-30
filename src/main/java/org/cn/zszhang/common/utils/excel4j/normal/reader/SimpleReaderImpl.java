package org.cn.zszhang.common.utils.excel4j.normal.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBook;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelBookFactory;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelCell;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zszhang
 * @version 1.0
 * @created 12-四月-2015 19:25:34
 */
public class SimpleReaderImpl implements SimpleReader {
	private final static Logger logger = LoggerFactory.getLogger(SimpleReaderImpl.class);

	public SimpleReaderImpl() {

	}

	public void finalize() throws Throwable {

	}

	private SheetData readSheet(ExcelSheet sheet) {
		if (sheet == null) {
			logger.warn("传入参数为null !");
			return null;
		}

		SheetDataImpl data = new SheetDataImpl();
		for (ExcelRow row : sheet) {
			data.addRow();
			for (ExcelCell cell : row) {
				String value = cell.getCellValueAsString();
				data.addColData(data.getRowCount()-1, value);
			}
		}

		return data;
	}

	/**
	 * 将文件中读出所有sheet的数据
	 * 
	 * @param filename
	 */
	public List<SheetData> read(String filename) {
		return this.read(filename, 0, Integer.MAX_VALUE);
	}

	/**
	 * 将文件中读出第sheetNo页的数据
	 * 
	 * @param filename
	 * @param sheetNo
	 */
	public SheetData read(String filename, int sheetNo) {
		ExcelBook wb = ExcelBookFactory.openBook(filename);
		if (wb == null)
			return null;

		ExcelSheet sheet = wb.getSheetAt(sheetNo);
		SheetData sd = readSheet(sheet);

		return sd;
	}

	/**
	 * 从beginSheetNo页开始，共读count页
	 * 
	 * @param filename
	 * @param beginSheetNo
	 * @param count
	 */
	public List<SheetData> read(String filename, int beginSheetNo, int count) {
		ExcelBook wb = ExcelBookFactory.openBook( filename);
		if (wb == null)
			return null;

		List<SheetData> result = new ArrayList<SheetData>();
		int endSheetNo = beginSheetNo + count < wb.getNumberOfSheets() ? beginSheetNo + count  : wb.getNumberOfSheets();
		for (int i = beginSheetNo; i < endSheetNo; i++) {
			ExcelSheet sheet = wb.getSheetAt(i);
			SheetData sd = readSheet(sheet);
			result.add(sd);
		}

		return result;
	}

	/**
	 * 将sheets数组中的所有sheetNo页读入进来
	 * 
	 * @param filename
	 * @param sheets
	 */
	public List<SheetData> read(String filename, int[] sheets) {
		ExcelBook wb = ExcelBookFactory.openBook(filename);
		if (wb == null || sheets == null)
			return null;

		List<SheetData> result = new ArrayList<SheetData>();
		for (int i = 0; i < sheets.length; i++) {
			ExcelSheet sheet = wb.getSheetAt(sheets[i]);
			SheetData sd = readSheet(sheet);
			result.add(sd);
		}

		return result;
	}

}