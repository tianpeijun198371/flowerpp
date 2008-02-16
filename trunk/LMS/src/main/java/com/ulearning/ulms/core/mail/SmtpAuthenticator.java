/**
 * SmtpAuthenticator.java.
 * User: fengch  Date: 2004-7-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class SmtpAuthenticator extends Authenticator
{
        private String user;
        private String password;

        public SmtpAuthenticator(String user, String password)
        {
                this.user = user;
                this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication()
        {
                return new PasswordAuthentication(this.user, this.password);
        }
}
