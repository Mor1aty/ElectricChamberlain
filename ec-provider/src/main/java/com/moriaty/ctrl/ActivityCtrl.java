package com.moriaty.ctrl;

import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.UserToken;
import com.moriaty.bean.back.RedPaperBack;
import com.moriaty.login.aspect.NeedLogin;
import com.moriaty.service.ActivityService;
import com.moriaty.valid.aspect.ParamValidation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity")
@AllArgsConstructor
public class ActivityCtrl {

    private ActivityService activityService;

    // 获取每日红包
    @PostMapping("redPaper")
    @NeedLogin("user")
    @ParamValidation
    public Wrapper<RedPaperBack> redPaper(WrapParams wrapParams) {
        return activityService.redPaper(((UserToken) wrapParams.getTokenValue("user")).getPhone());
    }

    // 查询我的红包
    @GetMapping("redPaper")
    @NeedLogin("user")
    @ParamValidation
    public Wrapper<String> checkRedPaper(WrapParams wrapParams) {
        return activityService.checkRedPaper(((UserToken) wrapParams.getTokenValue("user")).getPhone());
    }

}
