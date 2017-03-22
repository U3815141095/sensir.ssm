package com.sensir.service.impl;

import com.sensir.service.TempService;
import com.sensir.studio.data.source.DataSource;
import com.sensir.studio.data.source.DataSourceHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test service
 * Created by Leon on 2017-3-17.
 */
@Service
@Transactional(readOnly = true)
public class TempServiceImpl implements TempService {

    @DataSource(DataSourceHolder.DATA_SOURCE_A)
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSql() {
        System.out.println("test service");
    }
}
