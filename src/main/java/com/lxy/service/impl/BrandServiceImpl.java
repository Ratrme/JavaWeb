package com.lxy.service.impl;

import com.lxy.mapper.BrandMapper;
import com.lxy.pojo.Brand;
import com.lxy.pojo.PageBean;
import com.lxy.service.BrandService;
import com.lxy.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //查询所有
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    //新增
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }


    //修改
    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * pageSize;
        List<Brand> rows = mapper.selectByPage(begin, pageSize);
        int totalCount = mapper.selectTotalCount();
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(rows);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * pageSize;

        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }

        List<Brand> rows = mapper.selectByPageAndCondition(begin,pageSize,brand);
        int totalCountByCondition = mapper.selectTotalCountByCondition(brand);
        PageBean<Brand> pageBean = new PageBean<Brand>();
        pageBean.setTotalCount(totalCountByCondition);
        pageBean.setRows(rows);
        sqlSession.close();
        return pageBean;
    }
}
