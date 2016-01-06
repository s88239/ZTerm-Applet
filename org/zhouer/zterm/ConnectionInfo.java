package org.zhouer.zterm;

public class ConnectionInfo {
	private String device_name;
	private String protocol;
	private String ip;
	private String port;
	
	public ConnectionInfo(){
		device_name = "New Device";
		protocol = "telnet";
		ip = "";
		port = "";
	}
	
	public ConnectionInfo(String _name, String _protocol, String _ip, String _port){
		device_name = _name;
		protocol = _protocol.toLowerCase(); // transform the string of protocol to lower case
		ip = _ip;
		port = _port;
	}
	
	public void setDeviceName(String _name) { device_name = _name; }
	public void setProtocol(String _protocol) { protocol = _protocol; }
	public void setIp(String _ip) { ip = _ip; }
	public void setPort(String _port) { port = _port; }
	
	public String getDeviceName() { return device_name; }
	public String getProtocol() { return protocol; }
	public String getIp() { return ip; }
	public String getPort() { return port; }
	
	public String getUrl() {
		String url = protocol + "://" + ip;
		if(!port.isEmpty() ) url = url + ":" + port;
		return url;
	}
}
