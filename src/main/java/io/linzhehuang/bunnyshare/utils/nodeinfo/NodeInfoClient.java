package io.linzhehuang.bunnyshare.utils.nodeinfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class NodeInfoClient {
	private MulticastSocket client;
	private ReceiveNodeInfoHandler handler;
	private boolean looped;
	private Thread listenerThread;
	
	public NodeInfoClient(String port, String multicastGroup) {
		try {
			client = new MulticastSocket(Integer.parseInt(port));
			client.joinGroup(InetAddress.getByName(multicastGroup));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NodeInfoClient registerHandler(ReceiveNodeInfoHandler handler) {
		this.handler = handler;
		return this;
	}
	
	public NodeInfoClient startup() {
		looped = true;
		listenerThread = new Thread(new Runnable() {
			public void run() {
				while (looped) {
					DatagramPacket packet = new DatagramPacket(
							new byte[NodeInfo.SIZE], NodeInfo.SIZE);
					try {
						client.receive(packet);
						if (handler != null) {
							NodeInfo nodeInfo = new NodeInfo();
							nodeInfo.parse(packet.getData());
							handler.handler(nodeInfo);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		listenerThread.start();
		return this;
	}
	
	public void block() {
		try {
			listenerThread.join(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		looped = false;
		listenerThread.interrupt();
		client.close();
	}
}
