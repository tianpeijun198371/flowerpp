/**
 * CodeModel.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import java.io.Serializable;


public class CodeModel implements Serializable
{
        private int codeID;
        private String codeName;
        private int defaultPassValue;

        public CodeModel()
        {
        }

        public CodeModel(int codeID, String codeName)
        {
                this.codeID = codeID;
                this.codeName = codeName;
        }

        public CodeModel(int codeID, String codeName, int defaultPassValue)
        {
                this.codeID = codeID;
                this.codeName = codeName;
                this.defaultPassValue = defaultPassValue;
        }

        public int getCodeID()
        {
                return codeID;
        }

        public void setCodeID(int codeID)
        {
                this.codeID = codeID;
        }

        public String getCodeName()
        {
                return codeName;
        }

        public void setCodeName(String codeName)
        {
                this.codeName = codeName;
        }

        public int getDefaultPassValue()
        {
                return defaultPassValue;
        }

        public void setDefaultPassValue(int defaultPassValue)
        {
                this.defaultPassValue = defaultPassValue;
        }
}
