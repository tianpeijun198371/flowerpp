package com.ulearning.ulms.content.schoolbook.model;

import com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachsort;


/**
 * This is the object class that relates to the M_TEACHSORT table.
 * Any customizations belong here.
 */
public class MTeachsort extends BaseMTeachsort
{
        /*[CONSTRUCTOR MARKER BEGIN]*/
        public MTeachsort()
        {
                super();
        }

        /**
         * Constructor for primary key
         */
        public MTeachsort(long _id)
        {
                super(_id);
        }

        /*[CONSTRUCTOR MARKER END]*/
        /*
         private java.lang.Integer _bsifid;
         private java.lang.String _bsifbookname;
         private Float _tcstprice;
         private Float _tcstquantity;
         private Float _tcsttotal;
         private java.lang.String _remark1;
         private java.lang.String _remark2;
         private java.lang.String _remark3;
       * */
        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("bsifid=").append(getBsifid());
                sb.append("\r\n").append("bsifbookname=").append(getBsifbookname());
                sb.append("\r\n").append("tcstprice=").append(getTcstprice());
                sb.append("\r\n").append("tcstquantity=").append(getTcstquantity());
                sb.append("\r\n").append("tcsttotal=").append(getTcsttotal());
                sb.append("\r\n").append("remark1=").append(getRemark1());
                sb.append("\r\n").append("remark2=").append(getRemark2());
                sb.append("\r\n").append("remark3=").append(getRemark3());

                return sb.toString();
        }
}
