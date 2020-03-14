package com.moriaty.ctrl;

import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.login.aspect.NeedLogin;
import com.moriaty.service.DealService;
import com.moriaty.valid.aspect.Param;
import com.moriaty.valid.aspect.ParamValidation;
import com.moriaty.valid.bean.Method;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:14
 * @Description TODO
 *   交易 Ctrl
 */
@RestController
@RequestMapping("deal")
@AllArgsConstructor
public class DealCtrl {

    private DealService dealService;

    // 用户交易
    @PutMapping("money")
    @NeedLogin("user")
    @ParamValidation({@Param(value = "type", method = Method.NUMBER), @Param("payer"),
            @Param("payee"), @Param(value = "amount", method = Method.NUMBER), @Param("context")})
    public Wrapper<String> money(WrapParams wrapParams) {
        return dealService.money(wrapParams.getIntValue("type"), wrapParams.getString("payer"),
                wrapParams.getString("payee"), wrapParams.getLongValue("amount"),
                wrapParams.getString("context"));
    }
}
