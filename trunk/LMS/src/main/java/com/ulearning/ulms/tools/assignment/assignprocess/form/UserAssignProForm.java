/** * UserAssignProForm.java.
 * User: xiejh  Date: 2004-8-18 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.form;

public class UserAssignProForm
{
        private String loginName = null;
        private String name = null;
        private AssignProcessForm ap = null;

        public String getLoginName()
        {
                return loginName;
        }

        public void setLoginName(String loginName)
        {
                this.loginName = loginName;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public AssignProcessForm getAp()
        {
                return ap;
        }

        public void setAp(AssignProcessForm ap)
        {
                this.ap = ap;
        }
}
