/**
 * BBS成员类
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
	 * 添加一个BBS成员
	 * 
	 * @param model
	 *            BBS成员model类
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
	 * 根据平台UserForm添加一个BBS用户
	 * 
	 * @param uf
	 *            平台UserForm
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
	 *            int BBS成员ID，修改时使用
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
		model.setMembercountry("中华人民共和国");
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
	 * 修改成员信息
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
	 * 根据UserForm修改BBS成员信息
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
	 * 用户修改时同时修改BBS的密码
	 * @param userId 用户ID
	 * @param pass　　密码
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
	 * 根据BBS成员名称取成员信息
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
	 * 查询BBS成员
	 * 
	 * @param mId
	 * @param mName
	 * @param postcount
	 *            发帖总数
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
