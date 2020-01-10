package com.tudou.extra.system;

/**
 * 用户信息工具
 *
 * @author weihua
 * @create 2017-05-13 11:24
 */
public final class UserInfoUtil {
   private static AuthInfo authInfo = new AuthInfo();

    public static void setAuthInfo(String userId,String userName,String department){
        authInfo.setEmpId(userId);
        authInfo.setEmpName(userName);
        authInfo.setDepartment(department);
    }

    /**
     * 获取用户的验证信息
     *
     * @return
     */
    public static AuthInfo getCurrentAuthInfo() {
        return authInfo;
    }
}
