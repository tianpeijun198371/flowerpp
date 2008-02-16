package com.ulearning.ulms.util;

import java.util.List;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.util.PageModel;

public class PublicDAOImpl implements PublicDAO {

	/**
	 * ȡ���ݿ��¼����
	 * 
	 * @param hql
	 *            һ��������ȡ������SQL����ʽ�磺select count(*) from Table where ....
	 * @return
	 * @throws ULMSAppException
	 */
	public int getRecordTotal(String hql) throws ULMSAppException {
		int iTotal = 0;
		iTotal = HibernateDAO.count(hql);
		return iTotal;
	}

	/**
	 * ����ͨ�ò�ѯ����
	 * 
	 * @param hql��һ�������Ĳ�ѯSQL��
	 * @return �õ������ݼ���
	 * @throws ULMSAppException
	 */
	public List getList(String hql) throws ULMSAppException {
		List list = null;
		list = HibernateDAO.find(hql.toString());
		return list;
	}

	/**
	 * ����ͨ�ò�ѯ����,����ͨ�ò�ѯ����,����ҳ
	 *
	 *            ��ҳ��
	 * @param recdCount
	 *            ��¼����
	 * @param hql��һ�������Ĳ�ѯSQL��
	 * @return ArrayListModel ��¼����ҳ��Ϣ��
	 * @throws ULMSAppException
	 */
	public ArrayListModel getListPage(String hql, PageModel pageModel,
			int recdCount) throws ULMSAppException {
		ArrayListModel listModel = null;
		listModel = getListPageDate(hql, "", "", pageModel, recdCount);
		return listModel;
	}

	/**
	 * ����ͨ�ò�ѯ����,����ͨ�ò�ѯ����,����ҳ,������������ѯ
	 *
	 *            ��ҳ��
	 * @param recdCount
	 *            ��¼����
	 * @param hql��һ�������Ĳ�ѯSQL��
	 * @return ArrayListModel ��¼����ҳ��Ϣ��
	 * @throws ULMSAppException
	 */
	public ArrayListModel getListPageDate(String hql, String startDateTime,
			String endDateTime, PageModel pageModel, int recdCount)
			throws ULMSAppException {
		List list = null;
		ArrayListModel listModel = null;
		if (null != pageModel) {
			// ��÷�ҳ��Ϣ
			int currentPage = 1;
			
			
			if (Integer.parseInt(pageModel.getCURRENTPAGE()) <= 0) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(pageModel.getCURRENTPAGE());
			}
			int pageRecord = 0;
			if (Integer.parseInt(pageModel.getPAGERECORD()) <= 0) {
				pageRecord = 10;
			} else {
				pageRecord = Integer.parseInt(pageModel.getPAGERECORD());
			}

			PageModel paginationModel = null;
			// ȡ����ҳ��
			paginationModel = pageModel.getPageCountByRecordCount(recdCount);
			// �Ϸ���
			if ((currentPage - 1) * pageRecord > recdCount) {
				throw new ULMSAppException("pageModel wrong");
			}
			// ��ѯ,����ҳ

			list = HibernateDAO.find(hql, startDateTime, endDateTime,
					((currentPage - 1) * pageRecord), pageRecord);
		} else {
			//��ѯ,������ҳ
			list = HibernateDAO.find(hql, startDateTime, endDateTime, -1, -1);
		}
		listModel = new ArrayListModel(list);
		return listModel;
	}

	/**
	 * ����ͨ��������ӷ���
	 * 
	 * @param obj
	 *            object �������з���hibernate�淶��model
	 * @return ��ӳɹ��������ݵ�ID��û�гɹ�����0
	 * @throws ULMSAppException
	 */
	public int addData(Object obj) throws ULMSAppException {
		int rId = 0;

		// HibernateDAO.add(obj);
		// rId=
		// Integer.parseInt(HibernateDAO.add((PublicModel)obj).toString());
		rId = Integer.parseInt(HibernateDAO.add(obj).toString());

		return rId;
	}

	/**
	 * ����ͨ���޸����ݷ���
	 * 
	 *            object �������з���hibernate�淶��model
	 * @throws ULMSAppException
	 */
	public void updateData(Object obj) throws ULMSAppException {
		// HibernateDAO.update((PublicModel)obj);

		HibernateDAO.update(obj);

	}

	/**
	 * ����ͨ��ɾ�����ݷ���
	 * 
	 * @param hql
	 *            һ�������ķ���hibernate��SQL
	 * @throws ULMSAppException
	 */
	public void removeData(String hql) throws ULMSAppException {

		HibernateDAO.delete(hql);

	}

}
