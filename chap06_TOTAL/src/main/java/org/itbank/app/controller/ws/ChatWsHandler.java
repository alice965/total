package org.itbank.app.controller.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("cws")
public class ChatWsHandler extends TextWebSocketHandler {
	List<WebSocketSession> list ;
	@Autowired
	ObjectMapper mapper;
	
	@PostConstruct //빈 설정 init-method
	public void chatWSInit() {
		System.out.println("ChatWsHandler..chatInit");
		list= new ArrayList<WebSocketSession>();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished");
		list.add(session);
		String json = String.format("{\"mode\":\"join\", \"cnt\":%d ,\"user\" : \"%s\"}", list.size(),
				"사용자" + session.getId());
		System.out.println(json +" at afterConnectionEstablished." );
		for(WebSocketSession wss : list) {
			if(wss != session) {
				wss.sendMessage(new TextMessage(json));
			} 
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed");
		list.remove(session);
		String json = String.format("{\"mode\":\"exit\", \"cnt\":%d ,\"user\" : \"%s\"}", list.size(),
				"사용자" + session.getId());
		System.out.println(json + " at afterConnectionEstablished.");
		for (WebSocketSession wss : list) {
				wss.sendMessage(new TextMessage(json));
		}
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage");
		
		Map map = new HashMap<>();
			map.put("mode", "chat");
			map.put("sender", "사용자 :"+session.getId());
			map.put("msg", message.getPayload());
			map.put("cnt", list.size());
		
		String json = mapper.writeValueAsString(map);
		System.out.println(json);
		
		for(WebSocketSession wss: list) {
			wss.sendMessage(new TextMessage(json));
		}
		
		
		String m = message.getPayload();
		String id = session.getId(); //HttpSession ID는 아니고, 자동으로 1씩 증가 설정
	}

}
