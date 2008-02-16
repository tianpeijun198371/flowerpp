/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;

import java.util.*;


/**
 * Inteface of all operations on category table.
 *
 * @author Huaxia
 */
public interface CategoryDao
{
        // for browse:
        List getCategories(int accountId) throws QueryException;

        List getCategoriesOfArticle(int accountId) throws QueryException;

        List getCategoriesOfType(int accountId, int type) throws QueryException;

        Category getCategory(int categoryId) throws QueryException;

        // for management:
        void createCategory(Category category) throws CreateException;

        void updateCategory(Category category) throws UpdateException;

        void deleteCategory(int categoryId) throws DeleteException;
}
