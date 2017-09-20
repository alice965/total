package org.itbank.app.controller.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component("lws")
public class LoginUserWsHandler extends TextWebSocketHandler{
	Map<String, WebSocketSession> users = new HashMap<>();
	// Map<String, Set<WebSocketSession> > users
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//HttpSession을 어떻게 접근?
		//Intercepter 등록을 한 경우에만 가능
		Map<String, Object> hs = session.getAttributes();
		System.out.println("ws login hs값 : "+hs);
		//HttpSession setAttribute 되어 있는 값들이 맵으로 추출됨
		String id =(String)hs.get("auth_id");
		users.put(id, session);
	}
	
	//컨트롤러 쪽에서 사용하기 위해 메서드 하나 추가
	public void sendMessageToUser(String id, String msg) {
		if(users.containsKey(id)) {
			try {
				users.get(id).sendMessage(new TextMessage(msg));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
