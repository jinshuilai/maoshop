package com.mao.shop.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(mywebsockHandler(), "/websocket.do").addInterceptors(new WebsocketHandshakeInterceptor());
		System.out.println("==========websocketע��ɹ�==========");
	}

	@Bean
	public WebSocketHandler mywebsockHandler(){
		return new WebsocketEndPoint();
	}
}
