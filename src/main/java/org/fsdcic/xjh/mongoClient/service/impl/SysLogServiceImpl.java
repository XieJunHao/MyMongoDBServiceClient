package org.fsdcic.xjh.mongoClient.service.impl;

import org.fsdcic.xjh.mongo.service.impl.MongoBaseServiceImpl;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Created by ben on 2017-9-19.
 */
//@Service("sysLogServiceImpl")
public class SysLogServiceImpl extends MongoBaseServiceImpl {


    public SysLogServiceImpl(MongoOperations mongoOperations) {
        super(mongoOperations);
    }

}
