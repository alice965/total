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
		//HttpSession�� ��� ����?
		//Intercepter ����� �� ��쿡�� ����
		Map<String, Object> hs = session.getAttributes();
		System.out.println("ws login hs�� : "+hs);
		//HttpSession setAttribute �Ǿ� �ִ� ������ ������ �����
		String id =(String)hs.get("auth_id");
		users.put(id, session);
	}
	
	//��Ʈ�ѷ� �ʿ��� ����ϱ� ���� �޼��� �ϳ� �߰�
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
