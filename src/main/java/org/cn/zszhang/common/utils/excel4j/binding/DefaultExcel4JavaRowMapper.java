package org.cn.zszhang.common.utils.excel4j.binding;

import java.lang.reflect.Field;
import java.util.List;

import org.cn.zszhang.comm.sysutil.reflect.ClassUtil;
import org.cn.zszhang.comm.sysutil.reflect.FieldUtil;
import org.cn.zszhang.common.utils.excel4j.config.Excel4JavaConfiguration;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelCell;
import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缺省的ExcelRowMapper实现，采用反射机制。
 * 
 * @author zszhang
 * @version 1.0
 * @updated 16-四月-2015 19:37:20
 */
public class DefaultExcel4JavaRowMapper<T> implements Excel4JavaRowMapper<T> {
	private final static Logger logger = LoggerFactory.getLogger(DefaultExcel4JavaRowMapper.class);

	public DefaultExcel4JavaRowMapper() {

	}

	/**
	 * 将一个bean转到一行数据。失败返回null。
	 * 
	 * @param bean
	 *            要转换为ExcelRow的bean
	 * @param row
	 *            转换到的row对象
	 */
	public final ExcelRow bean2Row(T bean, ExcelRow row) {
		@SuppressWarnings("unchecked")
		Class<T> beanType = (Class<T>)bean.getClass();
		List<Field> fields = FieldUtil.getAllFields(beanType);
		int cellnum=0;
		for(Field field : fields) {
			field.setAccessible(true);
			if( Excel4JavaConfiguration.isExcludeFieldModifier(field.getModifiers()) ) continue;

			try {
				row.createCell(cellnum++).setCellValue(field.get(bean));
			} catch (IllegalAccessException e) {
				logger.warn("取字段值时出现非法访问异常:" + field.getName());
				logger.warn("错误信息:" + e.getMessage());
				continue;
			}
		}
		return row;
	}

	/**
	 * 将一行数据转到一个bean中
	 * 
	 * @return 转换后的bean
	 * 
	 * @param row     待转换的行记录
	 * @param clazz	要转换到的bean的类型
	 */
	public final T row2Bean(ExcelRow row, Class<T> clazz) {
		T bean = ClassUtil.createBean(clazz);
		List<Field> fields = FieldUtil.getAllFields(clazz);
		ExcelCell cell = null;
		Object value = null;
		int cellnum=0; 
		for (Field field : fields) {
			if( Excel4JavaConfiguration.isExcludeFieldModifier(field.getModifiers()) ) continue;
			cell = (ExcelCell)row.getCell(cellnum++);
			if( null == cell ) continue;
			value = cell.getCellValue();
			if( null == value ) continue;
			FieldUtil.setFieldValue(bean, field, value);
		}
		return bean;
	}

}