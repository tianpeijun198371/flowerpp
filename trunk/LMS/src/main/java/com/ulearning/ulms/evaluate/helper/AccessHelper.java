/**
 * AccessHelper.java.
 * User: fengch Date: 2005-6-29 14:45:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.helper;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.model.EAccessModel;
import com.ulearning.ulms.evaluate.model.StayTimeModel;
import com.ulearning.ulms.evaluate.util.PerformanceKeys;
import com.ulearning.ulms.evaluate.util.PerformanceUtil;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLine3DRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.data.time.*;

import java.awt.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;


public class AccessHelper
{
        /**
         * 返回用户的最后一次登陆的时间.
         * todo:yud
         *
         * @return
         */
        public static Date getLastAccessDate(int userID)
                throws EvaluateManageSysException
        {
                String hql = " from EAccessModel where userID=" + userID;
                List list = null;
                Date dat = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                if (list.size() >= 2)
                {
                        dat = ((EAccessModel) list.get(list.size() - 2)).getAccessDate();
                }
                else if (list.size() == 1)
                {
                        dat = ((EAccessModel) list.get(list.size() - 1)).getAccessDate();
                }
                else if (list.size() == 0)
                {
                        dat = new Date();
                }

                return dat;
        }

        /**
         * 返回用户在平台的停留时间
         * todo:yud
         *
         * @return
         */
        public static long getStayTime(int userID)
                throws EvaluateManageSysException
        {
                String hql = " from StayTimeModel where userID = " + userID;
                List list = null;
                long dat = 0;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                if (list.size() > 0)
                {
                        dat = ((StayTimeModel) list.get(0)).getStayTime();
                }

                return dat;
        }

        /**
         * 返回用户登陆平台的次数.
         * todo:yud
         *
         * @return
         */
        public static long getAccessCounts(int userID)
                throws EvaluateManageSysException
        {
                String hql = " from EAccessModel where userID=" + userID;
                List list = null;
                long dat = 0;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                if (list != null)
                {
                        dat = list.size();
                }
                else
                {
                        dat = 0;
                }

                return dat;
        }

        /**
         * 返回平台被访问的次数.
         * todo:yud
         *
         * @return
         */
        public static Long getAllAccessCounts()
        {
                return null;
        }

        /**
         * 返回model list
         *
         * @param beginTime
         * @param endTime
         * @return
         */
        public List getEAccessModel(Date beginTime, Date endTime)
        {
                List list = new ArrayList();
                String sql = "select * from E_Access_Tab where ACCESSDATE>=? and ACCESSDATE< ? order by ACCESSDATE";
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setObject(1, new Timestamp(beginTime.getTime()));
                        pstmt.setObject(2, new Timestamp(endTime.getTime()));
                        rs = pstmt.executeQuery();

                        EAccessModel eAccess = null;

                        while (rs.next())
                        {
                                Date accessDate = rs.getTimestamp("accessDate");
                                eAccess = new EAccessModel();
                                eAccess.setAccessDate(accessDate);
                                list.add(eAccess);
                        }

                        return list;
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                rs.close();
                                pstmt.close();
                                conn.close();
                        }
                        catch (Exception ex)
                        {
                                ex.printStackTrace();
                        }
                }

                return null;
        }

        public JFreeChart chartByAccess(Date beginTime, Date endTime)
        {
                try
                {
                        TimeSeries timeseries = null;
                        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
                        //String index = getIndexByOnlineUserCount(beginTime, endTime);
                        timeseries = getSeriesByInlineUserCount("访问次数 ", beginTime, endTime);

                        if (timeseries != null)
                        {
                                timeseriescollection.addSeries(timeseries);
                                timeseriescollection.setDomainIsPointsInTime(true); //x轴上的刻度点代表的是时间点而不是时间段
                        }

                        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("用户访问数分析图",
                                "访问时间", "访问数", timeseriescollection, true, true, false);
                        XYPlot xyplot = jfreechart.getXYPlot();
                        xyplot.setRenderer(new XYLine3DRenderer());

                        //  NumberAxis numAxis = (NumberAxis)xyplot.getRangeAxis();
                        //numAxis.setAutoRange( true);
                        return jfreechart;
                }
                catch (Exception exception)
                {
                        exception.printStackTrace();
                }

                return null;
        }

        public TimeSeries getSeriesByInlineUserCount(String name, Date beginTime,
                                                     Date endTime)
        {
                try
                {
                        List list = getEAccessModel(beginTime, endTime);
                        TimeSeries timeseries = null;

                        int i = PerformanceUtil.getTimePeriod(beginTime, endTime);

                        switch (i)
                        {
                                case PerformanceKeys.TIMEPERIOD_MINUTE:
                                        timeseries = new TimeSeries(name, Minute.class);

                                        for (int j = 0; j < list.size(); j++)
                                        {
                                                EAccessModel eAccessModel = (EAccessModel) list.get(j);
                                                int m = j + 1;
                                                timeseries.addOrUpdate(new Minute(
                                                        eAccessModel.getAccessDate()), m);
                                        }

                                        break;

                                case PerformanceKeys.TIMEPERIOD_HOURH:
                                        timeseries = new TimeSeries(name, Hour.class);

                                        for (int j = 0; j < list.size(); j++)
                                        {
                                                EAccessModel eAccessModel = (EAccessModel) list.get(j);
                                                int m = j + 1;
                                                timeseries.addOrUpdate(new Hour(
                                                        eAccessModel.getAccessDate()), m);
                                        }

                                        break;

                                case PerformanceKeys.TIMEPERIOD_DAY:
                                        timeseries = new TimeSeries(name, Day.class);

                                        for (int j = 0; j < list.size(); j++)
                                        {
                                                EAccessModel eAccessModel = (EAccessModel) list.get(j);
                                                int m = j + 1;
                                                timeseries.addOrUpdate(new Day(eAccessModel.getAccessDate()),
                                                        m);
                                        }

                                        break;

                                case PerformanceKeys.TIMEPERIOD_WEEK:
                                        timeseries = new TimeSeries(name, Week.class);

                                        for (int j = 0; j < list.size(); j++)
                                        {
                                                EAccessModel eAccessModel = (EAccessModel) list.get(j);
                                                int m = j + 1;
                                                timeseries.addOrUpdate(new Week(
                                                        eAccessModel.getAccessDate()), m);
                                        }

                                        break;

                                case PerformanceKeys.TIMEPERIOD_MONTH:
                                        timeseries = new TimeSeries(name, Month.class);

                                        for (int j = 0; j < list.size(); j++)
                                        {
                                                EAccessModel eAccessModel = (EAccessModel) list.get(j);
                                                int m = j + 1;
                                                timeseries.addOrUpdate(new Month(
                                                        eAccessModel.getAccessDate()), m);
                                        }

                                        break;
                        }

                        return timeseries;
                }
                catch (Exception exception)
                {
                        //  logger.error("getHitUserCountIndex", exception);
                        exception.printStackTrace();
                }

                return null;
        }

        /**
         public  String getIndexByOnlineUserCount(Date beginTime, Date endTime) {
         //logger.info("getHitUserCountIndex - start");
         try {
         List list = getHibernateTemplate().find("select max(his_onlineuser_count),min(his_onlineuser_count),avg(his_onlineuser_count)  " +
         "from PerformancePO  where collect_time>=? and collect_time<=?", new Object[]{
         beginTime, endTime
         });
         Object objects[] = (Object[]) list.get(0);
         int max = 0;
         if (objects[0] != null) {
         max = ((Integer) objects[0]).intValue();
         }
         int min = 0;
         if (objects[1] != null) {
         min = ((Integer) objects[1]).intValue();
         }
         int avg = 0;
         if (objects[2] != null) {
         avg = ((Double) objects[2]).intValue();
         }
         int current = (max + min) / 2;
         String result = "  max:" + max + "  min:" + min + "  avg:" + avg + "  current:" + current;
         // logger.info("result=" + result);
         // logger.info("getHitUserCountIndex - end");
         return result;
         }
         catch (Exception exception) {
         //logger.error("getHitUserCount", exception);
         exception.printStackTrace();
         return "";
         }
         }  */
}
