package com.ulearning.ulms.course.model;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-27
 * Time: 23:32:36
 * To change this template use File | Settings | File Templates.
 */
public class CourseTreeModel implements Serializable
{
        private CourseListModel courseList;
        private CatalogListModel catalogList;

        public CourseTreeModel()
        {
        }

        public CourseTreeModel(CourseListModel courseList,
                               CatalogListModel catalogList)
        {
                this.courseList = courseList;
                this.catalogList = catalogList;
        }

        public CourseListModel getCourseList()
        {
                return courseList;
        }

        public void setCourseList(CourseListModel courseList)
        {
                this.courseList = courseList;
        }

        public CatalogListModel getCatalogList()
        {
                return catalogList;
        }

        public void setCatalogList(CatalogListModel catalogList)
        {
                this.catalogList = catalogList;
        }
}
