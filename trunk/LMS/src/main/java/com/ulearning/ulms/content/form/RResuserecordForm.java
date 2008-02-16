/**
 * RResuserecordForm.java.
 * User: liz  Date: 2006-2-20
 * ��Դʹ�ü�¼form
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.form;

import com.ulearning.ulms.content.model.RResuserecordModel;

import org.apache.struts.action.ActionForm;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.Date;


public class RResuserecordForm extends ActionForm
{
        /**
         * ����
         */
        private int resuseid = 0;

        /**
         * ��ԴID
         */
        private int resid = 0;

        /**
         * ��Դ����
         */
        private String resname;

        /**
         * Ԥ��ASPID
         */
        private int aspid = 0;

        /**
         * Ԥ��ORGID
         */
        private int orgid = 0;

        /**
         * Ԥ��ASP����
         */
        private String aspname;

        /**
         * Ԥ��ORG����
         */
        private String orgname;

        /**
         * Ԥ����ID
         */
        private int userid = 0;

        /**
         * Ԥ��������
         */
        private String username;

        /**
         * Ԥ��ʹ������
         */
        private Date userdate;

        /**
         * Ԥ����ʼʹ��ʱ
         */
        private String beginhour;

        /**
         * Ԥ����ʼʹ�÷�
         */
        private String bgeinminute;

        /**
         * Ԥ������ʹ��ʱ
         */
        private String endhour;

        /**
         * Ԥ������ʹ�÷�
         */
        private String endminute;

        /**
         * ��;
         */
        private String respurpost;

        /**
         * �û�ɾ�����
         * 0��û��ɾ��  1���Ѿ�ɾ��
         */
        private int userdel = 0;

        /**
         * ��˱��0����Ԥ��  1�����ͨ��   2����˲�ͨ��
         */
        private int audittag = 0;

        /**
         * �����ID
         */
        private int audituserid = 0;

        /**
         * ���������
         */
        private String auditusername;

        /**
         * nullable persistent field
         */
        private String uresremark1;

        /**
         * nullable persistent field
         */
        private String uresremark2;
        RResuserecordModel model = null;

        public RResuserecordForm()
        {
        }

        public RResuserecordForm(RResuserecordModel mod)
        {
                this.aspid = mod.getAspid();
                this.aspname = mod.getAspname();
                this.audittag = mod.getAudittag();
                this.audituserid = mod.getAudituserid();
                this.auditusername = mod.getAuditusername();
                this.beginhour = Integer.toString(mod.getUserbegindate().getHours());
                this.bgeinminute = Integer.toString(mod.getUserbegindate().getMinutes());
                this.endhour = Integer.toString(mod.getUserenddate().getHours());
                this.endminute = Integer.toString(mod.getUserenddate().getMinutes());
                this.orgid = mod.getOrgid();
                this.orgname = mod.getOrgname();
                this.resid = mod.getResid();
                this.resname = mod.getResname();
                this.respurpost = mod.getRespurpost();
                this.resuseid = mod.getResuseid();
                this.uresremark1 = mod.getUresremark1();
                this.uresremark2 = mod.getUresremark2();
                this.userdate = makedate(mod.getUserenddate());
                this.userdel = mod.getUserdel();
                this.userid = mod.getUserid();
                this.username = mod.getUsername();
        }

        public RResuserecordModel makeModel()
        {
                model = new RResuserecordModel();
                model.setAspid(this.aspid);
                model.setAspname(this.aspname);
                model.setAudittag(this.audittag);
                model.setAudituserid(this.audituserid);
                model.setAuditusername(this.auditusername);
                model.setUserbegindate(makebegDate(this.beginhour, this.bgeinminute));
                model.setUserenddate(makebegDate(this.endhour, this.endminute));
                model.setOrgid(this.orgid);
                model.setOrgname(this.orgname);
                model.setResid(this.resid);
                model.setResname(this.resname);
                model.setRespurpost(this.respurpost);
                model.setResuseid(this.resuseid);
                model.setUresremark1(this.uresremark1);
                model.setUresremark2(this.uresremark2);
                model.setUserdel(this.userdel);
                model.setUserid(this.userid);
                model.setUsername(this.username);

                return model;
        }

        /**
         * ����   yyyy-MM-dd
         *
         * @param d Date
         * @return
         */
        private Date makedate(Date d)
        {
                Date bd = null;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ParsePosition pos = new ParsePosition(0);
                bd = formatter.parse(formatter.format(d), pos);

                return bd;
        }

        /**
         * �ϳ� yyyy-MM-dd HH:mm:ss��ʽ����
         *
         * @param hour
         * @param minute
         * @return
         */
        private Date makebegDate(String hour, String minute)
        {
                Date bd = null;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ParsePosition pos = new ParsePosition(0);
                StringBuffer sDate = new StringBuffer();
                sDate.append(this.userdate);
                sDate.append(" ");
                sDate.append(hour);
                sDate.append(":");
                sDate.append(minute);
                sDate.append(":00");
                bd = formatter.parse(sDate.toString(), pos);

                return bd;
        }

        public int getResuseid()
        {
                return resuseid;
        }

        public void setResuseid(int resuseid)
        {
                this.resuseid = resuseid;
        }

        public int getResid()
        {
                return resid;
        }

        public void setResid(int resid)
        {
                this.resid = resid;
        }

        public String getResname()
        {
                return resname;
        }

        public void setResname(String resname)
        {
                this.resname = resname;
        }

        public int getAspid()
        {
                return aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
        }

        public int getOrgid()
        {
                return orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getAspname()
        {
                return aspname;
        }

        public void setAspname(String aspname)
        {
                this.aspname = aspname;
        }

        public String getOrgname()
        {
                return orgname;
        }

        public void setOrgname(String orgname)
        {
                this.orgname = orgname;
        }

        public int getUserid()
        {
                return userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public Date getUserdate()
        {
                return userdate;
        }

        public void setUserdate(Date userdate)
        {
                this.userdate = userdate;
        }

        public String getBeginhour()
        {
                return beginhour;
        }

        public void setBeginhour(String beginhour)
        {
                this.beginhour = beginhour;
        }

        public String getBgeinminute()
        {
                return bgeinminute;
        }

        public void setBgeinminute(String bgeinminute)
        {
                this.bgeinminute = bgeinminute;
        }

        public String getEndhour()
        {
                return endhour;
        }

        public void setEndhour(String endhour)
        {
                this.endhour = endhour;
        }

        public String getEndminute()
        {
                return endminute;
        }

        public void setEndminute(String endminute)
        {
                this.endminute = endminute;
        }

        public String getRespurpost()
        {
                return respurpost;
        }

        public void setRespurpost(String respurpost)
        {
                this.respurpost = respurpost;
        }

        public int getUserdel()
        {
                return userdel;
        }

        public void setUserdel(int userdel)
        {
                this.userdel = userdel;
        }

        public int getAudittag()
        {
                return audittag;
        }

        public void setAudittag(int audittag)
        {
                this.audittag = audittag;
        }

        public int getAudituserid()
        {
                return audituserid;
        }

        public void setAudituserid(int audituserid)
        {
                this.audituserid = audituserid;
        }

        public String getAuditusername()
        {
                return auditusername;
        }

        public void setAuditusername(String auditusername)
        {
                this.auditusername = auditusername;
        }

        public String getUresremark1()
        {
                return uresremark1;
        }

        public void setUresremark1(String uresremark1)
        {
                this.uresremark1 = uresremark1;
        }

        public String getUresremark2()
        {
                return uresremark2;
        }

        public void setUresremark2(String uresremark2)
        {
                this.uresremark2 = uresremark2;
        }
}
