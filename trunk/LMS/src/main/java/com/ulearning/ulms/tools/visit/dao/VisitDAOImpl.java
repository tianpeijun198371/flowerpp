package com.ulearning.ulms.tools.visit.dao;

import com.ulearning.ulms.tools.visit.model.VisitModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Jacky
 * Date: 2007-12-28
 * Time: 11:18:02
 * To change this template use File | Settings | File Templates.
 */
public class VisitDAOImpl implements VisitDAO {

    public int addVisit(VisitModel details) {
        int visitID = 0;
        try {
            String visit = HibernateDAO.add(details).toString();
            visitID = Integer.parseInt(visit);
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
        }
        return visitID;
    }

    public void saveVisit(VisitModel details) {
        int visitID = 0;
        try {
            HibernateDAO.add(details);
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
        }
    }

    public void updateVisit(VisitModel details) {
        int visitID = 0;
        try {
            HibernateDAO.update(details);
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
        }
    }

    public VisitModel getVisit(int visitID) {
        VisitModel result = null;
        try {
            result = (VisitModel) HibernateDAO.load(VisitModel.class, new Integer(visitID).toString());
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
        }
        return result;
    }

    public VisitModel getVisit_bak(int type, int relationID) {
        VisitModel result = null;
        String hql = " from VisitModel where type =" + type + " and relationID =" + relationID;
        try {
            //result = (VisitModel) HibernateDAO.load(VisitModel.class, new Integer(visitID).toString());
            List list = HibernateDAO.find(hql);
            if (list != null) {
                result = (VisitModel) list.get(0);
            }
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
        }
        return result;
    }

    public VisitModel getVisit(int type, int relationID) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        VisitModel result = null;
        try {
            conn = DBUtil.getConnection();
            String hql = " select * from T_VISIT_TAB where type =" + type + " and relationID= " + relationID;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(hql);
            if (rs.next()) {
                result = new VisitModel();
                result.setVisitID(rs.getInt("visitID"));
                result.setType(rs.getInt("type"));
                result.setRelationID(rs.getInt("relationID"));
                result.setVisitNum(rs.getInt("visitNum"));
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new UserDAOSysException("VISITSysException while addUser(VisitModel details) method" + se);
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("VISITSysException while addUser(VisitModel details) method" + e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }
        return result;

    }

    public List getVisitListJDBC(int type, int rowNum) {
        List result = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String hql = " select * from T_VISIT_TAB where type =" + type + " order by visitNum desc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(hql);
            if (rowNum > 0) {
                while (rs.next() && rowNum > 0) {
                    VisitModel vm = new VisitModel();
                    vm.setType(rs.getInt("type"));
                    vm.setRelationID(rs.getInt("relationID"));
                    vm.setVisitNum(rs.getInt("visitNum"));
                    result.add(vm);
                    rowNum--;
                }
            } else {
                while (rs.next()) {
                    VisitModel vm = new VisitModel();
                    vm.setType(rs.getInt("type"));
                    vm.setRelationID(rs.getInt("relationID"));
                    vm.setVisitNum(rs.getInt("visitNum"));
                    result.add(vm);
                }
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new UserDAOSysException("VISITSysException while addUser(VisitModel details) method" + se);
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("VISITSysException while addUser(VisitModel details) method" + e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(conn);
        }
        return result;

    }

    public List getVisitList(int type, int rowNum) {
        return getVisitListJDBC(type, rowNum);
    }

    public List getVisitList_H(int type, int rowNum) {
        List result = new ArrayList();
        try {
            String hql = " from VisitModel where type =" + type + " order by visitNum desc";
            List list = HibernateDAO.find(hql);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    VisitModel vm = new VisitModel();
                    vm = (VisitModel) list.get(i);
                    result.add(vm);
                }
            }
        }
        catch (ULMSSysException e) {
            e.printStackTrace();
            throw new UserDAOSysException("ULMSSysException while addUser(UserForm details) method" + e);
        }
        return result;

    }

    protected void closeResultSet(ResultSet result) throws UserDAOSysException {
        try {
            if (result != null) {
                result.close();
            }
        }
        catch (SQLException se) {
            throw new UserDAOSysException("SQL Exception while closing " +
                    "Result Set : \n" + se);
        }
    }

    protected void closeStatement(Statement stmt) throws UserDAOSysException {
        try {
            if (stmt != null) {
                stmt.close();
            }
        }
        catch (SQLException se) {
            throw new UserDAOSysException("SQL Exception while closing " +
                    "Statement : \n" + se);
        }
    }

    protected void closeConnection(Connection conn) throws UserDAOSysException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        catch (SQLException se) {
            throw new UserDAOSysException("SQL Exception while closing " +
                    "DB connection : \n" + se);
        }
    }

}
