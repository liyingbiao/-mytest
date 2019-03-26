package com.itheima.dao;

import com.itheima.domain.Order;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderDao {
    /**
     * 查询全部订单
     * 注意： 关联查询时候，查询的列不能重名，否则导致封装数据失败。
     */
    @Select("select o.*,p.id pid,p.productnum,p.productname,p.cityname,p.productprice \n" +
            "from orders o inner join product p on o.productid=p.id")
    @Results({
            /*1. 封装订单信息，只要设置id即可。*/
            @Result(id = true,property = "id",column = "id"),

            /*2. 封装产品信息*/
            @Result(property = "product.id",column = "pid"),
            @Result(property = "product.productNum",column = "productnum"),
            @Result(property = "product.productName",column = "productname"),
            @Result(property = "product.cityName",column = "cityname"),
            @Result(property = "product.productPrice",column = "productprice")
    })
    List<Order> findAll();
}




















