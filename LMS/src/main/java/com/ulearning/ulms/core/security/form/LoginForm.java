/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Mar 25, 2004
 * Time: 1:17:52 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.core.security.form;

import org.apache.struts.action.ActionForm;


public class LoginForm extends ActionForm
{
        private String name = null;
        private String passwd = null;

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getPasswd()
        {
                return passwd;
        }

        public void setPasswd(String passwd)
        {
                this.passwd = passwd;
        }
}
