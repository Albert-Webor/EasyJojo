package com.easyjojo.common.utils;

public class DecodeUtils {
    public static String getScbTaxId(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        // 密钥是 "idcard" 的第一个字母 'i'
        char key = 'i';
        StringBuilder result = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            // 对每一个字符进行 XOR (异或) 运算，并强制转换回 char 类型
            result.append((char) (c ^ key));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String original = "[\\][[[]YQZQ_Q";
        String encoded = getScbTaxId(original);
        String decoded = getScbTaxId(encoded);

        System.out.println("Original: " + original);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + decoded);
    }

}
