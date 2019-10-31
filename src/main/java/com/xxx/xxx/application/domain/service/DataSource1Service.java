package com.xxx.xxx.application.domain.service;

import com.xxx.xxx.application.annotation.DataSource;
import com.xxx.xxx.application.datasource.DataSourceNames;
import com.xxx.xxx.application.domain.model.User;
import com.xxx.xxx.application.infrastructure.dao.mysql.DataSource1TestDao;
import com.xxx.xxx.application.infrastructure.dao.mysql.DataSource2TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenyuhao
 * @Date: 2019/10/31 11:21
 * @Version 1.0
 */
@Service
public class DataSource1Service {

    @Autowired
    private DataSource1TestDao dataSource1TestDao;

    @Autowired
    private DataSource2TestDao dataSource2TestDao;

    @DataSource(name = DataSourceNames.SLAVE)
    public User getUser(){
        return dataSource1TestDao.getUser();
    }

    @DataSource(name = DataSourceNames.MASTER)
    public User getUser2(){
        return dataSource2TestDao.getUser();
    }

}
