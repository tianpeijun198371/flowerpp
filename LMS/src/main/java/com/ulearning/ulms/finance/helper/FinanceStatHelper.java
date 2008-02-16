/**
 * FinanceStatHelper.java.
 * User: liz  Date: 2005-12-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.finance.form.FinanceStatForm;
import com.ulearning.ulms.finance.form.FinanceStatResultForm;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * 经费模块统计类
 *
 * @author liz
 * @ date 2005-12-23
 */
public class FinanceStatHelper
{
        /**
         * 根据经费类型ID和要搜索的业务名称列出要业务信息
         *
         * @param sName  要搜索的名称
         * @param typeID 经费类型的ID
         * @param aspId
         * @return List
         */
        public List statEntity(int typeID, String sName, int aspId)
        {
                List list = null;

                if (7 == typeID)
                { //考试
                }
                else if (8 == typeID)
                { //答疑
                }
                else if (9 == typeID)
                { //课程
                        list = getCouser(typeID, sName, aspId); //调用
                }
                else if (10 == typeID)
                { // 培训班
                        list = getCertificate(typeID, sName, aspId);
                }
                else if (11 == typeID)
                { // 教材
                }
                else if (12 == typeID)
                { //  培训活动
                }

                return list;
        }

        /**
         * 根据班级名称取信息
         *
         * @param typeID
         * @param sName
         * @return
         */
        private List getCertificate(int typeID, String sName, int aspId)
        {
                List list = null;
                List listResult = null;
                list = CertHelper.getCertList(0, aspId);

                CertForm frm = null;

                if ((null != list) && (0 != list.size()))
                {
                        FinanceStatForm frmStat = null; //new FinanceStatForm();
                        listResult = new ArrayList();

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                frmStat = new FinanceStatForm();
                                frm = (CertForm) iter.next();

                                if ((null == frm.getDescription()) ||
                                        frm.getDescription().equals(""))
                                {
                                        frmStat.setDescription("　");
                                }
                                else
                                {
                                        frmStat.setDescription(frm.getDescription());
                                }

                                //System.out.println("getCertificateID=="+frm.getCertificateID());
                                frmStat.setEntityid(frm.getCertificateID());
                                frmStat.setSname(frm.getName());
                                frmStat.setTypeId(typeID);
                                listResult.add(frmStat);
                        }
                }

                return listResult;
        }

        /**
         * 根据名称取课程信息
         *
         * @param typeID
         * @param sName
         * @return
         */
        private List getCouser(int typeID, String sName, int aspId)
        {
                List list = null;
                List listResult = null;
                CourseWebImpl couser = new CourseWebImpl();
                CourseListModel clm = null;
                clm = couser.search(sName, aspId);

                if (null != clm)
                {
                        list = clm.getList();
                }

                FinanceStatForm frm = null; // new FinanceStatForm();
                CourseModel model = new CourseModel();
                listResult = new ArrayList();

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        model = (CourseModel) iter.next();
                        frm = new FinanceStatForm();
                        frm.setEntityid(model.getCourseID());

                        if ((null == model.getDescription()) ||
                                model.getDescription().equals(""))
                        {
                                frm.setDescription("　");
                        }
                        else
                        {
                                frm.setDescription(model.getDescription());
                        }

                        frm.setSname(model.getName());
                        frm.setTypeId(typeID);
                        listResult.add(frm);
                }

                return listResult;
        }

        /*
          private List getCertificate(String sName){
                  List list = null;
                  //CourseWebImpl couser = new CourseWebImpl();
                  CourseListModel clm = null;
                  clm = couser.search(sName);
                  if (null != clm)
                  {
                          list = clm.getList();
                  }
                  FinanceStatForm frm = new FinanceStatForm();
                  //CourseModel model = new CourseModel();
                  list = new ArrayList();
                  for (Iterator iter = list.iterator(); iter.hasNext();)
                  {
                          model = (CourseModel) iter.next();
                          frm.setiId(model.getCourseID());
                          frm.setDescription(model.getDescription());
                          frm.setsName(model.getName());
                          list.add(frm);
                  }
                  return list;
          }
        */

        /**
         * 　根据明细类型ID、业务实体ID生成对应业务的收费情况明细
         *
         * @param typeID     收入类型ID
         * @param entityID   对应业务实体的ID
         * @param entityName 对应业务实体的名称从requset中取，不用再查询表
         * @return List
         */
        public List makeEntityfinance(int entityID, int typeID, String entityName)
                throws UnsupportedEncodingException
        {
                List list = null;
                List listResult = null;
                UserAccountHelper help = new UserAccountHelper();
                list = help.getUADetail(entityID, typeID, 2);

                UserAccountDetailForm detailFrm = null;
                String sName;

                if ((null != list) && (0 != list.size()))
                {
                        listResult = new ArrayList();
                        detailFrm = new UserAccountDetailForm();

                        // byte temp[]=entityName.getBytes("iso8859-1");
                        // sName =new String(temp);
                        double dSum = 0.0; //合计
                        FinanceStatResultForm frm;

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                detailFrm = (UserAccountDetailForm) iter.next();
                                frm = new FinanceStatResultForm();
                                frm.setEntityID(entityID);
                                //frm.setEntityName(sName);
                                frm.setEntityName(entityName);
                                frm.setOutlayDate(detailFrm.getUaDetailDate());
                                frm.setOutlaySum(detailFrm.getUaDetailAmount());
                                frm.setUserID(detailFrm.getUserID());
                                frm.setUserName(detailFrm.getUaDetailUserName());
                                dSum = dSum + detailFrm.getUaDetailAmount();
                                listResult.add(frm);
                        }

                        frm = new FinanceStatResultForm();
                        frm.setEntityID(0);
                        ;
                        frm.setEntityName("合  计");

                        Date date = new Date();
                        //String sDate=DateTimeUtil.FormatDateToWeb1(date);
                        //frm.setOutlayDate(DateTimeUtil.parseDate(sDate));
                        //frm.setOutlayDate();
                        frm.setOutlaySum(dSum);
                        frm.setUserID(0);
                        frm.setUserName("　");
                        listResult.add(frm);
                }

                return listResult;
        }
}
