/** * StyleConstants.java.
 * User: xiejh  Date: 2004-7-6 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import org.apache.struts.util.RequestUtils;


public interface StyleConstants
{
        public static final String TITLE_STYLE = "";
        public static final String TR_STYLE1 = "rowEven";
        public static final String TR_STYLE2 = "rowOdd";
        public static final String TD_STYLE1 = "";
        public static final String TD_STYLE2 = "";
        public static final String FORM_TITLE_STYLE = "";
        public static final String FORM_CONTENT_STYLE = "";
        public static final String FORM_TR_STYLE1 = "";
        public static final String FORM_TR_STYLE2 = "";

        ///////this is new Style below

        /**
         * ���ӱ�����ʽ����table����ʽADD_FORM_TABLE_STYLE
         */
        public static final String ADD_FORM_TABLE_STYLE = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\" class=\"tableadd\"";

        /**
         * View Form table ��ʽ
         */
        public static final String VIEW_FORM_TABLE_STYLE = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\" class=\"tableview\"";

        /**
         * ��������table ��ʽ
         */
        public static final String NAVIGATOR_TABLE_STYLE = " width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"link01\"";

        /**
         * List table ��ʽ
         */
        public static final String LIST_FORM_TABLE_STYLE = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\"    class=\"tablemainxx\"";

        /**
         * ������ύ��ť��table ��ʽ
         */
        public static final String SUB_BUTTON_TABLE_STYLE = " width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\"";

        /**
         * ���ܰ�ť���ڵ�table ����ʽ
         */
        public static final String FUN_BUTTON_TABLE_STYLE = " width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"button02\"";

        /**
         * hr����ʽ;
         */
        public static final String HR_STYLE = "<hr width=\"100%\" size=\"1\" noshade color=\"#9C9A9C\">";

        /**
         * input ����ʽ
         */
        public static final String INPUT_STYLE = " class=\"textfield01\" ";

        /**
         * button ����ʽ
         */
        public static final String BUTTON_STYLE = " class=\"button\" ";

        /**
         * û�����ݵ���ʽ
         */

        //RequestUtils.message(pageContext, null, null, "message.certificate")
        public static final String NO_CONTENT = "<tr>\n" +
                "                <td colspan=\"20\"><div align=\"left\">��δ�ҵ��κ��</div></td>\n" +
                "        </tr>";
}
