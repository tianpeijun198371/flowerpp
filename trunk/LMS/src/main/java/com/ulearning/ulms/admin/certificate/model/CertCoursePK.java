/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-4
 * Time: 10:50:04
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.certificate.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class CertCoursePK implements Serializable
{
        /**
         * identifier field
         */
        private int certificateID;

        /**
         * identifier field
         */
        private int courseID;

        /**
         * identifier field
         */
        private int type;

        /**
         * full constructor
         */
        public CertCoursePK(int certificateID, int courseID, int type)
        {
                this.certificateID = certificateID;
                this.courseID = courseID;
                this.type = type;
        }

        /**
         * default constructor
         */
        public CertCoursePK()
        {
        }

        public int getCertificateID()
        {
                return this.certificateID;
        }

        public void setCertificateID(int certificateID)
        {
                this.certificateID = certificateID;
        }

        public int getCourseID()
        {
                return this.courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("certificateID",
                        getCertificateID()).append("courseID", getCourseID())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof CertCoursePK))
                {
                        return false;
                }

                CertCoursePK castOther = (CertCoursePK) other;

                return new EqualsBuilder().append(this.getCertificateID(),
                        castOther.getCertificateID())
                        .append(this.getCourseID(),
                                castOther.getCourseID()).append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getCertificateID())
                        .append(getCourseID()).append(getType())
                        .toHashCode();
        }
}
