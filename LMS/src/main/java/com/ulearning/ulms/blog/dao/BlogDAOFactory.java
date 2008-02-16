package com.ulearning.ulms.blog.dao;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2005-4-8
 */
public class BlogDAOFactory
{
        public static BlogDAO getDAO()
        {
                BlogDAO dao = new BlogDAOImpl();

                return dao;
        }
}
