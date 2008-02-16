/**
 * DefaultSessionBean.java.
 * User: dengj  Date: 2004-6-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.ejb;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


public class DefaultSessionBean implements SessionBean
{
        public SessionContext sessionContext = null;

        public void setSessionContext(SessionContext context)
        {
                sessionContext = context;
        }

        public void ejbCreate()
        {
        }

        public void ejbRemove()
        {
        }

        public void ejbActivate()
        {
        }

        public void ejbPassivate()
        {
        }
}
