package com.ulearning.ulms.tools.message.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class MessageUserModel implements Serializable
{
        /**
         * identifier field
         */
        private com.ulearning.ulms.tools.message.model.MessageUserModelPK comp_id;
        public int type;

        /**
         * nullable persistent field
         */
        public MessageUserModel(MessageUserModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        /**
         * full constructor
         */
        public MessageUserModel()
        {
        }

        public MessageUserModel(MessageUserModelPK comp_id, int type)
        {
                this.comp_id = comp_id;
                this.type = type;
        }

        public MessageUserModelPK getComp_id()
        {
                return comp_id;
        }

        public void setComp_id(MessageUserModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }
}
