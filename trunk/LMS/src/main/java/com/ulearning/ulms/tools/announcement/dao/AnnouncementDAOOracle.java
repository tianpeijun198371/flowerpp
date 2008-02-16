/**
 * AnnouncementDAOOracle.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.announcement.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementAppException;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.tools.announcement.model.Announcement;
import com.ulearning.ulms.tools.announcement.model.AnnouncementList;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class AnnouncementDAOOracle extends AnnouncementDAOImpl
{
        /**
         * publish a new announcement
         *
         * @param announcement the new announcement
         * @throws AnnouncementSysException If an lmsSys error has occurred.
         */

        /*
 public void insert(Announcement announcement)
          throws AnnouncementSysException
 {
         int relationID = announcement.getRelationID();
         int userID = announcement.getUserID();
         int totalCountInserted;
         String type ="'"+announcement.getType()+"'";
         String announcementID = "AnnouncementID.nextval";
         String subject = "'"+announcement.getSubject()+"'";
         java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
         java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
         String createDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
         String modifyDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
         String isPermanent=announcement.getIsPermanent();
         String isPermanent_str="'"+isPermanent+"'";
         //may be null value
         String message = announcement.getMessage();
         String sql_str;
         String userName=announcement.getUserName();
         userName="'"+userName+"'";
         String textType=""+announcement.getTextType();
         textType="'"+textType+"'";
         String beginDate=announcement.getBeginDate();
         if   (beginDate==null)
         {
                 beginDate="''";
         }
         else
         {
                 beginDate="to_date('" + beginDate + "','yyyy-mm-dd')";
         }
         String endDate=announcement.getEndDate();
         if   (endDate==null)
         {
                 endDate="''";
         }
         else
         {
                 endDate="to_date('" + endDate + "','yyyy-mm-dd')";
         }
         String link=null;
         link=announcement.getLink();
         if(link!=null)
                 link="'"+link.trim()+"'";
         else
                 link="''";
         if(message==null)
         {
                 message=null;
         }
         else
         {
                 message="'"+message+"'";
         }
         {
         }
         try
         {
                 //prepare the sql string to insert
                 sql_str="insert into T_Announcement_TAB(AnnouncementID,relationID,"
                         + " userID,userName,subject,Message,CreateDate,"
                         + " ModifyDate,isPermanent,DisPlayBeginDate,"
                         + " DisPlayEndDate,Type,textType,link) values ("
                         + announcementID+","+relationID+","
                         + userID+","+userName+","+subject+","+message+","
                         + createDate+","+  modifyDate+","+isPermanent_str +","
                         + beginDate+","+endDate+"," +type+","+textType+","+link+")";
                 LogUtil.debug("system", "[AnnouncementDAOOracle] "+sql_str);
                 LogUtil.debug("system", "[AnnouncementDAOOracle] "+"一共插入 "+totalCountInserted+"行");
         }
         catch (ULMSSysException se)
         {
                 se.printStackTrace();
                 throw new AnnouncementSysException(se);
         }
 }
 public static void main(String[] args)
 {
         System.out.println("Hello World!");
         java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
         String createDate  = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
         System.out.println(dayToInsert);
         java.sql.Time dayToInsert1 = new java.sql.Time(System.currentTimeMillis());
         System.out.println(dayToInsert1);
 }       */
}
