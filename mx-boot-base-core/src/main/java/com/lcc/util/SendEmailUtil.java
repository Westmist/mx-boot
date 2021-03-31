package com.lcc.util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Congcong Liao
 * @since 2021-01-26
 **/


/**
 * @Package: com.jzkj.util
 * @ClassName: 邮件工具类
 * @Author: Cong-Cong Liao
 * @CreateTime: 2021/1/19
 * @Description:
 */
public class SendEmailUtil {
    /**
     * 发送邮件
     *
     * @param address 接收方邮件地址
     * @throws Exception
     */
    public static void sendMailMessage(String address) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.ssl.enable", "false");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置环境信息
        Session session = Session.getDefaultInstance(props);
        // 设置debug模式 在控制台看到交互信息
        session.setDebug(true);
        // 建立一个要发送的信息
        Message msg = new MimeMessage(session);
        msg.setSubject("【明轩博客注册验证码】");
        // 邮件正文
        StringBuffer messageText = new StringBuffer();
        messageText.append("<h3>您正在注册明轩博客账户，请点击下方链接激活账号：</h3>");
        messageText.append("<a href=\"");
        // 实际的跳转地址
//        messageText.append("https://www.mingxuanblog.cn?activeUser=");
        // 本地测试接口
        messageText.append("http://localhost:8086/userInfoManager/activeUser?email=");
        // 将邮箱号设置为验证参数
        messageText.append(address);
        // 邮件中显示的链接
        messageText.append("\">https://www.mingxuanblog.cn</a>");
        messageText.append("<h3>如非本人操作请忽略。</h3>");
        // 邮件格式设置为Html
        msg.setContent(messageText.toString(), "text/html;charset=UTF-8");
        // 发件人邮箱号
        msg.setFrom(new InternetAddress("mingxuanblog@163.com"));
        // 发送信息的工具
        Transport transport = session.getTransport();
        // 发件人邮箱号和密码
        transport.connect("smtp.163.com", 25, "mingxuanblog", "QTXWOTEUSHARYDWT");
        transport.sendMessage(msg, new Address[]
                {new InternetAddress(address)});
        transport.close();
    }

}

