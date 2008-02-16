package com.ulearning.ulms.content.schoolbook.model.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 * <p/>
 * This is an object that contains data related to the M_TEACHMAINSTOR table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="M_TEACHMAINSTOR"
 */
public abstract class BaseMTeachmainstor implements Serializable
{
        public static String PROP_TCMAINAUDMARK = "tcmainaudmark";
        public static String PROP_TCMAINUSER = "tcmainuser";
        public static String PROP_INMAINMARK = "inmainmark";
        public static String PROP_REMARK3 = "remark3";
        public static String PROP_TCMAINDATE = "tcmaindate";
        public static String PROP_REMARK1 = "remark1";
        public static String PROP_TCMAINAUDMAN = "tcmainaudman";
        public static String PROP_TCMAINMODE = "tcmainmode";
        public static String PROP_TCMAINMEMO = "tcmainmemo";
        public static String PROP_REMARK2 = "remark2";
        public static String PROP_ID = "id";
        public static String PROP_TCMAINOPERATOR = "tcmainoperator";
        public static String PROP_TCMAINTRAN = "tcmaintran";
        private int hashCode = Integer.MIN_VALUE;

        // primary key
        private long _id;

        // fields
        private java.util.Date _tcmaindate;
        private java.lang.String _tcmainoperator;
        private java.lang.String _tcmainmemo;
        private java.lang.Integer _tcmainaudmark;
        private java.lang.String _tcmainaudman;
        private java.lang.Integer _tcmainmode;
        private java.lang.Integer _inmainmark;
        private java.lang.String _tcmaintran;
        private java.lang.String _tcmainuser;
        private java.lang.String _remark1;
        private java.lang.String _remark2;
        private java.lang.String _remark3;

        // constructors
        public BaseMTeachmainstor()
        {
                initialize();
        }

        /**
         * Constructor for primary key
         */
        public BaseMTeachmainstor(long _id)
        {
                this.setId(_id);
                initialize();
        }

        protected void initialize()
        {
        }

        /**
         * Return the unique identifier of this class
         *
         * @hibernate.id generator-class="sequence"
         * column="TCMAINID"
         */
        public long getId()
        {
                return _id;
        }

        /**
         * Set the unique identifier of this class
         *
         * @param _id the new ID
         */
        public void setId(long _id)
        {
                this._id = _id;
                this.hashCode = Integer.MIN_VALUE;
        }

        /**
         * Return the value associated with the column: TCMAINDATE
         */
        public java.util.Date getTcmaindate()
        {
                return _tcmaindate;
        }

        /**
         * Set the value related to the column: TCMAINDATE
         *
         * @param _tcmaindate the TCMAINDATE value
         */
        public void setTcmaindate(java.util.Date _tcmaindate)
        {
                this._tcmaindate = _tcmaindate;
        }

        /**
         * Return the value associated with the column: TCMAINOPERATOR
         */
        public java.lang.String getTcmainoperator()
        {
                return _tcmainoperator;
        }

        /**
         * Set the value related to the column: TCMAINOPERATOR
         *
         * @param _tcmainoperator the TCMAINOPERATOR value
         */
        public void setTcmainoperator(java.lang.String _tcmainoperator)
        {
                this._tcmainoperator = _tcmainoperator;
        }

        /**
         * Return the value associated with the column: TCMAINMEMO
         */
        public java.lang.String getTcmainmemo()
        {
                return _tcmainmemo;
        }

        /**
         * Set the value related to the column: TCMAINMEMO
         *
         * @param _tcmainmemo the TCMAINMEMO value
         */
        public void setTcmainmemo(java.lang.String _tcmainmemo)
        {
                this._tcmainmemo = _tcmainmemo;
        }

        /**
         * Return the value associated with the column: TCMAINAUDMARK
         */
        public java.lang.Integer getTcmainaudmark()
        {
                return _tcmainaudmark;
        }

