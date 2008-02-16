/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.user.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.user.model.UserModel;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class UserForm extends UploadForm
{

        private int userID = 0;
        private String loginName = null;
        private String password = null;
        private String name = null;
        private String available = null;
        private String mail = "";
        private String card = "";
        private String sex = null;
        private String phone = "";
        private String cellPhone = "";
        private String address = "";
        private String postalcode = "";
        private String eduLevel = null;
        private String pwdQuestion = "";
        private String pwdAnswer = "";
        private Date registerDate = null;
        private int catalogID = 0;
        private Date lastloginDate = null;
        private String lastloginDateStr = null;
        private String desc0 = null;
        private String desc1 = null;
        private String description = null;
        private String remark1 = null;
        private String remark2 = null;
        private String remark3 = null;
        private String remark4 = null;
        private String remark5 = null;
        private String remark6 = null;
        private String remark7 = null;
        private String remark8 = null;
        private String remark9 = null;
        private String remark10 = null;
        private String remark11 = null;
        private String remark12 = null;
        private String plainPassword = null;

        //集成系统的ID，如学窗系统对应用户的ID
        private Integer externalSystemUserID;

        private String fax;

        private int user_type = 0;
        String[] roleID = null;
        String[] orgRoleID = null;
        private int aspID;


        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getLoginName()
        {
                return loginName;
        }

        public void setLoginName(String loginName)
        {
                this.loginName = loginName;
        }

        public Integer getExternalSystemUserID()
        {
                return externalSystemUserID;
        }

        public void setExternalSystemUserID(Integer externalSystemUserID)
        {
                this.externalSystemUserID = externalSystemUserID;
        }

        public String getPassword()
        {
                return password;
        }

        public String getFax()
        {
                return fax;
        }

        public void setFax(String fax)
        {
                this.fax = fax;
        }

        public String getRemark10()
        {
                return remark10;
        }

        public void setRemark10(String remark10)
        {
                this.remark10 = remark10;
        }

        public String getRemark11()
        {
                return remark11;
        }

        public void setRemark11(String remark11)
        {
                this.remark11 = remark11;
        }

        public String getRemark12()
        {
                return remark12;
        }

        public void setRemark12(String remark12)
        {
                this.remark12 = remark12;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getLastloginDateStr()
        {
                return lastloginDateStr;
        }

        public void setLastloginDateStr(String lastloginDateStr)
        {
                this.lastloginDateStr = lastloginDateStr;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getAvailable()
        {
                return available;
        }

        public void setAvailable(String available)
        {
                this.available = available;
        }

        public String getMail()
        {
                return mail;
        }

        public void setMail(String mail)
        {
                this.mail = mail;
        }

        public String getCard()
        {
                return card;
        }

        public void setCard(String card)
        {
                this.card = card;
        }

        public String getSex()
        {
                return sex;
        }

        public void setSex(String sex)
        {
                this.sex = sex;
        }

        public String getPhone()
        {
                return phone;
        }

        public void setPhone(String phone)
        {
                this.phone = phone;
        }

        public String getCellPhone()
        {
                return cellPhone;
        }

        public void setCellPhone(String cellPhone)
        {
                this.cellPhone = cellPhone;
        }

        public String getAddress()
        {
                return address;
        }

        public void setAddress(String address)
        {
                this.address = address;
        }

        public String getPostalcode()
        {
                return postalcode;
        }

        public void setPostalcode(String postalcode)
        {
                this.postalcode = postalcode;
        }

        public String getEduLevel()
        {
                return eduLevel;
        }

        public void setEduLevel(String eduLevel)
        {
                this.eduLevel = eduLevel;
        }

        public String getPwdQuestion()
        {
                return pwdQuestion;
        }

        public void setPwdQuestion(String pwdQuestion)
        {
                this.pwdQuestion = pwdQuestion;
        }

        public String getPwdAnswer()
        {
                return pwdAnswer;
        }

        public void setPwdAnswer(String pwdAnswer)
        {
                this.pwdAnswer = pwdAnswer;
        }

        public Date getRegisterDate()
        {
                return registerDate;
        }

        public void setRegisterDate(Date registerDate)
        {
                this.registerDate = registerDate;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public Date getLastloginDate()
        {
                return lastloginDate;
        }

        public void setLastloginDate(Date lastloginDate)
        {
                this.lastloginDate = lastloginDate;
        }

        public String getDesc0()
        {
                return desc0;
        }

        public void setDesc0(String desc0)
        {
                this.desc0 = desc0;
        }

        public String getDesc1()
        {
                return desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }

        public String getRemark7()
        {
                return remark7;
        }

        public void setRemark7(String remark7)
        {
                this.remark7 = remark7;
        }

        public String getRemark8()
        {
                return remark8;
        }

        public void setRemark8(String remark8)
        {
                this.remark8 = remark8;
        }

        public String getRemark9()
        {
                return remark9;
        }

        public void setRemark9(String remark9)
        {
                this.remark9 = remark9;
        }

        public int getUser_type()
        {
                return user_type;
        }

        public void setUser_type(int user_type)
        {
                this.user_type = user_type;
        }

        public String[] getRoleID()
        {
                return roleID;
        }

        public void setRoleID(String[] roleID)
        {
                this.roleID = roleID;
        }

        public String[] getOrgRoleID()
        {
                return orgRoleID;
        }

        public void setOrgRoleID(String[] orgRoleID)
        {
                this.orgRoleID = orgRoleID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public String getPlainPassword()
        {
                return plainPassword;
        }

        public void setPlainPassword(String plainPassword)
        {
                this.plainPassword = plainPassword;
        }

        public UserForm()
        {

        }

        public UserForm(UserModel um)
        {
                if (um != null)
                {
                        this.userID = um.getUserid();
                        this.loginName = um.getLoginname();
                        this.password = um.getPassword();
                        this.plainPassword = um.getPlainPassword();
                        this.name = um.getName();
                        this.available = um.getAvailable();
                        this.mail = um.getMail();
                        this.card = um.getCard();
                        this.sex = StringUtil.nullToStr(um.getSex());
                        this.phone = StringUtil.nullToStr(um.getPhone());
                        this.cellPhone = StringUtil.nullToStr(um.getCellphone());
                        this.address = StringUtil.nullToStr(um.getAddress());
                        this.postalcode = StringUtil.nullToStr(um.getPostalcode());
                        this.eduLevel = StringUtil.nullToStr(um.getEdulevel());
                        this.pwdQuestion = um.getPwdquestion();
                        this.pwdAnswer = um.getPwdanswer();
                        this.registerDate = um.getRegisterdate();
                        this.catalogID = um.getCatalogid();
                        this.lastloginDate = um.getLastlogindate();
                        this.desc0 = StringUtil.nullToStr(um.getDesc0());
                        this.desc1 = StringUtil.nullToStr(um.getDesc1());
                        this.description = StringUtil.nullToStr(um.getDescription());
                        this.remark1 = StringUtil.nullToStr(um.getRemark1());
                        this.remark2 = StringUtil.nullToStr(um.getRemark2());
                        this.remark3 = StringUtil.nullToStr(um.getRemark3());
                        this.remark4 = StringUtil.nullToStr(um.getRemark4());
                        this.remark5 = StringUtil.nullToStr(um.getRemark5());
                        this.remark6 = StringUtil.nullToStr(um.getRemark6());
                        this.remark7 = StringUtil.nullToStr(um.getRemark7());
                        this.remark8 = StringUtil.nullToStr(um.getRemark8());
                        this.remark9 = StringUtil.nullToStr(um.getRemark9());
                        this.remark10 = StringUtil.nullToStr(um.getRemark10());
                        this.remark11 = StringUtil.nullToStr(um.getRemark11());
                        this.remark12 = StringUtil.nullToStr(um.getRemark12());
                        this.fax = StringUtil.nullToStr(um.getFax());
                        this.externalSystemUserID = um.getExternalSystemUserID();
                }
        }

        public UserModel getUserModel()
        {
                UserModel um = new UserModel();
                um.setUserid(this.userID);
                um.setLoginname(this.loginName);
                um.setPassword(this.password);
                um.setPlainPassword(this.plainPassword);
                um.setName(this.name);
                um.setAvailable(this.available);
                um.setMail(this.mail);
                um.setCard(this.card);
                um.setSex(this.sex);
                um.setPhone(this.phone);
                um.setCellphone(this.cellPhone);
                um.setAddress(this.address);
                um.setPostalcode(this.postalcode);
                um.setEdulevel(this.eduLevel);
                um.setPwdquestion(this.pwdQuestion);
                um.setPwdanswer(this.pwdAnswer);
                um.setRegisterdate(this.registerDate);
                um.setCatalogid(this.catalogID);
                um.setLastlogindate(this.lastloginDate);
                um.setDesc0(this.desc0);
                um.setDesc1(this.desc1);
                um.setDescription(this.description);
                um.setRemark1(this.remark1);
                um.setRemark2(this.remark2);
                um.setRemark3(this.remark3);
                um.setRemark4(this.remark4);
                um.setRemark5(this.remark5);
                um.setRemark6(this.remark6);
                um.setRemark7(this.remark7);
                um.setRemark8(this.remark8);
                um.setRemark9(this.remark9);
                um.setRemark10(this.remark10);
                um.setRemark11(this.remark11);
                um.setRemark12(this.remark12);
                um.setFax(this.fax);
                um.setExternalSystemUserID(this.externalSystemUserID);
                return um;
        }

        /**
         * Validate the properties that have been set from this HTTP request,
         * and return an <code>ActionErrors</code> object that encapsulates any
         * validation errors that have been found.  If no errors are found, return
         * <code>null</code> or an <code>ActionErrors</code> object with no
         * recorded error messages.
         *
         * @param mapping The mapping used to select this instance
         * @param request The servlet request we are processing
         */
        public ActionErrors validate(ActionMapping mapping,
                                     HttpServletRequest request)
        {

                // Perform validator framework validations
                ActionErrors errors = super.validate(mapping, request);
                if (!GenericValidator.isBlankOrNull(loginName))
                {
                        loginName = loginName.trim();
                }
                if (!GenericValidator.isBlankOrNull(password))
                {
                        password = password.trim();
                }
                return errors;

        }

}
