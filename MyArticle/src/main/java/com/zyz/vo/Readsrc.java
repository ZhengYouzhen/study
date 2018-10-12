package com.zyz.vo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zyz
 * @date 2018/7/30
 */
public class Readsrc {

        public static void main(String[] args) throws IOException {
            try {
//                      first();
                second();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 第一种，直接得到输入流
         * @throws IOException
         */
        public static void first() throws IOException {
            InputStream in = Readsrc.class.getClassLoader().getResourceAsStream("test.txt");
            byte[] bytes = new byte[1024];
            int length;
            while((length = in.read(bytes)) != -1) {
                System.out.write(bytes, 0, length);
            }
        }

        /**
         * 第二种，先获取文件的绝对路径
         * @throws Exception
         */
        public static void second() throws Exception {
            String filePath = Readsrc.class.getResource("/test.txt").getPath();
            filePath = filePath.substring(1);
            InputStream in = new FileInputStream(new File(filePath));
            byte[] bytes = new byte[1024];
            int length;
            while((length = in.read(bytes)) != -1) {
                System.out.write(bytes, 0, length);
            }
        }



}
