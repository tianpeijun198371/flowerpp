/**
 * RResuserecordHelper.java.
 * User: liz  Date: 2006-2-20
 * 资源使用辅助类
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.helper;

import com.ulearning.ulms.content.dao.RResuserecordDAO;
import com.ulearning.ulms.content.dao.RResuserecordDAOFactory;
import com.ulearning.ulms.content.form.PhysicsResForm;
import com.ulearning.ulms.content.form.RResuserecordForm;
import com.ulearning.ulms.content.form.ResPlanListForm;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.util.DateTimeUtil;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.*;


public class RResuserecordHelper
{
        /**
         * 添加新资源使用记录
         *
         * @param frm RResuserecordForm
         * @return 0:失败　　1：成功
         * @throws ULMSException
         */
        public static int insertResUsed(RResuserecordForm frm)
                throws ULMSException
        {
                int reuslt = 0;
                RResuserecordDAO dao = RResuserecordDAOFactory.getDAO();
                dao.addResUsed(frm.makeModel());

                return reuslt;
        }

        /**
         * 更新资源
         *
         * @param frm RResuserecordForm
         */
        public static void updateResUsed(RResuserecordForm frm)
                throws ULMSException
        {
                RResuserecordDAO dao = RResuserecordDAOFactory.getDAO();
                dao.updateResUsed(frm.makeModel());
        }

        /**
         * 删除资源使用记录，只是作删除标记
         *
         * @param reusedId
         */
        public static void delResUsed(String reusedId) throws ULMSException
        {
                RResuserecordDAO dao = RResuserecordDAOFactory.getDAO();
                dao.removeResUsed(Integer.parseInt(reusedId));
        }

        /**
         * 查询资源使用记录
         *
         * @param id
         * @param resid         资源ID
         * @param resname       资源名称
         * @param userid        预定人ID
         * @param username      预定人名称
         * @param date          预定日期
         * @param bhour         预定开始时
         * @param bminute       预定开始分
         * @param ehour         预定结束时
         * @param eminute       预定结束分
         * @param audittag      审核标记　0：新预定  1：审核通过   2：审核不通过
         * @param audituserid   审核人ID
         * @param auditusername 审核人名称
         * @param delmak        删除标记　0：没有删除  1：已经删除
         * @return
         */
        public static List getResUsedData(String id, String resid, String resname,
                                          String userid, String username, String date, String bhour,
                                          String bminute, String ehour, String eminute, String audittag,
                                          String audituserid, String auditusername, String delmak,
                                          int firstResult, int maxResults)
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                String bdate = null;
                String edate = null;
                hql.append(" from RResuserecordModel where 1>0");

                if ((null != id) && !id.equals(""))
                {
                        hql.append(" and resuseid=");
                        hql.append(id);
                }

                if ((null != resid) && !resid.equals(""))
                {
                        hql.append(" and resid=");
                        hql.append(resid);
                }

                if ((null != resname) && !resname.equals(""))
                {
                        hql.append(" and resname'%");
                        hql.append(resname);
                        hql.append("%'");
                }

                if ((null != userid) && !userid.equals(""))
                {
                        hql.append(" and userid=");
                        hql.append(userid);
                }

                if ((null != username) && !username.equals(""))
                {
                        hql.append(" and username='%");
                        hql.append(username);
                }

                if (null != date)
                {
                        //hql.append(" and userdate=:startDateTime");
                        bdate = makeDate(date, bhour, bminute);

                        if (null != bdate)
                        {
                                hql.append(" and userbegindate>=:startDateTime");
                        }

                        edate = makeDate(date, ehour, eminute);

                        if (null != edate)
                        {
                                hql.append(" and userenddate<=:endDateTime");
                        }
                }

                if ((null != audittag) && !audittag.equals(""))
                {
                        hql.append(" and audittag=");
                        hql.append(audittag);
                }

                if ((null != audituserid) && !audituserid.equals(""))
                {
                        hql.append(" and audituserid=");
                        hql.append(audituserid);
                }

                if ((null != auditusername) && !auditusername.equals(""))
                {
                        hql.append(" and auditusername='");
                        hql.append(auditusername);
                        hql.append("'");
                }

                if ((null != delmak) && !delmak.equals(""))
                {
                        hql.append(" and userdel=");
                        hql.append(delmak);
                }

                RResuserecordDAO dao = RResuserecordDAOFactory.getDAO();

                list = dao.getResUsedData(hql.toString(),
                        DateTimeUtil.parseDateTime(bdate),
                        DateTimeUtil.parseDateTime(edate), firstResult, maxResults);

                return list;
        }

        /**
         * 合成yyyy-MM-dd HH:mm:ss
         *
         * @param day
         * @param hour
         * @param minute
         * @return
         */
        private static String makeDate(String day, String hour, String minute)
        {
                StringBuffer sDate = null;

                if ((null == hour) || hour.equals(""))
                {
                        return sDate.toString();
                }

                sDate = new StringBuffer();
                sDate.append(day);
                sDate.append(" ");
                sDate.append(hour);
                sDate.append(":");
                sDate.append(minute);
                sDate.append(":00");

                return sDate.toString();
        }

        /**
         * 根据ID和删除标记取设施使用记录
         *
         * @param id
         * @param delmak 删除标记　0：没有删除  1：已经删除
         * @return RResuserecordForm
         */
        public static List getAllById(String id, String delmak)
        {
                List list = null;

                list = getResUsedData(id, null, null, null, null, null, null, null,
                        null, null, null, null, null, delmak, -1, -1);

                return list;
        }

        /**
         * 取当前日期时间以后的安排
         *
         * @param resid     设施ID
         * @param date      日
         * @param beghour   时
         * @param begminute 分
         * @return RResuserecordForm
         */
        public static List getresPlan(String resid, String date, String beghour,
                                      String begminute, String endhour, String endminute)
        {
                List list = null;
                list = getResUsedData(null, resid, null, null, null, date, beghour,
                        begminute, endhour, endminute, null, null, null, "0", -1, -1);

                return list;
        }

        /**
         * 取当前日期后的安排
         *
         * @param day String(yyyy-mm-dd) 给定一日期，列出该日期所在的周数据
         * @return ResPlanListForm
         */
        public static List getresUsedInfo(String day)
        {
                List listresult = null;
                List listRes = PhysicsResHelper.getResAllUsed("0", "1"); //从设施中取可用的教室资源

                if ((null == listRes) || (0 == listRes.size()))
                {
                        return listresult;
                }

                List listResUsed = null;
                ResPlanListForm planfrm = null;

                for (Iterator iter = listRes.iterator(); iter.hasNext();)
                {
                        listresult = new ArrayList();
                        planfrm = new ResPlanListForm();

                        String sresId = Integer.toString(((PhysicsResForm) iter.next()).getResid());
                        planfrm.setResid(sresId);

                        for (int i = 1; i < 8; i++) //取一周的预定数据
                        {
                                listResUsed = getweekdate(day, i, sresId); //调用
                                //建立一周的数据

                                if (1 == i)
                                {
                                        planfrm.setSunday(listResUsed);
                                }
                                else if (2 == i)
                                {
                                        planfrm.setMonday(listResUsed);
                                }
                                else if (3 == i)
                                {
                                        planfrm.setTuesday(listResUsed);
                                }
                                else if (4 == i)
                                {
                                        planfrm.setWednesday(listResUsed);
                                }
                                else if (5 == i)
                                {
                                        planfrm.setThursday(listResUsed);
                                }
                                else if (6 == i)
                                {
                                        planfrm.setFriday(listResUsed);
                                }
                                else if (7 == i)
                                {
                                        planfrm.setSaturday(listResUsed);
                                }
                        }

                        listresult.add(planfrm);
                }

                return listresult;
        }

        /**
         * cday给定的日期
         *
         * @param week
         * @param resid
         * @return
         */
        private static List getweekdate(String cday, int week, String resid)
        {
                List list = null;

                //String strDate = DateTimeUtil.FormatDateToWeb1(new Date());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.parse(cday, new ParsePosition(0));

                Calendar calendar = dateFormat.getCalendar();
                int day = calendar.get(java.util.Calendar.DAY_OF_WEEK);
                calendar.add(java.util.Calendar.DATE, week - day);
                list = getresPlan(resid, dateFormat.format(calendar.getTime()), "00",
                        "00", "24", "00"); //调用

                return list;
        }
}
