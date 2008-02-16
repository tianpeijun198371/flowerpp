package com.ulearning.ulms.organ.bean;

import com.ulearning.ulms.organ.form.OrganForm;


public class OrganLevelHelper
{
        public static int getOrgLevel(int orgID)
        {
                int orgLevel = -1;
                OrganForm organForm = OrganHelper.getOrgan(orgID);

                if (orgID == 0)
                {
                        orgLevel = orgID; //平台
                }
                else if (organForm.getParentID() == 0)
                {
                        orgLevel = orgID; //市局
                }
                else if (OrganHelper.getOrgan(organForm.getParentID()).getParentID() == 0)
                {
                        orgLevel = orgID; //区县局
                }
                else if (OrganHelper.getOrgan(OrganHelper.getOrgan(
                        organForm.getParentID()).getParentID()).getParentID() == 0)
                {
                        orgLevel = organForm.getParentID(); //班组
                }

                return orgLevel;
        }

        public static void main(String[] args)
        {
                System.out.print(OrganLevelHelper.getOrgLevel(156));
        }
}
