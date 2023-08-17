package com.atbanfei.ssm.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;





public class TokenUtil {
    // token有效期，单位为秒,时间为一小时
    private static final int TOKEN_EXPIRE_TIME = 3600;

    // 存储token的map，key为token，value为用户id
    public static Map<String, Integer> tokenMap = new HashMap<>();
    /**
     * 用户登录，生成并返回token
     * @param userId 用户id
     * @return token
     */
    public static String login(int userId) {
        // 生成唯一的token
        String token = UUID.randomUUID().toString().replace("-", "");
        // 将token和用户id存入map中
        tokenMap.put(token, userId);
        System.out.println(token);
        return token;
    }
    /**
     * 验证token是否有效，如果有效则返回对应的用户id
     * @param token
     * @return 用户id，如果token无效则返回null
     */
    public static Integer verifyToken(String token) {
        // 检查token是否存在
        if (!tokenMap.containsKey(token)) {
            return null;
        }
        // 检查token是否过期
        long createTime = Long.parseLong(token.substring(0, 13));
        long nowTime = System.currentTimeMillis();
        if ((nowTime - createTime) / 1000 > TOKEN_EXPIRE_TIME) {
            return null;
        }
        // 返回对应的用户id
        return tokenMap.get(token);
    }
    /**
     * 用户退出登录，删除对应的token
     * @param token
     */
    public static void logout(String token) {
        tokenMap.remove(token);
    }
}



