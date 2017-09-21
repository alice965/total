package org.itbank.app.controllers;

import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping("/basic")
	public String basicHandle() {
		return "test/basic";
	}
	
	@RequestMapping("/send01") 
		public void send01Handle(@RequestParam(name="t") String t) {
			//1. SimpleMailMessage ��ü �̿�
			SimpleMailMessage msg = new SimpleMailMessage();
				//�߽���, ������, ����
				try {
					msg.setTo(t);	//���� ��� �ּ�
					msg.setFrom("testalice965@gmail.com"); //������ ��� �ּ� (���⿡ �����ص� ������ ���� ���Ͽ� ���õ� �ּҷ� ���ư�)
						//smtp.gmail ���� ��ȸ�ؼ� ���۵� ��, ������ ��� �ּҰ� ������ �ּҷ� �ڵ� ��ȯ
					msg.setSubject("�̸��� ����");
					String text = "ȯ���մϴ�. \n  �ּ��� ���񽺸� �����ϰڽ��ϴ�. ";
					text +="<h2>testtest</h2>"; //�±� �ȵ�.. �ؽ�Ʈ�� ���� ����
					msg.setText(text);
					sender.send(msg); //�߼�
				} catch(Exception e) {
					e.printStackTrace();
				}
	}
	@RequestMapping("/send02")
	public void send02Handle(@RequestParam(name="t") String t) {
		//MimeMessage
		try {
			MimeMessage msg = sender.createMimeMessage();
			//To
			msg.setRecipient(RecipientType.TO, new InternetAddress(t));
			//Subject
			msg.setSubject("Mime Test ����");
			//Text
			String text ="<h1>ȯ���մϴ�</h1>";
			text+="���� ȯ���մϴ�";
			text += "<a href=\"http://192.168.10.78\">����Ʈ �̵�</a>";
			msg.setText(text, "UTF-8", "html");
			//From
			sender.send(msg); //�߼�
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/uuid")
	public void uuidHandle() {
		UUID u = UUID.randomUUID(); //���� �ĺ��ڰ� �ʿ��Ҷ� ����ϴ� Ŭ����
		// ������ ��Ģ�� ���� ���� �Ϻ��ϰ� �ߺ����� �ʴ� ���� Ű�� ����.
		// 32���� 16����.
		System.out.println(Math.pow(16, 32));
		System.out.println("uuid���� �����ϴ� ���� ���� : " + u.toString());
		String auth_str = u.toString().substring(0,13);
		System.out.println("������ȣ(auth_str) : "+auth_str);
	}

}
