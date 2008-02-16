/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.Link;
import com.ulearning.ulms.bloger.exception.*;

import java.util.List;


/**
 * Inteface of all operations on link table.
 *
 * @author Huaxia
 */
public interface LinkDao
{
        // for browse:
        List getAllLinks(int accountId) throws QueryException;

        Link getLink(int linkId) throws QueryException;

        int getLinksCount(int categoryId) throws QueryException;

        // for management:
        void createLink(Link link) throws CreateException;

        void updateLink(Link link) throws UpdateException;

        void deleteLink(int linkId) throws DeleteException;
}
