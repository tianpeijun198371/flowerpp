/**
 * EvaluateManageWebImpl.java.
 * User: yud  Date: 2005-06-17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.webimpls;

import com.ulearning.ulms.evaluate.dao.EvaluateManageDAO;
import com.ulearning.ulms.evaluate.dao.EvaluateManageDAOFactory;
import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.model.ERecordModel;
import com.ulearning.ulms.evaluate.model.ERecordRankStandardModel;

import java.util.List;


public class EvaluateManageWebImpl
{
        private EvaluateManageDAO dao;

        public EvaluateManageWebImpl() throws EvaluateManageSysException
        {
                try
                {
                        dao = EvaluateManageDAOFactory.getDAO();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * get list by moduleID
         *
         * @param orgID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getByModuleID(int orgID) throws EvaluateManageSysException
        {
                List list = dao.getByModuleID(orgID);

                return list;
        }

        /**
         * stat accord sort
         *
         * @param sort
         * @param num
         * @return
         * @throws EvaluateManageSysException
         */
        public List statAccordSort(int sort, int num)
                throws EvaluateManageSysException
        {
                List list = dao.statAccordSort(sort, num);

                return list;
        }

        /**
         * get min year
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getMinYear() throws EvaluateManageSysException
        {
                List list = dao.getMinYear();

                return list;
        }

        /**
         * get rankname's list
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getRankName() throws EvaluateManageSysException
        {
                List list = dao.getRankName();

                return list;
        }

        /**
         * get point of rank
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getRankPoint() throws EvaluateManageSysException
        {
                List list = dao.getRankPoint();

                return list;
        }

        /**
         * get list of point conversion
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getPointConversion() throws EvaluateManageSysException
        {
                List list = dao.getPointConversion();

                return list;
        }

        /**
         * get list from ERecord
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getERecord() throws EvaluateManageSysException
        {
                List list = dao.getERecord();

                return list;
        }

        /**
         * get rank of user
         *
         * @param point
         * @return
         * @throws EvaluateManageSysException
         */
        public int getUserType(int point) throws EvaluateManageSysException
        {
                int rank = 0;
                List list = dao.getRankPoint();

                for (int i = 0; i < list.size(); i++)
                {
                        int listValue = ((ERecordRankStandardModel) list.get(i)).getPoint();

                        if (point > listValue)
                        {
                                rank++;
                        }
                        else
                        {
                                break;
                        }
                }

                return rank;
        }

        public int getPointByUserID(int userID) throws EvaluateManageSysException
        {
                List list = null;
                ERecordModel eRecordModel = new ERecordModel();
                list = dao.isThisUserInERecord(userID);

                if ((list != null) && (list.size() > 0))
                {
                        eRecordModel = (ERecordModel) list.get(0);
                }
                else
                {
                        eRecordModel.setPoint(-100); //如果有用户没有登陆，返回这个参数防止页面出错。
                }

                return eRecordModel.getPoint();
        }
}
