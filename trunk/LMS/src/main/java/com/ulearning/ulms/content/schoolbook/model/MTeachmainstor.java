package com.ulearning.ulms.content.schoolbook.model;

import com.ulearning.ulms.content.schoolbook.model.base.BaseMTeachmainstor;


/**
 * This is the object class that relates to the M_TEACHMAINSTOR table.
 * Any customizations belong here.
 */
public class MTeachmainstor extends BaseMTeachmainstor
{
        /*[CONSTRUCTOR MARKER BEGIN]*/
        public MTeachmainstor()
        {
                super();
        }

        /**
         * Constructor for primary key
         */
        public MTeachmainstor(long _id)
        {
                super(_id);
        }

        public String toString()
        {
                StringBuffer sb = new StringBuffer(this.getClass().getName());
                sb.append("\r\n").append("tcmainaudmark=").append(getTcmainaudmark());
                sb.append("\r\n").append("tcmainuser=").append(getTcmainuser());
                sb.append("\r\n").append("inmainmark=").append(getInmainmark());
                sb.append("\r\n").append("remark3=").append(getRemark3());
                sb.append("\r\n").append("tcmaindate=").append(getTcmaindate());
                sb.append("\r\n").append("remark1=").append(getRemark1());
                sb.append("\r\n").append("tcmainaudman=").append(getTcmainaudman());
                sb.append("\r\n").append("tcmainmode=").append(getTcmainmode());
                sb.append("\r\n").append("tcmainmemo=").append(getTcmainmemo());
                sb.append("\r\n").append("remark2=").append(getRemark2());
                sb.append("\r\n").append("id=").append(getId());
                sb.append("\r\n").append("tcmainoperator=").append(getTcmainoperator());
                sb.append("\r\n").append("tcmaintran=").append(getTcmaintran());

                return sb.toString();
        }

        /*[CONSTRUCTOR MARKER END]*/
}
