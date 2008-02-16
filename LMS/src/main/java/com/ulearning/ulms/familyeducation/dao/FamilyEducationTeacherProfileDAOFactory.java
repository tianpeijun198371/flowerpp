/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-6 13:15:44
 */
package com.ulearning.ulms.familyeducation.dao;

import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;


public class FamilyEducationTeacherProfileDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static FamilyEducationTeacherProfileDAO getDAO()
                throws FamilyEducationSysException
        {
                return new FamilyEducationTeacherProfileDAOImpl();
        }
}
