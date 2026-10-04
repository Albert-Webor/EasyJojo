package com.easyjojo.common.shaunjava;

import com.easyjojo.common.utils.PrintUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BIO 高性能文件写入测试
 * 用于高效生成大量随机数据（如 1 亿行 10 位随机数字）
 */
public class ShaunBIO{
    static String SPATH = "/Users/shaun/Downloads";
    static String FILENAME = "acct.txt";
    static int SUB_FIEL_SIZE =  1000;
    private static final char[] LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main(String[] args) throws IOException {
//        genData(50000000);
//        double fileSize = Files.size(Path.of(SPATH + "/" + FILENAME))/1024.0;
//        Path.of(SPATH);
//        PrintUtils.printGreen("原始文件大小："+ fileSize +"KB");
//        long beginTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        PrintUtils.printGreen("开始时间:"+ beginTime);
//        try (BufferedReader br = new BufferedReader(new FileReader(SPATH + "/" + FILENAME),384 * 50000);){
//            String lineData;
//            int a=0;
//            int count = 0;
//            BufferedWriter bw = null;
//            while((lineData = br.readLine()) != null){
//                if(Files.notExists(Path.of(SPATH + "/sub_acct_"+a+".txt"))) {
//                    Files.createFile(Path.of(SPATH + "/sub_acct_"+a+".txt"));
//                    bw = new BufferedWriter(new FileWriter(SPATH + "/sub_acct_"+a+".txt"), 192 * 50000);
//                }
//                bw.write(lineData);
//                bw.newLine();
//                if(count / 200 == 0 && Files.size(Path.of(SPATH + "/sub_acct_"+a+".txt"))/(1024.0 * 1024.0) > 1000.0){
//                    bw.flush();
//                    bw.close();
//                    a++;
//                    count = 0;
//                }
//            }
//            long endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//            PrintUtils.printGreen("结束时间:"+ endTime);
//            PrintUtils.printGreen("耗时:"+(endTime-beginTime)+"ms");
//        } catch (Exception e) {
//            try (var stream = Files.list(Path.of(SPATH))) {
//                List<Path> subFiles = stream
//                        .filter(p -> Files.isRegularFile(p) && p.getFileName().toString().contains("sub"))
//                        .toList();
//                for (Path p : subFiles) {
//                    try {
//                        Files.deleteIfExists(p);
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            throw new RuntimeException(e);
//        }
        if (Files.exists(Path.of(SPATH))) {
            Files.list(Path.of(SPATH)).forEach(p -> {
                if(p.toString().contains("acct")) {
                    try {
                        Collections.sort(Files.readAllLines(p), (o1, o2) -> {
                            String acct1 = o1.substring(0, 10);
                            String acct2 = o2.substring(0, 10);
                            return acct1.compareTo(acct2);
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }




    /**
     * 生成指定行数的复杂测试数据并写入文件：
     * 1. 字段间使用分号 “;” 分隔，各字段定长。
     * 2. 包含 7 个定长字段：
     *    - 字段1：10 位账号（10 位随机数字）
     *    - 字段2：8 位系统日期（yyyyMMdd）
     *    - 字段3：40 位账户名称（至少 20 个英文字符，左填充空格占满 40 位）
     *    - 字段4：16 位账户余额（13 位整数 + 1 位小数点 + 2 位小数）
     *    - 字段5：16 位可用余额（与字段 4 完全一致）
     *    - 字段6：60 位 UDT（全部为英文字符，占满 60 位）
     *    - 字段7：40 位地址（全部为英文字符，占满 40 位）
     *
     * @param line 生成数据的行数
     * @throws IOException IO 异常
     */
    public static void genData(int line) throws IOException {
        Path targetPath = Path.of(SPATH, FILENAME);
        if (targetPath.getParent() != null && Files.notExists(targetPath.getParent())) {
            Files.createDirectories(targetPath.getParent());
        }

        PrintUtils.printGreen("开始生成数据，目标行数：" + line + " 行...");
        long beginTime = System.currentTimeMillis();

        // 8 位系统日期
        String dateStr = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        // 每行共 196 个字符：10 + 1 + 8 + 1 + 40 + 1 + 16 + 1 + 16 + 1 + 60 + 1 + 40 = 196
        char[] lineBuf = new char[196];
        // 预置固定分隔符 “;”
        lineBuf[10] = ';';
        lineBuf[19] = ';';
        lineBuf[60] = ';';
        lineBuf[77] = ';';
        lineBuf[94] = ';';
        lineBuf[155] = ';';

        // 预置日期字段（全过程不变）
        dateStr.getChars(0, 8, lineBuf, 11);

        // 使用 1MB 缓冲区提高写入吞吐量
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath.toFile()), 1024 * 1024)) {
            ThreadLocalRandom random = ThreadLocalRandom.current();

            for (int i = 0; i < line; i++) {
                // 1. 第一个字段：10 位账号（10 位随机数字：1000000000 ~ 9999999999）
                long acctNo = random.nextLong(1_000_000_000L, 10_000_000_000L);
                for (int j = 9; j >= 0; j--) {
                    lineBuf[j] = (char) ('0' + (acctNo % 10));
                    acctNo /= 10;
                }

                // 2. 第二个字段：8 位系统日期已预填在 lineBuf[11..18]

                // 3. 第三个字段：40 位账户名称，至少 20 个字符，左填充空格
                int nameLen = random.nextInt(20, 36); // 20 ~ 35 个字符
                int padSpaces = 40 - nameLen;
                int pos = 20;
                for (int j = 0; j < padSpaces; j++) {
                    lineBuf[pos++] = ' ';
                }
                for (int j = 0; j < nameLen; j++) {
                    lineBuf[pos++] = LETTERS[random.nextInt(LETTERS.length)];
                }

                // 4. 第四个字段：16 位账户余额，含 2 位小数（13 位整数 + 1 位小数点 + 2 位小数）
                long intPart = random.nextLong(0, 10_000_000_000_000L);
                int decPart = random.nextInt(0, 100);
                lineBuf[76] = (char) ('0' + (decPart % 10));
                lineBuf[75] = (char) ('0' + (decPart / 10));
                lineBuf[74] = '.';
                for (int j = 73; j >= 61; j--) {
                    lineBuf[j] = (char) ('0' + (intPart % 10));
                    intPart /= 10;
                }

                // 5. 第五个字段：可用余额，和第四个字段值一样
                System.arraycopy(lineBuf, 61, lineBuf, 78, 16);

                // 6. 第六个字段：UDT，60 位英文字符，占满
                for (int j = 95; j < 155; j++) {
                    lineBuf[j] = LETTERS[random.nextInt(LETTERS.length)];
                }

                // 7. 第七个字段：地址，40 位英文字符，占满
                for (int j = 156; j < 196; j++) {
                    lineBuf[j] = LETTERS[random.nextInt(LETTERS.length)];
                }

                // 写入当前行
                bw.write(lineBuf, 0, 196);
                bw.newLine();
            }
        }

        long cost = System.currentTimeMillis() - beginTime;
        PrintUtils.printGreen("数据生成完毕！文件路径：" + targetPath + "，耗时：" + cost + " ms");
    }
}
