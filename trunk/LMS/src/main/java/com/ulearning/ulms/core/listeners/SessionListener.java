/**
 * SessionListener.java.
 * User: fengch Date: 2005-6-20 17:47:35
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.listeners;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.Timer;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAO;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAOImpl;
import com.ulearning.ulms.evaluate.form.ERecordForm;
import com.ulearning.ulms.evaluate.form.StayTimeForm;
import com.ulearning.ulms.evaluate.helper.EvaluateManageHelper;
import com.ulearning.ulms.evaluate.model.ERecordModel;
import com.ulearning.ulms.evaluate.model.ERecordPointConversionModel;
import com.ulearning.ulms.evaluate.model.StayTimeModel;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener
{
        //当前活动的session
        private static int activeSessions = 0;

        //用户session的计时器
        private Timer timer = null;

        public void sessionCreated(HttpSessionEvent event)
        {
                HttpSession session = event.getSession();
                String str = (String) session.getAttribute(LMSConstants.USERID_KEY);
                LogUtil.debug("system",
                        "[SessionListener]sessionCreated---UserID_str=" + str);
                timer = new Timer();
                activeSessions++;
                LogUtil.debug("system",
                        "[SessionListener]sessionCreated---activeSessions =" +
                                activeSessions);
        }

        public void sessionDestroyed(HttpSessionEvent event)
        {
                System.out.println("[SessionListener]sessionDestroyed---event=" +
                        event);

                HttpSession session = event.getSession();
                System.out.println("[SessionListener]sessionDestroyed---session=" +
                        session);

                String str = (String) session.getAttribute(LMSConstants.USERID_KEY);
                LogUtil.debug("system",
                        "[SessionListener]sessionDestroyed---UserID_str=" + str);

                int userID = StringUtil.parseInt(str);
                LogUtil.debug("system",
                        "[SessionListener]sessionDestroyed---timer=" + timer);

                if (timer == null)
                {
                        return;
                }

                long totalTime = timer.getTotal();
                System.out.println("[SessionListener]sessionDestroyed---totalTime=" +
                        totalTime);
                System.out.println(
                        "[SessionListener]sessionDestroyed---totalTimeDescription=" +
                                DateTimeUtil.getDateTimeDescription(totalTime));

                if (activeSessions > 0)
                {
                        activeSessions--;
                }

                //set staytime about this userID,add by yud
                StayTimeForm stayTimeForm = new StayTimeForm();
                EvaluateManageDAO dao = new EvaluateManageDAOImpl();

                List list = dao.getIsHaveUser(userID);
                int modulID = StringUtil.parseInt((String) session.getAttribute(
                        LMSConstants.USER_ASPID_KEY));

                if (userID > 0)
                {
                        stayTimeForm.setType("0");
                        stayTimeForm.setUserID(userID);
                        stayTimeForm.setModuleID(modulID);
                        stayTimeForm.setParentID(0);
                        stayTimeForm.setCreateDate(new Date());

                        if (list.size() == 0)
                        {
                                stayTimeForm.setStayTime(totalTime);
                                stayTimeForm.setLastUpdateDate(new Date());
                        }
                        else
                        {
                                stayTimeForm.setStayTimeID(((StayTimeModel) list.get(0)).getStayTimeID());

                                long lastStayTime = ((StayTimeModel) list.get(0)).getStayTime();
                                stayTimeForm.setStayTime(totalTime + lastStayTime);
                                stayTimeForm.setLastUpdateDate(((StayTimeModel) list.get(0)).getLastUpdateDate());
                        }

                        EvaluateManageHelper.setStayTime(stayTimeForm);

                        //set point
                        List l = dao.getPointConversion();
                        int initialPoint = ((ERecordPointConversionModel) l.get(1)).getPoint(); //数据库中已经初始化的值

                        int thisPoint = (int) (initialPoint * (totalTime / (60 * 60 * 1000)));

                        List ll = dao.isThisUserInERecord(userID);
                        int oldPoint = ((ERecordModel) ll.get(0)).getPoint();

                        ERecordForm eRecordForm = new ERecordForm();
                        eRecordForm.setUserID(userID);
                        eRecordForm.setPoint(oldPoint + thisPoint);

                        EvaluateManageHelper.setPointERecord(eRecordForm);
                }

                System.out.println(
                        "[SessionListener]sessionDestroyed---activeSessions =" +
                                activeSessions);
        }
}
