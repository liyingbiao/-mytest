package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 产品模块
 */
public interface IProductDao {
    /**
     * 查询全部
     */
    @Select("select * from product")
    List<Product> findAll();

    /**
     * 添加产品
     */
    @Insert("insert into product values(SEQ_PRODUCT.nextval,#{productNum},#{productName}," +
            "#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 主键查询
     */
    @Select("select * from product where id=#{id}")
    Product findById(Integer id);

    /**
     * 修改
     */
    @Update("update product set productNum=#{productNum},productName=#{productName}," +
            "cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice}," +
            "productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void update(Product product);

    /**
     * 删除
     */
    @Delete("delete from product where id=#{id}")
    void delete(String id);

    /**
     * 分页方法
     * 注意：mybatis方法参数有多个，有3种解决方法。
     * 1. #{arg0} 表示对应方法第一个参数
     * 2. @Param  建立方法参数与占位符关系
     * 3. 把多个参数封装为对象，#{对象属性}
     */
    @Select("select * from (\n" +
            "select p.*,rownum rn from product p where rownum <= #{arg0}\n" +
            ") where rn>#{arg1}")
    List<Product> findByPage(int index,int count);

    @Select("select count(1) from product")
    long count();
}




















