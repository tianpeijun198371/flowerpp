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
         * ������ݿ��Ӧ�ַ���
         *
         * @param bodyIdentity
         * @return
         */
        public static String getBodyIdentityLibStr(int bodyIdentity)
        {
                String str = "�ҽ̽�ʦ��";

                if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_INSERVICE_TEACHER)
                {
                        str = "��ʦ��";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_COLLEGESTUDENT)
                {
                        str = "��ѧ����";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_OTHER_ORGAN)
                {
                        str = "����רҵ������";
                }

                return str;
        }

        /**
         * ������ݶ�Ӧ�ַ���
         *
         * @param bodyIdentity
         * @return
         */
        public static String getBodyIdentityStr(int bodyIdentity)
        {
                String str = "�ҽ̽�ʦ";

                if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_INSERVICE_TEACHER)
                {
                        str = "��ְ��ʦ";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_OTHER_ORGAN)
                {
                        str = "����רҵ����";
                }
                else if (bodyIdentity == FamilyEducationKeys.BODYIDENTITY_COLLEGESTUDENT)
                {
                        str = "��ѧ��";
                }

                return str;
        }

        /**
         * ���ؽ�Ա�Ա�Ҫ���Ӧ�ַ���
         *
         * @param bodyIdentity
         * @return
         */
        public static String getGenderRequiresStr(int bodyIdentity)
        {
                String str = "����ν";

                if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_FEMALE)
                {
                        str = "ҪŮ��Ա";
                }
                else if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_MALE)
                {
                        str = "Ҫ�н�Ա";
                }

                return str;
        }

        /**
         * ���ؽ�Ա�Ա�Ҫ���Ӧ�ַ���
         *
         * @param bodyIdentity
         * @return
         */
        public static String getGenderRequiresDisplayInPortal(int bodyIdentity)
        {
                String str = "[�����Ա�]";

                if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_FEMALE)
                {
                        str = "[Ů��Ա]";
                }
                else if (bodyIdentity == FamilyEducationKeys.GENDER_REQUIRES_MALE)
                {
                        str = "[�н�Ա]";
                }

                return str;
        }
}
