package com.ming.dao;

import com.ming.model.GoodDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodDAO {

    @Options(useGeneratedKeys=true, keyProperty="gId",keyColumn = "g_id")
    @Insert("insert into good(good_name, good_count, amount) " +
            "values(#{goodName}, #{goodCount}, #{amount})")
    Long insert(GoodDTO dto);

    @Select("select * from good")
    List<GoodDTO> select();

    @Select("select * from good where g_id = #{goodId}")
    GoodDTO getGood(Long goodId);

    @Update("update good set good_count = good_count - #{count} where g_id = #{goodId}")
    int decrease(@Param("goodId") Long goodId, @Param("count") int count);
}
