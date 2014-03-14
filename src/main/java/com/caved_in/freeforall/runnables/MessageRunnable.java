package com.caved_in.freeforall.runnables;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class MessageRunnable implements Runnable {
	//TODO Optimize this entire class to use XML with an attribute "premiummessage"
	int lastMessageIndex = 0;
	int currentMessageIndex = 0;

	public MessageRunnable() {

	}

	@Override
	public void run() {
//		currentMessageIndex = currentMessageIndex >= TDMGame.messages.size() ? 0 : currentMessageIndex;
//
//
//		String sendingMessage = StringUtil.formatColorCodes(TDMGame.messages.get(currentMessageIndex));
//		boolean isPremiumMessage = false;
//		if (sendingMessage.toLowerCase().contains("premium")) {
//			isPremiumMessage = true;
//		}
//
//		for (Player player : Bukkit.getOnlinePlayers()) {
//			if (isPremiumMessage) {
//				if (!PlayerHandler.isPremium(player)) {
//					player.sendMessage(sendingMessage);
//				}
//			} else {
//				player.sendMessage(sendingMessage);
//			}
//		}
//		currentMessageIndex += 1;
	}
}