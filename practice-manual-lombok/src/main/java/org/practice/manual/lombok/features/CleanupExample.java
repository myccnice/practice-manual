package org.practice.manual.lombok.features;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.Cleanup;

/**
 * https://projectlombok.org/features/Cleanup
 * 
 * Cleanup:自动资源管理。等效于try finally方式关闭资源，必须进行异常抛出，如果需要catch异常建议使用JDK7提供资源自动管理特性。
 *
 * @author 王鹏
 * @date 2019年1月31日
 */
public class CleanupExample {

    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }
}
