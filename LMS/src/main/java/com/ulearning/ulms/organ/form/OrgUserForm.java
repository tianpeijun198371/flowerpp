/**
 * OrgUserForm.java.
 * User: dengj  Date: 2004-5-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.organ.form;

import com.ulearning.ulms.organ.model.OrganUserModel;
import com.ulearning.ulms.organ.model.OrganUserModelPK;


public class OrgUserForm
{
        private int orgID = 0;
        private int userID = 0;
        private int type = 0;

        public OrgUserForm()
        {
        }

        public OrgUserForm(int orgID, int userID, int type)
        {
                this.orgID = orgID;
                this.userID = userID;
                this.type = type;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public OrganUserModel getOrganUserModel()
        {
                OrganUserModel oum = new OrganUserModel();
                OrganUserModelPK pk = new OrganUserModelPK(orgID, userID);
                oum.setComp_id(pk);

                return oum;
        }
}
