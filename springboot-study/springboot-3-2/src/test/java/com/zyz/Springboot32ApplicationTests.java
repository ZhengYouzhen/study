package com.zyz;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot32ApplicationTests {

	/*@Autowired
    Configuration configuration;*/

	/*@Autowired
	JavaMailSender javaMailSender;*/

	private String from = "zhengyouzhen@dingpeikeji.com";
	private  String[] tos = {"1006747577@qq.com"};
	private String[] cc = {"yangzaicheng@dingpeikeji.com","c1061960674@qq.com"};
	private String[] bcc = {""};
	/**
	 * 只发送内容
	 * @throws Exception
	 */
	@Test
	public void sendSimpleMail() throws Exception {
		//注入配置信息
		JavaMailSenderImpl mailSender = initJavaMailSender();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(tos);
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}

	/**
	 * 发送附件
	 * @throws Exception
	 */
	@Test
	public void sendAttachmentsMail() throws Exception {
//		发送邮件需要的配置信息
		JavaMailSenderImpl mailSender = initJavaMailSender();
//		初始化发送邮件信息
		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		是否有文件
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		邮件具体信息----------------------------------------------------------------
//		发送者
		helper.setFrom(from);
//		接收者，可传数组
		helper.setTo(tos);
//		抄送者，可传数组
		helper.setCc(cc);
//		密送者，可传数组
		helper.setBcc(bcc);
//		邮件标题
		helper.setSubject("主题：有附件");
//		邮件内容
		helper.setText("有附件的邮件");
//		如果内容是html，可以把html换成单独的模版
//		helper.setText("<html><body><h1>这是html<h1></body></html>", true);
//		附件信息
		FileSystemResource file = new FileSystemResource(new File("D:/test.png"));
//		添加发送附件
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);
//		邮件发送
		mailSender.send(mimeMessage);
	}

	/**
	 * 发送带有html格式的内容
	 * @throws Exception
	 */
	@Test
	public void sendInlineMail() throws Exception {
		JavaMailSenderImpl mailSender = initJavaMailSender();
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(tos);
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"https://rescdn.qqmail.com/zh_CN/htmledition/images/webp/spacer1e9c5d.gif\" ></body></html>", true);

		FileSystemResource file = new FileSystemResource(new File("D:/test.png"));
		helper.addInline("weixin", file);

		mailSender.send(mimeMessage);
	}

	/**
	 * 模版邮件
	 * @throws Exception
	 */
    /*@Test
    public void sendTemplateMail() throws Exception {
        JavaMailSenderImpl mailSender = initJavaMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("zhengyouzhen@dingpeikeji.com");
        helper.setTo("1006747577@qq.com");
        helper.setSubject("主题：模板邮件");
        Map<String, Object> model = new HashedMap();
        model.put("username", "lyz");
//        这里引用模版邮件用的是freemarker
        Template t = configuration.getTemplate("template.vm"); // freeMarker template
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }*/

	/**
	 * 这个方法在实际应用中，springboot会通过在配置文件application.xml
	 * 中加配置自动实例化JavaMailSenderImpl。
	 * 这里只是手动把信息写入JavaMailSenderImpl中。
	 */
	public static JavaMailSenderImpl initJavaMailSender() {
//		以下配置信息具体作用请自行百度
		Properties properties = new Properties();
		properties.setProperty("mail.debug", "true");// 是否显示调试信息(可选)
		properties.setProperty("mail.smtp.starttls.enable", "false");
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.required", "true");
		properties.put(" mail.smtp.timeout ", " 25000 ");

//		发送邮件配置
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setJavaMailProperties(properties);
		javaMailSender.setHost("smtp.ym.163.com");
		javaMailSender.setUsername("yangzaicheng@dingpeikeji.com");
		javaMailSender.setPassword("ZYZ0124?");
		javaMailSender.setPort(465);
		javaMailSender.setDefaultEncoding("UTF-8");

		return javaMailSender;
	}

}
