package org.cn.zszhang.common.utils.excel4j.binding;

import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;

/**
 * 从Excel中提取出bean对象的rowmapper接口
 * @author zszhang
 * @version 1.0
 * @created 17-四月-2015 11:30:49
 */
public interface Excel2BeanRowMapper<T> {

	/**
	 * 将一行数据转到一个bean中
	 * @return 转换后的bean
	 * 
	 * @param row    待转换的Excel行记录
	 * @param clazz	要转换到的bean的类型
	 */
	public T row2Bean(ExcelRow row, Class<T> clazz);

}