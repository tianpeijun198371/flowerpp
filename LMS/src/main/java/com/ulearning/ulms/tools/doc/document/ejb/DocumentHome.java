package com.ulearning.ulms.tools.doc.document.ejb;

import com.ulearning.ulms.tools.doc.document.exceptions.DocumentAppException;

import java.rmi.RemoteException;

import javax.ejb.*;


public interface DocumentHome extends javax.ejb.EJBHome
{
        /**
         * @J2EE_METHOD --  create
         * Called by the client to create an EJB bean instance. It requires a matching pair in
         * the bean class, i.e. ejbCreate().
         */
        public abstract com.ulearning.ulms.tools.doc.document.ejb.Document create()
                throws java.rmi.RemoteException, javax.ejb.DuplicateKeyException,
                javax.ejb.CreateException, DocumentAppException;
}
