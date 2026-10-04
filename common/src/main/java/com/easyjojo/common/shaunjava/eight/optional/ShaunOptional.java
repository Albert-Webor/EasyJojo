package com.easyjojo.common.shaunjava.eight.optional;
import com.easyjojo.common.utils.PrintUtils;

import java.math.BigDecimal;
import java.util.Optional;

public class ShaunOptional {
    static ShaunData data = new ShaunData("John");
    static ShaunData data2 = new ShaunData("Jack", "USA", new BigDecimal("78888"));
    public static void main(String[] args) {
        //1, 痛点1, 多层判空校验
        String sSalary = Optional.ofNullable(data)
                .filter(d -> d.getName() != null)
                .filter(d -> d.getCountry() != null)
                .map(ShaunData::getCountry)
                .orElse("Country not available");
        PrintUtils.printGreen("痛点1, 多层判空校验: " + sSalary);

        //2,痛点2, 方法签名模糊, 消费者, 拆包
        String sSalary2 = getHome()
                .map(String::toUpperCase)
                .orElse("为空");
        PrintUtils.printGreen("痛点2, 方法签名模糊: " + sSalary2);
    }

    //痛点2的定义方法: 生产者, 打包:把真实数据封进盒子里，交出去
    static Optional<String> getHome(){
        return Optional.of("Home");
    }
}
