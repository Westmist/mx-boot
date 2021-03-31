package com.lcc.util;

import java.util.Random;

/**
 * @Package: com.jzkj.util
 * @ClassName: 验证码工具类
 * @Author: Cong-Cong Liao
 * @CreateTime: 2021/1/19
 * @Description:
 */
public class RandomCodeUtil {
    /**
     * 生成字符串类型的数字验证码
     * @param total 需要生成的验证数位
     * @return
     */
    public static String randomCode(int total) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < total; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
