/*
 * Copyright (c) 2004 HuaXia. All Rights Reserved.
 */
package com.ulearning.ulms.course.model;

import java.util.List;


public class CourseCertList
{
        private List courseList;
        private List certList;

        public CourseCertList()
        {
        }

        public CourseCertList(List courseList, List certList)
        {
                this.courseList = courseList;
                this.certList = certList;
        }

        public List getCourseList()
        {
                return courseList;
        }

        public void setCourseList(List courseList)
        {
                this.courseList = courseList;
        }

        public List getCertList()
        {
                return certList;
        }

        public void setCertList(List certList)
        {
                this.certList = certList;
        }
}
