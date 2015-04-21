package org.cn.zszhang.common.utils.excel4j.normal.writer;

import java.util.List;


/**
 * ����һ��sheetҳ����Ϣ��
 * sheetҳ��header+data��ɣ��ݲ�����tail.
 * header�������data,�ݲ����Ǽ�����е������
 * @author zszhang
 * @version 1.0
 * @created 13-����-2015 17:34:39
 */
public class MetaSheet {

	/**
	 * ���ݲ��ֵ�������Ϣ
	 */
	private List<MetaColumn> data;
	/**
	 * �����������Ϣ
	 */
	private List<List<MetaColumn>> header;
	/**
	 * ����ĸ߶ȣ��Ե�Ԫ����㡣��header��Ϊ��ʱ���Ҵ��ֶ�С��header�е�ʵ�ʸ߶�ʱ����header�߶�Ϊ׼�������Դ��ֶ�Ϊ׼��
	 */
	private short heightOfHeader = 1;
	public MetaColumn m_MetaColumn;

	public MetaSheet(){

	}

	public void finalize() throws Throwable {

	}

}