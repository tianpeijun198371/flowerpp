/**
 * PlanHelper.java.
 * User: huangsb  Date: 2004-4-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.plan.bean;

import com.ulearning.ulms.admin.plan.dao.PlanDAO;
import com.ulearning.ulms.admin.plan.dao.PlanDAOFactory;
import com.ulearning.ulms.admin.plan.dao.PlanDAOImpl;
import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.admin.plan.form.PlanForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.util.LMSConstants;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.*;


public class PlanHelper
{
        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @param planID
         * @return the admin modle according to the planID
         */
        public static PlanForm getPlan(int planID)
        {
                PlanForm pf = null;

                try
                {
                        PlanDAO planDao = PlanDAOFactory.getDAO();
                        pf = planDao.getPlan(planID);
                }
                catch (PlanDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return pf;
        }

        /**
         * Get userID by loginName
         *
         * @param title
         * @return int value if exist, 0 otherwise
         */
        public static int getPlanID(String title, int isContent, int parentID)
        {
                int planID = 0;

                try
                {
                        PlanDAO planDao = PlanDAOFactory.getDAO();
                        planID = planDao.getPlanID(title, isContent, parentID);
                }
                catch (PlanDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return planID;
        }

        /**
         * Wrapping the get planList method for JSP and  the other modules
         *
         * @param orgID
         * @return the plan list according to the orgID
         */
        public List getPlanList(int orgID)
        {
                List planList = null;

                try
                {
                        PlanDAO planDao = PlanDAOFactory.getDAO();
                        planList = planDao.getPlanList(orgID);
                }
                catch (PlanDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return planList;
        }

        /**
         * Wrapping the get planList method for JSP and  the other modules
         *
         * @param parentID
         * @return the plan list according to the orgID
         */
        public List getPlanList(String parentID)
        {
                List planList = null;

                try
                {
                        PlanDAO planDao = PlanDAOFactory.getDAO();
                        planList = planDao.getPlanList(parentID);
                }
                catch (PlanDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return planList;
        }

        /**
         * Wrapping the get planList method for JSP and  the other modules
         *
         * @param orgID ,parentID
         * @return the plan list according to the orgID ,parentID
         */
        public List getPlanList(int orgID, int parentID)
        {
                List planList = null;

                try
                {
                        PlanDAO planDao = PlanDAOFactory.getDAO();
                        planList = planDao.getPlanList(orgID, parentID);
                }
                catch (PlanDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                //planList = detectPlanDate(planList); //调用判断逾期
                return planList;
        }

        /**
         * @param planID
         */
        public List getPlanPath(int planID) throws PlanDAOSysException
        {
                List l;

                PlanDAO planDao = PlanDAOFactory.getDAO();
                l = planDao.getPlanPath(planID);

                return l;
        }

        /**
         * 检测计划是否过期（detdct plan whether expired）
         *
         * @param l 得到的计划
         * @return 整理后的计划
         */
        private List detectPlanDate(List l)
        {
                List list = null;

                if (null == l)
                {
                        return list;
                }

                PlanForm pf = null;
                list = new ArrayList();

                Date edate; //计划日期
                String endhour;
                String endtime;
                int islink = 0;
                PlanDAOImpl planImpl = new PlanDAOImpl();

                for (Iterator iter = l.iterator(); iter.hasNext();)
                {
                        pf = (PlanForm) iter.next();
                        edate = pf.getEstablishTime();
                        endhour = pf.getEndTimehour();
                        endtime = pf.getEndTimemin();
                        islink = pf.getIsHyperLink();

                        //判断是否目录或执行计划的复本
                        if ((0 != pf.getIsContent()) && (2 != pf.getIsContent()))
                        { //判断逾期

                                if (compareDate(edate, endhour, endtime, islink))
                                {
                                        pf.setIsHyperLink(LMSConstants.PLAN_BACK);
                                        planImpl.updatePlan(pf);
                                }
                        }

                        list.add(pf);
                }

                return list;
        }

        /**
         * 比较计划日期是否逾期(compare plan date)
         *
         * @param date
         * @return
         */
        private boolean compareDate(Date date, String ehour, String etime,
                                    int islink)
        {
                boolean boln = false;
                Date cdate = new Date();
                String cyear = DateTimeUtil.FormatDateToWeb1(cdate);
                String pyear = DateTimeUtil.FormatDateToWeb1(date);

                if (2 == islink)
                { //判断下月待办是否逾期

                        GregorianCalendar grc = new GregorianCalendar();
                        grc.setTime(date);
                        grc.add(GregorianCalendar.MONTH, 30);

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String pdate = new String(formatter.format(grc.getTime()));
                        Date pdt = formatter.parse(pdate, new ParsePosition(0));
                        Date cdt = formatter.parse(cyear, new ParsePosition(0));

                        if (1 == cdt.compareTo(pdt))
                        { //已逾期
                                boln = true;

                                return boln;
                        }

                        return false;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date pdt = formatter.parse(pyear, new ParsePosition(0));
                Date cdt = formatter.parse(cyear, new ParsePosition(0));

                if ((1 == cdt.compareTo(pdt)) && (0 == islink))
                { //判断逾期
                        boln = true;
                }
                else
                {
                        int chour = cdate.getHours();
                        int cminute = cdate.getMinutes();

                        if (chour > Integer.parseInt(ehour))
                        {
                                boln = true;
                        }
                        else if ((chour == Integer.parseInt(ehour)) &&
                                (cminute > Integer.parseInt(etime)))
                        {
                                boln = true;
                        }
                }

                return boln;
        }

        /**
         * Judge whether the id is duplicate
         *
         * @param list
         * @param id
         * @return true if the id is duplicated, false otherwise
         */
        public static boolean isDuplicate(List list, int id)
        {
                for (int i = 0; i < list.size(); i++)
                {
                        int dupID = ((Integer) list.get(i)).intValue();

                        if (dupID == id)
                        {
                                //slist.remove(list.get(i));
                                return true;
                        }
                }

                return false;
        }
}
