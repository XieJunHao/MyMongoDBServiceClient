package org.fsdcic.xjh.mongoClient.service.impl;

import org.fsdcic.xjh.mongo.service.impl.MongoBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

/**
 * Created by ben on 2017-9-19.
 */
@Service("sysLogServiceImpl")
public class SysLogServiceImpl extends MongoBaseServiceImpl {


    @Autowired
    public SysLogServiceImpl(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

    public void testAop(){
        System.out.println("測試aop");
    }
}
