package io.renren.dao;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据库接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2018-07-24
 */
@Mapper
public interface GeneratorDao {

    @MapKey("")
    List<Map<String, Object>> queryList(@Param("map") Map<String, Object> map);

    @MapKey("")
    Map<String, String> queryTable(String tableName);

    @MapKey("")
    List<Map<String, String>> queryColumns(@Param("tableName") String tableName);
}
