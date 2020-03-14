package com.moriaty.mapper;

import com.moriaty.bean.db.DealRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2020/3/14 13:40
 * @Description TODO
 * 交易 Mapper
 */
public interface DealMapper {

    // 写入交易记录表
    @Insert("INSERT INTO deal_record(type, payer, payee, amount, context, record_time) " +
            "VALUES(#{dealRecord.type}, #{dealRecord.payer.phone}, #{dealRecord.payee.phone}, " +
            "#{dealRecord.amount}, #{dealRecord.context}, #{dealRecord.recordTime})")
    int insertDealRecord(@Param("dealRecord") DealRecord dealRecord);
}
