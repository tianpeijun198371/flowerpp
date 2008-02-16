/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-16
 * Time: 10:20:01
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.form;

import com.ulearning.ulms.course.test.grade.model.PaperUserModel;
import com.ulearning.ulms.course.test.grade.model.PaperUserModelPK;
import com.ulearning.ulms.user.form.UserForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class PaperUserForm extends ActionForm
{
        private int paperID = 0;
        private int userID = 0;
        private int type = 0;
        private float subjective = 0;
        private float objective = 0;
        private float grade = 0;
        private int status = 0;
        private Date startTime = null;
        private Date endTime = null;
        private String description = null;
        private String desc1 = null;
        private String desc2 = null;
        private String desc3 = null;
        private String desc4 = null;
        private String userName = null;

        public PaperUserForm()
        {
        }

        public PaperUserForm(PaperUserModel pum)
        {
                if (pum != null)
                {
                        this.paperID = pum.getComp_id().getPaperid();
                        this.userID = pum.getComp_id().getUserid();
                        this.type = pum.getType();
                        this.subjective = pum.getSubjective();
                        this.objective = pum.getObjective();
                        this.grade = pum.getGrade();
                        this.status = pum.getStatus();
                        this.startTime = pum.getStarttime();
                        this.endTime = pum.getEndtime();
                        this.description = pum.getDescription();
                        this.desc1 = pum.getDesc1();
                        this.desc2 = pum.getDesc2();
                        this.desc3 = pum.getDesc3();
                        this.desc4 = pum.getDesc4();
                }
        }

        public PaperUserModel getPaperUserModel()
        {
                PaperUserModel pum = new PaperUserModel();
                PaperUserModelPK pk = new PaperUserModelPK();
                pk.setPaperid(this.paperID);
                pk.setUserid(this.userID);
                pum.setComp_id(pk);
                pum.setType(this.type);
                pum.setSubjective(this.subjective);
                pum.setObjective(this.objective);
                pum.setGrade(this.grade);
                pum.setStatus(this.status);
                pum.setStarttime(this.startTime);
                pum.setEndtime(this.endTime);
                pum.setDescription(this.description);
                pum.setDesc1(this.desc1);
                pum.setDesc2(this.desc2);
                pum.setDesc3(this.desc3);
                pum.setDesc4(this.desc4);

                return pum;
        }

        //        public String getUserName()
        //        {
        //                return userName;
        //        }
        //
        //        public void setUserName(String userName)
        //        {
        //                this.userName = userName;
        //        }
        public int getPaperID()
        {
                return paperID;
        }

        public void setPaperID(int paperID)
        {
                this.paperID = paperID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public float getSubjective()
        {
                return subjective;
        }

        public void setSubjective(float subjective)
        {
                this.subjective = subjective;
        }

        public float getObjective()
        {
                return objective;
        }

        public void setObjective(float objective)
        {
                this.objective = objective;
        }

        public float getGrade()
        {
                return grade;
        }

        public void setGrade(float grade)
        {
                this.grade = grade;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public Date getStartTime()
        {
                return startTime;
        }

        public void setStartTime(Date startTime)
        {
                this.startTime = startTime;
        }

        public Date getEndTime()
        {
                return endTime;
        }

        public void setEndTime(Date endTime)
        {
                this.endTime = endTime;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getDesc1()
        {
                return desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDesc2()
        {
                return desc2;
        }

        public void setDesc2(String desc2)
        {
                this.desc2 = desc2;
        }

        public String getDesc3()
        {
                return desc3;
        }

        public void setDesc3(String desc3)
        {
                this.desc3 = desc3;
        }

        public String getDesc4()
        {
                return desc4;
        }

        public void setDesc4(String desc4)
        {
                this.desc4 = desc4;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }
}
