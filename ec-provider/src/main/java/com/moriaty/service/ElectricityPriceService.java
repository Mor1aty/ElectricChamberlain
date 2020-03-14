package com.moriaty.service;

import com.moriaty.base.wrap.WrapMapper;
import com.moriaty.base.wrap.Wrapper;
import com.moriaty.bean.db.ElectricityPrice;
import com.moriaty.mapper.ElectricityPriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:14
 * @Description TODO
 *   电价 Service
 */
@Service
@AllArgsConstructor
public class ElectricityPriceService {

    private ElectricityPriceMapper electricityPriceMapper;

    // 获取指定时间段，指定省市区的电价
    public Wrapper<List<ElectricityPrice>> electricityPrice(String city, String startTime, String endTime) {
        return WrapMapper.ok("查询成功", electricityPriceMapper.findElectricityPriceByCityBetweenTime(city, startTime, endTime));
    }
}
