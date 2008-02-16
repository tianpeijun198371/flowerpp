package com.ulearning.ulms.content.schoolbook.model;

import com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachstorItemTab;


/**
 * This is the object class that relates to the M_TEACHSTOR_ITEM_TAB table.
 * Any customizations belong here.
 */
public class MTeachstorItemTab extends BaseMTeachstorItemTab
{
        /*[CONSTRUCTOR MARKER BEGIN]*/
        public MTeachstorItemTab()
        {
                super();
        }

        /**
         * Constructor for primary key
         */
        public MTeachstorItemTab(long _id)
        {
                super(_id);
        }

        /*
       * private java.lang.Integer _tcmainid;
           private java.lang.Integer _bsifid;
           private java.lang.String _bsifbookname;
           private float _tcitemprice;
           private float _tcitemquantity;
           private float _tcitemtotal;
           private java.lang.Integer _tcitemtclientid;
           private java.lang.String _tcitemtclientname;
           private java.lang.Integer _tcitemsupplierid;
           private java.lang.String _tcitemsuppliername;
           private java.lang.Integer _tcitemsourceid;
           private java.lang.String _tcitemsourcename;
       * */
        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("tcmainid=").append(this.getTcmainid());
                sb.append("\r\n").append("bsifid=").append(this.getBsifid());
                sb.append("\r\n").append("bsifbookname=").append(this.getBsifbookname());
                sb.append("\r\n").append("tcitemprice=").append(this.getTcitemprice());
                sb.append("\r\n").append("tcitemquantity=")
                        .append(this.getTcitemquantity());
                sb.append("\r\n").append("tcitemtotal=").append(this.getTcitemtotal());
                sb.append("\r\n").append("tcitemtclientid=")
                        .append(this.getTcitemtclientid());
                sb.append("\r\n").append("tcitemtclientname=")
                        .append(this.getTcitemtclientname());
                sb.append("\r\n").append("tcitemsupplierid=")
                        .append(this.getTcitemsupplierid());
                sb.append("\r\n").append("tcitemsuppliername=")
                        .append(this.getTcitemsuppliername());
                sb.append("\r\n").append("tcitemsourceid=")
                        .append(this.getTcitemsourceid());
                sb.append("\r\n").append("tcitemsourcename=")
                        .append(this.getTcitemsourcename());

                return sb.toString();
        }
}
