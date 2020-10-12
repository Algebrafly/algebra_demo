package com.algebra.authentication;

import com.algebra.authentication.util.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/10/12 10:31
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {

    @Autowired
    MailUtil mailService;

    private static final String TO = "1136125618@qq.com";
    private static final String SUBJECT = "测试邮件";
    private static final String CONTENT = "test content";

    @Test
    public void sendSimpleMailMessage() {
        mailService.sendSimpleMailMessage(TO, SUBJECT, CONTENT);
    }

    /**
     * 测试发送html邮件
     */
    @Test
    public void sendHtmlMessage() {
        String htmlStr = "<h1>Test</h1>";
        mailService.sendMimeMessage(TO, SUBJECT, htmlStr);
    }

    /**
     * 测试发送带附件的邮件
     * @throws FileNotFoundException
     */
    @Test
    public void sendAttachmentMessage() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:test.txt");
        String filePath = file.getAbsolutePath();
        mailService.sendMimeMessage(TO, SUBJECT, CONTENT, filePath);
    }

    /**
     * 测试发送带附件的邮件
     * @throws FileNotFoundException
     */
    @Test
    public void sendPicMessage() throws FileNotFoundException {
        String htmlStr = "<html><body>测试：图片1 <img src='cid:pic1' /> </body></html>";
        Map<String, String> rscIdMap = new HashMap<>(2);
        rscIdMap.put("pic1", ResourceUtils.getFile("classpath:pic01.jpg").getAbsolutePath());
        mailService.sendMimeMessage(TO, SUBJECT, htmlStr, rscIdMap);
    }


}
