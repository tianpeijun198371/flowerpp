/**
 * BBS����������
 */
package com.ulearning.ulms.bbs.helper;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.util.ArrayListModel;
import com.ulearning.ulms.util.PageModel;
import com.ulearning.ulms.util.PublicDAO;
import com.ulearning.ulms.util.PublicDAOFactory;

public class BbsPostHelper {
	
	/**
	 * ȡĳ�û��ķ������򾫻���
	 * @param username �û���
	 * @param postType 1����ѯ��������2����ѯ������
	 * @return int
	 * @throws ULMSAppException
	 */
	public int getBbsCount(String username,String postType) throws ULMSAppException{
		int iCount=0;
		StringBuffer hql=new StringBuffer();
		StringBuffer where=new StringBuffer();
		hql.append("select count(*) from Mvnforumthread where 1>0");
		if(null!=username && !username.equals("")){
			where.append(" and membername='");
			where.append(username.trim());
			where.append("'");
		}
		if(null!=postType && !postType.equals("")){
			if(postType.equals("9")){//ȡ����
				where.append(" and ( threadtype=1 or threadtype=2 or threadtype=3)");
			}			
		}
		if(null==where || 0==where.length()){
			return iCount;
		}
		hql.append(where);
		PublicDAO  dao = PublicDAOFactory.getDAO();
		iCount = dao.getRecordTotal(hql.toString());
		return iCount;
	}
	
	/**
	 * ȡBBS��Ŀ��Ϣ
	 * @param Id����ĿID
	 * @param forumId������̳ID
	 * @param mName��������������
	 * @param topic����Ŀ����
	 * @param viewCount�����ۿ�����
	 * @param replyCount�� ��Ӧ����
	 * @param headType  ����״̬��0��һ�㣻1��ճ���ģ�2�����滰�⣻3��ȫ�ֹ��棬 1��2��3Ŀǰ��Ϊ����,���������9����ȡ1\2\3��
	 * @param pModel  ��ҳ����
	 * @return Model List
	 * @throws ULMSAppException
	 */
	public ArrayListModel getBbsHead(Integer Id, Integer forumId, String mName,
			String topic, Integer viewCount, Integer replyCount,Integer headType,PageModel pModel)
			throws ULMSAppException{
		ArrayListModel list=null;
		int dataRowCount=0;
		StringBuffer hql=new StringBuffer();
		StringBuffer sWhere=new StringBuffer();
		hql.append(" from Mvnforumthread where 1>0");
		if(null!=Id && !Id.equals("")){
			sWhere.append(" and threadid=");
			sWhere.append(Id);
		}
		if (null!=forumId && !forumId.equals("")){
			sWhere.append(" and forumid=");
			sWhere.append(forumId);
		}
		if (null!=mName && !mName.equals("")){
			sWhere.append(" and membername like '%");
			sWhere.append(mName);
			sWhere.append("%'");
		}
		if (null!=topic && !topic.equals("")){
			sWhere.append(" and threadtopic like '%");
			sWhere.append(topic);
			sWhere.append(topic);
		}
		if(null!=viewCount && !viewCount.equals("")){
			sWhere.append(" and threadviewcount=");
			sWhere.append(viewCount);
		}
		if(null!=replyCount && !replyCount.equals("")){
			sWhere.append(" and threadreplycount=");
			sWhere.append(replyCount);
		}
		if(null!=headType && !headType.equals("")){
			if(headType.equals(new Integer(9))){
				sWhere.append(" and ( threadtype=1 or threadtype=2 or threadtype=3)");
			}else{
				sWhere.append(" and threadtype=");
				sWhere.append(headType);
			}
		}
		hql.append(sWhere);
		PublicDAO  dao = PublicDAOFactory.getDAO();
		if(null!=pModel ){
			//ȡ�����ݿ��¼����,��ʵ�ַ�ҳ
			dataRowCount=dao.getRecordTotal("select count(*) from Mvnforumthread where 1>0"+sWhere);
			list = dao.getListPage(hql.toString(),pModel,dataRowCount);
		}else{
			list = new ArrayListModel(dao.getList(hql.toString()));
		}
			
		
		
		
		return list;
	}
	
	/**
	 * ȡ���е�������Ϣ
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getAllData() throws ULMSAppException{
		ArrayListModel list=null;
		list = getBbsHead(null,null,null,null,null,null,null,null);
		return list;
	}
	/**
	 * ������̳����IDȡ������Ϣ
	 * @param Id ������ID
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getById(Integer Id) throws ULMSAppException{
		ArrayListModel list=null;
		list = getBbsHead(Id,null,null,null,null,null,null,null);
		return list;
	}

	/**
	 * �������ⷢ��������ȡ������Ϣ
	 * @param mName BBS��Ա����
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getBymName(String mName) throws ULMSAppException{
		ArrayListModel list=null;
		list = getBbsHead(null,null,mName,null,null,null,null,null);
		return list;
	}
	
	/**
	 * ȡ������
	 * @param rowCount ��Ҫ�ļ�¼����
	 * @param headType �������ͣ�9ȡ������1��2��3�Ļ��⡣
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getEliteByNum(Integer headType,Integer rowCount) throws ULMSAppException{
		ArrayListModel list=null;
		PageModel pmodel=new PageModel(0,0,rowCount.intValue(),1);
		
		list=getBbsHead(null,null,null,null,null,null,headType,pmodel);
		return list;
		
	}
}
