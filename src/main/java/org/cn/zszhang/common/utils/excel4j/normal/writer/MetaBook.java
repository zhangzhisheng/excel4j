package org.cn.zszhang.common.utils.excel4j.normal.writer;


/**
 * ����excel������WorkBook��Ϣ
 * @author zszhang
 * @version 1.0
 * @created 13-����-2015 17:34:39
 */
public class MetaBook {

	/**
	 * �ӵ�beginSheetҳ��ʼ����sheetҳ��
	 */
	private short beginSheet = 0;
	/**
	 * �ܹ���Ҫ�����sheet����
	 */
	private short sheetCnt = 1;
	public MetaSheet m_MetaSheet;

	public MetaBook(){

	}

	public void finalize() throws Throwable {

	}

}