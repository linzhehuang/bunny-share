package io.linzhehuang.bunnyshare.utils.nodeinfo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class NodeInfoTests {
	private static final String IPV4 = "127.255.0.1";
	private static final String PORT = "8080";
	private static final byte[] BYTES = {
			0x7f, -0x1, 0x0, 0x1,
			0x1f, -0x70
	};
	
	@Test
	public void getBytesTest() {
		NodeInfo nodeInfo = new NodeInfo(IPV4,
				PORT /* 8080 equals 0001 1111 1001 0000 */ );
		/* 1001 0000 (two's complement representation)
		 * 1000 1111 (ones' complement)
		 * 1111 0000 (true form) equals -0x70
		 */
		assertArrayEquals(BYTES, nodeInfo.getBytes());		
	}
	
	@Test
	public void parseTests() {
		NodeInfo nodeInfo = new NodeInfo();
		nodeInfo.parse(BYTES);
		assertEquals(IPV4, nodeInfo.getIpv4());
		assertEquals(PORT, nodeInfo.getPort());
	}
}
