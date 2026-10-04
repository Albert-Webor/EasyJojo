package com.easyjojo.common.shaunjava.eight.stream;
import com.easyjojo.common.utils.PrintUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ShaunStream {
    static ArrayList<ShaunData> array = new ArrayList<>();
    public static void main(String[] args) {
        //初始化数据
        array.add(new ShaunData("John", "USA", new BigDecimal("50000")));
        array.add(new ShaunData("Alice", "Canada", new BigDecimal("60000")));
        array.add(new ShaunData("Bob", "USA", new BigDecimal("55000")));
        array.add(new ShaunData("Eve", "Australia", new BigDecimal("70000")));
        array.add(new ShaunData("Charlie", "USA", new BigDecimal("65000")));

        //1, 解决痛点1, 避免关注数组越界问题: Salary >= 60000
        List<ShaunData> desLst = (List<ShaunData>) array.stream().filter(data -> data.getSalary() != null && data.getSalary().compareTo(new BigDecimal("60000")) >= 0)
                .toList();
        desLst.forEach(data -> PrintUtils.printGreen("解决痛点1, 大量遍历和处理的样板代码: 符合条件的对象: " + data.getName() + ", Salary: " + data.getSalary()));

        //2, 解决痛点2, 找到符合的对象, 然后处理符合条件的对象, 然后只选择其中的2位, 避免创建大量中间对象
        List<ShaunData> desLst1 = (List<ShaunData>) array.stream().filter(data -> data.getSalary() != null && data.getSalary().compareTo(new BigDecimal("50000")) >= 0)
                .peek(data -> {
                    if (data.getName() != null) {
                        data.setName(data.getName().toUpperCase());
                    }
                })
                .sorted((a, b) -> a.getSalary().compareTo(b.getSalary()))
                .limit(2)
                .toList();
        desLst1.forEach(data -> PrintUtils.printYellow("解决痛点2, 找到符合的对象, 然后处理符合条件的对象, 然后只选择其中的2位, 避免创建大量中间对象: " + data.getName() + ", Salary: " + data.getSalary()));

        //3, 解决痛点3, 实现聚合计算, 类似sql的group by (结合Collectors 来实现)
        Map<String,BigDecimal> desLst2 = array.stream()
                .collect(Collectors.groupingBy(ShaunData::getCountry,Collectors.reducing(BigDecimal.ZERO,
                        data -> data.getSalary() == null ? BigDecimal.ZERO : data.getSalary(), // 2. 字段提取 + null 兜底
                        BigDecimal::add)));
        desLst2.forEach((country, totalSalary) -> PrintUtils.printBlue("解决痛点3, 实现聚合计算, 类似sql的group by (结合Collectors 来实现): Country: " + country + ", Total Salary: " + totalSalary));


    }
}
