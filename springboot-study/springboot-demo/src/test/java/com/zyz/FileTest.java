package com.zyz;

import com.zyz.utils.XDocService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zyz
 * @date 2018/8/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Test
    public void testExcel() {
        XDocService service = new XDocService();
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("title", "微服务");
            param.put("img", "http://cxytiandi.com/images/gongzhonghao.jpg");
            service.run("C:\\Users\\zyz\\Desktop\\test.docx", new File("C:\\Users\\zyz\\Desktop\\test.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
