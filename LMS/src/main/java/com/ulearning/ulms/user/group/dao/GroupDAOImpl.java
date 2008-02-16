/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.tools.report.util.bean.UtilHelper;
import com.ulearning.ulms.user.group.exceptions.GroupDAOSysException;
import com.ulearning.ulms.user.group.form.GroupForm;
import com.ulearning.ulms.user.group.model.GroupModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051124
 * Time: 155359
 */

public class GroupDAOImpl implements GroupDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public int insertGroup2(GroupForm details) throws GroupDAOSysException
        {
                int i = 0;
                Serializable s = null;
                try
                {
                        s = HibernateDAO.add(details.getGroupModel());
                        i = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GroupDAOSysException("" + e);
                }
                return i;
        }

        public Serializable insertGroup(GroupForm details) throws GroupDAOSysException
        {
                int i = 0;
                Serializable s = null;
                try
                {
                        s = HibernateDAO.add(details.getGroupModel());
                        i = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GroupDAOSysException("" + e);
                }
                return s;
        }

        public void updateGroup(GroupForm details) throws GroupDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getGroupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GroupDAOSysException("" + e);
                }
        }

        /**
         * Remove the Group from database by the GROUPID
         *
         * @param pkID
         * @throws GroupDAOSysException
         */
        public void deleteGroup(int pkID) throws GroupDAOSysException
        {
                String hql = " from GroupModel where GROUPID = " + pkID;
                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GroupDAOSysException("" + e);
                }
        }

        /**
         * Get the Group info via the unique GROUPID
         *
         * @param pkID
         * @return the prepared GroupForm, default is null
         * @throws GroupDAOSysException
         */
        public GroupForm getGroup(int pkID) throws GroupDAOSysException
        {
                GroupForm bf = new GroupForm();
                GroupForm res = null;
                List tmList = null;
                String hql = " from GroupModel where GROUPID = " + pkID;
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GroupDAOSysException("" + e);
                }
                if ((tmList != null) && (tmList.size() > 0))
                {
                        GroupModel bm = (GroupModel) tmList.get(0);
                        res = bf.getGroupForm(bm);
                }
                return res;
        }

        /**
         * Get the Group list by the catalogID
         *
         * @return The prepared arraylist object,default size is 0
         * @throws GroupDAOSysException
         */
        public List getGroupList() throws GroupDAOSysException
        {
                GroupForm bf = new GroupForm();
                GroupModel bm = null;
                ArrayList GroupList = new ArrayList();
                List tmList = null;
                String hql = " from GroupModel where  1 = 1 ";
                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new GroupDAOSysException("" + e);
                }
                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (GroupModel) tmList.get(i);
                        GroupList.add(bf.getGroupForm(bm));
                }
                return GroupList;
        }

        /**
         * Remove the Group from database by the GROUPID
         * <p/>
         * 按照机构表插入班级-人员数据
         *
         * @throws GroupDAOSysException
         */
        public void insertGroupbyorg(int orgid, int Groupid) throws GroupDAOSysException
        {
                try
                {
                        String hql = "INSERT INTO U_UserGroup_tab (UserID, GroupID) (SELECT UserID," + Groupid + " FROM TM_OrgUser_Tab WHERE orgid = " + orgid + ")";
                        //jdbcdb.exequerysql(hql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                }
                catch (SQLException e)
                {
                        //e.printStackTrace();
                        //throw new GroupDAOSysException(""+e);
                }
        }

        /**
         * Remove the Group from database by the GROUPID
         * <p/>
         * 按照课程表插入班级-人员数据
         *
         * @throws GroupDAOSysException
         */
        public void insertGroupbyCourse(int Courseid, int Groupid) throws GroupDAOSysException
        {
                try
                {
                        String hql = "INSERT INTO U_UserGroup_tab (UserID, GroupID) (SELECT UserID," + Groupid + " FROM C_user_Tab WHERE RelationID = " + Courseid + ")";
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                }
                catch (SQLException e)
                {
                        //e.printStackTrace();
                        //    throw new GroupDAOSysException(""+e);
                }
        }

        //得到数据全部信息
        public int getcgroup() throws Exception
        {
                int a = 0;
                try
                {
                        String hql = "SELECT count(userid) as countsum from u_usergroup_tab";

                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                        System.out.println();
                        if (rs.next())
                        {
                                a = rs.getInt("countsum");
                        }
                        rs.close();
                }
                catch (ULMSSysException e)
                {
                        //e.printStackTrace();
                        //    throw new GroupDAOSysException(""+e);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
                return a;
        }

        //插入数据
        public void insertgroup(int courseid, int fgroupid, int type) throws GroupDAOSysException
        {
                String hql = "INSERT INTO u_usergroup_tab (groupid, userid, remark1) " +
                        "(SELECT " + fgroupid +
                        " AS groupid, a.UserID AS userid, b.Remark7 AS remark1 " +
                        "FROM C_User_Tab a,U_User_Tab b " +
                        "WHERE a.RelationID = " + courseid + " and a.type=" + type + " and a.UserID = b.UserID)";
                System.out.println("sql   insert(int courseid, String fgroupid)  =================================== " + hql);
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                        //throw new GroupDAOSysException(""+e);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        //删除组
        public void delgroup(int courseid) throws GroupDAOSysException
        {
                String hql = "delete from U_Group_tab where RelationID=" + courseid;
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                }
                catch (SQLException e)
                {
                        //e.printStackTrace();
                        //    throw new GroupDAOSysException(""+e);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        //插入数据用户
        public void insert(int groupid, String[] userid)
        {
                String str_temp = "";
                for (int i = 0; i < userid.length; i++)
                {
                        str_temp = str_temp + userid[i] + ",";
                }
                str_temp = str_temp.substring(0, str_temp.length() - 1);
                String sql = "insert into u_usergroup_tab (groupid,userid) " +
                        "(select " + groupid + " as groupid,userid from u_user_tab where userid in (" + str_temp + "))";

                System.out.println("sql   insert(int groupid, String[] userid)  =================================== " + sql);
                try
                {
                        System.out.println("jdbcdb ======================================== ");
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                }
                catch (SQLException e)
                {
                        //e.printStackTrace();
                        //    throw new GroupDAOSysException(""+e);
                }
        }

        /*
        sqlserver
        //更新数据 cnum：需要更新的数据 fgroupid:第一个插入的groupid  nowid：现在的groupid
        public void updategroup(int coum, int fgroupid, int nowid)
        {
                String hql = "update u_usergroup_tab set groupid=" + nowid +
                        " where userid in " +
                        "(SELECT top " + coum + " userid from u_usergroup_tab where groupid=" +
                        fgroupid + " order " +
                        "by Remark1 desc) ";
                try
                {
                        jdbcdb.exequerysql(hql);
                }
                catch (ULMSSysException e)
                {
                }
        }*/
        //db2
        //更新数据 cnum：需要更新的数据 fgroupid:第一个插入的groupid  nowid：现在的groupid
        public void updategroup(int coum, int fgroupid, int nowid)
        {
                String hql = "update u_usergroup_tab set groupid=" + nowid +
                        " where userid in " +
                        "(SELECT  userid from u_usergroup_tab where groupid=" +
                        fgroupid + " order " +
                        "by Remark1 desc  fetch first " + coum + " rows only ) ";
                System.out.println("==================hql  userGroup    ============ " + hql);
                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                }
                catch (SQLException e)
                {
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }


        //主操作
        public void insertshu(int shu, int courseid, int type) throws Exception
        {

                this.delgroup(courseid);
                GroupDAOImpl aa = new GroupDAOImpl();
                GroupForm groupForm = new GroupForm();
                groupForm.setGroupname("第1组");
                groupForm.setRelationid(courseid);
                groupForm.setType(2);
                int oldgroup = aa.insertGroup2(groupForm);
                //int oldgroup = 0;
                int newgroupid = 0;
                this.insertgroup(courseid, oldgroup, type);
                int count = 0;
                if (this.getcgroup() % shu > 0)
                {
                        count = Math.round(this.getcgroup() / shu) + 1;
                }
                else
                {
                        count = Math.round(this.getcgroup() / shu);
                }
                System.out.println("count        before===" + count);
                for (int i = 0; i < shu - 1; i++)
                {
                        groupForm.setGroupname("第" + (i + 2) + "组");
                        groupForm.setRelationid(courseid);
                        groupForm.setType(2);
                        newgroupid = aa.insertGroup2(groupForm);
                        System.out.println("count        end===" + count);
                        this.updategroup(count, oldgroup, newgroupid);
                }

        }


        /**
         * Remove the Group from database by the GROUPID
         * <p/>
         * 按照学号插入班级-人员数据
         *
         * @throws GroupDAOSysException
         */
        public void insertGroupbyNum(String Bnumid, String Enumid, String groupname) throws GroupDAOSysException
        {
                try
                {
                        GroupDAOImpl aa = new GroupDAOImpl();
                        GroupForm groupForm = new GroupForm();
                        groupForm.setGroupname(groupname + "组");
                        groupForm.setRelationid(2);
                        groupForm.setType(2);
                        int Groupid = aa.insertGroup2(groupForm);
                        String hql = "INSERT INTO U_UserGroup_tab (UserID, GroupID) " +
                                "(SELECT u.UserID," + Groupid + " FROM U_User_Tab u ,c_User_tab cu WHERE u.userID=cu.userID and " +
                                "(SUBSTRING(u.Remark7, 6, 8) >= " + Bnumid + ") AND (SUBSTRING(u.Remark7, 6, 8)  <= " + Enumid + "))";
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(hql);
                }
                catch (SQLException e)
                {
                        //e.printStackTrace();
                        //throw new GroupDAOSysException(""+e);
                }
        }

        /**
         * Remove the Group from database by the GROUPID
         * <p/>
         * 按照课程表插入班级
         *
         * @throws GroupDAOSysException
         */
        public void insertGroupbyorg(List orgList) throws GroupDAOSysException
        {
                GroupDAOImpl aa = new GroupDAOImpl();
                GroupForm groupForm = new GroupForm();
                //for(int i=0;i<paperList.size();i++){
                //ExambatchForm pf=(ExambatchForm)paperList.get(i);
                for (int i = 0; i < orgList.size(); i++)
                {
                        //System.out.println(CourseList.size());
                        OrganForm organForm = (OrganForm) orgList.get(i);
                        groupForm.setGroupname(organForm.getOrgName() + "组");
                        groupForm.setType(3);
                        groupForm.setRelationid(3);
                        int bb = aa.insertGroup2(groupForm);
                        //System.out.println(bb);
                        aa.insertGroupbyCourse(organForm.getOrgID(), bb);
                }
        }

        /**
         * Remove the Group from database by the GROUPID
         * <p/>
         * 按照课程表插入班级
         *
         * @throws GroupDAOSysException
         */
        public void insertGroupbyCourse(List CourseList) throws GroupDAOSysException
        {
                GroupDAOImpl aa = new GroupDAOImpl();
                GroupForm groupForm = new GroupForm();
                //for(int i=0;i<paperList.size();i++){
                //ExambatchForm pf=(ExambatchForm)paperList.get(i);
                for (int i = 0; i < CourseList.size(); i++)
                {
                        //System.out.println(CourseList.size());
                        CourseModel course = (CourseModel) CourseList.get(i);
                        groupForm.setGroupname(course.getName() + "组");
                        groupForm.setType(3);
                        groupForm.setRelationid(3);
                        int bb = aa.insertGroup2(groupForm);
                        //System.out.println(bb);
                        aa.insertGroupbyCourse(course.getCourseID(), bb);
                }
        }


        public static void main(String args[]) throws Exception
        {
                /* GroupDAOImpl bb = new GroupDAOImpl();
                //bb.insertshu(3, 8);
                String[] tmp={"3852","3853"};
                bb.insert(139, tmp)  ;
                String aaa = "120049:121059:A课程|121059:122059:B课程|122059:123059:B课程|123059:124059:B课程";
                String[] tmp = StringUtil.splitString(aaa, "|");
                for (int i = 0; i < tmp.length; i++)
                {
                        String[] tmp_str = StringUtil.splitString(tmp[i], ":");
                        bb.insertGroupbyNum(tmp_str[0], tmp_str[1], tmp_str[2]);
                }*/
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                int fileNum = 2;
                int pageNo = 1;
                int pageSize = 5000;
                String[] str = {"0", "0"};
                /*String sql = "select loginname,userID from u_user_tab where loginname in('黄勇军','覃炳和','韦金宋','刘佑强'," +
                "'罗汉英','王秀芳','吴军','唐七珍','杨定红','陈鑫','银景枝','dlwws','dlwmj'," +
                "'覃志咏','黄树文','梁  娥','祝永文','王志强','覃勇','张华','覃昌键','韦扬陆','莫先全'," +
                " '吴永红','dlqhb','周静强','dlhh','潘智参','覃惠乐','覃智硬','梁水好','gxhc_wft','gxhc_ga'," +
                "'gxhc_hlf','gxhc_wsling','gxhc_mcz','gxhc_dxx','gxhc_whj','gxhc_hqh','gxhc_wr','gxhc_ysy'," +
                "'gxhc_ljx','gxhc_qhs','gxhc_hjq','daqwc','dacd','dalzl','dadjn','daldh','dayzy','dawj','dayb'," +
                "'dawjx','dayl','dahrx','gxhc_wm','gxhc_lzm','吴学剑','黄可交','黄开宏','朱宗庆','龙利梅','罗昌林'," +
                "'黄法宝','罗少良','韦东','彭天军','覃爱菊','索彬新','莫宇明','韦波','张国枢','甘昆冰','邓燕平'," +
                "'林成勇','莫宇晖','廖衡','罗启凤','罗日红','韦祥林','韦学忠','卢伟','卢荣刁','黄志旺','龙学汇'," +
                "'邱建林','胡忠','丽珍','荣保','芝勇','干明','小敏','文光','李诚','天勇','传宗','李滨','盛珍','大化烟草蓝猛'," +
                "'大化烟草吕菊','大化烟草梁立生','大化烟草韦先勇','大化烟草黄琳','大化烟草黄凤雅','大化烟草韦宏英','大化烟草潘柳凤1'," +
                "'test31','test32','test33','test34','test35','test36','test37','test38','test39','test40','test41','test42','test43','test44','test45')";
                */
                /*
                String sql = "select loginname,userID from u_user_tab where loginname in(" +
                        "'1001','1002','1003','1004','1005','1006','1007','1008','1009','1010'," +
                        "'1011','1012','1013','1014','1015','1016','1017','1018','1019','1020'," +
                        "'1021','1022','1023','1024','1025','1026','1027','1028','1029','1030'," +
                        "'1031','1032','1033','1034','1035','1036','1037','1038','1039','1040'," +
                        "'1041','1042','1043','1044','1045','1046','1047','1048','1049','1050'" +
                        ")";
                */
                String sql = "select loginname,userID from u_user_tab where loginname in(" +
                        "'lxy','lgc','zsl','xgl','lmc'" +
                        ")";

                UtilHelper uh = new UtilHelper();
                List list = uh.getList(sql, fileNum, pageNo, pageSize);
                String[][] values = new String[list.size()][2];
                int i = 0;
                try
                {
                        for (Iterator it = list.iterator(); it.hasNext();)
                        {
                                str = (String[]) it.next();
                                sql = "insert into u_usergroup_tab(groupID,userID) values(139," + str[1] + ")";
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                stmt.executeQuery(sql);
                                values[i][0] = str[0];
                                values[i][1] = str[1];
                                System.out.print(" values[i][0] = " + values[i][0]);
                                System.out.println(" values[i][1] = " + values[i][1]);
                                i++;
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

        }


}
