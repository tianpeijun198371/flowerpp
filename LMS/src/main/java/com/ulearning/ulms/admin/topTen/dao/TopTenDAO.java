/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.topTen.dao;

import com.ulearning.ulms.admin.topTen.exceptions.TopTenDAOSysException;
import com.ulearning.ulms.admin.topTen.form.TopTenForm;

import java.util.List;


public interface TopTenDAO
{
        public void addTopTen(TopTenForm details) throws TopTenDAOSysException;

        public void updateTopTen(TopTenForm details) throws TopTenDAOSysException;

        public void removeTopTen(int ID) throws TopTenDAOSysException;

        public List getTopTenList(String type) throws TopTenDAOSysException;

        public TopTenForm getTopTen(int ID) throws TopTenDAOSysException;

        public int getID(String name, String type) throws TopTenDAOSysException;

        public List getTopTenList(String type, String is_display)
                throws TopTenDAOSysException;

        public List getTopTen_T_List(String teacher_type, String is_display)
                throws TopTenDAOSysException;

        public List getTopTenList_month(String type, String month,
                                        String teacher_type) throws TopTenDAOSysException;

        public List getTopTenList_month(String type, String month)
                throws TopTenDAOSysException;
}
