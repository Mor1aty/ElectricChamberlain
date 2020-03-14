package com.moriaty.ctrl;

import com.moriaty.base.wrap.WrapParams;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.db.ElectricityPrice;
import com.moriaty.service.ElectricityPriceService;
import com.moriaty.valid.aspect.Param;
import com.moriaty.valid.aspect.ParamValidation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:15
 * @Description TODO
 *   电价 Ctrl
 */
@RestController
@RequestMapping("electricity")
@AllArgsConstructor
public class ElectricityPriceCtrl {

    private ElectricityPriceService electricityPriceService;

    // 电价展示
    @GetMapping("electricityPrice")
    @ParamValidation({@Param("city"), @Param("startTime"), @Param("endTime")})
    public Wrapper<List<ElectricityPrice>> electricityPrice(WrapParams wrapParams) {
        return electricityPriceService.electricityPrice(wrapParams.getString("city"),
                wrapParams.getString("startTime"), wrapParams.getString("endTime"));
    }
}
