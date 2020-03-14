package com.moriaty.ctrl;

import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.UserToken;
import com.moriaty.login.aspect.NeedLogin;
import com.moriaty.service.ApplyService;
import com.moriaty.valid.aspect.Param;
import com.moriaty.valid.aspect.ParamValidation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 13:08
 * @Description TODO
 * 申请 Ctrl
 */
@RestController
@RequestMapping("apply")
@AllArgsConstructor
public class ApplyCtrl {

    private ApplyService applyService;

    // 用户升级申请
    @PutMapping("identity")
    @NeedLogin("user")
    @ParamValidation(@Param("context"))
    public Wrapper<String> identity(WrapParams wrapParams) {
        return applyService.identity(((UserToken) wrapParams.getTokenValue("user")).getPhone(),
                wrapParams.getString("context"));
    }
}
