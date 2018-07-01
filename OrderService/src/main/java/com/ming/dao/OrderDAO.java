package com.ming.dao;

import com.ming.model.OrderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderDAO {

    @Select("select * from order")
    List<OrderDTO> select();

    @Options(useGeneratedKeys=true, keyProperty="oId",keyColumn = "o_id")
    @Insert("insert into `order` (u_id, g_id, good_count, goot_amount) " +
            "values(#{uId}, #{gId}, #{goodCount}, #{gootAmount})")
    Long inset(OrderDTO dto);
}
