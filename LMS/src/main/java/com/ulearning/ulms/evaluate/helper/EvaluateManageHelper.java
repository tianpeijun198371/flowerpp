/**
 * EvaluateManageHelper.java.
 * User: yud Date: 2005-06-14 15:15:51
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.helper;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAO;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAOImpl;
import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.form.*;
import com.ulearning.ulms.evaluate.model.*;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.Date;
import java.util.List;


public class EvaluateManageHelper
{
        public static EvaluateManageDAO dao;

        static
        {
                try
                {
                        dao = new EvaluateManageDAOImpl();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * add a Access.
         *
         * @param eAccessform
         * @throws EvaluateManageSysException
         */
        public static void addAccess(EAccessForm eAccessform)
                throws EvaluateManageSysException
        {
                EAccessModel accessmodel = eAccessform.getAccessModel();
                accessmodel.setAccessDate(DateTimeUtil.parseDateTime(
                        DateTimeUtil.FormatDateToWeb3(new Date())));
                dao.addAccess(accessmodel);
        }

        /**
         * set user's staytime
         *
         * @param stayTimeForm
         */
        public static void setStayTime(StayTimeForm stayTimeForm)
                throws EvaluateManageSysException
        {
                StayTimeModel stayTimeModel = stayTimeForm.getstayTimeModel();
                List list = dao.getIsHaveUser(stayTimeModel.getUserID());

                if (list.size() == 0)
                {
                        dao.addStayTime(stayTimeModel);
                }
                else
                {
                        dao.updateStayTime(stayTimeModel);
                }
        }

        /**
         * get rank point
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public static List getRankPoint() throws EvaluateManageSysException
        {
                List list = dao.getRankPoint();

                return list;
        }

        /**
         * update RankStandard of user
         *
         * @param em
         * @throws EvaluateManageSysException
         */
        public static void updateERecordRankStandard(ERecordRankStandardModel em)
                throws EvaluateManageSysException
        {
                dao.updateERecordRankStandard(em);
        }

        /**
         * get list of point conversion
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public static List getPointConversion() throws EvaluateManageSysException
        {
                List list = dao.getPointConversion();

                return list;
        }

        /**
         * update PointConversion
         *
         * @param em
         * @throws EvaluateManageSysException
         */
        public static void updateERecordPointConversion(
                ERecordPointConversionModel em) throws EvaluateManageSysException
        {
                dao.updateERecordPointConversion(em);
        }

        /**
         * set user's point
         *
         * @param eRecordForm
         */
        public static void setPointERecord(ERecordForm eRecordForm)
                throws EvaluateManageSysException
        {
                ERecordModel eRecordModel = eRecordForm.geteRecordModel();
                List list = dao.isThisUserInERecord(eRecordModel.getUserID());

                if (list.size() == 0)
                {
                        dao.addERecord(eRecordModel);
                }
                else
                {
                        eRecordModel.seteRecordID(((ERecordModel) list.get(0)).geteRecordID());
                        dao.updateERecord(eRecordModel);
                }
        }
}
