package org.itbank.app.controller.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*ws protocol ó���� ��Ʈ�ѷ�
 * 	AbstractWebSocketHandler �� TextWebSocketHandler �� �� �ϳ� ��ӹ���.
*/
public class BasicWSHandler extends TextWebSocketHandler{
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//Ŭ���̾�Ʈ������ ������ �߻����� ��
		System.out.println("afterConnectionEstablished");
		String msg="ȯ���մϴ�.";
		session.sendMessage(new TextMessage(msg));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//Ŭ���̾�Ʈ ������ ������ ���� �Ǿ�����
		System.out.println("afterConnectionClosed");
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//Ŭ���̾�Ʈ�κ��� send�� �߻� ( ������ ���۹޾��� ��)
		System.out.println("handleTextMessage");
		String m = message.getPayload(); //������ �����Ų �� ��ܿ��� ���
		System.out.println("���ŵ� ���� : " + m);
	}
}
