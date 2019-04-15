package io.linzhehuang.bunnyshare.utils.nodeinfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class NodeInfoServer {
	
	private MulticastSocket server;
	private DatagramPacket packet;
	
	public NodeInfoServer(String port,
			String multicastGroup, NodeInfo nodeInfo) {
		try {
			server = new MulticastSocket(Integer.parseInt(port));
			server.joinGroup(InetAddress.getByName(multicastGroup));
			packet = new DatagramPacket(nodeInfo.getBytes(), NodeInfo.SIZE,
					InetAddress.getByName(multicastGroup),
					Integer.parseInt(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void multicast(int sendTimes, long duration) {
		while ((sendTimes--) != 0) {
			try {
				server.send(packet);
				Thread.sleep(duration);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cleanup() {
		if (server != null) {
			server.close();
		}
	}
}
