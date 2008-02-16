/*
 * Created on 2004-9-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;


/**
 * Inteface of all operations on sequence table.
 * SequenceDao produces the next id for inserting a new record.
 *
 * @author Huaxia
 */
public interface SequenceDao
{
        int getNextAccountId();

        int getNextCategoryId();

        int getNextArticleId();

        int getNextFeedbackId();

        int getNextLinkId();

        int getNextImageId();

        int getNextMessageId();
}
