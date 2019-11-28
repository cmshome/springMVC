//package com.lxk.repository;
//
//import com.google.common.collect.Maps;
//import com.sun.istack.internal.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author wei.Li
// */
//@Repository
//public class ReportExportRecordsRepositoryCustom {
//
//    private static final String
//            MAP =
//            "function () {" +
//                    " emit(this.reportSettingId, {'size':1});" +
//                    "}",
//            REDUCE =
//                    "function (key, values) {" +
//                            "var obj = {" +
//                            "   reportSettingId: key," +
//                            "   size: 0" +
//                            " };" +
//                            " values.forEach(function(val){" +
//                            "    obj.size += val.size;" +
//                            " });" +
//                            "return obj;" +
//                            "}";
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    /**
//     * 条件查询报表生成记录
//     *
//     * @param reportSettingIds reportSettingIds
//     * @return 查询结果集对象 key = reportSettingId , value = 该任务下生成的报表数量
//     */
//    public
//    @NotNull
//    Map<String, Integer> searchReportSettingCountVOByReportSettingIds(Collection<String> reportSettingIds) {
//
//        final Query query = Query.query(
//                Criteria
//                        .where("reportSettingId").in(reportSettingIds)
//        );
//        return this.searchReportSettingCountVOByReportSettingIds(reportSettingIds, query);
//    }
//
//    /**
//     * 条件查询报表生成记录
//     *
//     * @param searchStartTime  查询起始时间
//     * @param searchEndTime    查询结束时间
//     * @param reportSettingIds reportSettingIds
//     * @return 查询结果集对象 key = reportSettingId , value = 该任务下生成的报表数量
//     */
//    public
//    @NotNull
//    Map<String, Integer> searchReportSettingCountVOByReportSettingIds(long searchStartTime,
//                                                                      long searchEndTime,
//                                                                      Collection<String> reportSettingIds) {
//
//        final Query query = Query.query(
//                Criteria
//                        .where("startTime").gte(searchStartTime)
//                        .and("endTime").lte(searchEndTime)
//                        .and("reportSettingId").in(reportSettingIds)
//        );
//
//        return this.searchReportSettingCountVOByReportSettingIds(reportSettingIds, query);
//    }
//
//    /**
//     * mapReduce 查询每个报表任务生成的报表文件数量
//     *
//     * @param reportSettingIds 报表任务 id
//     * @param query            查询 query
//     * @return 查询结果集对象 key = reportSettingId , value = 该任务下生成的报表数量
//     */
//    private Map<String, Integer> searchReportSettingCountVOByReportSettingIds(Collection<String> reportSettingIds, Query query) {
//        final MapReduceResults<Object> mapReduce = mongoTemplate.mapReduce(query, "report_export_records_copy0720", MAP, REDUCE, Object.class);
//        final Map<String, Integer> map = Maps.newHashMapWithExpectedSize(reportSettingIds.size());
//        for (Object o : mapReduce) {
//            HashMap o1 = (HashMap) o;
//            final String reportSettingId = o1.get("_id").toString();
//            final HashMap value = ((HashMap) o1.get("value"));
//            if (value == null) {
//                continue;
//            }
//            final Double count = Double.parseDouble(value.get("size").toString());
//            map.put(reportSettingId, count.intValue());
//        }
//        return map;
//    }
//
//}
