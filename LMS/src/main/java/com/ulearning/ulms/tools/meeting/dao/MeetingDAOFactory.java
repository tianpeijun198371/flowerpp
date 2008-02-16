/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-12 13:13:24
 */


package com.ulearning.ulms.tools.meeting.dao;

import com.ulearning.ulms.tools.meeting.exception.MeetingSysException;

public class MeetingDAOFactory
{
        public static MeetingDAO getDAO() throws MeetingSysException
        {
                MeetingDAO dao = null;

                try
                {
                        dao = new MeetingDAOImpl();
                }
                catch (Exception se)
                {
                        throw new MeetingSysException(
                                "CourseContentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
