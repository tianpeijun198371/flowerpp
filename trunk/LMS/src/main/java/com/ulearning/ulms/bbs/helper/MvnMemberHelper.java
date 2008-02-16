/**
 * BBS��Ա��
 */
package com.ulearning.ulms.bbs.helper;

import com.ulearning.ulms.bbs.model.Mvnforummember;
import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.PublicDAO;
import com.ulearning.ulms.util.PublicDAOFactory;
import net.myvietnam.mvncore.security.Encoder;

import java.util.ArrayList;
import java.util.List;

public class MvnMemberHelper {
	// private String classname = "com.eduedu.elms.util.PublicDAOImpl";
	/**
	 * ���һ��BBS��Ա
	 * 
	 * @param model
	 *            BBS��Աmodel��
	 * @throws ULMSAppException
	 */
	public int addMvnMember(Mvnforummember model) throws ULMSAppException {
		int rid = 0;
		if (null != model) {
			PublicDAO dao = PublicDAOFactory.getDAO();
			rid = dao.addData(model);
		}
		return rid;
	}

	/**
	 * ����ƽ̨UserForm���һ��BBS�û�
	 * 
	 * @param uf
	 *            ƽ̨UserForm
	 * @return
	 * @throws ULMSAppException
	 */
	public int addMvnMemberForUserForm(UserForm uf) throws ULMSAppException {
		int rid = 0;

		rid = addMvnMember(makeMvnModelForUserForm(uf, 0, uf.getPassword()));
		return rid;

	}

	/**
	 * 
	 * @param uf
	 *            UserForm
	 * @param mvnid
	 *            int BBS��ԱID���޸�ʱʹ��
	 */
	private Mvnforummember makeMvnModelForUserForm(UserForm uf, int mvnid,
			String pass) {
        String encodePass = Encoder.getMD5_Base64(pass);
        System.out.println("==============bbs    encodePass ========= " + encodePass);
        Mvnforummember model = new Mvnforummember();
		model.setMemberactivatecode("");
		model.setMemberaddress(uf.getAddress());
		model.setMemberaol("");
		model.setMemberavatar("");
		model.setMemberbirthday(com.ulearning.ulms.core.util.DateTimeUtil
				.getRecentDay(new java.util.Date(), 0));
		model.setMembercareer("");
		model.setMembercity(uf.getRemark3() + uf.getRemark4());
		model.setMembercoollink1("http://");
		model.setMembercoollink2("http://");
		model.setMembercountry("�л����񹲺͹�");
		model.setMembercreationdate(uf.getRegisterDate());
		model.setMemberemail(uf.getMail());
		model.setMemberemailvisible(new Short("1"));
		model.setMemberexpiredate(uf.getRegisterDate());
		model.setMemberfax("");
		model.setMemberfirstemail(uf.getMail());
		model.setMemberfirstip("");
		model.setMemberfirstname("");
		model.setMembergender(new Short("1"));
		model.setMemberhomepage("http://");
		model.setMembericq("");
		if (0 != mvnid) {
			model.setMemberid(mvnid);
		}
		model.setMemberlanguage("");
		model.setMemberlastip("");
		model.setMemberlastlogon(uf.getRegisterDate());
		model.setMemberlastname("");
		model.setMembermessagecount(new Integer(0));
		model.setMembermessageoption(new Integer(0));
		model.setMembermobile(uf.getCellPhone());
		model.setMembermodifieddate(uf.getRegisterDate());
		model.setMembermsn("");
		model.setMembername(uf.getLoginName());
		model.setMembernamevisible(new Short("1"));
		model.setMemberoption(new Integer(0));
        //model.setMemberpassword(net.app.core.crypto.MD5Encrypt.encrypt(pass));
        model.setMemberpassword(encodePass);
        model.setMemberphone(uf.getPhone());
		model.setMemberpostcount(new Integer(0));
		model.setMemberpostsperpage(new Short("10"));
		model.setMemberrewardpoints(new Integer(0));
		model.setMembersignature("");
		model.setMemberskin("");
		model.setMemberstate("");
		model.setMemberstatus(new Integer(0));
		model.setMembertemppassword(encodePass);
		model.setMembertimezone(new Integer(8));
		model.setMembertitle("");
		model.setMemberviewcount(new Integer(0));
		model.setMembervotecount(new Integer(0));
		model.setMembervotetotalstars(new Integer(0));
		model.setMemberwarncount(new Short("0"));
		model.setMemberyahoo("");

		return model;

	}

	/**
	 * �޸ĳ�Ա��Ϣ
	 * 
	 * @param model
	 */
	public void upadateMvnMember(Mvnforummember model) {
		PublicDAO dao;
		try {
			dao = PublicDAOFactory.getDAO();
			dao.updateData(model);
		} catch (ULMSAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����UserForm�޸�BBS��Ա��Ϣ
	 * 
	 * @param uf
	 */
	public void updateMvnMemberForUserForm(UserForm uf) {
		List list = new ArrayList();
		list = getMemberByName(uf.getLoginName());
		int mId = 0;
		if (null != list && 0 < list.size()) {
			mId = ((Mvnforummember) list.get(0)).getMemberid();
		}
		if (0 < mId) {
			upadateMvnMember(makeMvnModelForUserForm(uf, mId, uf.getPassword()));
		}
	}

	/**
	 * �û��޸�ʱͬʱ�޸�BBS������
	 * @param userId �û�ID
	 * @param pass��������
	 */
	public void updateMvnPass(String userId, String pass) {
		UserHelper helper = new UserHelper();
		UserForm userForm = helper.getUser(userId);
        String encodePass = Encoder.getMD5_Base64(pass);
        System.out.println("==============bbs    encodePass ========= " + encodePass);
        String uLName = null;
		if (null != userForm) {
			uLName = userForm.getLoginName();
		}
		if (null != uLName && !uLName.equals("")) {
			Mvnforummember model = null;
			List list = getMemberByName(uLName);
			if (null != list && 0 < list.size()) {
				model = (Mvnforummember) list.get(0);
				//model.setMemberpassword(net.app.core.crypto.MD5Encrypt.encrypt(pass));
                model.setMemberpassword(encodePass);
                //model.setMembertemppassword(net.app.core.crypto.MD5Encrypt.encrypt(pass));
                model.setMembertemppassword(encodePass);
                upadateMvnMember(model);
			}
		}

	}

	/**
	 * ����BBS��Ա����ȡ��Ա��Ϣ
	 * 
	 * @param mName
	 */
	public List getMemberByName(String mName) {
		List list = null;
		if (null != mName && !mName.equals("")) {
			list = getAllMvnMember(null, mName, null);
		}
		return list;
	}

	/**
	 * ��ѯBBS��Ա
	 * 
	 * @param mId
	 * @param mName
	 * @param postcount
	 *            ��������
	 */
	public List getAllMvnMember(String mId, String mName, String postcount) {
		List list = null;
		PublicDAO dao;
		StringBuffer hql = new StringBuffer();
		hql.append(" from Mvnforummember where 1>0");
		if (null != mId && !mId.equals("")) {
			hql.append(" and memberid=");
			hql.append(mId);
		}
		if (null != mName && !mName.equals("")) {
			hql.append(" and membername ='");
			hql.append(mName);
			hql.append("'");
		}
		if (null != postcount && !postcount.equals("")) {
			hql.append(" and memberpostcount=");
			hql.append(postcount);
		}
		try {
			dao = PublicDAOFactory.getDAO();
			list = dao.getList(hql.toString());
		} catch (ULMSAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

}
