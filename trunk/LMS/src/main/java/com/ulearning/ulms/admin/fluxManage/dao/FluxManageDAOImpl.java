/**
 * FluxManageDAoImpl.java.
 * User: fengch  Date: 2004-6-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.dao;

import com.ulearning.ulms.admin.fluxManage.exceptions.FluxManageSysException;
import com.ulearning.ulms.admin.fluxManage.model.FluxModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.I18Util;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;


public class FluxManageDAOImpl implements FluxManageDAO
{
        /**
         * 插入流量记录.<br>
         *
         * @param fm
         * @throws FluxManageSysException
         */
        public void insert(FluxModel fm) throws FluxManageSysException
        {
                try
                {
                        HibernateDAO.add(fm);
                }
                catch (ULMSSysException e)
                {
                        throw new FluxManageSysException(e);
                }
        }

        /**
         * 按年返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByYear(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                List fluxList = new ArrayList();

                Calendar calStart = Calendar.getInstance();
                calStart.setTime(startDate);

                Calendar calEnd = Calendar.getInstance();
                calEnd.setTime(endDate);

                String sql = null;

                if (aspID == -1)
                {
                        sql = " select sum(Flux)  as total from  T_Flux_Tab where  " +
                                " DATEPART(year, createdate)= ?";

                        /*sql = " select sum(Flux)  as total from  T_Flux_Tab where  " +
                   " YEAR(createdate)= ?";   */
                }
                else
                {
                        sql = " select sum(Flux)  as total from  T_Flux_Tab where OrgID= " +
                                aspID + " and DATEPART(year, createdate)= ?";

                        /*sql = " select sum(Flux)  as total from  T_Flux_Tab where OrgID= " + aspID +
                   " and YEAR(createdate)= ?";  */
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByYear()]--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByYear()]--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                try
                {
                        conn = DBUtil.getConnection();
                        ps = conn.prepareStatement(sql);

                        while (calStart.get(Calendar.YEAR) <= calEnd.get(Calendar.YEAR))
                        {
                                ps.setInt(1, calStart.get(Calendar.YEAR));
                                rs = ps.executeQuery();

                                while (rs.next())
                                {
                                        FluxModel fluxModel = new FluxModel();
                                        fluxModel.setFlux(rs.getLong("total"));
                                        fluxModel.setDescription("" + calStart.get(Calendar.YEAR));
                                        fluxList.add(fluxModel);
                                }

                                calStart.add(Calendar.YEAR, 1);
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return fluxList;
        }

        /**
         * 按月返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByMonth(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                List fluxList = new ArrayList();

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                Calendar calStart = Calendar.getInstance();
                calStart.setTime(startDate);

                Calendar calEnd = Calendar.getInstance();
                calEnd.setTime(endDate);

                String sql = null;

                if (aspID == -1)
                {
                        sql = " select sum(flux)  as total from  T_Flux_Tab where " +
                                "  CreateDate>=? and CreateDate <= ? and  DATEPART(month, createdate)= ? ";
                }
                else
                {
                        sql = " select sum(flux)  as total from  T_Flux_Tab where OrgID= " +
                                aspID +
                                " and  CreateDate>=? and CreateDate <= ? and  DATEPART(month, createdate)= ? ";
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByMonth()]--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByMonth()]--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                try
                {
                        conn = DBUtil.getConnection();
                        ps = conn.prepareStatement(sql);

                        for (int i = 1; i < 13; i++)
                        {
                                ps.setDate(1, new java.sql.Date(startDate.getTime()));
                                ps.setDate(2, new java.sql.Date(endDate.getTime()));
                                ps.setInt(3, (i));
                                rs = ps.executeQuery();

                                if (rs.next())
                                {
                                        FluxModel fluxModel = new FluxModel();
                                        fluxModel.setFlux(rs.getLong("total"));
                                        fluxModel.setDescription(String.valueOf(i));
                                        fluxList.add(fluxModel);
                                }
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return fluxList;
        }

        /**
         * 按星期返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByWeek(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                List fluxList = new ArrayList();

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                Calendar calStart = Calendar.getInstance();
                calStart.setTime(startDate);

                Calendar calEnd = Calendar.getInstance();
                calEnd.setTime(endDate);

                String sql = null;

                if (aspID == -1)
                {
                        sql = " select sum(Flux)  as total from  T_Flux_Tab where " +
                                " CreateDate>=? and CreateDate <= ?  " +
                                " and DATEPART(weekday , createdate)= ? ";
                }
                else
                {
                        sql = " select sum(Flux)  as total from  T_Flux_Tab where OrgID= " +
                                aspID + " and CreateDate>=? and CreateDate <= ?  " +
                                " and DATEPART(weekday , createdate)= ? ";
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByWeek()]--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByWeek()]--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                try
                {
                        conn = DBUtil.getConnection();
                        ps = conn.prepareStatement(sql);
                        ps.setDate(1, new java.sql.Date(startDate.getTime()));
                        ps.setDate(2, new java.sql.Date(endDate.getTime()));

                        for (int i = 1; i < 8; i++)
                        {
                                ps.setInt(3, i);
                                rs = ps.executeQuery();

                                while (rs.next())
                                {
                                        FluxModel fluxModel = new FluxModel();
                                        fluxModel.setFlux(rs.getLong("total"));
                                        fluxModel.setDescription("" + i);
                                        fluxList.add(fluxModel);
                                }
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return fluxList;
        }

        /**
         * 按天返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByTheDay(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                List fluxList = new ArrayList();

                Calendar calStart = Calendar.getInstance();
                calStart.setTime(startDate);

                Calendar calEnd = Calendar.getInstance();
                calEnd.setTime(endDate);

                String sql = null;

                if (aspID == -1)
                {
                        sql = " select sum(flux)  as total from  T_Flux_Tab where " +
                                "   DATEPART(year, createdate)= ? and DATEPART(month, createdate)= ?  and DATEPART(day, createdate)= ?";
                }
                else
                {
                        sql = " select sum(flux)  as total from  T_Flux_Tab where OrgID= " +
                                aspID +
                                "   and   DATEPART(year, createdate)= ? and DATEPART(month, createdate)= ?  and DATEPART(day, createdate)= ?";
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByDay()]--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByDay()]--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));
                LogUtil.info("system", "[FluxManageDAOImpl:getByDay()]--sql=" + sql);

                try
                {
                        conn = DBUtil.getConnection();
                        ps = conn.prepareStatement(sql);

                        while (!calStart.after(calEnd))
                        {
                                ps.setInt(1, calStart.get(Calendar.YEAR));
                                ps.setInt(2, calStart.get(Calendar.MONTH) + 1);
                                ps.setInt(3, calStart.get(Calendar.DAY_OF_MONTH));
                                rs = ps.executeQuery();

                                if (rs.next())
                                {
                                        FluxModel fluxModel = new FluxModel();
                                        fluxModel.setFlux(rs.getLong("total"));
                                        fluxModel.setDescription(DateTimeUtil.FormatDateToWeb1(
                                                calStart.getTime()));
                                        fluxList.add(fluxModel);
                                }

                                calStart.add(Calendar.DAY_OF_MONTH, 1);
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return fluxList;
        }

        /**
         * 按每月的天返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByDay(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                List fluxList = new ArrayList();

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                Calendar calStart = Calendar.getInstance();
                calStart.setTime(startDate);

                Calendar calEnd = Calendar.getInstance();
                calEnd.setTime(endDate);

                String sql = null;

                if (aspID == -1)
                {
                        sql = " select sum(flux)  as total from  T_Flux_Tab where " +
                                "   DATEPART(day, createdate)= ? " +
                                "and CreateDate>=? and CreateDate <= ?";
                }
                else
                {
                        sql = " select sum(flux)  as total from  T_Flux_Tab where OrgID= " +
                                aspID + "   and    DATEPART(day, createdate)= ? " +
                                "and CreateDate>=? and CreateDate <= ?";
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByDay()]--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByDay()]--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));
                LogUtil.info("system", "[FluxManageDAOImpl:getByDay()]--sql=" + sql);

                try
                {
                        conn = DBUtil.getConnection();
                        ps = conn.prepareStatement(sql);

                        for (int i = 1; i < 32; i++)
                        {
                                ps.setInt(1, i);
                                ps.setDate(2, new java.sql.Date(startDate.getTime()));
                                ps.setDate(3, new java.sql.Date(endDate.getTime()));
                                rs = ps.executeQuery();

                                if (rs.next())
                                {
                                        FluxModel fluxModel = new FluxModel();
                                        fluxModel.setFlux(rs.getLong("total"));
                                        fluxModel.setDescription(String.valueOf(i));
                                        fluxList.add(fluxModel);
                                }

                                calStart.add(Calendar.DAY_OF_MONTH, 1);
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return fluxList;
        }

        /**
         * 按小时返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByHour(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                List fluxList = new ArrayList();

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                String sql = null;

                if (aspID == -1)
                {
                        sql = " select sum(Flux)  as total from  T_Flux_Tab where " +
                                "  CreateDate>=? and CreateDate <= ? and DATEPART(hour, createdate)= ?";
                }
                else
                {
                        sql = " select sum(Flux)  as total from  T_Flux_Tab where OrgID= " +
                                aspID +
                                " and CreateDate>=? and CreateDate <= ? and DATEPART(hour, createdate)= ?";
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByHour()]--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl:getByHour()]--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                try
                {
                        conn = DBUtil.getConnection();
                        ps = conn.prepareStatement(sql);
                        ps.setDate(1, new java.sql.Date(startDate.getTime()));
                        ps.setDate(2, new java.sql.Date(endDate.getTime()));

                        for (int i = 0; i < 24; i++)
                        {
                                ps.setInt(3, i);
                                rs = ps.executeQuery();

                                if (rs.next())
                                {
                                        FluxModel fluxModel = new FluxModel();
                                        fluxModel.setFlux(rs.getLong("total"));
                                        fluxModel.setDescription("" + i);
                                        ;
                                        fluxList.add(fluxModel);
                                }
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return fluxList;
        }

        /**
         * 按用户返回流量记录列表.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByUser(String key, int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                LogUtil.info("system",
                        "[FluxManageDAOImpl]getByUser--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                List list = new ArrayList();
                Session session = null;

                String hql = null;
                String tmp = "";

                if (aspID != -1)
                {
                        tmp = " and orgid = " + aspID;
                }

                if (key == null)
                {
                        hql = "from FluxModel where  " + "  CreateDate >= :startDate" +
                                " and CreateDate <= :endDate" + tmp;
                }
                else
                {
                        hql = "from FluxModel where  " + "  (clientLoginName like '%" +
                                key + "%' " + " or clientUserName like '%" + key + "%')" +
                                " and CreateDate >= :startDate" +
                                " and CreateDate <= :endDate" + tmp;
                }

                LogUtil.info("system",
                        "[FluxManageDAOImpl]getByUser--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("system",
                        "[FluxManageDAOImpl]getByUser--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));
                LogUtil.info("system", "[FluxManageDAOImpl]getByUser--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);

                        query.setParameter("startDate", startDate);
                        query.setParameter("endDate", endDate);
                        list = query.list();
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }
}
