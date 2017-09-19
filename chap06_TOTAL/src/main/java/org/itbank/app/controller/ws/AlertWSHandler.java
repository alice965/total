package org.itbank.app.controller.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component("aws")
public class AlertWSHandler extends TextWebSocketHandler{
	List<WebSocketSession> list =  new ArrayList<WebSocketSession>();
	
	//이 핸들러 객체를 통해 열결되어 있는 모든 WebSocketSession에 메세지를 보낼 수 있는
	//메서드를 하나 추가하고, 
	public void sendMessage(String message) {
		for(WebSocketSession s : list) {
			try {
				s.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
	}
	

}
