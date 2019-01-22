/*************************************************************************** * Copyright 2001-2003 The eXo Platform SARL         All rights reserved.  * * Please look at license.txt in info directory for more license detail.   * **************************************************************************/package org.exoplatform.portlets.monitor.portlet.component;import java.util.List;import org.exoplatform.faces.core.component.UIExoComponentBase;import org.exoplatform.portlets.monitor.portlet.component.model.PortletApplicationData;import org.exoplatform.services.portletcontainer.monitor.PortletContainerMonitor;/** * Wed, Dec 22, 2003 @ 23:14 * * @author: Tuan Nguyen * @email: tuan08@users.sourceforge.net * @version: $Id: UIPortletContainerMonitor.java,v 1.5 2004/08/02 12:04:26 tuan08 Exp $ */public class UIPortletContainerMonitor extends UIExoComponentBase {  private PortletContainerMonitor service_;  private UIPortletMenu uiMenu_;  private UIPortletApplicationMonitor uiAppMonitor_;  private UIPortletMonitor uiPortletMonitor_;  public UIPortletContainerMonitor(PortletContainerMonitor service) {    setRendererType("PortletContainerMonitorRenderer");    setId("UIPortletContainerMonitor");    service_ = service ;    List children = getChildren();    uiMenu_ = new UIPortletMenu(service_.getPortletRuntimeDataMap().values());    uiMenu_.setRendered(true);    children.add(uiMenu_);    uiAppMonitor_ = new UIPortletApplicationMonitor();    PortletApplicationData appData = (PortletApplicationData) uiMenu_.        getPortletApplications().iterator().next();    uiAppMonitor_.setPortletApplicationData(appData);    uiAppMonitor_.setRendered(true);    children.add(uiAppMonitor_);    uiPortletMonitor_ = new UIPortletMonitor();    uiPortletMonitor_.setRendered(true);  }  public UIPortletMenu getUIPortletsMenu() {    return uiMenu_;  }  public UIPortletMonitor getUIPortletMonitor() {    return uiPortletMonitor_;  }  public UIPortletApplicationMonitor getUIPortletApplicationMonitor() {    return uiAppMonitor_;  }  public void showMonitor(UIExoComponentBase monitor) {    List children = getChildren();    children.remove(1);    children.add(monitor);  }  public String getFamily() {    return "org.exoplatform.portlets.monitor.portlet.component.UIPortletContainerMonitor";  }}