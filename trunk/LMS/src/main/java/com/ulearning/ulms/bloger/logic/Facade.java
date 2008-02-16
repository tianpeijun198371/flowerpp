/*
 * Created on 2004-10-2
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.logic;

import com.ulearning.ulms.bloger.dao.*;
import com.ulearning.ulms.bloger.domain.*;
import com.ulearning.ulms.bloger.exception.*;
import com.ulearning.ulms.bloger.web.Identity;
import com.ulearning.ulms.core.util.Config;

import java.io.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * The implementation of Facade.
 *
 * @author Huaxia
 */
public class Facade
{
        private AccountDao accountDao;
        private ArticleDao articleDao;
        private CategoryDao categoryDao;
        private FeedbackDao feedbackDao;
        private ImageDao imageDao;
        private LinkDao linkDao;
        private SequenceDao sequenceDao;

        // for dependency injection:
        public void setAccountDao(AccountDao accountDao)
        {
                this.accountDao = accountDao;
        }

        public void setArticleDao(ArticleDao articleDao)
        {
                this.articleDao = articleDao;
        }

        public void setCategoryDao(CategoryDao categoryDao)
        {
                this.categoryDao = categoryDao;
        }

        public void setFeedbackDao(FeedbackDao feedbackDao)
        {
                this.feedbackDao = feedbackDao;
        }

        public void setImageDao(ImageDao imageDao)
        {
                this.imageDao = imageDao;
        }

        public void setLinkDao(LinkDao linkDao)
        {
                this.linkDao = linkDao;
        }

        public void setSequenceDao(SequenceDao sequenceDao)
        {
                this.sequenceDao = sequenceDao;
        }

        // for abstract method in Facade:
        public Identity login(String username, String password)
                throws AuthorizationException
        {
                int accountId = accountDao.loginAccount(username, password);

                return new Identity(accountId, username);
        }

        public Account getAccount(String username) throws QueryException
        {
                return accountDao.getAccount(username);
        }

        public List getRecentAccounts(int num) throws QueryException
        {
                return accountDao.getRecentAccounts(num);
        }

        public void changePassword(Identity id, String oldPassword,
                                   String newPassword, String newPassword2)
        {
                Account account = getAccount(id.getAccountId());

                // validate old password:
                if (!account.getPassword().equals(oldPassword))
                {
                        throw new RuntimeException("The old password is invalid.");
                }

                // validate new password:
                if ((newPassword == null) || (newPassword2 == null))
                {
                        throw new RuntimeException("The new password is invalid.");
                }

                if (!newPassword.equals(newPassword2))
                {
                        throw new RuntimeException("The 2 new passwords are not match.");
                }

                if (!newPassword.matches("[a-zA-Z0-9][a-zA-Z0-9]{6,20}"))
                {
                        throw new RuntimeException(
                                "The new password contains illegal characters.");
                }

                accountDao.changePassword(id.getAccountId(), newPassword);
        }

        public Account getAccount(int accountId) throws QueryException
        {
                return accountDao.getAccount(accountId);
        }

        public Account getAccountByCategory(int categoryId)
                throws QueryException
        {
                return accountDao.getAccountByCategory(categoryId);
        }

        public Account getAccountByArticle(int articleId) throws QueryException
        {
                return accountDao.getAccountByArticle(articleId);
        }

        public Article getArticle(int articleId) throws QueryException
        {
                return articleDao.getArticle(articleId);
        }

        public List getArticles(int accountId, int num, int page)
                throws QueryException
        {
                return articleDao.getArticles(accountId, num, page);
        }

        public List getRecentArticlesInfo(int num) throws QueryException
        {
                return articleDao.getRecentArticlesInfo(num);
        }

        public List getArticlesByCategory(int categoryId, int num, int page)
                throws QueryException
        {
                return articleDao.getArticlesByCategory(categoryId, num, page);
        }

