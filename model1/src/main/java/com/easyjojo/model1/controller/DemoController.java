package com.easyjojo.model1.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easyjojo.common.result.Result;
import com.easyjojo.model1.mapper.KdpaSubAcctMapper;
import com.easyjojo.model1.models.entity.Kdpa_Sub_Acct;
import com.easyjojo.model1.models.vo.kdpaSubAcctComplex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private KdpaSubAcctMapper kdpaSubAcctMapper;

    /**
     * 原有 Mock 模拟数据接口
     */
    @GetMapping("/hello")
    public Result<kdpaSubAcctComplex> hello() {
        kdpaSubAcctComplex complex = new kdpaSubAcctComplex();

        Kdpa_Sub_Acct acct = new Kdpa_Sub_Acct();
        acct.setCustAcctNum("123456789");
        acct.setAcctBal(new BigDecimal("1000.00"));
        complex.kdpaSubAcct = acct;

        return Result.success(complex);
    }

    /**
     * 真实查库接口：根据客户账号查询第一条记录
     */
    @GetMapping("/account/{custAcctNum}")
    public Result<Kdpa_Sub_Acct> getAccount(@PathVariable String custAcctNum) {
        LambdaQueryWrapper<Kdpa_Sub_Acct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Kdpa_Sub_Acct::getCustAcctNum, custAcctNum);
        
        Kdpa_Sub_Acct account = kdpaSubAcctMapper.selectOne(wrapper, false);
        return Result.success(account);
    }

    /**
     * 真实查库接口：查询所有账户列表（支持限制前 10 条）
     */
    @GetMapping("/account/list")
    public Result<List<Kdpa_Sub_Acct>> listAccounts() {
        LambdaQueryWrapper<Kdpa_Sub_Acct> wrapper = new LambdaQueryWrapper<>();
        wrapper.last("LIMIT 10");
        List<Kdpa_Sub_Acct> list = kdpaSubAcctMapper.selectList(wrapper);
        return Result.success(list);
    }
}
