/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Mar 25, 2004
 * Time: 3:45:17 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.util.i18n;

import com.ulearning.ulms.util.log.LogUtil;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class LocalizationAction extends Action
{

        /**
         * Process the request and return an <code>ActionForward</code> instance
         * describing where and how control should be forwarded, or
         * <code>null</code>if the response has already been completed.
         *
         * @param mapping  The ActionMapping used to select this instance
         * @param form     The optional ActionForm bean for this request (if any)
         * @param request  The HTTP request we are processing
         * @param response The HTTP response we are creating
         * @return the ActionForward for the next view
         * @throws Exception if the application logic throws an exception
         */
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                // Extract attributes we will need
                HttpSession session = request.getSession();

                // Get locale from request, if any
                Locale locale = request.getLocale();

                // If supplied, set Locale based on request parameters;
                // country and language
                String language = request.getParameter("language");
                String country = request.getParameter("country");

                if ((language != null && language.length() > 0)
                        && (country != null && country.length() > 0))
                {
                        locale = new java.util.Locale(language, country);
                }
                else if (language != null && language.length() > 0)
                {
                        locale = new java.util.Locale(language, "");
                }

                //Logger logger = LogUtil.getLogger("system");
                LogUtil.debug("system", "[LocalizationAction] from ulms ========= >language= " + language);

                session.setAttribute(Globals.LOCALE_KEY, locale);

                // Forward to result page
                return mapping.findForward("success");
        }
}
