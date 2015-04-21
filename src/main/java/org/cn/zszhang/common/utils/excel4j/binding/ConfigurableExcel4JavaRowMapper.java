package org.cn.zszhang.common.utils.excel4j.binding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cn.zszhang.common.utils.excel4j.reflect.ClassUtil;
import org.cn.zszhang.common.utils.excel4j.reflect.FieldUtil;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelCell;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 可配置化的Excel4JavaRowMapper实现
 * @author zszhang
 * @version 1.0
 * @created 20-四月-2015 10:08:35
 */
public class ConfigurableExcel4JavaRowMapper<T> implements Excel4JavaRowMapper<T> {
	private final static Logger logger = LoggerFactory.getLogger(ConfigurableExcel4JavaRowMapper.class);
	
	private List<String> map = new ArrayList<String>();
	private final List<_MapEntry> _map = new ArrayList<_MapEntry>();

	public ConfigurableExcel4JavaRowMapper(){
	}

	/**
	 * 将一行数据转到一个bean中
	 * @return 转换后的bean
	 * 
	 * @param row    待转换的Excel行记录
	 * @param clazz    要转换到的bean的类型
	 */
	public T row2Bean(ExcelRow row, Class<T> clazz){
		T bean = ClassUtil.createBean(clazz);
		List<Field> fields = FieldUtil.getAllFields(clazz);
		Field f = null;
		ExcelCell cell = null;
		Object value = null;
		_MapEntry m = null;
		for( int i=0; i<_map.size(); i++) {
			m = _map.get(i);
			f = FieldUtil.getField(fields, m.attrName);
			if( null == f )	continue; // 如果没找见该属性，取下一个
			cell = row.getCell(m.colLabel);
			if( null == cell )	 continue;
			value = cell.getCellValue();
			if( null == value ) continue;
			FieldUtil.setFieldValue(bean, f, value);
		}
		return bean;
	}

	/**
	 * 将一个bean转到一行数据。失败返回null。
	 * 
	 * @param bean    要转换为ExcelRow的bean
	 * @param row    转换到的row对象
	 */
	@SuppressWarnings("unchecked")
	public ExcelRow bean2Row(T bean, ExcelRow row){
		if( map == null ) return row;
		Field f = null;
		ExcelCell cell = null;
		Object value = null;
		List<Field> fields = FieldUtil.getAllFields((Class<T>)bean.getClass());
		_MapEntry m = null;
		for( int i=0; i<_map.size(); i++) {
			m = _map.get(i);
			f = FieldUtil.getField(fields, m.attrName);
			if( null == f )	continue; // 如果没找见该属性，取下一个
			cell = row.createCell(m.colLabel);
			// 处理方法调用得到值
			value = FieldUtil.getFieldValue(f, bean);
			//value = getMethodResult(m.method, value);
			// 设置值
			cell.setCellValue(value);
		}
		return row;
	}

	
	/**
	 * 设置要转换的bean属性到excel中列之间的关系
	 * 
	 * @param map   属性和cel列之间的映射关系，格式为: colLabel, attrName
	 */
	public void setMap(List<String> map) {
		if( null == map || map.isEmpty() )	return;
		this.map = map;
		_MapEntry m = null;
		for( String s : map ) {
			m = new _MapEntry();
			if( m.setMapEntryValue(s) )
				_map.add(m);
		}
	}

	public List<String> getMap() {
		return map;
	}

	class _MapEntry {
		private final Pattern _mapPattern = Pattern.compile("^\\s*([a-zA-Z]{1,3})\\s*,\\s*(\\w+)\\s*$");
		String attrName;
		String colLabel;
		String method;
		protected boolean setMapEntryValue(String mapStr) {
			Matcher matcher = _mapPattern.matcher(mapStr);
			if( !matcher.find() ) {
				logger.warn( mapStr + "映射串配置格式错误, 此字段将不再做映射！");
				return false;
			}
			attrName = matcher.group(2);
			colLabel = matcher.group(1);
			method = null;
			return true;
		}
		protected _MapEntry() {};
	}

}