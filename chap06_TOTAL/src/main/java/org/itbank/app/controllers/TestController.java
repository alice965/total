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
			//1. SimpleMailMessage 객체 이용
			SimpleMailMessage msg = new SimpleMailMessage();
				//발신자, 수신자, 내용
				try {
					msg.setTo(t);	//받을 사람 주소
					msg.setFrom("testalice965@gmail.com"); //보내는 사람 주소 (여기에 설정해도 스프링 설정 파일에 세팅된 주소로 날아감)
						//smtp.gmail 이쪽 우회해서 전송될 때, 보내는 사람 주소가 지메일 주소로 자동 변환
					msg.setSubject("이메일 제목");
					String text = "환영합니다. \n  최선의 서비스를 제공하겠습니다. ";
					text +="<h2>testtest</h2>"; //태그 안들어감.. 텍스트만 전송 가능
					msg.setText(text);
					sender.send(msg); //발송
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
			msg.setSubject("Mime Test 제목");
			//Text
			String text ="<h1>환영합니다</h1>";
			text+="가입 환영합니다";
			text += "<a href=\"http://192.168.10.78\">사이트 이동</a>";
			msg.setText(text, "UTF-8", "html");
			//From
			sender.send(msg); //발송
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/uuid")
	public void uuidHandle() {
		UUID u = UUID.randomUUID(); //고유 식별자가 필요할때 사용하는 클래스
		// 정해진 규칙에 의해 거의 완벽하게 중복되지 않는 고유 키를 생성.
		// 32개의 16진수.
		System.out.println(Math.pow(16, 32));
		System.out.println("uuid에서 생성하는 숫자 갯수 : " + u.toString());
		String auth_str = u.toString().substring(0,13);
		System.out.println("인증번호(auth_str) : "+auth_str);
	}

}
