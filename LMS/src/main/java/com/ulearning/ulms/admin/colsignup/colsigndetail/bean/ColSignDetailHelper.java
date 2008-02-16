/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:10:23
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.bean;

import com.ulearning.ulms.admin.colsignup.colsigndetail.dao.ColSignDetailDAO;
import com.ulearning.ulms.admin.colsignup.colsigndetail.dao.ColSignDetailDAOFactory;
import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.ColSignDetailAppException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.ColSignDetailDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.DetialIsExisteException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class ColSignDetailHelper
{
        /**
         * Wrapping the get book method for JSP and  the other modules
         *
         * @param ColSignID
         * @return the book modle according to the bookID
         */
        public static boolean isExist(int ColSignID, int RelationID, int Type)
        {
                boolean isExist = false;

                try
                {
                        ColSignDetailDAO Dao = ColSignDetailDAOFactory.getDAO();
                        isExist = Dao.isExist(ColSignID, RelationID, Type);
                }
                catch (ColSignDetailDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return isExist;
        }

        /**
         * Wrapping the get book method for JSP and  the other modules
         *
         * @param csdf
         * @return the book modle according to the bookID
         */
        public static int addColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailAppException
        {
                int id = 0;
                boolean isExist = false;

                try
                {
                        ColSignDetailDAO Dao = ColSignDetailDAOFactory.getDAO();
                        isExist = Dao.isExist(csdf.getColSignID(), csdf.getRelationID(),
                                csdf.getTypeID());

                        if (isExist)
                        {
                                LogUtil.debug("colsign", "[ColSignDAOOracle]  申请已存在!");
                                throw new DetialIsExisteException("此申请已存在!");
                        }
                        else
                        {
                                id = Dao.addColSignDetail(csdf);
                        }
                }
                catch (ColSignDetailDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return id;
        }

        public static int ColSignStudentNum(int ColSignDetailID, int approved)
                throws ColSignDetailAppException
        {
                int Total = 0;

                try
                {
                        ColSignDetailDAO Dao = ColSignDetailDAOFactory.getDAO();
                        Total = Dao.ColSignStudentNum(ColSignDetailID, approved);
                }
                catch (ColSignDetailDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return Total;
        }

        public static List getColSignDetailList(int ColSignID)
                throws ColSignDetailAppException
        {
                List list = new ArrayList();

                try
                {
                        ColSignDetailDAO Dao = ColSignDetailDAOFactory.getDAO();
                        list = Dao.getColSignDetailList(ColSignID);
                }
                catch (ColSignDetailDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }
}
