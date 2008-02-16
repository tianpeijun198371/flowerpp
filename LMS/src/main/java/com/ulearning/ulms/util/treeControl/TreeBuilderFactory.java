/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-21
 * Time: 14:59:09
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.util.treeControl;

import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.util.*;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.organ.util.OrganTreeBuilder;
import org.apache.webapp.admin.TreeBuilder;

public class TreeBuilderFactory
{
        public static TreeBuilder getTreeBuilder(String type, String id)
        {
                TreeBuilder treeBuilder = null;
                if (type.equals(LMSConstants.TREE_COURSE))
                {
                        treeBuilder = new CourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_PUBLISH_COURSE))
                {
                        treeBuilder = new CourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_CERT))
                {
                        treeBuilder = new CertTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_PUBLISH_CERT))
                {
                        treeBuilder = new CertTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_CERT_MASTER))
                {
                        treeBuilder = new CertCourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_PUBLISH))
                {
                        treeBuilder = new PublishCourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_PUBLISHED_COURSE))
                {
                        treeBuilder = new PublishedCourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_PUBLISHED_CERT))
                {
                        treeBuilder = new PublishedCertTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_SELECT))
                {
                        treeBuilder = new SelectCourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_ORGAN))
                {
                        treeBuilder = new OrganTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_BROWSE_COURSE))
                {
                        treeBuilder = new BrowseCourseTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_SELECT_NO_SELECT_COURSE))
                {
                        treeBuilder = new SelectCourseForNoApplyTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_STUDENT_SIGNUP))
                {
                        treeBuilder = new SelectCourseForNoApplyTreeBuilder();
                }
                if (type.equals(LMSConstants.TREE_CERT_OFFLINE))
                {
                        treeBuilder = new OffLineCertTreeBuild();
                }
                return treeBuilder;
        }
}
