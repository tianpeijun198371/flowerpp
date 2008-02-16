/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.bean;

import com.ulearning.ulms.GCertificate.dao.GCertificateDAO;
import com.ulearning.ulms.GCertificate.dao.GCertificateDAOFactory;
import com.ulearning.ulms.GCertificate.exceptions.GCertificateDAOSysException;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;
import com.ulearning.ulms.core.util.StringUtil;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060111
 * Time: 110302
 */
public class GCertificateImpl
{
        public List getGCertificateList() throws GCertificateDAOSysException
        {
                List GCertificateList = null;

                try
                {
                        GCertificateDAO dao = GCertificateDAOFactory.getDAO();
                        GCertificateList = dao.getGCertificateList();
                }
                catch (GCertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GCertificateList;
        }

        public int getGPiod(int Userid) throws GCertificateDAOSysException
        {
                List tf = null;
                int ij = 0;

                try
                {
                        GCertificateDAO dao = GCertificateDAOFactory.getDAO();
                        tf = dao.getGCertificateList(Userid);

                        //System.out.println(tf.size());
                        for (int i = 0; i < tf.size(); i++)
                        {
                                GCertificateForm aa = (GCertificateForm) tf.get(i);
                                System.out.println(aa.getGCGrade());
                                ij += StringUtil.parseFloat(aa.getGCGrade());
                        }
                }
                catch (GCertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return ij;
        }

        public GCertificateForm getGCertificate(int GID)
                throws GCertificateDAOSysException
        {
                GCertificateForm tf = null;

                try
                {
                        GCertificateDAO dao = GCertificateDAOFactory.getDAO();
                        tf = dao.getGCertificate(GID);
                }
                catch (GCertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteGCertificate(int GID) throws GCertificateDAOSysException
        {
                try
                {
                        GCertificateDAO dao = GCertificateDAOFactory.getDAO();
                        dao.deleteGCertificate(GID);
                }
                catch (GCertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }

        public static void main(String[] args) throws Exception
        {
                GCertificateImpl aa = new GCertificateImpl();
                System.out.println(aa.getGPiod(105));
        }
}
