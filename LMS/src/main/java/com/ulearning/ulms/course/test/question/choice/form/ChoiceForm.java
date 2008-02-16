/** * ChoiceForm.java.
 * User: xiejh  Date: 2004-6-16 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.question.choice.form;

import com.ulearning.ulms.course.test.question.base.form.BaseForm;

import java.util.ArrayList;


public class ChoiceForm
{
        private BaseForm baseForm = null;
        private ArrayList list = null;

        public BaseForm getBaseForm()
        {
                return baseForm;
        }

        public void setBaseForm(BaseForm baseForm)
        {
                this.baseForm = baseForm;
        }

        public ArrayList getList()
        {
                return list;
        }

        public void setList(ArrayList list)
        {
                this.list = list;
        }
}
