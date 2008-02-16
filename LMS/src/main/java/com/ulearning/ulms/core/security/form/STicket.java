/**
 * STicket.java.
 * User: dengj  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.form;

import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.user.form.UserForm;

import java.util.ArrayList;
import java.util.List;


public class STicket implements java.io.Serializable
{
        private static final int ticketLength = 94; // Defind the ticket byte[]'s length
        private byte[] uTicketID = new byte[ticketLength]; // Defind the user's ticket bytes buffer
        private List roleList = new ArrayList(); // Defind the user's roleID
        private List rolePermission = null; //roleID and permission
        private int userID = 0;
        private int orgID = 1;
        private int aspID = 1;
        private UserForm uf = null;
        private OrganForm of = null;

        /**
         * Default to create the ticke object
         */
        public STicket()
        {
                for (int i = 0; i < ticketLength; i++)
                {
                        uTicketID[i] = (byte) (0x00); // Clear the buffer
                }

                roleList = new ArrayList(); // Create it
        }

        //private List rolePermission =  new ArrayList();
        public List getRolePermission()
        {
                return rolePermission;
        }

        public void setRolePermission(List rolePermission)
        {
                this.rolePermission = rolePermission;
        }

        public List getRoleList()
        {
                return roleList;
        }

        public void setRoleList(List roleList)
        {
                this.roleList = roleList;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public UserForm getUf()
        {
                return uf;
        }

        public void setUf(UserForm uf)
        {
                this.uf = uf;
        }

        public OrganForm getOf()
        {
                return of;
        }

        public void setOf(OrganForm of)
        {
                this.of = of;
        }
}