        public int getArticlesCount(int categoryId) throws QueryException
        {
                return articleDao.getArticlesCount(categoryId);
        }

        public List searchArticle(String keyword, int num, int page)
                throws QueryException
        {
                throw new RuntimeException("Code is not finished.");
        }

        public Category getCategory(int categoryId) throws QueryException
        {
                return categoryDao.getCategory(categoryId);
        }

        public List getCategories(int accountId) throws QueryException
        {
                return categoryDao.getCategories(accountId);
        }

        public List getCategoriesOfArticle(int accountId) throws QueryException
        {
                return categoryDao.getCategoriesOfArticle(accountId);
        }

        public List getCategoriesOfType(int accountId, int type)
                throws QueryException
        {
                return categoryDao.getCategoriesOfType(accountId, type);
        }

        /**
         * Create a new category.
         *
         * @param id       The Identity from session.
         * @param category Must specify "title", "type", "visible", "description".
         * @throws CreateException If creation failed.
         */
        public void createCategory(Identity id, Category category)
                throws CreateException
        {
                category.validate();
                // set category:
                category.setAccountId(id.getAccountId());
                category.setCategoryId(sequenceDao.getNextCategoryId());
                categoryDao.createCategory(category);
        }

        public void deleteCategory(Identity id, int categoryId)
                throws DeleteException
        {
                // check:
                Category category = getCategory(categoryId);

                if (category == null)
                {
                        throw new RuntimeException("Category is not existed.");
                }

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new AuthorizationException("Permission denied.");
                }

                // make sure it is empty:
                boolean empty = true;

                switch (category.getType())
                {
                        case Category.TYPE_ARTICLES:
                                empty = (getArticlesCount(categoryId) == 0);

                                break;

                        case Category.TYPE_IMAGES:
                                empty = (getImagesCount(categoryId) == 0);

                                break;

                        case Category.TYPE_LINKS:
                                empty = (getLinksCount(categoryId) == 0);

                                break;
                }

                if (!empty)
                {
                        throw new RuntimeException("不能删除这个 " + category.getName() +
                                "类型，因为此类型不为空.");
                }

