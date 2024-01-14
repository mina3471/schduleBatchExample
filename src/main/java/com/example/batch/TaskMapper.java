package com.example.batch;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT language, SUM(`count`)  as totalCount FROM `data` GROUP BY `language`") // Group by
    List<TaskDTO> read_data();
    @Select("SELECT * FROM `data`") // 다 가져온 뒤에 자바에서 정제하기
    List<TaskDTO> read_all_data();
}
