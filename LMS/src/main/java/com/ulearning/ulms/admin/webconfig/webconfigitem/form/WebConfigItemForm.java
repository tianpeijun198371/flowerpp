/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 9:48:11
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.form;

import org.apache.struts.action.ActionForm;


public class WebConfigItemForm extends ActionForm
{
        private int ConfigItemID = 0;
        private String ItemType = "0";
        private String Type = "0";
        private String Content = null;
        private String Description = null;

        public int getConfigItemID()
        {
                return ConfigItemID;
        }

        public void setConfigItemID(int configItemID)
        {
                ConfigItemID = configItemID;
        }

        public String getItemType()
        {
                return ItemType;
        }

        public void setItemType(String itemType)
        {
                ItemType = itemType;
        }

        public String getType()
        {
                return Type;
        }

        public void setType(String type)
        {
                Type = type;
        }

        public String getContent()
        {
                return Content;
        }

        public void setContent(String content)
        {
                Content = content;
        }

        public String getDescription()
        {
                return Description;
        }

        public void setDescription(String description)
        {
                Description = description;
        }
}
