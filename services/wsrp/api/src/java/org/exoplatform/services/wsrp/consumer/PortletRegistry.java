package org.exoplatform.services.wsrp.consumer;import java.util.Iterator;import org.exoplatform.services.wsrp.exceptions.WSRPException;/** * This interface defines a registry which holds portlet objects. * * @author Stephan Laertz * @author Benjamin Mestrallet */public interface PortletRegistry {  /**   * Add a portlet to the registry   *   * @param portlet The portlet to add   */  public void addPortlet(WSRPPortlet portlet) throws WSRPException;  /**   * Get the portlet for the given producer and portlet handle   *   * @param portletKey The portlet key identifying the portlet   * @return The portlet with the given portlet key   */  public WSRPPortlet getPortlet(PortletKey portletKey);  /**   * Remove the portlet with the given portlet key   *   * @param portletKey The portlet key identifying the portlet   * @return The portlet which has been removed or null   */  public WSRPPortlet removePortlet(PortletKey portletKey);  /**   * Tests if a portlet with the given portlet key   *   * @param portletKey The portlet key identifying the portlet   * @return True if portlet exists with this portlet key   */  public boolean existsPortlet(PortletKey portletKey);  /**   * Get all the portlets in the register   *   * @return Iterator with all portlets in the registry   */  public Iterator getAllPortlets();  /**   * Remove all portlets from the registry   */  public void removeAllPortlets();}