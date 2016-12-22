package com.mao.shop.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mao.shop.dao.ShipAddrDao;
import com.mao.shop.po.CustShipaddr;

@Repository
public class ShipAddrDaoImpl extends SqlSessionDaoSupport implements ShipAddrDao {
 
	@Autowired
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	String ns = "com.mao.shop.mapper.CustShipaddrMapper.";

	@Override
	public List<CustShipaddr> selectByUserId(Integer cid) {
		
		return getSqlSession().selectList(ns+"selectByUserId", cid);
	}

	@Override
	public void saveShipAddr(CustShipaddr shipaddr) {
		getSqlSession().insert(ns+"insert", shipaddr);
	}

	@Override
	public void resetDefalut(Integer customerId) {
		getSqlSession().update(ns+"updateIsDefault", customerId);
	}

	@Override
	public CustShipaddr selectByShipId(Integer id) {
		return getSqlSession().selectOne(ns+"selectByPrimaryKey", id);
	}

	@Override
	public void updateShipAddr(CustShipaddr shipaddr) {
		getSqlSession().update(ns+"updateByPrimaryKey", shipaddr);
	}

	@Override
	public void updateDefault(Integer id) {
		getSqlSession().update(ns+"updateDefault",id);
	}

	@Override
	public void deleteByShipId(Integer id) {
		getSqlSession().delete(ns+"deleteByPrimaryKey",id);
	}
	
	
}
