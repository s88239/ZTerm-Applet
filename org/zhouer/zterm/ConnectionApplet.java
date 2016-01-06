package org.zhouer.zterm;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class ConnectionApplet extends JApplet {
	private static final long serialVersionUID = 1875716719880652455L;
	
	private ConnectionInfo[] connection_info_list;
	
	public void init() {
        //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                	parseParam();
                    add(new ZTerm(connection_info_list)); // Create ZTerm with passed parameters
                }
            });
        } catch (Exception e) {
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
    private void parseParam(){
    	/*String[] device_name_list = "Howdy,IOG,CISCO".split(",");
    	String[] protocol_list = "ssh,telnet,telnet".split(",");
    	String[] ip_list = "192.168.121.39,192.168.123.254,192.168.123.208".split(",");
    	String[] port_list = "22,4001,23".split(",");*/
    	
    	// get all information passed from UI
    	String[] device_name_list = getParameter("device_name").split(",");
    	String[] protocol_list = getParameter("protocol").split(",");
    	String[] ip_list = getParameter("ip").split(",");
    	String[] port_list = getParameter("port").split(",");
    	
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
}
