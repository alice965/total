package org.itbank.app.controller.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*ws protocol 처리용 컨트롤러
 * 	AbstractWebSocketHandler 나 TextWebSocketHandler 둘 중 하나 상속받음.
*/
public class BasicWSHandler extends TextWebSocketHandler{
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//클라이언트측에서 연결이 발생했을 때
		System.out.println("afterConnectionEstablished");
		String msg="환영합니다.";
		session.sendMessage(new TextMessage(msg));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//클라이언트 측에서 연결이 해제 되었을때
		System.out.println("afterConnectionClosed");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//클라이언트로부터 send가 발생 ( 데이터 전송받았을 때)
		System.out.println("handleTextMessage");
		String m = message.getPayload(); //웹소켓 센드시킨 거 당겨오는 기능
		System.out.println("수신된 내용 : " + m);
	}
}
