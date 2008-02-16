/**
 * VideoScopeDAOImpl.java.
 * User: liaoxingxing  Date: 2007-1-5 10:35:08
 *
 * Copyright (c) 2000-2004.  Tech Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.video.dao;

import com.ulearning.ulms.video.exceptions.VideoScopeDAOSysException;
import com.ulearning.ulms.video.model.*;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.List;
import java.io.Serializable;

public class VideoScopeDAOImpl implements VideoScopeDAO
{

        public int addVideoUser(VideouserModel videoUserModel) throws VideoScopeDAOSysException
        {

                Serializable s = null;

                int vclassid = 0;

                try
                {
                        s = HibernateDAO.add(videoUserModel);
                        System.out.println("s.toString() = " + s.toString());
                        vclassid = ((VideouserModelPK) s).getvclassid();
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("" + e);
                }
                return vclassid;
        }

        public int addVideoOrg(VideoOrganModel videoOrganModel) throws VideoScopeDAOSysException
        {
                Serializable s = null;
                int vclassid = 0;
                try
                {
                        s = HibernateDAO.add(videoOrganModel);
                        vclassid = ((VideoOrganModelPK) s).getvclassid();
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("" + e);
                }
                return vclassid;
        }

        /*  public int addVideoCondition(VideoConditionModel videoConditionModel) throws VideoScopeDAOSysException{
                    Serializable s = null;
                    int vclassid = 0;
                    try
                    {
                            s = HibernateDAO.add(videoConditionModel);
                            vclassid = ((VideoConditionModelPK)s).getvclassid();
                    }
                    catch (ULMSSysException e)
                    {
                            e.printStackTrace();
                            throw new VideoScopeDAOSysException("" + e);
                    }
                    return vclassid;
            }
        */
        public int addVideo(VideoModel videomodel) throws VideoScopeDAOSysException
        {
                Serializable s = null;
                int vclassid = 0;
                try
                {
                        s = HibernateDAO.add(videomodel);
                        vclassid = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("" + e);
                }
                return vclassid;
        }

        public void updateVideoUser(VideouserModel videoUserModel) throws VideoScopeDAOSysException
        {
                try
                {
                        HibernateDAO.update(videoUserModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }

        public void updateVideoOrg(VideoOrganModel videoOrganModel) throws VideoScopeDAOSysException
        {
                try
                {
                        HibernateDAO.update(videoOrganModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }

/*        public void updateVideoVideo(VideoVideoModel videoVideoModel) throws VideoScopeDAOSysException{
                try
                {
                        HibernateDAO.update(videoVideoModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }*/

        public void updateVideo(VideoModel conditionModel) throws VideoScopeDAOSysException
        {
                try
                {
                        HibernateDAO.update(conditionModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new VideoScopeDAOSysException("addPaperAnswer(PaperAnswerForm paperAnswerForm) method" + e);
                }
        }

        public List getVideoUser(int vclassid, int type) throws VideoScopeDAOSysException
        {
                String hql = "from VideouserModel as epm where epm.comp_id.vclassid =" + vclassid + " and epm.comp_id.type=" + type;
                List videoUserList = null;
                try
                {
                        videoUserList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException use)
                {
                        throw new VideoScopeDAOSysException("HibernateException while getVideoPaperList: \n" + use);
                }
                return videoUserList;
        }

        public List getVideoUser(int vclassid, int userID, int type) throws VideoScopeDAOSysException
        {
                String hql = "from VideouserModel as epm where epm.comp_id.vclassid =" + vclassid + " and epm.comp_id.userid=" + userID;
                List videoUserList = null;
                try
                {
                        videoUserList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while getVideoPaperList: \n" + use);
                }
                return videoUserList;
        }

        public List getVideoList(int type) throws VideoScopeDAOSysException
        {
                String hql = "from VideoModel where type=" + type;
                List videoUserList = null;
                try
                {
                        videoUserList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while getVideoPaperList: \n" + use);
                }
                return videoUserList;
        }

        public List getVideoOrg(int vclassid, int type) throws VideoScopeDAOSysException
        {
                String hql = "from VideoOrganModel as epm where epm.comp_id.vclassid =" + vclassid + " and epm.comp_id.type=" + type;
                List videoOrgList = null;
                try
                {
                        videoOrgList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException use)
                {
                        throw new VideoScopeDAOSysException("HibernateException while getVideoPaperList: \n" + use);
                }
                return videoOrgList;
        }

        public List getVideoOrg(int vclassid, int orgID, int type) throws VideoScopeDAOSysException
        {
                String hql = "from VideoOrganModel as epm where epm.comp_id.vclassid =" + vclassid + " and epm.comp_id.orgid=" + orgID + " and epm.comp_id.type=" + type;
                List videoOrgList = null;
                try
                {
                        videoOrgList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException use)
                {
                        throw new VideoScopeDAOSysException("HibernateException while getVideoPaperList: \n" + use);
                }
                return videoOrgList;
        }

        public VideoModel getVideo(int vclassid, int type) throws VideoScopeDAOSysException
        {
                VideoModel pm = null;
                try
                {
                        Object object = HibernateDAO.load(VideoModel.class, new Integer(vclassid));
                        if (object != null)
                        {
                                pm = (VideoModel) object;
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new VideoScopeDAOSysException("SQLException while updating " + "video; " +
                                "Serial = " + vclassid + " :\n" + se);
                }
                return pm;
        }

        public void delVideoUser(int vclassid, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        String hql = " from VideouserModel as epm where epm.comp_id.vclassid = " + vclassid + " and epm.comp_id.type=" + type;
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while delVideoPaper: \n" + use);
                }
        }

        public void delVideoUserbyuserid(int vclassid, int userID, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        String hql = " from VideouserModel as epm where epm.comp_id.vclassid = " + vclassid +
                                "and epm.comp_id.type='" + type +
                                "' and epm.comp_id.userid=" + userID;
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while delVideoPaper: \n" + use);
                }
        }

        public void delVideoOrg(int vclassid, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        String hql = " from VideoOrganModel as epm where epm.comp_id.vclassid = " + vclassid + " and epm.comp_id.type=" + type;
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while delVideoPaper: \n" + use);
                }
        }

        public void delVideoOrgbyorgid(int vclassid, int orgID, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        String hql = " from VideoOrganModel as epm where epm.comp_id.vclassid = " + vclassid +
                                " and epm.comp_id.type='" + type +
                                "' and epm.comp_id.orgid=" + orgID;
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while delVideoPaper: \n" + use);
                }
        }

        public void delVideo(int vclassid, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        String hql = " from VideoModel as epm where  epm.type=" + type + "  and epm.vclassid = " + vclassid;
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException use)
                {
                        use.printStackTrace();
                        throw new VideoScopeDAOSysException("HibernateException while delVideoPaper: \n" + use);
                }
        }

        public static void main(String[] args)
        {
                VideouserModel m1 = new VideouserModel();
                VideouserModelPK vimpk = new VideouserModelPK(0, 100, "8");
                m1.setComp_id(vimpk);
                ;
                VideoOrganModel m2 = new VideoOrganModel();
                VideoOrganModelPK mpk2 = new VideoOrganModelPK(0, 0, "8");
                m2.setComp_id(mpk2);
                //VideoVideoModel m3 = new VideoVideoModel();
                //VideoVideoModelPK epk3 = new VideoVideoModelPK(2,2,"2");
                //m3.setComp_id(epk3);
                //VideoModel m4 = new VideoModel();
                //m4.setConditonid(new Integer(2));
                //m4.setType("2");
                //m4.setName("hiboy");
                //m4.setRelationidstr("23");
                VideoScopeDAOImpl esd = new VideoScopeDAOImpl();
                //esd.addVideoOrg(m2);
                //esd.addVideoUser(m1);
                //esd.addVideoUser(m1);
                //esd.addVideo(m4);
                //esd.addVideoVideo(m3);
                //esd.addVideoOrg(m2);
                //esd.updateVideo(m4);
                //esd.updateVideoVideo(m3);
                //esd.updateVideoOrg(m2);
                //esd.updateVideoUser(m1);
                //esd.getVideoVideo(1,2);
                // List ll= esd.getVideoOrg(1,248,3);
                List ll = esd.getVideoUser(0, 8);
                System.out.println("ll.size() = " + ll.size());
                //esd.getVideoUser(1,2);
                //esd.delVideoVideo(1,2);
                //esd.delVideoOrg(1,2);
                //esd.delVideoUser(1,2);
        }
}
