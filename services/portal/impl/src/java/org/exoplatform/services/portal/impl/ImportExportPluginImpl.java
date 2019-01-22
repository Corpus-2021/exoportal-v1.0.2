/*************************************************************************** * Copyright 2001-2003 The eXo Platform SARL         All rights reserved.  * * Please look at license.txt in info directory for more license detail.   * **************************************************************************/package org.exoplatform.services.portal.impl;import java.util.ArrayList;import java.util.Collection;import java.util.Iterator;import java.util.List;import java.util.zip.ZipFile;import java.util.zip.ZipOutputStream;import net.sf.hibernate.Hibernate;import net.sf.hibernate.Session;import org.picocontainer.Startable;import org.exoplatform.services.backup.ImportExportService ;import org.exoplatform.services.backup.ImportExportPlugin;import org.exoplatform.services.backup.Metadata;import org.exoplatform.services.backup.XMLObjectConverter;import org.exoplatform.services.database.XResources;import org.exoplatform.services.portal.model.NodeNavigation;import org.exoplatform.services.portal.model.Page;import org.exoplatform.services.portal.model.PortalConfig;import org.exoplatform.xml.object.XMLCollection;import org.exoplatform.xml.object.XMLObject;/** * May 27, 2004  * @author: Tuan Nguyen * @email:   tuan08@users.sourceforge.net * @version: $ID$ **/public class ImportExportPluginImpl extends ImportExportPlugin implements Startable {  static String PLUGIN_NAME = "portal" ;  static String META_DATA_ENTRY   = "portal/metadata.xml" ;  static String PORTAL_CONFIG_ENTRY = "portal/portal-config.xml" ;  static String PAGES_CONFIG_ENTRY = "portal/pages-config.xml" ;  static String NAVIGATION_CONFIG_ENTRY = "portal/navigation-config.xml" ;  public ImportExportPluginImpl(ImportExportService ieservice) throws Exception {    setName("organization") ;    setDescription("organization import/export plugin") ;    ieservice.addPlugin(this) ;    init() ;  }    public String getName() {    return  PLUGIN_NAME ;}    public String getDescription() {  return "Export the user portal configuration" ; }  public boolean hasServiceData()  { return false ;  }  public void exportUserData(String userName, XResources resources, ZipOutputStream os) throws Exception {    Metadata mdata = new Metadata(getCurrentDataVersion(), getDescription(), "single-user") ;    createEntry(META_DATA_ENTRY , os, mdata) ;     Session session = (Session) resources.getResource(Session.class);    PortalConfigData data = (PortalConfigData) session.get(PortalConfigData.class, userName) ;    createEntry(PORTAL_CONFIG_ENTRY, os, data.getPortalConfig()) ;     List l = session.find(PortalConfigServiceImpl.queryPageDataByOwner,                           userName, Hibernate.STRING) ;    List pages = new ArrayList(l.size() + 5)  ;    for(int i = 0 ; i < l.size(); i++) {      PageData pageData = (PageData) l.get(i) ;      pages.add(pageData.getPage()) ;    }    createEntry(PAGES_CONFIG_ENTRY, os, pages) ;     NodeNavigationData nvdata =       (NodeNavigationData) session.get(NodeNavigationData.class, userName) ;    createEntry(NAVIGATION_CONFIG_ENTRY, os, nvdata.getNodeNavigation()) ;   }  public void importUserData(String user, XResources resources, ZipFile in) throws Exception {    Metadata mdata = getMetadata(META_DATA_ENTRY, in) ;    String version = mdata.getDataVersion() ;    XMLObjectConverter converter = getXMLObjectConverter(version) ;    Session session = (Session) resources.getResource(Session.class);        XMLObject xmlobject = XMLObject.getXMLObject(getEntry(PORTAL_CONFIG_ENTRY, in)) ;    if(converter != null) converter.traverse(xmlobject) ;    PortalConfig pconfig = (PortalConfig)XMLObject.getObject(getEntry(PORTAL_CONFIG_ENTRY, in)) ;    session.saveOrUpdateCopy(new PortalConfigData(pconfig)) ;        XMLCollection xmlcol =  XMLCollection.getXMLCollection(getEntry(PAGES_CONFIG_ENTRY, in)) ;    if(converter != null) converter.traverse(xmlcol) ;    Collection pages = xmlcol.getCollection()  ;    Iterator i = pages.iterator() ;    while(i.hasNext()) {      Page page = (Page)  i.next() ;      session.saveOrUpdateCopy(new PageData(page)) ;    }        xmlobject = XMLObject.getXMLObject(getEntry(NAVIGATION_CONFIG_ENTRY, in)) ;    if(converter != null) converter.traverse(xmlobject) ;    NodeNavigation nn = (NodeNavigation) xmlobject.toObject() ;    session.saveOrUpdateCopy(new NodeNavigationData(nn)) ;  }  public void exportServiceData(XResources resources, ZipOutputStream os) throws Exception {      }  public void importServiceData(XResources resources, ZipFile in) throws Exception {      }  public void start() {  }  public void stop() {  }}