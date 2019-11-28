//package com.lxk.service;
//
//import com.lxk.model.Area;
//import com.lxk.repository.AreaRepository;
//import com.mongodb.BasicDBObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 测试3.x mongo 的查询
// *
// * @author LiXuekai on 2018/12/3
// */
//@Service("areaService")
//public class AreaService {
//    @Resource(name = "areaRepository")
//    private AreaRepository dao;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public boolean isMongoStateOK() {
//        Object okObj = mongoTemplate.getDb().runCommand( new BasicDBObject( "dbstats" , Boolean.TRUE )).get("ok");
//        if (okObj instanceof Double) {
//            return (Double) okObj == 1.0;
//        }
//        return false;
//    }
//
//    /**
//     * 查询所有数据
//     */
//    public List<Area> findAll() {
//        return dao.findAll();
//    }
//
//    /**
//     * service抛异常，controller是可以catch到的。
//     */
//    public void exception() throws Exception {
//        throw new Exception("我 service 抛异常。") ;
//    }
//
//    /**
//     * 修改区域
//     */
//    public Area findById(String id, Area area) {
//        return dao.findById(id).orElse(null);
//    }
//
//}
