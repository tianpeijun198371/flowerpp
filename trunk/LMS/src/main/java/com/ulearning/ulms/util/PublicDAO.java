package com.ulearning.ulms.util;

import java.util.List;

import com.ulearning.ulms.core.exceptions.ULMSAppException;

public interface PublicDAO {
	
	/**
	 * 取数据库记录总数
	 * @param hql　一个完整的取总数的SQL，形式如：select count(*) from Table where ....
	 * @return
	 * @throws ULMSAppException
	 */
	public int getRecordTotal(String hql) throws ULMSAppException;
	
	/**
	 * 抽象通用查询方法
	 * @param hql，一个完整的查询SQL。
	 * @return 得到的数据集。
	 * @throws ULMSAppException
	 */
    public List getList(String hql) throws ULMSAppException;
    
    /**
     * 抽象通用查询方法,带分页
     * @param pageModel 分页类
     * @param recdCount 记录总数
     * @param hql
     * @return ArrayListModel 记录带分页信息类
     * @throws ULMSAppException
     */
    public ArrayListModel getListPage(String hql,PageModel pageModel,int recdCount) throws ULMSAppException;
    
    /**
     * 抽象通用查询方法,带分页,带日期条件查询
     * @param pageModel 分页类
     * @param recdCount 记录总数
     * @param startDateTime 开始日期
     * @param endDateTime　　结束日期
     * @param hql
     * @return ArrayListModel  记录带分页信息类
     * @throws ULMSAppException
     */
    public ArrayListModel getListPageDate(String hql,String startDateTime, String endDateTime,PageModel pageModel,int recdCount) throws ULMSAppException;
    /**
     * 抽象通用数据添加方法
     * @param obj object 接收所有符合hibernate规范的model
     * @return 添加成功后本条数据的ID，没有成功返回0
     * @throws ULMSAppException
     */
    public int addData(Object obj) throws ULMSAppException;
    
    /**
     * 抽象通用修改数据方法
     * @param obj object 接收所有符合hibernate规范的model
     * @throws ULMSAppException
     */
    public void updateData(Object obj) throws ULMSAppException;
    
    /**
     * 抽象通用删除数据方法
     * @param hql 一个完整的符合hibernate的SQL
     * @throws ULMSAppException
     */
    public void removeData(String hql) throws ULMSAppException;
}
