package com.mao.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mao.shop.dao.RegionDao;
import com.mao.shop.dao.ShipAddrDao;
import com.mao.shop.po.CustShipaddr;
import com.mao.shop.po.Region;
import com.mao.shop.service.ShipAddrService;

@Service
public class ShipAddrServiceImpl implements ShipAddrService {

	@Autowired
	private RegionDao regionDao;
	@Autowired
	private ShipAddrDao shipAddrDao;

	@Override
	public List<Region> selectProvince() {
		
		return regionDao.selectProvince();
	}

	@Override
	public List<Region> selectRegionByParentId(Short parentId) {
		
		return regionDao.selectByParentId(parentId);
	}

	@Override
	public List<CustShipaddr> selectByUserId(Integer cid) {
		
		return shipAddrDao.selectByUserId(cid);
	}

	@Override
	public void saveShipAddr(CustShipaddr shipaddr) {
		if (shipaddr.getIsDefault() == null) {
			shipaddr.setIsDefault(false);
		}else {
			shipAddrDao.resetDefalut(shipaddr.getCustomerId());
		}
		shipAddrDao.saveShipAddr(shipaddr);
	}

	@Override
	public CustShipaddr selectByShipId(Integer id) {
		return shipAddrDao.selectByShipId(id);
	}

	@Override
	public void updateShipAddr(CustShipaddr shipaddr) {
		if (shipaddr.getIsDefault() == null) {
			shipaddr.setIsDefault(false);
		}else {
			shipAddrDao.resetDefalut(shipaddr.getCustomerId());
		}
		shipAddrDao.updateShipAddr(shipaddr);	
	}

	@Override
	public void updateShipAddrDefault(Integer id,Integer userId) {
		shipAddrDao.resetDefalut(userId);
		shipAddrDao.updateDefault(id);
	}

	@Override
	public void deleteByShipid(Integer id) {
		shipAddrDao.deleteByShipId(id);
	}
}
