package com.easyjojo.common.shaunjava.eight.stream;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ShaunData {
    String Name;
    String Country;
    BigDecimal Salary;
    public ShaunData(String name){
        this(name, null, null);
    }
    public ShaunData(String name, String country){
        this(name, country, null);
    }
    public ShaunData(BigDecimal salary){
        this(null, null, salary);
    }
    public ShaunData(String name, String country, BigDecimal salary) {
        Name = name;
        Country = country;
        Salary = salary;
    }
}
