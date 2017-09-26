import com.mongodb.DB;
import com.mongodb.Mongo;
import org.fsdcic.xjh.mongo.entity.ServiceResult;
import org.fsdcic.xjh.mongoClient.advice.Advices;
import org.fsdcic.xjh.mongoClient.entity.OperationLog;
import org.fsdcic.xjh.mongoClient.entity.SysLog;
import org.fsdcic.xjh.mongoClient.service.impl.SysLogServiceImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ben on 2017-9-15.
 */
@ContextConfiguration(locations = "classpath:application.xml")
public class MongoSpringTest extends AbstractJUnit4SpringContextTests {
    //
//    @Autowired
//    MongoTemplate mongoTemplate;
//
    @Autowired
    private SysLogServiceImpl sysLogService;

    @Test
    public void testMong() {
        Assert.assertNotNull(sysLogService);
    }

    //  测试连接mongodb数据库
    @Test
    public void testMongodb() {
        try {
            // 连接到 mongodb 服务
            Mongo mongo = new Mongo("192.168.1.30", 27017);
            //根据mongodb数据库的名称获取mongodb对象 ,
            DB db = mongo.getDB("test");
            Set<String> collectionNames = db.getCollectionNames();
            // 打印出test中的集合
            for (String name : collectionNames) {
                System.out.println("collectionName===" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ClassPathXmlApplicationContext app;
    private static String collectionName;


    //    执行每一个测试方法前，都要跑一次
//    @Before
    //针对所有测试方法，只执行一次，且必须为static void
    @BeforeClass
    public static void initSpring() {
        try {
//            MongoOperations mongoOps = new MongoTemplate(new Mongo("192.168.1.30",27017), "test");
            collectionName = "SysLog";

//            app = new ClassPathXmlApplicationContext(new String[] { "classpath:application.xml"});

//            mongoBaseService = new SysLogServiceImpl(mongoOps);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRemove() {
        List<String> ids = new ArrayList<String>();
//        for(int i=0;i<10;i++){
//            ids.add(i+"");
//        }
        ids.add("59c0ea18ca111a03845e2e36");
        ids.add("59c1b9d8ca111a3794ebdc56");
        ServiceResult serviceResult = sysLogService.deleteByids(ids, OperationLog.class, "SysLog");
        System.out.print(serviceResult.getMessage());
    }

    @Test
    public void testSave() {
        SysLog sysLog = new SysLog();
        sysLog.setContentData("测试service泛型,id自增");
//        mongoBaseService.save(null,sysLog,collectionName);
        OperationLog operationLog = new OperationLog();
        operationLog.setUserName("测试jar包");
//        operationLog.setId("12345678");
        sysLogService.save(operationLog, "OperationLog");
    }

    @Test
    public void testFind() {
        ServiceResult serviceResult = sysLogService.findOne("1", SysLog.class, "SysLog", true);
        serviceResult.getMessage();
    }

    @Test
    public void testUpdate() {
        Update update = new Update().set("userName", "测试更新");
        update.set("ip2", "测试当不存在字段时，进行更新5");
        sysLogService.updateById("59c2320fca111a3f34106ad1", update, OperationLog.class, "OperationLog");
    }

    @Test
    public void testFindPageData() {
        //定义查询条件：id小于2
        Query query = new Query(Criteria.where("id").lt("2"));

        int pageNumber = 1;
        int pageSize = 5;
        String sord = "desc";
        String sort = "id";
        //生成Sort变量
        Sort sort1 = new Sort(Sort.Direction.DESC, "id");
        //生成Pageable变量
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, sort1);

        PageImpl page = (PageImpl) sysLogService.findPageData(query, pageable, OperationLog.class, "SysLog").getData();
        page.getSize();
    }

    @Test
    public void testAop() {
//      创建工厂
        ProxyFactory proxyFactory = new ProxyFactory();
//       设定监控类
        proxyFactory.setTarget(sysLogService);
//        添加aop类
        proxyFactory.addAdvice(new Advices());

        SysLogServiceImpl sysLogService = (SysLogServiceImpl) proxyFactory.getProxy();
        sysLogService.findOne("1", SysLog.class, "SysLog", true);
    }

    @Test
    public void testAop2() {
        this.testFind();
    }
}
