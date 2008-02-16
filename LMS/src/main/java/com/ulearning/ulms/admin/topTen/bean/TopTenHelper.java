/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.topTen.bean;

import com.ulearning.ulms.admin.topTen.dao.TopTenDAO;
import com.ulearning.ulms.admin.topTen.dao.TopTenDAOFactory;
import com.ulearning.ulms.admin.topTen.exceptions.TopTenDAOSysException;
import com.ulearning.ulms.admin.topTen.form.TopTenForm;

import java.util.List;


public class TopTenHelper
{
        public TopTenForm getTopTen(int ID)
        {
                TopTenForm tf = null;

                try
                {
                        TopTenDAO toptenDao = TopTenDAOFactory.getDAO();

                        tf = toptenDao.getTopTen(ID);
                }
                catch (TopTenDAOSysException e)
                {
                        e.printStackTrace();
                }

                return tf;
        }

        public static int getID(String name, String type)
        {
                int id = 0;

                try
                {
                        TopTenDAO topTenDao = TopTenDAOFactory.getDAO();
                        id = topTenDao.getID(name, type);
                }
                catch (TopTenDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return id;
        }

        public List getTopTenList(String type)
        {
                List tmList = null;

                try
                {
                        TopTenDAO toptenDao = TopTenDAOFactory.getDAO();
                        tmList = toptenDao.getTopTenList(type);
                }
                catch (TopTenDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return tmList;
        }

        public List getTopTenList(String type, String is_display)
        {
                List tmList = null;

                try
                {
                        TopTenDAO toptenDao = TopTenDAOFactory.getDAO();
                        tmList = toptenDao.getTopTenList(type, is_display);
                }
                catch (TopTenDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return tmList;
        }

        public List getTopTen_T_List(String teacher_type, String is_display)
        {
                List tmList = null;

                try
                {
                        TopTenDAO toptenDao = TopTenDAOFactory.getDAO();
                        tmList = toptenDao.getTopTen_T_List(teacher_type, is_display);
                }
                catch (TopTenDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return tmList;
        }

        public List getTopTenList_month(String type, String month,
                                        String teacher_type)
        {
                List tmList = null;

                try
                {
                        TopTenDAO toptenDao = TopTenDAOFactory.getDAO();
                        tmList = toptenDao.getTopTenList_month(type, month, teacher_type);
                }
                catch (TopTenDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return tmList;
        }

        public List getTopTenList_month(String type, String month)
        {
                List tmList = null;

                try
                {
                        TopTenDAO toptenDao = TopTenDAOFactory.getDAO();
                        tmList = toptenDao.getTopTenList_month(type, month);
                }
                catch (TopTenDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return tmList;
        }
}
