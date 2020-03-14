package com.moriaty.service;

import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.mapper.ApplyMapper;
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
 * @date 2020/3/14 13:07
 * @Description TODO
 * 申请 Service
 */
@Service
@AllArgsConstructor
public class ApplyService {

    private ApplyMapper applyMapper;
    private UserMapper userMapper;

    // 用户升级申请
    @Transactional
    public Wrapper<String> identity(String phone, String context) {
        if (applyMapper.insertApply(phone, context, LocalDateTime.now()) == 0) {
            return WrapMapper.error("申请失败");
        }
        if (userMapper.updateUserApplyType(phone) == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return WrapMapper.error("申请失败");
        }
        return WrapMapper.ok("申请成功");
    }
}
