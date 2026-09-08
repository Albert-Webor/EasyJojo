package com.easyjojo.model1.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easyjojo.model1.models.entity.Kdpa_Sub_Acct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KdpaSubAcctMapper extends BaseMapper<Kdpa_Sub_Acct> {

    /**
     * 根据客户账号查询账户列表（手写 SQL）
     */
    Kdpa_Sub_Acct selKdpaSubAcctWithAcctNum(@Param("custAcctNum") String custAcctNum);
}
