package org.itbank.app.controllers;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/validate")
public class authEmailController {
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping("/sendMail") 
	@ResponseBody
		public String sendMailHandle(@RequestBody String body, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
			//인증번호
			UUID u = UUID.randomUUID();
			String auth_str = u.toString().substring(0,13);
			
			session.setAttribute("auth_str", auth_str);
			
			//파라미터 처리
			Map map = mapper.readValue(body, Map.class);
			String emailto=(String) map.get("email");
			
			//메일 전송
			int ecode =0;
			try {
				MimeMessage msg = sender.createMimeMessage();
				//To
				msg.setRecipient(RecipientType.TO, new InternetAddress(emailto));
				//Subject
				msg.setSubject("회원가입 인증 메일");
				//Text
				String text ="<h1>이메일 인증</h1>";
				text+=auth_str+"<>"; //uuid 인증번호 출력
				text += "<a href=\"http://192.168.10.78\">사이트 이동</a>";
				msg.setText(text, "UTF-8", "html");
				//From
				sender.send(msg); //발송
				ecode=1;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		 return "{\"result\": " +ecode +" }";	
		
		}
		
	
	//인증번호 확인
	@RequestMapping(path = "/check", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String emailValidateHandle(@RequestBody String body, HttpSession session)
			throws Exception {
		System.out.println("RequestBody :" +body);
		Map map = mapper.readValue(body, Map.class);
		System.out.println("map : "+map);
		int code=0;
		
		//사용자가 입력한 번호
		String vnum = (String) map.get("vnum");
		
		//세션에 올려진 번호
		String snum=(String) session.getAttribute("auth_str");
		System.out.println("vnum : " + vnum + "/ snum: " + snum);
		if(vnum.equals(snum) ) {
			code=1;
		}
			
		return "{\"result\": " +code +" }";
	}
}
