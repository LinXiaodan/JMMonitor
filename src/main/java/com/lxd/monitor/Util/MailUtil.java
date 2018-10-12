package com.lxd.monitor.Util;

//https://blog.csdn.net/u011244202/article/details/54809696/
//https://blog.csdn.net/weixin_39220472/article/details/80210909

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailUtil {
//    @Autowired
//    private JavaMailSender mailSender; //自动注入的Bean
////
//    @Value("${spring.mail.username}")
//    private String Sender;

    public void sendSimpleMail(JavaMailSender mailSender, String Sender) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Sender);
        message.setTo("1340787373@qq.com"); //自己给自己发送邮件
        System.out.println(Sender);
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
        System.out.println("Send email end.");
    }

    public void sendAttachmentsMail(JavaMailSender mailSender, String sender, String receiver, String filePath){
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSubject("主题：带附件的邮件");
            helper.setText("带附件的邮件内容");
            System.out.println("111");
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File(filePath));
            System.out.println("222");
            //加入邮件
            helper.addAttachment("a.txt", file);
            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
