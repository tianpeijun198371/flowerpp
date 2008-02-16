/*
 * Created on 2004-8-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;

import java.util.List;


/**
 * Inteface of all operations on image table.
 *
 * @author Huaxia
 */
public interface ImageDao
{
        // for browse:
        Image getImage(int imageId) throws QueryException;

        List getImages(int categoryId, int num, int page) throws QueryException;

        List getImages(int categoryId) throws QueryException;

        int getImagesCount(int categoryId) throws QueryException;

        // for management:
        void createImage(Image image) throws CreateException;

        void deleteImage(int imageId) throws DeleteException;
}
