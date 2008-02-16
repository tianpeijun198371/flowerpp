/*
 * Created on 2004-10-4
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.LinkDao;
import com.ulearning.ulms.bloger.domain.Link;
import com.ulearning.ulms.bloger.exception.*;

import java.sql.SQLException;

import java.util.List;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class SqlMapLinkDao implements LinkDao
{
        private SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public List getAllLinks(int accountId) throws QueryException
        {
                try
                {
                        return sqlMap.queryForList("getAllLinks", new Integer(accountId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public Link getLink(int linkId) throws QueryException
        {
                try
                {
                        return (Link) sqlMap.queryForObject("getLink", new Integer(linkId));
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public int getLinksCount(int categoryId) throws QueryException
        {
                try
                {
                        return ((Integer) sqlMap.queryForObject("getLinksCount",
                                new Integer(categoryId))).intValue();
                }
                catch (SQLException sqle)
                {
                        throw new QueryException(sqle);
                }
        }

        public void createLink(Link link) throws CreateException
        {
                try
                {
                        sqlMap.insert("createLink", link);
                }
                catch (SQLException sqle)
                {
                        throw new CreateException(sqle);
                }
        }

        public void updateLink(Link link) throws UpdateException
        {
                try
                {
                        sqlMap.update("updateLink", link);
                }
                catch (SQLException sqle)
                {
                        throw new UpdateException(sqle);
                }
        }

        public void deleteLink(int linkId) throws DeleteException
        {
                try
                {
                        sqlMap.delete("deleteLink", new Integer(linkId));
                }
                catch (SQLException sqle)
                {
                        throw new DeleteException(sqle);
                }
        }
}
