package com.mao.shop.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebsocketEndPoint implements WebSocketHandler {

	private static final Logger logger;
	private static final List<WebSocketSession> users;
	
	static{
		users = new ArrayList<WebSocketSession>();
		logger = LoggerFactory.getLogger(WebsocketEndPoint.class);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1) throws Exception {
		logger.debug("websocket connection closed");
		users.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.debug("connect to the websocket success");
		users.add(session);
		//查询数据库是否有未处理的订单并通知
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		//后台不用回传消息
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable arg1) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		logger.debug("websocket connection closed");
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToUser(String username,TextMessage message) {
		for (WebSocketSession user : users) {
			if (user.getAttributes().get("username").equals(username)) {
				try {
					if (user.isOpen()) {
						user.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
