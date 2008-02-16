package com.ulearning.ulms.content.schoolbook.model.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 * <p/>
 * This is an object that contains data related to the M_TEACHINSTOR_TAB table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="M_TEACHINSTOR_TAB"
 */
public abstract class BaseMTeachinstorTab implements Serializable
{
        public static String PROP_TCINMEMO = "tcinmemo";
        public static String PROP_REMARK2 = "remark2";
        public static String PROP_TCINOPERATOR = "tcinoperator";
        public static String PROP_TCINAUDMAN = "tcinaudman";
        public static String PROP_REMARK3 = "remark3";
        public static String PROP_TCINAUDMARK = "tcinaudmark";
        public static String PROP_REMARK1 = "remark1";
        public static String PROP_TCINDATE = "tcindate";
        public static String PROP_ID = "id";
        private int hashCode = Integer.MIN_VALUE;

        // primary key
        private long _id;

        // fields
        private java.util.Date _tcindate;
        private java.lang.String _tcinoperator;
        private java.lang.String _tcinmemo;
        private java.lang.Integer _tcinaudmark;
        private java.lang.String _tcinaudman;
        private java.lang.String _remark1;
        private java.lang.String _remark2;
        private java.lang.String _remark3;

        // constructors
        public BaseMTeachinstorTab()
        {
                initialize();
        }

        /**
         * Constructor for primary key
         */
        public BaseMTeachinstorTab(long _id)
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
         * column="TCINID"
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
         * Return the value associated with the column: TCINDATE
         */
        public java.util.Date getTcindate()
        {
                return _tcindate;
        }

        /**
         * Set the value related to the column: TCINDATE
         *
         * @param _tcindate the TCINDATE value
         */
        public void setTcindate(java.util.Date _tcindate)
        {
                this._tcindate = _tcindate;
        }

        /**
         * Return the value associated with the column: TCINOPERATOR
         */
        public java.lang.String getTcinoperator()
        {
                return _tcinoperator;
        }

        /**
         * Set the value related to the column: TCINOPERATOR
         *
         * @param _tcinoperator the TCINOPERATOR value
         */
        public void setTcinoperator(java.lang.String _tcinoperator)
        {
                this._tcinoperator = _tcinoperator;
        }

        /**
         * Return the value associated with the column: TCINMEMO
         */
        public java.lang.String getTcinmemo()
        {
                return _tcinmemo;
        }

        /**
         * Set the value related to the column: TCINMEMO
         *
         * @param _tcinmemo the TCINMEMO value
         */
        public void setTcinmemo(java.lang.String _tcinmemo)
        {
                this._tcinmemo = _tcinmemo;
        }

        /**
         * Return the value associated with the column: TCINAUDMARK
         */
        public java.lang.Integer getTcinaudmark()
        {
                return _tcinaudmark;
        }

        /**
         * Set the value related to the column: TCINAUDMARK
         *
         * @param _tcinaudmark the TCINAUDMARK value
         */
        public void setTcinaudmark(java.lang.Integer _tcinaudmark)
        {
                this._tcinaudmark = _tcinaudmark;
        }

        /**
         * Return the value associated with the column: TCINAUDMAN
         */
        public java.lang.String getTcinaudman()
        {
                return _tcinaudman;
        }

        /**
         * Set the value related to the column: TCINAUDMAN
         *
         * @param _tcinaudman the TCINAUDMAN value
         */
        public void setTcinaudman(java.lang.String _tcinaudman)
        {
                this._tcinaudman = _tcinaudman;
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

                if (!(obj instanceof com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachinstorTab))
                {
                        return false;
                }
                else
                {
                        com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachinstorTab mObj =
                                (com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachinstorTab) obj;

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
