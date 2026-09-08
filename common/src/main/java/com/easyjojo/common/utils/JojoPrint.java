package com.easyjojo.common.utils;

/**
 * 控制台彩色打印工具类
 */
public final class JojoPrint {

    // 重置颜色
    private static final String RESET = "\033[0m";

    // 样式
    private static final String BOLD = "\033[1m";

    // 前景基础颜色 (文字颜色)
    private static final String BLACK = "\033[30m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
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

    private JojoPrint() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // ================= 基础单参数打印 (snake_case) =================

    public static void print_red(Object msg) {
        System.out.println(RED + msg + RESET);
    }

    public static void print_green(Object msg) {
        System.out.println(GREEN + msg + RESET);
    }

    public static void print_yellow(Object msg) {
        System.out.println(YELLOW + msg + RESET);
    }

    public static void print_blue(Object msg) {
        System.out.println(BLUE + msg + RESET);
    }

    public static void print_purple(Object msg) {
        System.out.println(PURPLE + msg + RESET);
    }

    public static void print_cyan(Object msg) {
        System.out.println(CYAN + msg + RESET);
    }

    public static void print_white(Object msg) {
        System.out.println(WHITE + msg + RESET);
    }

    public static void print_black(Object msg) {
        System.out.println(BLACK + msg + RESET);
    }

    // ================= 粗体高亮打印 =================

    public static void print_bold(Object msg) {
        System.out.println(BOLD + msg + RESET);
    }

    public static void print_bold_red(Object msg) {
        System.out.println(BOLD + RED + msg + RESET);
    }

    public static void print_bold_green(Object msg) {
        System.out.println(BOLD + GREEN + msg + RESET);
    }

    public static void print_bold_yellow(Object msg) {
        System.out.println(BOLD + YELLOW + msg + RESET);
    }

    public static void print_bold_blue(Object msg) {
        System.out.println(BOLD + BLUE + msg + RESET);
    }

    public static void print_bold_purple(Object msg) {
        System.out.println(BOLD + PURPLE + msg + RESET);
    }

    public static void print_bold_cyan(Object msg) {
        System.out.println(BOLD + CYAN + msg + RESET);
    }

    // ================= 格式化打印 (支持 String.format 占位符) =================

    public static void print_red(String format, Object... args) {
        System.out.println(RED + String.format(format, args) + RESET);
    }

    public static void print_green(String format, Object... args) {
        System.out.println(GREEN + String.format(format, args) + RESET);
    }

    public static void print_yellow(String format, Object... args) {
        System.out.println(YELLOW + String.format(format, args) + RESET);
    }

    public static void print_blue(String format, Object... args) {
        System.out.println(BLUE + String.format(format, args) + RESET);
    }

    public static void print_cyan(String format, Object... args) {
        System.out.println(CYAN + String.format(format, args) + RESET);
    }

    // ================= 驼峰命名兼容 (camelCase) =================

    public static void printRed(Object msg) {
        print_red(msg);
    }

    public static void printGreen(Object msg) {
        print_green(msg);
    }

    public static void printYellow(Object msg) {
        print_yellow(msg);
    }

    public static void printBlue(Object msg) {
        print_blue(msg);
    }

    public static void printCyan(Object msg) {
        print_cyan(msg);
    }
}
