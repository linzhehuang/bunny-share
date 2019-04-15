package io.linzhehuang.bunnyshare.utils.nodeinfo;

public class NodeInfo {
	public static final int SIZE = 6;
	private String ipv4 = null;
	private String port = null;
	
	public NodeInfo() { }
	
	public NodeInfo(String ipv4, String port) {
		// TODO: checkout arguments
		this.ipv4 = ipv4;
		this.port = port;
	}
	
	public byte[] getBytes() {
		byte[] ret = new byte[SIZE];
		int i = 0;
		// 4 bytes ipv4
		for (String field : ipv4.split("\\.")) {
			ret[i++] = (byte)Short.parseShort(field);
		}
		// 2 bytes port
		ret[i++] = (byte)((Short.parseShort(port) & 0xFF00) >> 8);
		ret[i] = (byte)(Short.parseShort(port) & 0xFF);
		return ret;
	}
	
	public void parse(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(
					Integer.toString(
							Byte.toUnsignedInt(bytes[i])));
			if (i != 3) sb.append(".");
		}
		ipv4 = sb.toString();
		int portHigh = Byte.toUnsignedInt(bytes[4]),
			portLow = Byte.toUnsignedInt(bytes[5]);
		port = Integer.toString((portHigh << 8) + portLow);
	}

	public String getIpv4() {
		return ipv4;
	}

	public String getPort() {
		return port;
	}
}