        /**
         * Set the value related to the column: TCMAINAUDMARK
         *
         * @param _tcmainaudmark the TCMAINAUDMARK value
         */
        public void setTcmainaudmark(java.lang.Integer _tcmainaudmark)
        {
                this._tcmainaudmark = _tcmainaudmark;
        }

        /**
         * Return the value associated with the column: TCMAINAUDMAN
         */
        public java.lang.String getTcmainaudman()
        {
                return _tcmainaudman;
        }

        /**
         * Set the value related to the column: TCMAINAUDMAN
         *
         * @param _tcmainaudman the TCMAINAUDMAN value
         */
        public void setTcmainaudman(java.lang.String _tcmainaudman)
        {
                this._tcmainaudman = _tcmainaudman;
        }

        /**
         * Return the value associated with the column: TCMAINMODE
         */
        public java.lang.Integer getTcmainmode()
        {
                return _tcmainmode;
        }

        /**
         * Set the value related to the column: TCMAINMODE
         *
         * @param _tcmainmode the TCMAINMODE value
         */
        public void setTcmainmode(java.lang.Integer _tcmainmode)
        {
                this._tcmainmode = _tcmainmode;
        }

        /**
         * Return the value associated with the column: INMAINMARK
         */
        public java.lang.Integer getInmainmark()
        {
                return _inmainmark;
        }

        /**
         * Set the value related to the column: INMAINMARK
         *
         * @param _inmainmark the INMAINMARK value
         */
        public void setInmainmark(java.lang.Integer _inmainmark)
        {
                this._inmainmark = _inmainmark;
        }

        /**
         * Return the value associated with the column: TCMAINTRAN
         */
        public java.lang.String getTcmaintran()
        {
                return _tcmaintran;
        }

        /**
         * Set the value related to the column: TCMAINTRAN
         *
         * @param _tcmaintran the TCMAINTRAN value
         */
        public void setTcmaintran(java.lang.String _tcmaintran)
        {
                this._tcmaintran = _tcmaintran;
        }

        /**
         * Return the value associated with the column: TCMAINUSER
         */
        public java.lang.String getTcmainuser()
        {
                return _tcmainuser;
        }

        /**
         * Set the value related to the column: TCMAINUSER
         *
         * @param _tcmainuser the TCMAINUSER value
         */
        public void setTcmainuser(java.lang.String _tcmainuser)
        {
                this._tcmainuser = _tcmainuser;
        }

        /**
         * Return the value associated with the column: REMARK1
         */
        public java.lang.String getRemark1()
        {
                return _remark1;
        }

        /**
         * Set the value related to the column: REMARK1
         *
         * @param _remark1 the REMARK1 value
         */
        public void setRemark1(java.lang.String _remark1)
        {
                this._remark1 = _remark1;
        }

        /**
         * Return the value associated with the column: REMARK2
         */
        public java.lang.String getRemark2()
        {
                return _remark2;
        }

        /**
         * Set the value related to the column: REMARK2
         *
         * @param _remark2 the REMARK2 value
         */
        public void setRemark2(java.lang.String _remark2)
        {
                this._remark2 = _remark2;
        }

        /**
         * Return the value associated with the column: REMARK3
         */
        public java.lang.String getRemark3()
        {
                return _remark3;
        }

        /**
         * Set the value related to the column: REMARK3
         *
         * @param _remark3 the REMARK3 value
         */
        public void setRemark3(java.lang.String _remark3)
        {
                this._remark3 = _remark3;
        }

        public boolean equals(Object obj)
        {
                if (null == obj)
                {
                        return false;
                }

                if (!(obj instanceof com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachmainstor))
                {
                        return false;
                }
                else
                {
                        com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachmainstor mObj =
                                (com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachmainstor) obj;

                        return (this.getId() == mObj.getId());
                }
        }

        public int hashCode()
        {
                if (Integer.MIN_VALUE == this.hashCode)
                {
                        return (int) this.getId();
                }

                return this.hashCode;
        }

        public String toString()
        {
                return super.toString();
        }
}