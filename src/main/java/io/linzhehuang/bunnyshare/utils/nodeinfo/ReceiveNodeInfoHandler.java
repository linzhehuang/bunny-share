package io.linzhehuang.bunnyshare.utils.nodeinfo;

@FunctionalInterface
public interface ReceiveNodeInfoHandler {
	public void handler(NodeInfo nodeInfo);
}
