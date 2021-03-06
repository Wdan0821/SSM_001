package com.swjd.mapper;

import com.swjd.bean.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {

    //查询全部
    public abstract List<Customer> getAll();
    //条件分页查询(条件:当前第几条开始,每页多少条)
    public abstract List<Customer> findForSearch(@Param("map")Map<String,String> param,
                                                 @Param("page")Integer page,
                                                 @Param("rows")Integer rows);
    //总条数(满足条件的,参数:条件)
    public abstract int findForCount(@Param("map")Map<String,String> param);

    //新增
    public abstract int addCustomer(Customer customer);

    //批量删除
    public int deleteCustomer(String[] ids);

    //单个查询
    public abstract Customer findOne(int id);

    //修改
    public int updateOne(Customer customer);

}
