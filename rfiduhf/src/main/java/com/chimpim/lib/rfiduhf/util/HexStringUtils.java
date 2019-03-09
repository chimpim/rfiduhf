package com.chimpim.lib.rfiduhf.util;

import org.jetbrains.annotations.NotNull;

public class HexStringUtils {

    @NotNull
    public static String bytesToHexString(byte[] bytes) {
        if (null == bytes) return "";
        StringBuilder strBuilder = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (1 == hex.length()) {
                strBuilder.append("0");
            }
            strBuilder.append(hex.toUpperCase());
        }
        return strBuilder.toString();
    }

    @NotNull
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null) return new byte[0];
        int len = (hexString.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (charToByte(achar[pos]) << 4 | charToByte(achar[pos + 1]));
        }
        return result;
    }

    // 字符转字节
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
