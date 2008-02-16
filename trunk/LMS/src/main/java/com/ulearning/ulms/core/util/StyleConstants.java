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
         * 增加表单的样式里面table的样式ADD_FORM_TABLE_STYLE
         */
        public static final String ADD_FORM_TABLE_STYLE = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\" class=\"tableadd\"";

        /**
         * View Form table 样式
         */
        public static final String VIEW_FORM_TABLE_STYLE = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\" class=\"tableview\"";

        /**
         * 导般条的table 样式
         */
        public static final String NAVIGATOR_TABLE_STYLE = " width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"link01\"";

        /**
         * List table 样式
         */
        public static final String LIST_FORM_TABLE_STYLE = " border=\"0\" cellpadding=\"4\" cellspacing=\"1\"    class=\"tablemainxx\"";

        /**
         * 标题和提交按钮的table 样式
         */
        public static final String SUB_BUTTON_TABLE_STYLE = " width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\"";

        /**
         * 功能按钮所在的table 的样式
         */
        public static final String FUN_BUTTON_TABLE_STYLE = " width=\"100%\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\" class=\"button02\"";

        /**
         * hr的样式;
         */
        public static final String HR_STYLE = "<hr width=\"100%\" size=\"1\" noshade color=\"#9C9A9C\">";

        /**
         * input 的样式
         */
        public static final String INPUT_STYLE = " class=\"textfield01\" ";

        /**
         * button 的样式
         */
        public static final String BUTTON_STYLE = " class=\"button\" ";

        /**
         * 没有内容的样式
         */

        //RequestUtils.message(pageContext, null, null, "message.certificate")
        public static final String NO_CONTENT = "<tr>\n" +
                "                <td colspan=\"20\"><div align=\"left\">（未找到任何项）</div></td>\n" +
                "        </tr>";
}
