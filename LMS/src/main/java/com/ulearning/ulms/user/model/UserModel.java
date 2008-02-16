package com.ulearning.ulms.user.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class UserModel implements Serializable
{

        /**
         * identifier field
         */
        private int userid;

        /**
         * persistent field
         */
        private String loginname;

        /**
         * persistent field
         */
        private String password;

        private String plainPassword;

        //集成系统的ID，如学窗系统对应用户的ID
        private Integer externalSystemUserID;


        private String fax;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * persistent field
         */
        private String available;

        /**
         * nullable persistent field
         */
        private String mail;

        /**
         * nullable persistent field
         */
        private String card;

        /**
         * nullable persistent field
         */
        private String sex;

        /**
         * nullable persistent field
         */
        private String phone;

        /**
         * nullable persistent field
         */
        private String cellphone;

        /**
         * nullable persistent field
         */
        private String address;

        /**
         * nullable persistent field
         */
        private String postalcode;

        /**
         * nullable persistent field
         */
        private String edulevel;

        /**
         * nullable persistent field
         */
        private String pwdquestion;

        /**
         * nullable persistent field
         */
        private String pwdanswer;

        /**
         * nullable persistent field
         */
        private Date registerdate;

        /**
         * nullable persistent field
         */
        private int catalogid;

        /**
         * nullable persistent field
         */
        private Date lastlogindate;

        /**
         * nullable persistent field
         */
        private String desc0;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * nullable persistent field
         */
        private String remark4;

        /**
         * nullable persistent field
         */
        private String remark5;

        /**
         * nullable persistent field
         */
        private String remark6;

        /**
         * nullable persistent field
         */
        private String remark7;

        /**
         * nullable persistent field
         */
        private String remark8;

        /**
         * nullable persistent field
         */
        private String remark9;

        private String remark10 = null;
        private String remark11 = null;
        private String remark12 = null;

        public UserModel(int userid, String loginname, String password, String plainPassword, String name, String available, String mail, String card, String sex, String phone, String cellphone, String address, String postalcode, String edulevel, String pwdquestion, String pwdanswer, Date registerdate, int catalogid, Date lastlogindate, String desc0, String desc1, String description, String remark1, String remark2, String remark3, String remark4, String remark5, String remark6, String remark7, String remark8, String remark9)
        {
                this.userid = userid;
                this.loginname = loginname;
                this.password = password;
                this.plainPassword = plainPassword;
                this.name = name;
                this.available = available;
                this.mail = mail;
                this.card = card;
                this.sex = sex;
                this.phone = phone;
                this.cellphone = cellphone;
                this.address = address;
                this.postalcode = postalcode;
                this.edulevel = edulevel;
                this.pwdquestion = pwdquestion;
                this.pwdanswer = pwdanswer;
                this.registerdate = registerdate;
                this.catalogid = catalogid;
                this.lastlogindate = lastlogindate;
                this.desc0 = desc0;
                this.desc1 = desc1;
                this.description = description;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
                this.remark6 = remark6;
                this.remark7 = remark7;
                this.remark8 = remark8;
                this.remark9 = remark9;
        }

        /**
         * default constructor
         */
        public UserModel()
        {
        }


        /**
         * minimal constructor
         */
        public UserModel(String loginname, String password, String available)
        {
                this.loginname = loginname;
                this.password = password;
                this.available = available;
        }

        public Integer getExternalSystemUserID()
        {
                return externalSystemUserID;
        }

        public void setExternalSystemUserID(Integer externalSystemUserID)
        {
                this.externalSystemUserID = externalSystemUserID;
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

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public String getFax()
        {
                return fax;
        }

        public void setFax(String fax)
        {
                this.fax = fax;
        }

        public String getLoginname()
        {
                return this.loginname;
        }

        public void setLoginname(String loginname)
        {
                this.loginname = loginname;
        }

        public String getPassword()
        {
                return this.password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getAvailable()
        {
                return this.available;
        }

        public void setAvailable(String available)
        {
                this.available = available;
        }

        public String getMail()
        {
                return this.mail;
        }

        public void setMail(String mail)
        {
                this.mail = mail;
        }

        public String getPlainPassword()
        {
                return plainPassword;
        }

        public void setPlainPassword(String plainPassword)
        {
                this.plainPassword = plainPassword;
        }

        public String getCard()
        {
                return this.card;
        }

        public void setCard(String card)
        {
                this.card = card;
        }

        public String getSex()
        {
                return this.sex;
        }

        public void setSex(String sex)
        {
                this.sex = sex;
        }

        public String getPhone()
        {
                return this.phone;
        }

        public void setPhone(String phone)
        {
                this.phone = phone;
        }

        public String getCellphone()
        {
                return this.cellphone;
        }

        public void setCellphone(String cellphone)
        {
                this.cellphone = cellphone;
        }

        public String getAddress()
        {
                return this.address;
        }

        public void setAddress(String address)
        {
                this.address = address;
        }

        public String getPostalcode()
        {
                return this.postalcode;
        }

        public void setPostalcode(String postalcode)
        {
                this.postalcode = postalcode;
        }

        public String getEdulevel()
        {
                return this.edulevel;
        }

        public void setEdulevel(String edulevel)
        {
                this.edulevel = edulevel;
        }

        public String getPwdquestion()
        {
                return this.pwdquestion;
        }

        public void setPwdquestion(String pwdquestion)
        {
                this.pwdquestion = pwdquestion;
        }

        public String getPwdanswer()
        {
                return this.pwdanswer;
        }

        public void setPwdanswer(String pwdanswer)
        {
                this.pwdanswer = pwdanswer;
        }

        public Date getRegisterdate()
        {
                return this.registerdate;
        }

        public void setRegisterdate(Date registerdate)
        {
                this.registerdate = registerdate;
        }

        public int getCatalogid()
        {
                return this.catalogid;
        }

        public void setCatalogid(int catalogid)
        {
                this.catalogid = catalogid;
        }

        public Date getLastlogindate()
        {
                return this.lastlogindate;
        }

        public void setLastlogindate(Date lastlogindate)
        {
                this.lastlogindate = lastlogindate;
        }

        public String getDesc0()
        {
                return this.desc0;
        }

        public void setDesc0(String desc0)
        {
                this.desc0 = desc0;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return this.remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return this.remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return this.remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }

        public String getRemark7()
        {
                return this.remark7;
        }

        public void setRemark7(String remark7)
        {
                this.remark7 = remark7;
        }

        public String getRemark8()
        {
                return this.remark8;
        }

        public void setRemark8(String remark8)
        {
                this.remark8 = remark8;
        }

        public String getRemark9()
        {
                return this.remark9;
        }

        public void setRemark9(String remark9)
        {
                this.remark9 = remark9;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("userid", getUserid())
                        .toString();
        }

}
