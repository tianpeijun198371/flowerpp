package com.ulearning.ulms.content.schoolbook.model.base;

import java.io.Serializable;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 * <p/>
 * This is an object that contains data related to the M_TEACHOUTSTOR table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="M_TEACHOUTSTOR"
 */
public abstract class BaseMTeachoutstor implements Serializable
{
        public static String PROP_REMARK2 = "remark2";
        public static String PROP_TCOUTOPERATOR = "tcoutoperator";
        public static String PROP_REMARK3 = "remark3";
        public static String PROP_TCOUTMEMO = "tcoutmemo";
        public static String PROP_TCOUTMODE = "tcoutmode";
        public static String PROP_TCOUTAUDMAN = "tcoutaudman";
        public static String PROP_TCINAUDMARK = "tcinaudmark";
        public static String PROP_REMARK1 = "remark1";
        public static String PROP_TCOUTDATE = "tcoutdate";
        public static String PROP_ID = "id";
        private int hashCode = Integer.MIN_VALUE;

        // primary key
        private long _id;

        // fields
        private java.util.Date _tcoutdate;
        private java.lang.String _tcoutoperator;
        private java.lang.String _tcoutmemo;
        private java.lang.Integer _tcinaudmark;
        private java.lang.String _tcoutaudman;
        private java.lang.Integer _tcoutmode;
        private java.lang.String _remark1;
        private java.lang.String _remark2;
        private java.lang.String _remark3;

        // constructors
        public BaseMTeachoutstor()
        {
                initialize();
        }

        /**
         * Constructor for primary key
         */
        public BaseMTeachoutstor(long _id)
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
         * column="TCOUTID"
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
         * Return the value associated with the column: TCOUTDATE
         */
        public java.util.Date getTcoutdate()
        {
                return _tcoutdate;
        }

        /**
         * Set the value related to the column: TCOUTDATE
         *
         * @param _tcoutdate the TCOUTDATE value
         */
        public void setTcoutdate(java.util.Date _tcoutdate)
        {
                this._tcoutdate = _tcoutdate;
        }

        /**
         * Return the value associated with the column: TCOUTOPERATOR
         */
        public java.lang.String getTcoutoperator()
        {
                return _tcoutoperator;
        }

        /**
         * Set the value related to the column: TCOUTOPERATOR
         *
         * @param _tcoutoperator the TCOUTOPERATOR value
         */
        public void setTcoutoperator(java.lang.String _tcoutoperator)
        {
                this._tcoutoperator = _tcoutoperator;
        }

        /**
         * Return the value associated with the column: TCOUTMEMO
         */
        public java.lang.String getTcoutmemo()
        {
                return _tcoutmemo;
        }

        /**
         * Set the value related to the column: TCOUTMEMO
         *
         * @param _tcoutmemo the TCOUTMEMO value
         */
        public void setTcoutmemo(java.lang.String _tcoutmemo)
        {
                this._tcoutmemo = _tcoutmemo;
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
         * Return the value associated with the column: TCOUTAUDMAN
         */
        public java.lang.String getTcoutaudman()
        {
                return _tcoutaudman;
        }

        /**
         * Set the value related to the column: TCOUTAUDMAN
         *
         * @param _tcoutaudman the TCOUTAUDMAN value
         */
        public void setTcoutaudman(java.lang.String _tcoutaudman)
        {
                this._tcoutaudman = _tcoutaudman;
        }

        /**
         * Return the value associated with the column: TCOUTMODE
         */
        public java.lang.Integer getTcoutmode()
        {
                return _tcoutmode;
        }

        /**
         * Set the value related to the column: TCOUTMODE
         *
         * @param _tcoutmode the TCOUTMODE value
         */
        public void setTcoutmode(java.lang.Integer _tcoutmode)
        {
                this._tcoutmode = _tcoutmode;
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

                if (!(obj instanceof com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachoutstor))
                {
                        return false;
                }
                else
                {
                        com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachoutstor mObj =
                                (com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachoutstor) obj;

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
