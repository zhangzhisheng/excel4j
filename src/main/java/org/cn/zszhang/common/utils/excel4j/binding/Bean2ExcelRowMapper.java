package org.cn.zszhang.common.utils.excel4j.binding;

import org.cn.zszhang.common.utils.excel4j.usermodel.ExcelRow;

/**
 * java bean 转到 ExcelRow 的映射接口
 * @author zszhang
 * @version 1.0
 * @created 17-四月-2015 11:30:39
 */
public interface Bean2ExcelRowMapper<T> {

	/**
	 * 将一个bean转到一行数据。失败返回null。
	 * 
	 * @param bean    要转换为ExcelRow的bean
	 * @param row    转换到的row对象
	 */
	public ExcelRow bean2Row(T bean, ExcelRow row);

}