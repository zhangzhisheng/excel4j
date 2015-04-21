package org.cn.zszhang.common.utils.excel4j.config;

import java.lang.reflect.Modifier;

/**
 * 设置对Excel4Java的控制属性
 * @author zszhang
 * @version 1.0
 * @created 19-四月-2015 14:30:39
 */
public class Excel4JavaConfiguration {

	/**
	 * excludeFieldModifiers的内部整型表示，程序以此做判断。
	 * 缺省设置为static transient
	 */
	private static int _excludeMod = 0x00000088;
	/**
	 * 以空格分割的字符串，表示这种修饰符的字段不与excel交互。
	 * 初始化为"static,transient"，表名这两种字段不从excel写入、读出
	 */
	private static String excludeFiledModifiers = "static transient";
	
	/**
	 * 缺省的excel格式， 2003代表Excel2003及以下版本， 2007代表Excel2007级以上版本。
	 */
	private static int defaultExcelFromat = 2007;

	public Excel4JavaConfiguration(){

	}
	
	/**
	 * 	判断当前修饰符是否在排除列表中
	 * 
	 * @param mod 	修饰符的内部表示
	 */
	public static boolean isExcludeFieldModifier(int mod) {
		return (_excludeMod & mod) > 0;
	}
	public static String getExcludeFiledModifiers() {
		return excludeFiledModifiers;
	}

	/**
	 * 	按修饰符设置排除在外的字段种类
	 * 
	 * @param excludeFiledModifiers 	以空格分割的字符串，表示这种修饰符的字段不与excel交互。
	 * 			 		 初始化为"static,transient"，表名这两种字段不从excel写入、读出。
	 */
	public static void setExcludeFiledModifiers(String excludeFiledModifiers) {
		Excel4JavaConfiguration.excludeFiledModifiers = excludeFiledModifiers;
		
		if( null == excludeFiledModifiers || excludeFiledModifiers.isEmpty() ) {
			_excludeMod = 0;
		} else {
			excludeFiledModifiers = excludeFiledModifiers.toLowerCase();
			String[] modifiers = excludeFiledModifiers.split("\\s");
			for( String mod : modifiers ) {
				if( mod.equals("public") )	_excludeMod |= Modifier.PUBLIC;
				else if ( mod.equals("protected") ) 	_excludeMod |= Modifier.PROTECTED;
				else if ( mod.equals("private") ) 	_excludeMod |= Modifier.PRIVATE;
				else if ( mod.equals("static") ) 	_excludeMod |= Modifier.STATIC;
				else if ( mod.equals("final") ) 	_excludeMod |= Modifier.FINAL;
				else if ( mod.equals("transient") ) 	_excludeMod |= Modifier.TRANSIENT;
				else if ( mod.equals("volatile") ) 	_excludeMod |= Modifier.VOLATILE;
				// else do nothing. 因字段修饰符只有以上7种。
			}
		}
	}

	/**
	 * 获取缺省的excel格式
	 * @return --  2003代表Excel2003及以下版本， 2007代表Excel2007级以上版本。
	 */
	public static int getDefaultExcelFromat() {
		return defaultExcelFromat;
	}

	/**
	 * 设置缺省的excel格式
	 * @param fromat  -- 2003代表Excel2003及以下版本， 2007代表Excel2007级以上版本。
	 * 									如果不是2003和2007,do nothing.
	 */
	public static void setDefaultExcelFromat(int format) {
		if( 2003 == format || 2007 == format )
			Excel4JavaConfiguration.defaultExcelFromat = format;
	}

}