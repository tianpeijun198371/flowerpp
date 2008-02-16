/*
 * Created on 2004-10-4
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.ImageDao;
import com.ulearning.ulms.bloger.domain.Image;
import com.ulearning.ulms.bloger.exception.*;

import java.sql.SQLException;

import java.util.*;


/**
 * Test SqlMapImage.
 *
 * @author Huaxia
 */
public class SqlMapImageDao implements ImageDao
{
        private SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public Image getImage(int imageId) throws QueryException
        {
                try
                {
                        return (Image) sqlMap.queryForObject("getImage",
                                new Integer(imageId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public List getImages(int categoryId, int num, int page)
                throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getImages", new Integer(categoryId),
                                num * (page - 1), num);
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public List getImages(int categoryId) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getImages", new Integer(categoryId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public int getImagesCount(int categoryId) throws QueryException
        {
                try
                {
                        return ((Integer) sqlMap.queryForObject("getImagesCount",
                                new Integer(categoryId))).intValue();
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public void createImage(Image image) throws CreateException
        {
                try
                {
                        sqlMap.insert("createImage", image);
                }
                catch (SQLException sqle)
                {
                        throw new CreateException(sqle);
                }
        }

        public void deleteImage(int imageId) throws DeleteException
        {
                try
                {
                        sqlMap.delete("deleteImage", new Integer(imageId));
                }
                catch (SQLException sqle)
                {
                        throw new DeleteException(sqle);
                }
        }
}
