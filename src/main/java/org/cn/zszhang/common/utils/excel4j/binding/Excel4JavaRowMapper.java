package org.cn.zszhang.common.utils.excel4j.binding;

/**
 * 将excel中的每条数据转换到java对象的接口。
 * @author zszhang
 * @version 1.0
 * @updated 17-四月-2015 11:31:09
 */
public interface Excel4JavaRowMapper<T> extends Excel2BeanRowMapper<T>, Bean2ExcelRowMapper<T> {

}