                // delete it:
                categoryDao.deleteCategory(categoryId);
        }

        public void updateCategory(Identity id, Category category)
                throws UpdateException
        {
                category.validate();

                // check account:
                if (id.getAccountId() != category.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // update:
                categoryDao.updateCategory(category);
        }

        public List getAllLinks(int accountId) throws QueryException
        {
                return linkDao.getAllLinks(accountId);
        }

        public Link getLink(int linkId) throws QueryException
        {
                return linkDao.getLink(linkId);
        }

        public int getLinksCount(int categoryId) throws QueryException
        {
                return linkDao.getLinksCount(categoryId);
        }

        public List getFeedbacks(int articleId) throws QueryException
        {
                return feedbackDao.getFeedbacks(articleId);
        }

        public void createFeedback(Feedback feedback) throws CreateException
        {
                feedback.validate();
                feedback.setFeedbackId(sequenceDao.getNextFeedbackId());
                feedbackDao.createFeedback(feedback);
        }

        public Image getImage(int imageId) throws QueryException
        {
                return imageDao.getImage(imageId);
        }

        public int getImagesCount(int categoryId) throws QueryException
        {
                return imageDao.getImagesCount(categoryId);
        }

        public void sendMessage(Message message)
        {
                // check:
                message.validate();
                // complete the fields:
                message.setSentDate(new Date());

                //message.setMessageId(getNextMessageId());
                //messageDao.sendMessage(message);
        }

        public void createAccount(Account account) throws CreateException
        {
                account.setAccountId(sequenceDao.getNextAccountId());
                account.validate();
                accountDao.createAccount(account);
        }

        public void deleteAccount(int accountId) throws DeleteException
        {
                accountDao.deleteAccount(accountId);
        }

        public void lockAccount(int accountId) throws UpdateException
        {
                accountDao.lockAccount(accountId);
        }

        /**
         * Update the account, but the accountId and username will not be updated.
         *
         * @param id      The identity of the session.
         * @param account The account.
         */
        public void updateAccount(Identity id, Account account)
        {
                account.validate();

                if (account.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                accountDao.updateAccount(account);
        }

        /**
         * Create a new article.
         *
         * @param id      The identity of the session.
         * @param article Must specify all attributes except "articleId" and "accountId".
         */
        public void createArticle(Identity id, Article article)
                throws CreateException
        {
                // check category:
                Category category = getCategory(article.getCategoryId());

                if ((category == null) || !category.isForArticles())
                {
                        throw new IllegalArgumentException("Category is invalid.");
                }

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                article.validate();

                article.setAccountId(id.getAccountId());
                article.setArticleId(sequenceDao.getNextArticleId());
                articleDao.createArticle(article);
        }

        public void deleteArticle(Identity id, int articleId)
                throws DeleteException
        {
                // check:
                Article article = getArticleInfo(articleId);

                if (article.getAccountId() != id.getAccountId())
                {
                        throw new AuthorizationException("Permission denied.");
                }

                // delete it:
                String inclosurePath = articleDao.getArticle(articleId)
                        .getInclosurePath();

                if ((inclosurePath != null) && ((inclosurePath.trim().length()) > 1))
                {
                        String filePath = Config.getUploadPhysicalPath() + File.separator +
                                "blogIMG" + File.separator +
                                articleDao.getArticle(articleId).getInclosurePath();
                        File delFile = new File(filePath);
                        System.out.println("Will Delete " + filePath);

                        if (delFile.exists())
                        {
                                boolean delelted = delFile.delete();

                                if (delelted)
                                {
                                        System.out.println("File has been deleted " + filePath);
                                }
                        }
                        else
                        {
                                System.out.println("Can't find the file: " + filePath);
                        }
                }

                articleDao.deleteArticle(articleId);
        }

        public Article getArticleInfo(int articleId) throws QueryException
        {
                return articleDao.getArticleInfo(articleId);
        }

        public List getArticlesByCategory(int categoryId) throws QueryException
        {
                return articleDao.getArticlesByCategory(categoryId);
        }

        public void updateArticle(Identity id, Article article)
        {
                article.validate();

                // check accountId:
                if (article.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // check category:
                Category category = getCategory(article.getCategoryId());

                if ((category == null) || !category.isForArticles())
                {
                        throw new IllegalArgumentException("Category is invalid.");
                }

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // update:
                articleDao.updateArticle(article);
        }

        public void updateArticleInfo(Identity id, Article article)
                throws UpdateException
        {
                article.validate();

                // check account:
                if (article.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // check category:
                Category category = getCategory(article.getCategoryId());

                if ((category == null) || !category.isForArticles())
                {
                        throw new IllegalArgumentException("Category is invalid.");
                }

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // update:
                articleDao.updateArticleInfo(article);
        }

        public void deleteFeedback(int feedbackId) throws DeleteException
        {
                feedbackDao.deleteFeedback(feedbackId);
        }

        public Feedback getFeedback(int feedbackId) throws QueryException
        {
                return feedbackDao.getFeedback(feedbackId);
        }

        public void deleteImage(Identity id, int imageId, String baseDir)
                throws DeleteException
        {
                Image image = getImage(imageId);

                // check account:
                Category category = getCategory(image.getCategoryId());

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // ok, delete image file:
                new File(baseDir + id.getAccountId() + "/" + image.getFilename()).delete();
                new File(baseDir + id.getAccountId() + "/" +
                        image.getPreviewFilename()).delete();
                // and the record in the database:
                imageDao.deleteImage(imageId);
        }

        public void createImage(Identity id, Image image, String baseDir,
                                InputStream is) throws CreateException
        {
                OutputStream os = null;
                OutputStream os2 = null;

                try
                {
                        image.validate();

                        // check category:
                        Category category = getCategory(image.getCategoryId());

                        if ((category == null) || !category.isForImages())
                        {
                                throw new IllegalArgumentException("Gallery is invalid.");
                        }

                        if (category.getAccountId() != id.getAccountId())
                        {
                                throw new RuntimeException("Permission denied.");
                        }

                        // ok, set id:
                        image.setImageId(sequenceDao.getNextImageId());

                        //create catalog
                        File file = new File(baseDir + "/" + id.getAccountId());
                        file.mkdir();

                        // create file:
                        os = new BufferedOutputStream(new FileOutputStream(baseDir +
                                id.getAccountId() + "/" + image.getFilename()));
                        os2 = new BufferedOutputStream(new FileOutputStream(baseDir +
                                id.getAccountId() + "/" + image.getPreviewFilename()));

                        byte[] buffer = new byte[1024];
                        int n;

                        while ((n = is.read(buffer)) != (-1))
                        {
                                os.write(buffer, 0, n);
                                os2.write(buffer, 0, n);
                        }

                        os.close();
                        os2.close();
                        // create image:
                        imageDao.createImage(image);
                }
                catch (Exception e)
                {
                        // if there is any error, delete the image file:
                        new File(baseDir + id.getAccountId() + "/" + image.getFilename()).delete();
                        new File(baseDir + id.getAccountId() + "/" +
                                image.getPreviewFilename()).delete();
                        throw new RuntimeException("Upload image failed: " +
                                e.getMessage());
                }
                finally
                {
                        try
                        {
                                is.close();
                        }
                        catch (Exception e)
                        {
                        }

                        if (os != null)
                        {
                                try
                                {
                                        os.close();
                                }
                                catch (Exception e)
                                {
                                }
                        }

                        if (os2 != null)
                        {
                                try
                                {
                                        os2.close();
                                }
                                catch (Exception e)
                                {
                                }
                        }
                }
        }

        public List getImages(int categoryId, int num, int page)
                throws QueryException
        {
                return imageDao.getImages(categoryId, num, page);
        }

        public List getImages(int categoryId) throws QueryException
        {
                return imageDao.getImages(categoryId);
        }

        public void createLink(Identity id, Link link) throws CreateException
        {
                link.validate();

                // check category:
                Category category = getCategory(link.getCategoryId());

                if ((category == null) || !category.isForLinks())
                {
                        throw new IllegalArgumentException("Illegal argument: categoryId.");
                }

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new AuthorizationException("Permission denied.");
                }

                // create:
                link.setLinkId(sequenceDao.getNextLinkId());
                linkDao.createLink(link);
        }

        public void deleteLink(Identity id, int linkId) throws DeleteException
        {
                // check:
                Link link = getLink(linkId);

                if (link == null)
                {
                        throw new IllegalArgumentException("The link is invalid.");
                }

                Category category = getCategory(link.getCategoryId());

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new AuthorizationException("Permission denied.");
                }

                // delete it:
                linkDao.deleteLink(linkId);
        }

        public void updateLink(Identity id, Link link) throws UpdateException
        {
                link.validate();

                // check category:
                Category category = getCategory(link.getCategoryId());

                if ((category == null) || !category.isForLinks())
                {
                        throw new IllegalArgumentException(
                                "Favorite of the link is invalid.");
                }

                if (category.getAccountId() != id.getAccountId())
                {
                        throw new RuntimeException("Permission denied.");
                }

                // update:
                linkDao.updateLink(link);
        }

        public String writeRss(int accountId) throws QueryException
        {
                Account account = accountDao.getAccount(accountId);
                List articles = articleDao.getArticles(accountId,
                        account.getMaxPerPage(), 1);
                Iterator it = articles.iterator();

                while (it.hasNext())
                {
                        Article article = (Article) it.next();
                }

                return null;
        }
}
