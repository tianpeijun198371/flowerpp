/** * UpdateGeneralConfigAction.java.
 * User: chenxj  Date: 2004-8-17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved. */
package com.ulearning.ulms.admin.sysconfig.action;

import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAO;
import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAOFactory;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateGeneralConfigAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                SysConfigForm sf = (SysConfigForm) form;

                String startDate = request.getParameter("date1");
                String endDate = request.getParameter("date2");
                Date date1 = null;
                Date date2 = null;

                if ((startDate != null) && !startDate.trim().equals(""))
                {
                        date1 = stringToDate(startDate);
                }

                if ((endDate != null) && !endDate.trim().equals(""))
                {
                        date2 = stringToDate(endDate);
                }

                SysConfigDAO dao = SysConfigDAOFactory.getDAO();

                sf.setDownloadCourseWareStartDate(date1);
                sf.setDownloadCourseWareEndDate(date2);

                if (sf.getIsAllowDownloadCourseWare().equals("1"))
                {
                        if (date1.after(date2))
                        {
                                request.setAttribute("myerror", "开始时间不能大于结束时间");

                                return mapping.findForward(resultScreen);
                        }
                }

                dao.updateGeneralConfig(sf);
                LogUtil.info("system",
                        "[AddSysConfigAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }

        private Date stringToDate(String string) throws Exception
        {
                //String str1 = "2004-01-10 14:32"; //这种开式的
                //String str2="Nov 22, 2004 3:20:29"; //这种也行
                String str1 = string;
                Calendar cal1 = null;

                if (string.indexOf("-") == -1)
                {
                        List stringDateList = StringUtil.split(str1, " ");
                        String mmName = (String) stringDateList.get(0);
                        int mm = 0;

                        if (mmName.equals("Jan"))
                        {
                                mm = 0;
                        }
                        else if (mmName.equals("Feb"))
                        {
                                mm = 1;
                        }
                        else if (mmName.equals("Mar"))
                        {
                                mm = 2;
                        }
                        else if (mmName.equals("Apr"))
                        {
                                mm = 3;
                        }
                        else if (mmName.equals("May"))
                        {
                                mm = 4;
                        }
                        else if (mmName.equals("Jun"))
                        {
                                mm = 5;
                        }
                        else if (mmName.equals("Jul"))
                        {
                                mm = 6;
                        }
                        else if (mmName.equals("Aug"))
                        {
                                mm = 7;
                        }
                        else if (mmName.equals("Sep"))
                        {
                                mm = 8;
                        }
                        else if (mmName.equals("Oct"))
                        {
                                mm = 9;
                        }
                        else if (mmName.equals("Nov"))
                        {
                                mm = 10;
                        }
                        else if (mmName.equals("Dec"))
                        {
                                mm = 11;
                        }

                        String dayStr = (String) stringDateList.get(1);
                        dayStr = dayStr.substring(0, dayStr.length() - 1);

                        String yearStr = (String) stringDateList.get(2);
                        List time = StringUtil.split((String) stringDateList.get(3), ":");
                        String hhStr = (String) time.get(0);
                        String mmStr = (String) time.get(1);
                        int day1 = Integer.parseInt(dayStr);
                        int year1 = Integer.parseInt(yearStr);
                        int hh1 = Integer.parseInt(hhStr);
                        int mm1 = Integer.parseInt(mmStr);

                        cal1 = new GregorianCalendar(year1, mm, day1, hh1, mm1);
                }
                else
                {
                        List stringDateList = StringUtil.split(str1, " ");
                        List stringDate = StringUtil.split((String) stringDateList.get(0),
                                "-");
                        List stringtime = StringUtil.split((String) stringDateList.get(1),
                                ":");

                        int year1 = Integer.parseInt((String) stringDate.get(0));
                        int month1 = Integer.parseInt((String) stringDate.get(1));
                        int day1 = Integer.parseInt((String) stringDate.get(2));
                        int hh1 = Integer.parseInt((String) stringtime.get(0));
                        int mm1 = Integer.parseInt((String) stringtime.get(1));
                        cal1 = new GregorianCalendar(year1, month1 - 1, day1, hh1, mm1);
                }

                return cal1.getTime();
        }
}
