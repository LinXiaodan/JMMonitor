package com.lxd.monitor.controller;

import com.lxd.monitor.Util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class jobController {
    @Autowired
    private JavaMailSender mailSender; //自动注入的Bean

    @Value("${spring.mail.username}")
    private String Sender;

    @RequestMapping(value = "/index")
    public String index(){
        MailUtil mailUtil = new MailUtil();
        mailUtil.sendSimpleMail(mailSender, Sender);
        return "index";
    }

    @RequestMapping(value = "/testSendFile")
    public String send(){
        MailUtil mailUtil = new MailUtil();
        mailUtil.sendAttachmentsMail(mailSender, Sender, Sender, "C:/Users/linxiaodan/Desktop/a.txt");
        return "send";
    }
}
