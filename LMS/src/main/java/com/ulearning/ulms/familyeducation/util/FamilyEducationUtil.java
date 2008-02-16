/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: Fengch
 * Date: 2007-12-10 15:09:25
 */
package com.ulearning.ulms.familyeducation.util;

public class FamilyEducationUtil
{
        /**
         * 返回身份库对应字符串
         *
         * @param bodyIdentity
         * @return
         */
        public static String getBodyIdentityLibStr(int bodyIdentity)
        {
                String str = "家教教师库";

                if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_INSERVICE_TEACHER)
                {
                        str = "教师库";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_COLLEGESTUDENT)
                {
                        str = "大学生库";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_OTHER_ORGAN)
                {
                        str = "其它专业机构库";
                }

                return str;
        }

        /**
         * 返回身份对应字符串
         *
         * @param bodyIdentity
         * @return
         */
        public static String getBodyIdentityStr(int bodyIdentity)
        {
                String str = "家教教师";

                if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_INSERVICE_TEACHER)
                {
                        str = "在职教师";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_OTHER_ORGAN)
                {
                        str = "其它专业机构";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_COLLEGESTUDENT)
                {
                        str = "大学生";
                }

                return str;
        }

        /**
         * 返回教员性别要求对应字符串
         *
         * @param bodyIdentity
         * @return
         */
        public static String getGenderRequiresStr(int bodyIdentity)
        {
                String str = "无所谓";

                if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_FEMALE)
                {
                        str = "要女教员";
                }
                else if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_MALE)
                {
                        str = "要男教员";
                }

                return str;
        }

        /**
         * 返回教员性别要求对应字符串
         *
         * @param bodyIdentity
         * @return
         */
        public static String getGenderRequiresDisplayInPortal(int bodyIdentity)
        {
                String str = "[不限性别]";

                if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_FEMALE)
                {
                        str = "[女教员]";
                }
                else if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_MALE)
                {
                        str = "[男教员]";
                }

                return str;
        }
}
