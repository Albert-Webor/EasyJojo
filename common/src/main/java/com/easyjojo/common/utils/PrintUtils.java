package com.easyjojo.common.utils;

/**
 * 控制台彩色打印工具类
 */
public final class PrintUtils {

    // 重置颜色
    private static final String RESET = "\033[0m";

    // 样式
    private static final String BOLD = "\033[1m";

    // 前景基础颜色 (文字颜色)
    private static final String BLACK = "\033[30m";
    private static final String RED = "\033[31m";
    // 采用 ANSI 高亮鲜绿色 (Bright/High-Intensity Green: \033[92m)，替代昏暗的原生暗绿 (\033[32m)
    private static final String GREEN = "\033[92m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String PURPLE = "\033[35m";
    private static final String CYAN = "\033[36m";
    private static final String WHITE = "\033[37m";

    // 背景颜色
    private static final String RED_BG = "\033[41m";
    private static final String GREEN_BG = "\033[42m";
    private static final String YELLOW_BG = "\033[43m";
    private static final String BLUE_BG = "\033[44m";

    private PrintUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ================= 基础单参数打印 (camelCase) =================

    public static void printRed(Object msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void printGreen(Object msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void printYellow(Object msg) {
        System.out.println(YELLOW + msg + RESET);
    }

    public static void printBlue(Object msg) {
        System.out.println(BLUE + msg + RESET);
    }

    public static void printPurple(Object msg) {
        System.out.println(PURPLE + msg + RESET);
    }

    public static void printCyan(Object msg) {
        System.out.println(CYAN + msg + RESET);
    }

    public static void printWhite(Object msg) {
        System.out.println(WHITE + msg + RESET);
    }

    public static void printBlack(Object msg) {
        System.out.println(BLACK + msg + RESET);
    }

    // ================= 粗体高亮打印 (camelCase) =================

    public static void printBold(Object msg) {
        System.out.println(BOLD + msg + RESET);
    }

    public static void printBoldRed(Object msg) {
        System.out.println(BOLD + RED + msg + RESET);
    }

    public static void printBoldGreen(Object msg) {
        System.out.println(BOLD + GREEN + msg + RESET);
    }

    public static void printBoldYellow(Object msg) {
        System.out.println(BOLD + YELLOW + msg + RESET);
    }

    public static void printBoldBlue(Object msg) {
        System.out.println(BOLD + BLUE + msg + RESET);
    }

    public static void printBoldPurple(Object msg) {
        System.out.println(BOLD + PURPLE + msg + RESET);
    }

    public static void printBoldCyan(Object msg) {
        System.out.println(BOLD + CYAN + msg + RESET);
    }

    public static void printBoldWhite(Object msg) {
        System.out.println(BOLD + WHITE + msg + RESET);
    }

    public static void printBoldBlack(Object msg) {
        System.out.println(BOLD + BLACK + msg + RESET);
    }

    // ================= 格式化打印 (支持 String.format 占位符, camelCase) =================

    public static void printRed(String format, Object... args) {
        System.out.println(RED + String.format(format, args) + RESET);
    }

    public static void printGreen(String format, Object... args) {
        System.out.println(GREEN + String.format(format, args) + RESET);
    }

    public static void printYellow(String format, Object... args) {
        System.out.println(YELLOW + String.format(format, args) + RESET);
    }

    public static void printBlue(String format, Object... args) {
        System.out.println(BLUE + String.format(format, args) + RESET);
    }

    public static void printPurple(String format, Object... args) {
        System.out.println(PURPLE + String.format(format, args) + RESET);
    }

    public static void printCyan(String format, Object... args) {
        System.out.println(CYAN + String.format(format, args) + RESET);
    }

    public static void printWhite(String format, Object... args) {
        System.out.println(WHITE + String.format(format, args) + RESET);
    }

    public static void printBlack(String format, Object... args) {
        System.out.println(BLACK + String.format(format, args) + RESET);
    }
}
