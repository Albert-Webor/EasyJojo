package com.easyjojo.model1.services;

import com.easyjojo.model1.mappers.KdpaSubAcctMapper;
import com.easyjojo.model1.models.entity.Kdpa_Sub_Acct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KdpaSubAcctService {
    @Autowired
    KdpaSubAcctMapper kdpaSubAcctMapper;
    public Kdpa_Sub_Acct getAccountByAcctNum(String custAcctNum) {
        return kdpaSubAcctMapper.selKdpaSubAcctWithAcctNum(custAcctNum);
    }


}
