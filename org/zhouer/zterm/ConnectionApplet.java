package org.zhouer.zterm;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class ConnectionApplet extends JApplet {
	private static final long serialVersionUID = 1875716719880652455L;
	
	private ZTerm zterm;
	private ConnectionInfo[] connection_info_list;
	private String[] device_name_list;
	private String[] protocol_list;
	private String[] ip_list;
	private String[] port_list;
	
	public void init() {
		getParam();
		
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	parseParam();
                	zterm = new ZTerm(connection_info_list);
                    add(zterm); // Create ZTerm with passed parameters
                }
            });
        } catch (Exception e) {
        	zterm = null;
            System.err.println("createGUI didn't complete successfully");
        }
    }
    
	/*
	 * Get all parameters passed from Web UI in the format of following code.
	 * <applet>
	 *  <PARAM name="device_name" value="Howdy,IOG,CISCO">
	 *  <PARAM name="protocol" value="SSH,Telnet,Telnet">
	 *  <PARAM name="ip" value="192.168.121.39,192.168.123.254,192.168.123.208">
	 *  <PARAM name="port" value="22,4001,23">
	 * </applet>
	 */
	private void getParam() {
    	try { // get from UI
    		device_name_list = getParameter("device_name").split(",");
        	protocol_list = getParameter("protocol").split(",");
        	ip_list = getParameter("ip").split(",");
        	port_list = getParameter("port").split(",");
    	} catch (Exception e) { // test in eclipse
    		device_name_list = "Howdy,IOG,CISCO".split(",");
        	protocol_list = "ssh,telnet,telnet".split(",");
        	ip_list = "192.168.121.39,192.168.123.254,192.168.123.208".split(",");
        	port_list = "22,4001,23".split(",");
    	}
	}
	
	/*
	 * Split the string by comma.
	 * Each item with the same index is combined to connectionInfo.
	 */
    private void parseParam(){    	
    	connection_info_list = new ConnectionInfo[ip_list.length];
    	String device_name, protocol, port;
    	
    	// store all information from up-link
    	for(int i = 0; i < ip_list.length; ++i){ // run the times with numbers of ip
    		try { device_name = device_name_list[i]; }
    		catch (Exception e) { device_name = "Unknown"; }
    		try { protocol = protocol_list[i]; }
    		catch (Exception e) { protocol = "telnet"; }
    		try { port = port_list[i]; }
    		catch (Exception e) { port = ""; }
    		
    		connection_info_list[i] = new ConnectionInfo(device_name, protocol, ip_list[i], port);
    	}
    }
    
    /*
     * @see java.applet.Applet#destroy()
     * destroy method is called only when the browser is closed
     */
    public void destroy() {
    	if(zterm != null)
    		zterm.quit();
    }

}
