package com.example.demoechars.model.mapper;

import com.example.demoechars.model.entity.EcharsTable;
import com.example.demoechars.model.entity.EcharsTableExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
@Mapper
public interface EcharsTableMapper {
    long countByExample(EcharsTableExample example);

    int deleteByExample(EcharsTableExample example);

    int insert(EcharsTable record);

    int insertSelective(EcharsTable record);

    List<EcharsTable> selectByExample(EcharsTableExample example);

    int updateByExampleSelective(@Param("record") EcharsTable record, @Param("example") EcharsTableExample example);

    int updateByExample(@Param("record") EcharsTable record, @Param("example") EcharsTableExample example);

}