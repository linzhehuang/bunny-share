package io.linzhehuang.bunnyshare.util.nodeinfo;

@FunctionalInterface
public interface ReceiveNodeInfoHandler {
	public void handler(NodeInfo nodeInfo);
}
