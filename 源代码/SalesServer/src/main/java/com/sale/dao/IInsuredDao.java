package com.sale.dao;

import com.sale.entity.Insured;

import java.util.List;

public interface IInsuredDao extends  ICommonDao<Insured, Integer>{

    List<Insured> getAll(Integer id) throws Exception;
}
