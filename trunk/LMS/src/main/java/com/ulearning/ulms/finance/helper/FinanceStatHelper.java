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
 * ����ģ��ͳ����
 *
 * @author liz
 * @ date 2005-12-23
 */
public class FinanceStatHelper
{
        /**
         * ���ݾ�������ID��Ҫ������ҵ�������г�Ҫҵ����Ϣ
         *
         * @param sName  Ҫ����������
         * @param typeID �������͵�ID
         * @param aspId
         * @return List
         */
        public List statEntity(int typeID, String sName, int aspId)
        {
                List list = null;

                if (7 == typeID)
                { //����
                }
                else if (8 == typeID)
                { //����
                }
                else if (9 == typeID)
                { //�γ�
                        list = getCouser(typeID, sName, aspId); //����
                }
                else if (10 == typeID)
                { // ��ѵ��
                        list = getCertificate(typeID, sName, aspId);
                }
                else if (11 == typeID)
                { // �̲�
                }
                else if (12 == typeID)
                { //  ��ѵ�
                }

                return list;
        }

        /**
         * ���ݰ༶����ȡ��Ϣ
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
                                        frmStat.setDescription("��");
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
         * ��������ȡ�γ���Ϣ
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
                                frm.setDescription("��");
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
         * ��������ϸ����ID��ҵ��ʵ��ID���ɶ�Ӧҵ����շ������ϸ
         *
         * @param typeID     ��������ID
         * @param entityID   ��Ӧҵ��ʵ���ID
         * @param entityName ��Ӧҵ��ʵ������ƴ�requset��ȡ�������ٲ�ѯ��
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
                        double dSum = 0.0; //�ϼ�
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
                        frm.setEntityName("��  ��");

                        Date date = new Date();
                        //String sDate=DateTimeUtil.FormatDateToWeb1(date);
                        //frm.setOutlayDate(DateTimeUtil.parseDate(sDate));
                        //frm.setOutlayDate();
                        frm.setOutlaySum(dSum);
                        frm.setUserID(0);
                        frm.setUserName("��");
                        listResult.add(frm);
                }

                return listResult;
        }
}
