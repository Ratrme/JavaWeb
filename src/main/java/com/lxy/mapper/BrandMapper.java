package com.lxy.mapper;

import com.lxy.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    //查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    //新增
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);


    //修改
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);

    //删除
    @Delete("delete from tb_brand where id=#{id}")
    void deleteById(int id);


    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //分页查询数据
    @Select("select * from tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    //分页查询数据总条数
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    //搜索查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);

    //搜索查询数据总条数
    int selectTotalCountByCondition(Brand brand);

}
