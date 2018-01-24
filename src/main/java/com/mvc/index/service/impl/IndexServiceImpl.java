package com.mvc.index.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.common.dao.Dao;
import com.mvc.common.service.impl.ServiceSupport;
import com.mvc.index.dao.IndexDao;
import com.mvc.index.service.IndexService;

@Service("indexService")
public class IndexServiceImpl extends ServiceSupport implements IndexService {

	@Autowired
	private IndexDao indexDao;
	
	@Override
	public IndexDao getDao() {
		return indexDao;
	}

	public void setDao(IndexDao indexDao) {
		this.indexDao = indexDao;
	}

}
