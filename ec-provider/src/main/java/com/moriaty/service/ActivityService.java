package com.moriaty.service;

import com.moriaty.base.utils.ValueUtils;
import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.back.RedPaperBack;
import com.moriaty.bean.db.User;
import com.moriaty.mapper.ActivityMapper;
import com.moriaty.red.utils.RedPaperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 15:14
 * @Description TODO
 * 活动 Service
 */
@Service
@AllArgsConstructor
public class ActivityService {

    private ActivityMapper activityMapper;

    // 获取每日红包
    public Wrapper<RedPaperBack> redPaper(String phone) {
        Long money = RedPaperUtil.getRedPaper(phone);
        if (ValueUtils.valEmpty(money)) {
            return WrapMapper.error("红包已领取");
        }

        RedPaperBack redPaperBack = new RedPaperBack();
        redPaperBack.setMoney(money);
        User user = new User();
        user.setPhone("1000");
        user.setNickname("电价管家");
        redPaperBack.setPayer(user);
        RedPaperUtil.removeRedPaper(phone);
        return WrapMapper.ok("获取成功", redPaperBack);
    }
}
