/***************************************************************************
 * Copyright 2001-2003 The eXo Platform SARL         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package org.exoplatform.portlets.backup.component;


import java.util.* ;
import org.exoplatform.faces.core.component.UINode;

/**
 * May 31, 2004 
 * @author: Tuan Nguyen
 * @email:   tuan08@users.sourceforge.net
 * @version: $ID$
 **/
public class UIImportUserDataNode extends UINode {
	
	public UIImportUserDataNode(UIImportUserData uiImportUserData, UIData uiData) {
		setId("UIImportUserDataNode") ;
		setRendererType("ChildrenRenderer") ; 
		setName("User Data") ;
		List children = getChildren() ;
		uiImportUserData.setRendered(true) ;
		children.add(uiImportUserData) ;
		uiData.setImportClass(UIImportUserData.class) ;
		uiData.setRendered(false) ;
		children.add(uiData) ;
	}
	
	public String getFamily() {
		return "org.exoplatform.portlets.backup.component.UIImportUserDataNode" ;
	}
}