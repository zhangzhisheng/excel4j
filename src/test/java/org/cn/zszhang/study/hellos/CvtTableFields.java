package org.cn.zszhang.study.hellos;

import java.io.File;
import java.util.List;

import org.cn.zszhang.common.utils.excel4j.binding.DefaultExcel4Java;
import org.cn.zszhang.common.utils.excel4j.binding.DefaultExcel4JavaRowMapper;
import org.cn.zszhang.common.utils.excel4j.binding.Excel4Java;
import org.cn.zszhang.common.utils.excel4j.binding.Excel4JavaRowMapper;
import org.cn.zszhang.common.utils.excel4j.importer.DefaultImpExpExcel;
import org.cn.zszhang.common.utils.excel4j.importer.ImpExpExcel;
import org.cn.zszhang.study.hellos.model.TableMapper;

public class CvtTableFields {

	public static void main(String[] args) {
		Excel4Java e4j = new DefaultExcel4Java();
		String filename = "f:/test/ttt.xlsx";
		e4j.createExcelBook(filename);
		Excel4JavaRowMapper<TableMapper> mapper = new DefaultExcel4JavaRowMapper<TableMapper>();
		List<TableMapper> data = null;
//		List<TableMapper> result = null;
		for(int i=0;i<100;i++) {
			data = e4j.toBeans(i, 0, Integer.MAX_VALUE, mapper, TableMapper.class);
			if(null == data) break;
			data.forEach(e->e.setName("INFO:"+CvtTableFields.convertToAttrName(e.getCode())));
			e4j.toSheet(data, i, 0, mapper);
//			result = data.stream().map(e->{e.setName(CvtTableFields.convertToAttrName(e.getCode()));return e;}).
		}
		filename = "f:/test/ttt2.xlsx";
		e4j.write2File(filename);
	}

	public static String convertToAttrName(String colName) {
		char[] buf = colName.toCharArray();
		if(null == buf) return null;
		buf[0] = Character.toLowerCase(buf[0]);
		for(int i=1; i< buf.length; i++) {// 第一个字符不转大写，所以直接跳过
			buf[i] = Character.toLowerCase(buf[i]);
			if(buf[i-1] == '_') { // 如果前一个字符为下划线
				buf[i] = Character.toUpperCase(buf[i]);
			}
		}
		String tmp = new String(buf);
		String result = tmp.replaceAll("_", "");
		return result;
	}
}
