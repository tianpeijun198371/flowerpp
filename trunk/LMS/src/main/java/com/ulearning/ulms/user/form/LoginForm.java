/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Mar 25, 2004
 * Time: 1:17:52 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.form;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm
{
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

        private String name = null;

        private String passwd = null;
}
