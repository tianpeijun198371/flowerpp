/**
 * ��Դʹ�ü�¼��Model
 */
package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class RResuserecordModel implements Serializable
{
        /**
         * ����
         */
        private int resuseid;

        /**
         * ��ԴID
         */
        private int resid;

        /**
         * ��Դ����
         */
        private String resname;

        /**
         * Ԥ��ASPID
         */
        private int aspid;

        /**
         * Ԥ��ORGID
         */
        private int orgid;

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
        private int userid;

        /**
         * Ԥ��������
         */
        private String username;

        /**
         * Ԥ��ʹ�ÿ�ʼ����
         */
        private Date userbegindate;

        /**
         * Ԥ����������
         */
        private Date userenddate;

        /**
         * ��;
         */
        private String respurpost;

        /**
         * �û�ɾ�����
         * 0��û��ɾ��  1���Ѿ�ɾ��
         */
        private int userdel;

        /**
         * ��˱��0����Ԥ��  1�����ͨ��   2����˲�ͨ��
         */
        private int audittag;

        /**
         * �����ID
         */
        private int audituserid;

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

        /**
         * full constructor
         */
        public RResuserecordModel(int resid, String resname, int aspid, int orgid,
                                  String aspname, String orgname, int userid, String username,
                                  Date userbegindate, Date userenddate, String respurpost, int userdel,
                                  int audittag, int audituserid, String auditusername,
                                  String uresremark1, String uresremark2)
        {
                this.resid = resid;
                this.resname = resname;
                this.aspid = aspid;
                this.orgid = orgid;
                this.aspname = aspname;
                this.orgname = orgname;
                this.userid = userid;
                this.username = username;
                this.userbegindate = userbegindate;
                this.userenddate = userenddate;
                this.respurpost = respurpost;
                this.userdel = userdel;
                this.audittag = audittag;
                this.audituserid = audituserid;
                this.auditusername = auditusername;
                this.uresremark1 = uresremark1;
                this.uresremark2 = uresremark2;
        }

        /**
         * default constructor
         */
        public RResuserecordModel()
        {
        }

        public int getResuseid()
        {
                return this.resuseid;
        }

        public void setResuseid(int resuseid)
        {
                this.resuseid = resuseid;
        }

        public int getResid()
        {
                return this.resid;
        }

        public void setResid(int resid)
        {
                this.resid = resid;
        }

        public String getResname()
        {
                return this.resname;
        }

        public void setResname(String resname)
        {
                this.resname = resname;
        }

        public int getAspid()
        {
                return this.aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getAspname()
        {
                return this.aspname;
        }

        public void setAspname(String aspname)
        {
                this.aspname = aspname;
        }

        public String getOrgname()
        {
                return this.orgname;
        }

        public void setOrgname(String orgname)
        {
                this.orgname = orgname;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public String getUsername()
        {
                return this.username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public Date getUserbegindate()
        {
                return userbegindate;
        }

        public void setUserbegindate(Date userbegindate)
        {
                this.userbegindate = userbegindate;
        }

        public Date getUserenddate()
        {
                return userenddate;
        }

        public void setUserenddate(Date userenddate)
        {
                this.userenddate = userenddate;
        }

        public String getRespurpost()
        {
                return this.respurpost;
        }

        public void setRespurpost(String respurpost)
        {
                this.respurpost = respurpost;
        }

        public int getUserdel()
        {
                return this.userdel;
        }

        public void setUserdel(int userdel)
        {
                this.userdel = userdel;
        }

        public int getAudittag()
        {
                return this.audittag;
        }

        public void setAudittag(int audittag)
        {
                this.audittag = audittag;
        }

        public int getAudituserid()
        {
                return this.audituserid;
        }

        public void setAudituserid(int audituserid)
        {
                this.audituserid = audituserid;
        }

        public String getAuditusername()
        {
                return this.auditusername;
        }

        public void setAuditusername(String auditusername)
        {
                this.auditusername = auditusername;
        }

        public String getUresremark1()
        {
                return this.uresremark1;
        }

        public void setUresremark1(String uresremark1)
        {
                this.uresremark1 = uresremark1;
        }

        public String getUresremark2()
        {
                return this.uresremark2;
        }

        public void setUresremark2(String uresremark2)
        {
                this.uresremark2 = uresremark2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("resuseid", getResuseid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof RResuserecordModel))
                {
                        return false;
                }

                RResuserecordModel castOther = (RResuserecordModel) other;

                return new EqualsBuilder().append(this.getResuseid(),
                        castOther.getResuseid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getResuseid()).toHashCode();
        }
}
