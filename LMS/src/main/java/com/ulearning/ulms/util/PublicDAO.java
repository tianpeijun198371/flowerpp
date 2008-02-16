package com.ulearning.ulms.util;

import java.util.List;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public interface PublicDAO {
	
	/**
	 * ȡ���ݿ��¼����
	 * @param hql��һ��������ȡ������SQL����ʽ�磺select count(*) from Table where ....
	 * @return
	 * @throws ULMSAppException
	 */
	public int getRecordTotal(String hql) throws ULMSAppException;
	
	/**
	 * ����ͨ�ò�ѯ����
	 * @param hql��һ�������Ĳ�ѯSQL��
	 * @return �õ������ݼ���
	 * @throws ULMSAppException
	 */
    public List getList(String hql) throws ULMSAppException;
    
    /**
     * ����ͨ�ò�ѯ����,����ҳ
     * @param pageModel ��ҳ��
     * @param recdCount ��¼����
     * @param hql
     * @return ArrayListModel ��¼����ҳ��Ϣ��
     * @throws ULMSAppException
     */
    public ArrayListModel getListPage(String hql,PageModel pageModel,int recdCount) throws ULMSAppException;
    
    /**
     * ����ͨ�ò�ѯ����,����ҳ,������������ѯ
     * @param pageModel ��ҳ��
     * @param recdCount ��¼����
     * @param startDateTime ��ʼ����
     * @param endDateTime������������
     * @param hql
     * @return ArrayListModel  ��¼����ҳ��Ϣ��
     * @throws ULMSAppException
     */
    public ArrayListModel getListPageDate(String hql,String startDateTime, String endDateTime,PageModel pageModel,int recdCount) throws ULMSAppException;
    /**
     * ����ͨ��������ӷ���
     * @param obj object �������з���hibernate�淶��model
     * @return ��ӳɹ��������ݵ�ID��û�гɹ�����0
     * @throws ULMSAppException
     */
    public int addData(Object obj) throws ULMSAppException;
    
    /**
     * ����ͨ���޸����ݷ���
     * @param obj object �������з���hibernate�淶��model
     * @throws ULMSAppException
     */
    public void updateData(Object obj) throws ULMSAppException;
    
    /**
     * ����ͨ��ɾ�����ݷ���
     * @param hql һ�������ķ���hibernate��SQL
     * @throws ULMSAppException
     */
    public void removeData(String hql) throws ULMSAppException;
}
