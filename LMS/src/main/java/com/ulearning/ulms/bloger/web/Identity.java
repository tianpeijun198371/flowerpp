/*
 * Created on 2004-10-2
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.web;

import com.ulearning.ulms.bloger.exception.*;

import javax.servlet.http.*;


/**
 * Identity class uses in the session to identify the user.
 *
 * @author Huaxia
 */
public class Identity implements java.io.Serializable
{
        public static final String IDENTITY = "s_identity";
        public static final Identity ANONYMOUS = new Identity((-1), null);
        private int accountId;
        private String username;

        public Identity(int accountId, String username)
        {
                this.accountId = accountId;
                this.username = username;
        }

        public boolean isAnonymous()
        {
                return username == null;
        }

        public boolean isLogin()
        {
                return username != null;
        }

        public int getAccountId()
        {
                return accountId;
        }

        public String getUsername()
        {
                return (username == null) ? "guest" : username;
        }

        public boolean isAdmin()
        {
                return "admin".equals(username);
        }

        /**
         * Get the identity of the session.
         *
         * @param request The http request.
         * @return The Identity of the login user.
         * @throws AuthorizationException If not login.
         */
        public static Identity getIdentity(HttpServletRequest request)
        {
                Object obj = request.getSession().getAttribute(IDENTITY);

                if (obj == null)
                {
                        throw new AuthorizationException(
                                "Authorization failed: Anonymous user.");
                }

                Identity id = (Identity) obj;

                if (id.isAnonymous())
                {
                        throw new AuthorizationException(
                                "Authorization failed: Anonymous user.");
                }

                return id;
        }
}
