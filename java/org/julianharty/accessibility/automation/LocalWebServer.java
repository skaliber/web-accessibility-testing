/**
 * Copyright 2010 web-accessibility-testing committers

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.julianharty.accessibility.automation;

import java.io.IOException;
import java.net.ServerSocket;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.log.Log;

/**
 * Starts a local WebServer to facilitate testing our helper classes, etc.
 * 
 * Inspired by Michael Tamm's work in fighting-layout-bugs - thank you :)
 */

public class LocalWebServer {
	private static Server webserver;
	private int port; 
	
	/**
	 * Create a local web server using the default project folder for content.
	 */
	public LocalWebServer() {
		this("testwebpages");
	}
	
	/**
	 * Create a local web server.
	 * @param pathToContent folder with the static web pages.
	 */
	public LocalWebServer(String pathToContent) {
		webserver = new Server();
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase(pathToContent);
		Log.info("Serving: " + resourceHandler.getResourceBase());
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{resourceHandler,new DefaultHandler()});
		webserver.setHandler(handlers);
		}
	
	/**
	 * Start the local webserver using a random port number.
	 * 
	 * The local IP port is set to a free value each time start is called. Use
	 * getPort() if you need to know the port used.
	 * 
	 * NB: When run on Windows 7 the free port number (typical values around
	 * 52356) fail to bind when the server is started. This may be related to
	 * UAC and the Windows Firewall. If you experience similar problems, try to
	 * use a hard-coded port number that's well known but unlikely to be in use
	 * e.g. port 8080 on a Windows machine is a well known web server port, in
	 * the linux and unix worlds, but seldom used on Windows. You'll need to
	 * call the start(port) method instead of this one.
	 */
	public void start() {
		ServerSocket socket;
		try {
			socket = new ServerSocket(0);
		} catch (IOException ioe) {
			throw new RuntimeException("Could not obtain a free socket" + ioe);
		}
		start(socket.getLocalPort());
	}
	
	/**
	 * Start the local webserver on a specified
	 * @param portNumber
	 */
	public void start(int portNumber) {
		port = portNumber;
		Connector connector = new SocketConnector();
		connector.setPort(portNumber);
		webserver.addConnector(connector);
		try {
			webserver.start();
		} catch (Exception e) {
			throw new RuntimeException("Could not start " + webserver, e);
		}
	}

	/**
	 * Stop the local webserver.
	 */
	public void stop() {
		try {
			webserver.stop();
		} catch (Exception e) {
			Log.warn("Could not stop " + webserver, e);
		}
	}
	
	/**
	 * Get the port number the webserver is using to receive requests.
	 */
	public int getPort() {
		return port;
	}
}
