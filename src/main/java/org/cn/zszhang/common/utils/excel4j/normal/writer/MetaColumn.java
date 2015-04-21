package org.cn.zszhang.common.utils.excel4j.normal.writer;


/**
 * ���������excel����Ϣ����Щ������Ϣͨ������ȡֵ��Χ����ʽ��չ�ַ�ʽ
 * @author zszhang
 * @version 1.0
 * @created 13-����-2015 17:34:39
 */
public class MetaColumn {

	/**
	 * ������ɫ
	 */
	private String backgroundColor;
	/**
	 * ������ɫ
	 */
	private String fontColor;
	/**
	 * ���㹫ʽ��excel�ļ��㹫ʽ�����ڸ����û�������excel�Զ�������Ӧ���ݡ�
	 */
	private String formula;
	/**
	 * ����ռ�õ�Ԫ������������ͷ�⣬���ݲ��ֺ����õ�ռ�ö����Ԫ�����������Դ��ֶ���ʱ��ʵ�֡�
	 */
	private short heightByCells = 1;
	/**
	 * ','�ָ���ַ�������Ӧexcel�е�������Ч�Դ���
	 */
	private String range;
	/**
	 * ����ռ�õ�Ԫ������
	 */
	private short widthByCells = 1;

	public MetaColumn(){

	}

	public void finalize() throws Throwable {

	}

}