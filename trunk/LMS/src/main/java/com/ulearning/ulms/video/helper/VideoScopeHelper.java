package com.ulearning.ulms.video.helper;

import com.ulearning.ulms.video.exceptions.VideoScopeDAOSysException;
import com.ulearning.ulms.video.dao.VideoScopeDAO;
import com.ulearning.ulms.video.dao.VideoScopeDAOFactory;
import com.ulearning.ulms.video.exceptions.VideoScopeDAOSysException;
import com.ulearning.ulms.video.form.VideoScopeForm;
import com.ulearning.ulms.video.model.VideoModel;

import java.util.List;

public class VideoScopeHelper
{

        VideoScopeDAO dao = VideoScopeDAOFactory.getDAO();

        public int addVideoUser(VideoScopeForm ef) throws VideoScopeDAOSysException
        {
                int vclassid = 0;
                try
                {
                        vclassid = dao.addVideoUser(ef.getVideouserModel());
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return vclassid;
        }

        public int addVideoOrg(VideoScopeForm ef) throws VideoScopeDAOSysException
        {
                int vclassid = 0;
                try
                {
                        vclassid = dao.addVideoOrg(ef.getVideoOrganModel());
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return vclassid;
        }

/*    public int addVideoCondition(VideoScopeForm ef) throws VideoScopeDAOSysException {
        int vclassid = 0;
        try {
            vclassid = dao.addVideoCondition(ef.getVideoConditionModel());
        } catch (VideoScopeDAOSysException e) {
            e.printStackTrace();
        }
        return vclassid;
    }*/

        /*  public int addCondition(VideoScopeForm ef) throws VideoScopeDAOSysException {
            int vclassid = 0;
            try {
                vclassid = dao.addCondition(ef.getConditionModel());
            } catch (VideoScopeDAOSysException e) {
                e.printStackTrace();
            }
            return vclassid;
        }*/

        public void updateVideoUser(VideoScopeForm ef) throws VideoScopeDAOSysException
        {
                try
                {
                        dao.updateVideoUser(ef.getVideouserModel());
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void updateVideoOrg(VideoScopeForm ef) throws VideoScopeDAOSysException
        {
                try
                {
                        dao.updateVideoOrg(ef.getVideoOrganModel());
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        /*  public void updateVideoCondition(VideoScopeForm ef) throws VideoScopeDAOSysException {
            try {
                dao.updateVideoCondition(ef.getVideoConditionModel());
            } catch (VideoScopeDAOSysException e) {
                e.printStackTrace();
            }
        }

        public void updateCondition(VideoScopeForm ef) throws VideoScopeDAOSysException {
            try {
                dao.updateCondition(ef.getConditionModel());
            } catch (VideoScopeDAOSysException e) {
                e.printStackTrace();
            }
        }*/

        public List getVideoUser(int vclassid, int type) throws VideoScopeDAOSysException
        {
                List videouserlist = null;
                try
                {
                        videouserlist = dao.getVideoUser(vclassid, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return videouserlist;
        }

        public List getVideoUser(int vclassid, int userID, int type) throws VideoScopeDAOSysException
        {
                List videouserlist = null;
                try
                {
                        videouserlist = dao.getVideoUser(vclassid, userID, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return videouserlist;
        }

        public List getVideoOrg(int vclassid, int type) throws VideoScopeDAOSysException
        {
                List videoorg = null;
                try
                {
                        videoorg = dao.getVideoOrg(vclassid, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return videoorg;
        }

        public List getVideoOrg(int vclassid, int orgID, int type) throws VideoScopeDAOSysException
        {
                List videoorg = null;
                try
                {
                        videoorg = dao.getVideoOrg(vclassid, orgID, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return videoorg;
        }

        public List getVideoList(int type) throws VideoScopeDAOSysException
        {
                List video = null;
                try
                {
                        video = dao.getVideoList(type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return video;
        }

        public VideoModel getVideo(int vclassid, int type) throws VideoScopeDAOSysException
        {
                VideoModel cmd = null;
                VideoScopeForm vsf = new VideoScopeForm();
                try
                {
                        cmd = dao.getVideo(vclassid, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
                return cmd;
        }

        public void delVideoUser(int vclassid, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        dao.delVideoUser(vclassid, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void delVideoUserbyuserid(int vclassid, int userID, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        dao.delVideoUserbyuserid(vclassid, userID, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void delVideoOrg(int vclassid, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        dao.delVideoOrg(vclassid, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        public void delVideoOrgbyorgid(int vclassid, int orgID, int type) throws VideoScopeDAOSysException
        {
                try
                {
                        dao.delVideoOrgbyorgid(vclassid, orgID, type);
                }
                catch (VideoScopeDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

/*
    public void delVideoCondition(int vclassid, int type) throws VideoScopeDAOSysException {
        try {
            dao.delVideoCondition(vclassid, type);
        } catch (VideoScopeDAOSysException e) {
            e.printStackTrace();
        }
    }
*/


}