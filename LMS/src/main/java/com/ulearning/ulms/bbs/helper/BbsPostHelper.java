/**
 * BBS发帖辅助类
 */
package com.ulearning.ulms.bbs.helper;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.util.ArrayListModel;
import com.ulearning.ulms.util.PageModel;
import com.ulearning.ulms.util.PublicDAO;
import com.ulearning.ulms.util.PublicDAOFactory;

public class BbsPostHelper {
	
	/**
	 * 取某用户的发帖数或精华数
	 * @param username 用户名
	 * @param postType 1：查询发帖数；2：查询精华数
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
			if(postType.equals("9")){//取精华
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
	 * 取BBS题目信息
	 * @param Id　题目ID
	 * @param forumId　　论坛ID
	 * @param mName　　发起人姓名
	 * @param topic　题目名称
	 * @param viewCount　　观看次数
	 * @param replyCount　 响应次数
	 * @param headType  话题状态，0：一般；1：粘贴的；2：公告话题；3：全局公告， 1、2、3目前视为精华,如果传的是9，则取1\2\3。
	 * @param pModel  翻页参数
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
			//取得数据库记录总数,并实现翻页
			dataRowCount=dao.getRecordTotal("select count(*) from Mvnforumthread where 1>0"+sWhere);
			list = dao.getListPage(hql.toString(),pModel,dataRowCount);
		}else{
			list = new ArrayListModel(dao.getList(hql.toString()));
		}
			
		
		
		
		return list;
	}
	
	/**
	 * 取所有的论题信息
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getAllData() throws ULMSAppException{
		ArrayListModel list=null;
		list = getBbsHead(null,null,null,null,null,null,null,null);
		return list;
	}
	/**
	 * 根据论坛标题ID取论题信息
	 * @param Id 论题主ID
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getById(Integer Id) throws ULMSAppException{
		ArrayListModel list=null;
		list = getBbsHead(Id,null,null,null,null,null,null,null);
		return list;
	}

	/**
	 * 根据论题发起人姓名取论题信息
	 * @param mName BBS成员姓名
	 * @return
	 * @throws ULMSAppException
	 */
	public ArrayListModel getBymName(String mName) throws ULMSAppException{
		ArrayListModel list=null;
		list = getBbsHead(null,null,mName,null,null,null,null,null);
		return list;
	}
	
	/**
	 * 取精华帖
	 * @param rowCount 需要的记录行数
	 * @param headType 话题类型，9取类型是1、2、3的话题。
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
