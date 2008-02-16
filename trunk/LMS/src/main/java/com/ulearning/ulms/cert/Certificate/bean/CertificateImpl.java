/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.bean;

import com.ulearning.ulms.cert.Certificate.dao.CertificateDAO;
import com.ulearning.ulms.cert.Certificate.dao.CertificateDAOFactory;
import com.ulearning.ulms.cert.Certificate.exceptions.CertificateDAOSysException;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class CertificateImpl
{
        public List getCertificateList() throws CertificateDAOSysException
        {
                List CertificateList = null;

                try
                {
                        CertificateDAO dao = CertificateDAOFactory.getDAO();
                        CertificateList = dao.getCertificateList();
                }
                catch (CertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return CertificateList;
        }

        public CertificateForm getCertificate(int CtifiID)
                throws CertificateDAOSysException
        {
                CertificateForm tf = null;

                try
                {
                        CertificateDAO dao = CertificateDAOFactory.getDAO();
                        tf = dao.getCertificate(CtifiID);
                }
                catch (CertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public CertificateForm getCerCourse(int courseid)
                throws CertificateDAOSysException
        {
                CertificateForm tf = null;

                try
                {
                        CertificateDAO dao = CertificateDAOFactory.getDAO();
                        tf = dao.getCercourseID(courseid);
                }
                catch (CertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteCertificate(int CtifiID)
                throws CertificateDAOSysException
        {
                try
                {
                        CertificateDAO dao = CertificateDAOFactory.getDAO();
                        dao.deleteCertificate(CtifiID);
                }
                catch (CertificateDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
