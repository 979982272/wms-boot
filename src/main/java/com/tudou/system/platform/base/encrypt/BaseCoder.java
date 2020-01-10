package com.tudou.system.platform.base.encrypt;

/**
 * 加密基类
 *
 * @author weihua
 * @create 2017-05-13 16:50
 */
public abstract class BaseCoder {
    public BaseCoder() {
    }

    public static final String byteToHex(byte[] hash) {
        if(hash == null) {
            return null;
        } else {
            StringBuffer buf = new StringBuffer(hash.length * 2);

            for(int i = 0; i < hash.length; ++i) {
                if((hash[i] & 255) < 16) {
                    buf.append("0");
                }

                buf.append(Long.toString((long)(hash[i] & 255), 16));
            }

            return buf.toString().toUpperCase();
        }
    }

    public static final byte[] hexToByte(String hex) {
        if(hex == null) {
            return null;
        } else {
            int l = hex.length();
            if(l % 2 == 1) {
                return null;
            } else {
                byte[] b = new byte[l / 2];
                for(int i = 0; i != l / 2; ++i) {
                    b[i] = (byte)Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
                }
                return b;
            }
        }
    }
}
