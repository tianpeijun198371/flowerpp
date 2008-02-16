/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-11
 * Time: 15:22:43
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.certificate.model;

public class CertCurrentStatus
{
        private boolean maxEnrollStatus = false;
        private boolean lifeStatus = false;
        private boolean enrollStatus = false;
        private boolean approveStatus = false;
        private boolean feeStatus = false;
        private boolean browseStatus = false;
        private String enrollPassword = "";
        private int charge = 0;
        private int nowStudentNum = 0;
        private boolean regdate = false;

        public boolean isRegdate()
        {
                return regdate;
        }

        public void setRegdate(boolean regdate)
        {
                this.regdate = regdate;
        }

        public boolean isMaxEnrollStatus()
        {
                return maxEnrollStatus;
        }

        public void setMaxEnrollStatus(boolean maxEnrollStatus)
        {
                this.maxEnrollStatus = maxEnrollStatus;
        }

        public boolean isLifeStatus()
        {
                return lifeStatus;
        }

        public void setLifeStatus(boolean lifeStatus)
        {
                this.lifeStatus = lifeStatus;
        }

        public boolean isEnrollStatus()
        {
                return enrollStatus;
        }

        public void setEnrollStatus(boolean enrollStatus)
        {
                this.enrollStatus = enrollStatus;
        }

        public boolean isApproveStatus()
        {
                return approveStatus;
        }

        public void setApproveStatus(boolean approveStatus)
        {
                this.approveStatus = approveStatus;
        }

        public boolean isFeeStatus()
        {
                return feeStatus;
        }

        public void setFeeStatus(boolean feeStatus)
        {
                this.feeStatus = feeStatus;
        }

        public boolean isBrowseStatus()
        {
                return browseStatus;
        }

        public void setBrowseStatus(boolean browseStatus)
        {
                this.browseStatus = browseStatus;
        }

        public String getEnrollPassword()
        {
                return enrollPassword;
        }

        public void setEnrollPassword(String enrollPassword)
        {
                this.enrollPassword = enrollPassword;
        }

        public int getCharge()
        {
                return charge;
        }

        public void setCharge(int charge)
        {
                this.charge = charge;
        }

        public int getNowStudentNum()
        {
                return nowStudentNum;
        }

        public void setNowStudentNum(int nowStudentNum)
        {
                this.nowStudentNum = nowStudentNum;
        }
}
