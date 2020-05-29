package com.swjd.service;

import com.swjd.bean.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    //获取所有数据
    public abstract List<Customer> getList();
    //条件分页
    public abstract List<Customer> findForSearch(Map<String,String> map);
    //总条数(满足条件)
    public abstract int findForCount(Map<String,String> map);
    //新增
    public abstract int insertCustomer(Customer customer);
    //批量删除
    public int delete(String[] ids);
    //查单个
    public Customer selectOne(int id);
}
