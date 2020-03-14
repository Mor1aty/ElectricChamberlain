package com.moriaty.schedule;

import com.moriaty.bean.db.Activity;
import com.moriaty.bean.db.User;
import com.moriaty.mapper.ActivityMapper;
import com.moriaty.mapper.UserMapper;
import com.moriaty.red.utils.RedPaperUtil;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 15:42
 * @Description TODO
 * 定时活动任务
 */
@Component
@AllArgsConstructor
public class ActivitySchedule {

    private ActivityMapper activityMapper;
    private UserMapper userMapper;


    @Scheduled(cron = "00 00 00 * * *")
    public void redPaperSchedule() {
        Activity activity = activityMapper.findRedPaperActivity();
        if (LocalDateTime.now().isAfter(activity.getActivityTime())) {
            activityMapper.updateActivityIsEnd(activity.getId());
            System.out.println(activity.getId() + "活动过期");
            return;
        }
        List<User> allUser = userMapper.findAllUserPhone();
        List<String> phones = new ArrayList<>();
        allUser.forEach(user -> {
            phones.add(user.getPhone());
        });
        String[] section = activity.getMoneySection().split("-");
        RedPaperUtil.generateRedPaper(phones, Long.parseLong(section[0]), Long.parseLong(section[1]));
    }

}
