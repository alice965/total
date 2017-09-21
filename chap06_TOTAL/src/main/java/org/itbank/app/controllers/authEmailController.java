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
			//������ȣ
			UUID u = UUID.randomUUID();
			String auth_str = u.toString().substring(0,13);
			
			session.setAttribute("auth_str", auth_str);
			
			//�Ķ���� ó��
			Map map = mapper.readValue(body, Map.class);
			String emailto=(String) map.get("email");
			
			//���� ����
			int ecode =0;
			try {
				MimeMessage msg = sender.createMimeMessage();
				//To
				msg.setRecipient(RecipientType.TO, new InternetAddress(emailto));
				//Subject
				msg.setSubject("ȸ������ ���� ����");
				//Text
				String text ="<h1>�̸��� ����</h1>";
				text+=auth_str+"<>"; //uuid ������ȣ ���
				text += "<a href=\"http://192.168.10.78\">����Ʈ �̵�</a>";
				msg.setText(text, "UTF-8", "html");
				//From
				sender.send(msg); //�߼�
				ecode=1;
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		 return "{\"result\": " +ecode +" }";	
		
		}
		
	
	//������ȣ Ȯ��
	@RequestMapping(path = "/check", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String emailValidateHandle(@RequestBody String body, HttpSession session)
			throws Exception {
		System.out.println("RequestBody :" +body);
		Map map = mapper.readValue(body, Map.class);
		System.out.println("map : "+map);
		int code=0;
		
		//����ڰ� �Է��� ��ȣ
		String vnum = (String) map.get("vnum");
		
		//���ǿ� �÷��� ��ȣ
		String snum=(String) session.getAttribute("auth_str");
		System.out.println("vnum : " + vnum + "/ snum: " + snum);
		if(vnum.equals(snum) ) {
			code=1;
		}
			
		return "{\"result\": " +code +" }";
	}
}
