package com.moriaty.mapper;

import com.moriaty.bean.db.ElectricityPrice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @copyright ：Moriaty 版权所有 © 2019
 * @author 16计算机 Moriaty
 * @version 1.0
 * @date 2020/3/14 15:13
 * @Description TODO
 *   电价 Mapper
 */
public interface ElectricityPriceMapper {

    // 获取指定时间段，指定省市区的电价
    @Select("SELECT id, ordinary_money, discount_money, city, price_time FROM electricity_price " +
            "WHERE city = #{city} AND (price_time BETWEEN #{startTime} AND #{endTime})")
    List<ElectricityPrice> findElectricityPriceByCityBetweenTime(@Param("city") String city,
                                                                 @Param("startTime") String startTime,
                                                                 @Param("endTime") String endTime);
}
