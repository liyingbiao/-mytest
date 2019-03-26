package com.itheima.service.impl;
import com.itheima.dao.IProductDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    // 注入dao
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(String productIds) {
        if (productIds != null && !"".equals(productIds.length())){
            //1. 分割字符串
            String[] ids = productIds.split(",");
            //2. 遍历
            for(String id : ids){
                //3. 根据id删除产品
                productDao.delete(id);
            }
        }
    }

    @Override
    public PageBean<Product> findByPage(int pageNum, int pageSize) {

        if (pageNum<1){
            pageNum = 1;
        }

        //1. 查询总记录数
        long count = productDao.count();

        //2. 查询当前页数据
        List<Product> list =
                productDao.findByPage(pageNum * pageSize, (pageNum - 1) * pageSize);

        //3. 返回
        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setList(list);
        return pageBean;
    }
}

















