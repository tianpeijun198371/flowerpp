/**
 * EvaluateManageHelper.java.
 * User: yud Date: 2005-06-29 13:15:51
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.helper;

import com.ulearning.ulms.evaluate.dao.FeedBackDAO;
import com.ulearning.ulms.evaluate.dao.FeedBackDAOImpl;
import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.form.*;
import com.ulearning.ulms.evaluate.model.*;

import java.util.Date;
import java.util.List;


public class FeedBackHelper
{
        public static FeedBackDAO dao;

        static
        {
                try
                {
                        dao = new FeedBackDAOImpl();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public static void addFeedBack(EFeedBackForm eFeedBackForm)
                throws EvaluateManageSysException
        {
                FeedBackModel feedBackModel = eFeedBackForm.geteFeedBackModel();
                feedBackModel.setCreateDate(new Date());
                dao.insert(feedBackModel);
        }

        public static List getCourseCheck() throws EvaluateManageSysException
        {
                List list = dao.getCourseCheck();

                return list;
        }

        public static List getCheck(int orgID) throws EvaluateManageSysException
        {
                List list = dao.getCheck(orgID);

                return list;
        }

        public static List getCourseFeedBackByUser(int userID)
                throws EvaluateManageSysException
        {
                List list = dao.getCourseFeedBackByUser(userID);

                return list;
        }

        public static List getByOrgAndScore(int orgID, int score)
                throws EvaluateManageSysException
        {
                List list = dao.getByOrgAndScore(orgID, score);

                return list;
        }

        public static List getFeedBackByRelationID()
                throws EvaluateManageSysException
        {
                List list = dao.getFeedBackByRelationID();

                return list;
        }

        public static void addFeedBackOption(FeedBackOptionForm feedBackOptionForm)
                throws EvaluateManageSysException
        {
                FeedBackOptionModel feedBackOptionModel = feedBackOptionForm.getfeedBackOptionModel();
                feedBackOptionModel.setCreateDate(new Date());
                dao.addFeedBackOption(feedBackOptionModel);
        }

        public static void updateFeedBackOption(
                FeedBackOptionForm feedBackOptionForm)
                throws EvaluateManageSysException
        {
                FeedBackOptionModel feedBackOptionModel = feedBackOptionForm.getfeedBackOptionModel();
                dao.updateFeedBackOption(feedBackOptionModel);
        }

        public static List getByOrgID(int orgID) throws EvaluateManageSysException
        {
                List list = dao.getByOrgID(orgID);

                return list;
        }
}
