package io.linzhehuang.bunnyshare.utils.nodeinfo;

import org.junit.Test;

public class NodeInfoCommunicationTests {
	private final static String PORT = "9000";
	private final static String MULTICAST_GROUP = "225.0.0.1";
	private final static NodeInfo NODE_INFO = new NodeInfo("192.168.1.2", "9002");
	private final static int SEND_TIMES = 2;
	private final static long DURATION = 1000;
	
	@Test
	public void mainTest() throws InterruptedException {
		NodeInfoClient client = new NodeInfoClient(
				PORT, MULTICAST_GROUP);
		client.registerHandler(new ReceiveNodeInfoHandler() {
			public void handler(NodeInfo nodeInfo) {
				System.out.println(
						nodeInfo.getIpv4() + ":"+ nodeInfo.getPort());
			}
		}).startup();
		
		NodeInfoServer server = new NodeInfoServer(
				PORT, MULTICAST_GROUP, NODE_INFO);
		server.multicast(SEND_TIMES, DURATION);
		server.cleanup();
	}
}
