/**
 * MatchModelDaoImpl.java.
 * User: zhangy Date: 2005-6-6 14:10:07
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.model.CourseUser;
import com.ulearning.ulms.course.model.CourseUserPK;
import com.ulearning.ulms.course.model.SecUserRoleModel;
import com.ulearning.ulms.course.model.SecUserRolePK;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.match.exceptions.MatchDaoSysException;
import com.ulearning.ulms.match.form.MatchClassForm;
import com.ulearning.ulms.match.model.MatchClassModel;
import com.ulearning.ulms.match.model.MatchModel;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class MatchModelDaoImpl implements MatchModelDao
{
        /**
         * 接受 AddMatchNameAction 参数，向 M_MatchModel_Tab 表写入数据
         *
         * @param matchClassForm
         * @throws MatchDaoSysException
         */
        public void addMatchClass(MatchClassForm matchClassForm)
                throws MatchDaoSysException
        {
                try
                {
                        HibernateDAO.add(matchClassForm.getMatchClassModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'addMatchClass throw====" + e.getMessage());
                }
        }

        /**
         * 接受 AddMatchNameAction 参数，删除 M_MatchModel_Tab 表中数据
         *
         * @param matchid
         * @param modeleid
         * @throws MatchDaoSysException
         */
        public void delMatchClass(int matchid, int modeleid)
                throws MatchDaoSysException
        {
                try
                {
                        String sql = " from MatchClassModel where MATCHID=" + matchid +
                                " and MODELEID=" + modeleid;
                        HibernateDAO.delete(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'delMatchClass throw====" + e.getMessage());
                }
        }

        /**
         * 根据课程　ｉｄ，删除 M_MatchModel_Tab 表中相应的数据，为确保一门课程只能使用一项匹配规则。
         *
         * @param modeleid
         * @throws MatchDaoSysException
         */
        public void delMatchClassByModeleid(int modeleid)
                throws MatchDaoSysException
        {
                try
                {
                        String sql = " from MatchClassModel where MODELEID=" + modeleid;
                        HibernateDAO.delete(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'delMatchClass throw====" + e.getMessage());
                }
        }

        /**
         * 接受 AddMatchNameAction 参数，修改 M_MatchModel_Tab 表中数据
         *
         * @throws MatchDaoSysException
         */
        public void updateMatchClass(MatchClassForm matchClassForm)
                throws MatchDaoSysException
        {
                try
                {
                        HibernateDAO.update(matchClassForm.getMatchClassModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'updateMatchClass throw====" + e.getMessage());
                }
        }

        /**
         * 由 modeleid 得到 M_MatchModel_Tab 表中的相关的数据
         *
         * @param modeleid
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassList(int modeleid) throws MatchDaoSysException
        {
                List list = new ArrayList();
                String sql = "from MatchClassModel where MODELEID=" + modeleid;

                try
                {
                        LogUtil.debug("common",
                                "[MatchDaoImpl]getMatchClassList--sql==" + sql);
                        list = HibernateDAO.find(sql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "MatchDaoImpl'updateMatchClass throw====" + e.getMessage());
                }

                return list;
        }

        /**
         * 由 AspID 得到 M_MatchModel_Tab 表中的相关的数据
         *
         * @param aspID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassListAsp(int aspID) throws MatchDaoSysException
        {
                List result = getMatchClassObjectAsp(aspID);
                List resultRecord = new ArrayList();
                List list = new ArrayList();
                Object[] record = null;

                for (Iterator iter = result.iterator(); iter.hasNext();)
                {
                        record = (Object[]) iter.next();
                        resultRecord.add(record[0]);
                }

                for (int i = 0; i < resultRecord.size(); i++)
                {
                        MatchClassForm mcf = new MatchClassForm((MatchClassModel) resultRecord.get(
                                i));
                        list.add(mcf);
                }

                return list;
        }

        /**
         * 由 AspID 得到 M_MatchModel_Tab object类型的相关的数据
         *
         * @param aspID
         * @return
         * @throws MatchDaoSysException
         */
        public List getMatchClassObjectAsp(int aspID) throws MatchDaoSysException
        {
                List list = new ArrayList();
                List list1 = new ArrayList();
                Object[] record = null;
                String sql = "from MatchClassModel mcm,MatchModel mm where mcm.comp_id.matchid=mm.matchid and mm.relationid=" +
                        aspID;

                try
                {
                        LogUtil.debug("common",
                                "[MatchDaoImpl]getMatchClassObjectAsp--sql==" + sql);
                        list = HibernateDAO.find(sql);

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                record = (Object[]) iter.next();
                                list1.add(record);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "[MatchDaoImpl]getMatchClassListAsp throw====" + e);
                }

                return list1;
        }

        /**
         * 为课程添加用户
         * relationID 目前接受的是 aspID
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public void addCoursUser(UserForm userForm, int relationID, int type)
                throws MatchDaoSysException
        {
                Date date = new Date();
                CourseUserPK cupk = new CourseUserPK(userForm.getUserID(), relationID,
                        type);
                CourseUser cu = new CourseUser(cupk);
                cu.setApplyTime(date);
                cu.setJoinTime(date);
                cu.setState(String.valueOf(CourseKeys.COURSE_USER_AVAILABLE_STATE));

                SecUserRolePK surpk = new SecUserRolePK(userForm.getUserID(),
                        SecurityConstants.ROLE_COURSR_STUDENT, relationID, type);
                SecUserRoleModel surm = new SecUserRoleModel(surpk);

                try
                {
                        HibernateDAO.add(cu); //添加到 C_USER_TAB 表
                        HibernateDAO.add(surm); //添加到 Sec_UserRole_Tab 表
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "[MatchDaoImpl]addCoursUser throw====" + e.getMessage());
                }
        }

        /**
         * 通过 UserForm 查找 本部门所有的课程，有多门课程是否使用自动匹配规则，
         * 并把该用户加入的课程当中。
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public void addCoursUser(UserForm userForm, int aspID)
                throws MatchDaoSysException
        {
                List userFormList = MatchModelDaoFactory.getDAO()
                        .getMatchClassListAsp(aspID); //得到本 Asp所有使用匹配规则的课程
                //System.out.println("[MatchModelDaoImpl]addCoursUser====================UserCont=" + userFormList.size());

                LogUtil.debug("system",
                        "[MatchModelDaoImpl]addCoursUser====================UserCont=" +
                                userFormList.size());

                for (int i = 0; i < userFormList.size(); i++)
                {
                        MatchClassForm mcf = (MatchClassForm) userFormList.get(i);
                        List matchItermList = MatchItermDaoFactory.getDAO()
                                .getMatchIterm(mcf.getMatchid());
                        int catalogID = mcf.getModeleid();
                        String type = mcf.getType();
                        boolean isMatchUsers = MatchDaoFactory.getDAO()
                                .isMatchFromDepart(userForm,
                                        matchItermList); //判断用户是否符合匹配规则

                        if ((userForm != null) && isMatchUsers &&
                                isCoursUser(userForm, catalogID, Integer.parseInt(type)))
                        {
                                addCoursUser(userForm, catalogID, Integer.parseInt(type));
                        }
                }
        }

        /**
         * 判断将要添加的用户是否已经存在本课程中
         *
         * @param userForm
         * @throws MatchDaoSysException
         */
        public boolean isCoursUser(UserForm userForm, int relationID, int type)
                throws MatchDaoSysException
        {
                boolean isBoolean = true;
                List list = null;
                String sql = "from CourseUser where USERID=" + userForm.getUserID() +
                        " and RELATIONID=" + relationID + " and TYPE=" + type;

                try
                {
                        LogUtil.debug("common", "[MatchDaoImpl]isCoursUser--sql==" + sql);
                        list = HibernateDAO.find(sql);

                        if ((list.size() > 0) && (list != null))
                        {
                                isBoolean = false;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MatchDaoSysException(
                                "[MatchDaoImpl]isCoursUser throw====" + e.getMessage());
                }

                return isBoolean;
        }

        /**
         * 由 modeleid（课程编号） 判断出在 M_MatchModel_Tab 表中是否存在
         *
         * @param modeleid
         * @param matchid
         * @return
         */
        public boolean isMatchByMatchClass(int modeleid, int matchid)
        {
                boolean isBoolean = false;

                try
                {
                        List listMatchClass = this.getMatchClassList(modeleid);

                        for (int i = 0; i < listMatchClass.size(); i++)
                        {
                                MatchClassForm mcf = new MatchClassForm((MatchClassModel) listMatchClass.get(
                                        i));

                                if (mcf.getMatchid() == matchid)
                                {
                                        isBoolean = true;
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }

                return isBoolean;
        }
}
