package com.lxy.service;

import com.lxy.pojo.Brand;
import com.lxy.pojo.PageBean;

import java.util.List;

public interface BrandService {

    //查询所有
    List<Brand> selectAll();

    //新增
    void add(Brand brand);

    //修改
    void update(Brand brand);

    //根据id删除数据
    void deleteById(int id);

    //批量删除
    void deleteByIds(int[] ids);

    /**
     * 分页查询数据
     * @param currentPage 前页码数 当
     * @param pageSize 每页展示条数
     * @return Brand数据集合
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     *
     * @param currentPage 前页码数 当
     * @param pageSize 每页展示条数
     * @param brand 搜索的数据
     * @return Brand数据集合
     */
    PageBean<Brand> selectByPageCondition(int currentPage,int pageSize,Brand brand);
}
