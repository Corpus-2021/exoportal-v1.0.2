package org.exoplatform.services.portletcontainer.test.portlet;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Copyright 2001-2003 The eXo platform SARL All rights reserved.
 * Please look at license.txt in info directory for more license detail.
 **/

/**
 * Created by the Exo Development team.
 * Author : Mestrallet Benjamin
 *          benjmestrallet@users.sourceforge.net
 * Date: 16 nov. 2003
 * Time: 21:28:40
 */
public class PortletToTestNonPropagationOfActionParamToRender extends GenericPortlet{

	public void init(PortletConfig portletConfig) throws PortletException {
	}

	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
					throws PortletException, IOException {
    String param1 = actionRequest.getParameter("param");

    if(!"param-value".equals(param1))
			throw new PortletException("getParameter does not work");

    actionResponse.setRenderParameter("status", "Everything is ok");
	}

	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
					throws PortletException, IOException {
		/////test (xlviii)
    renderResponse.setContentType("text/html");
    String param1 = renderRequest.getParameter("param");

    if(param1 != null)
			throw new PortletException("getParameter does not work");

    PrintWriter w = renderResponse.getWriter();
    w.println("Everything is ok");
	}

	public void destroy() {
		//To change body of implemented methods use Options | File Templates.
	}

}
