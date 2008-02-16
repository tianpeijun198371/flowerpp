/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.dao;

import com.ulearning.ulms.GCertificate.exceptions.GCertificateDAOSysException;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060111
 * Time: 110302
 */
public interface GCertificateDAO
{
        public Serializable insertGCertificate(GCertificateForm tf)
                throws GCertificateDAOSysException;

        public void updateGCertificate(GCertificateForm tf)
                throws GCertificateDAOSysException;

        public void deleteGCertificate(int GID) throws GCertificateDAOSysException;

        public List getGCertificateList() throws GCertificateDAOSysException;

        public GCertificateForm getGCertificate(int GID)
                throws GCertificateDAOSysException;

        public List getGCertificateList(int Userid)
                throws GCertificateDAOSysException;

        public GCertificateForm getGCertificate(int gUserID, int gCourseID)
                throws GCertificateDAOSysException;
}
