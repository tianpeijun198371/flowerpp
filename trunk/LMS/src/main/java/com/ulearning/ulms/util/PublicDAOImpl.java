package com.ulearning.ulms.util;

import java.util.List;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.util.PageModel;

public class PublicDAOImpl implements PublicDAO {

	/**
	 * 取数据库记录总数
	 * 
	 * @param hql
	 *            一个完整的取总数的SQL，形式如：select count(*) from Table where ....
	 * @return
	 * @throws ULMSAppException
	 */
	public int getRecordTotal(String hql) throws ULMSAppException {
		int iTotal = 0;
		iTotal = HibernateDAO.count(hql);
		return iTotal;
	}

	/**
	 * 抽象通用查询方法
	 * 
	 * @param hql，一个完整的查询SQL。
	 * @return 得到的数据集。
	 * @throws ULMSAppException
	 */
	public List getList(String hql) throws ULMSAppException {
		List list = null;
		list = HibernateDAO.find(hql.toString());
		return list;
	}

	/**
	 * 抽象通用查询方法,抽象通用查询方法,带分页
	 *
	 *            分页类
	 * @param recdCount
	 *            记录总数
	 * @param hql，一个完整的查询SQL。
	 * @return ArrayListModel 记录带分页信息类
	 * @throws ULMSAppException
	 */
	public ArrayListModel getListPage(String hql, PageModel pageModel,
			int recdCount) throws ULMSAppException {
		ArrayListModel listModel = null;
		listModel = getListPageDate(hql, "", "", pageModel, recdCount);
		return listModel;
	}

	/**
	 * 抽象通用查询方法,抽象通用查询方法,带分页,带日期条件查询
	 *
	 *            分页类
	 * @param recdCount
	 *            记录总数
	 * @param hql，一个完整的查询SQL。
	 * @return ArrayListModel 记录带分页信息类
	 * @throws ULMSAppException
	 */
	public ArrayListModel getListPageDate(String hql, String startDateTime,
			String endDateTime, PageModel pageModel, int recdCount)
			throws ULMSAppException {
		List list = null;
		ArrayListModel listModel = null;
		if (null != pageModel) {
			// 获得分页信息
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
			// 取得总页数
			paginationModel = pageModel.getPageCountByRecordCount(recdCount);
			// 合法性
			if ((currentPage - 1) * pageRecord > recdCount) {
				throw new ULMSAppException("pageModel wrong");
			}
			// 查询,带翻页

			list = HibernateDAO.find(hql, startDateTime, endDateTime,
					((currentPage - 1) * pageRecord), pageRecord);
		} else {
			//查询,不带翻页
			list = HibernateDAO.find(hql, startDateTime, endDateTime, -1, -1);
		}
		listModel = new ArrayListModel(list);
		return listModel;
	}

	/**
	 * 抽象通用数据添加方法
	 * 
	 * @param obj
	 *            object 接收所有符合hibernate规范的model
	 * @return 添加成功后本条数据的ID，没有成功返回0
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
	 * 抽象通用修改数据方法
	 * 
	 *            object 接收所有符合hibernate规范的model
	 * @throws ULMSAppException
	 */
	public void updateData(Object obj) throws ULMSAppException {
		// HibernateDAO.update((PublicModel)obj);

		HibernateDAO.update(obj);

	}

	/**
	 * 抽象通用删除数据方法
	 * 
	 * @param hql
	 *            一个完整的符合hibernate的SQL
	 * @throws ULMSAppException
	 */
	public void removeData(String hql) throws ULMSAppException {

		HibernateDAO.delete(hql);

	}

}
