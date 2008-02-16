/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.dao;

import com.ulearning.ulms.cert.Certificate.exceptions.CertificateDAOSysException;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public interface CertificateDAO
{
        public Serializable insertCertificate(CertificateForm tf)
                throws CertificateDAOSysException;

        public void updateCertificate(CertificateForm tf)
                throws CertificateDAOSysException;

        public void deleteCertificate(int CtifiID)
                throws CertificateDAOSysException;

        public List getCertificateList() throws CertificateDAOSysException;

        public CertificateForm getCercourseID(int courseID)
                throws CertificateDAOSysException;

        public CertificateForm getCertificate(int CtifiID)
                throws CertificateDAOSysException;
}
