/*************************************************************************** * Copyright 2001-2003 The eXo Platform SARL         All rights reserved.  * * Please look at license.txt in info directory for more license detail.   * **************************************************************************/package org.exoplatform.portlets.monitor.portlet.renderer.html;import java.io.IOException;import java.util.List;import java.util.ResourceBundle;import javax.faces.component.UIComponent;import javax.faces.context.FacesContext;import javax.faces.context.ResponseWriter;import org.exoplatform.container.monitor.RequestMonitorData;import org.exoplatform.faces.core.renderer.html.HtmlBasicRenderer;import org.exoplatform.portlets.monitor.portlet.component.UIPortalMonitor;/** * Apr 28, 2004 * @author: Tuan Nguyen * @email:   tuan08@users.sourceforge.net * @version: $Id: PortalMonitorRenderer.java,v 1.4 2004/08/02 12:04:26 tuan08 Exp $ **/public class PortalMonitorRenderer extends HtmlBasicRenderer {  public void encodeChildren( FacesContext context, UIComponent component ) throws IOException {  	UIPortalMonitor uiMonitor = (UIPortalMonitor) component ;    ResponseWriter w = context.getResponseWriter() ;    ResourceBundle res = getApplicationResourceBundle(context.getExternalContext()) ;    List monitors = uiMonitor.getRequestMonitorData();    w.write("<table class='"); w.write(uiMonitor.getClazz()); w.write("'>") ;     w.  write("<tr>") ;    w.    write("<th>"); w.write(res.getString("UIPortalMonitor.header.time-range")); w.write("</th>") ;    w.    write("<th>"); w.write(res.getString("UIPortalMonitor.header.request-counter")); w.write("</th>") ;    w.    write("<th>"); w.write(res.getString("UIPortalMonitor.header.request-min-execution-time")); w.write("</th>") ;    w.    write("<th>"); w.write(res.getString("UIPortalMonitor.header.request-max-execution-time")); w.write("</th>") ;    w.    write("<th>"); w.write(res.getString("UIPortalMonitor.header.request-avg-execution-time")); w.write("</th>") ;    for(int i = 0 ; i < monitors.size(); i++) {    	RequestMonitorData data = (RequestMonitorData) monitors.get(i) ;      String clazz = "odd";      if (i % 2 == 0)        clazz = "even";    	w.write("<tr class='" + clazz + "'>") ;    	w. write("<td>");      w.   write(Long.toString(data.minRange())); w.write("-"); w.write(Long.toString(data.maxRange()));      w. write("</td>") ;    	w. write("<td>");  w.write(Long.toString(data.getRequestCounter()));  w.write("</td>");    	w. write("<td>");  w.write(Long.toString(data.minExecutionTime()));  w.write("</td>");    	w. write("<td>");  w.write(Long.toString(data.maxExecutionTime()));  w.write("</td>");    	w. write("<td>");  w.write(Long.toString(data.averageExecutionTime()));  w.write("</td>");    	w.write("</tr>") ;         }    w.write("<tr class='footer'>");    w.  write("<td>"); w.write(res.getString("UIPortalMonitor.header.all")) ; w.write("</td>") ;    w.  write("<td>"); w.write(uiMonitor.getRequestCounter()) ; w.write("</td>") ;    w.  write("<td>"); w.write(uiMonitor.getMinExecutionTime()) ; w.write("</td>") ;    w.  write("<td>"); w.write(uiMonitor.getMaxExecutionTime()) ; w.write("</td>") ;    w.  write("<td>"); w.write(uiMonitor.getAverageExecutionTime()) ; w.write("</td>") ;    w.write("</tr>") ;    w.write("</table>") ;  }}