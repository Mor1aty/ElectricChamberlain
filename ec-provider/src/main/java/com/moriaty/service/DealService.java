package com.moriaty.service;

import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.db.DealRecord;
import com.moriaty.bean.db.User;
import com.moriaty.mapper.DealMapper;
import com.moriaty.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 13:56
 * @Description TODO
 * 交易 Service
 */
@Service
@AllArgsConstructor
public class DealService {

    private DealMapper dealMapper;
    private UserMapper userMapper;

    // 用户交易
    @Transactional
    public Wrapper<String> money(int type, String payer, String payee, long amount, String context) {
        User payerUser = userMapper.findUserByPhone(payer);
        if (payerUser.getMoney() - amount < 0) {
            return WrapMapper.error("付款人余额不足");
        }
        User payeeUser = userMapper.findUserByPhone(payee);
        if(ValueUtils.valEmpty(payeeUser)){
            return WrapMapper.error("收款人不存在");
        }

        if (userMapper.updateMoney(payer, -amount) == 0) {
            return WrapMapper.error("转账失败");
        }

        if (userMapper.updateMoney(payee, amount) == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return WrapMapper.error("转账失败");
        }

        DealRecord dealRecord = new DealRecord();
        dealRecord.setType(type);
        dealRecord.setPayer(payerUser);
        dealRecord.setPayee(payeeUser);
        dealRecord.setAmount(amount);
        dealRecord.setContext(context);
        dealRecord.setRecordTime(LocalDateTime.now());

        if (dealMapper.insertDealRecord(dealRecord) == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return WrapMapper.error("转账失败");
        }

        return WrapMapper.ok("转账成功");
    }

}
