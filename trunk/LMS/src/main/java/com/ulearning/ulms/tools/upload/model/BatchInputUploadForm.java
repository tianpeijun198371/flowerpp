/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-1
 * Time: 12:02:08
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.tools.upload.model;

public class BatchInputUploadForm extends UploadForm
{
        private String orgID = "";
        private String secOrgID = "";
        private String type = "";
        private String temp = "";

        public String getOrgID()
        {
                return orgID;
        }

        public void setOrgID(String orgID)
        {
                this.orgID = orgID;
        }

        public String getSecOrgID()
        {
                return secOrgID;
        }

        public void setSecOrgID(String secOrgID)
        {
                this.secOrgID = secOrgID;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getTemp()
        {
                return temp;
        }

        public void setTemp(String temp)
        {
                this.temp = temp;
        }


}
