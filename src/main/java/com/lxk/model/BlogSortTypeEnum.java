package com.lxk.model;


/**
 * 博客排序类型枚举
 * <p>
 * Created by lxk on 2017/5/18
 */
public enum BlogSortTypeEnum {
    /**
     * 阅读次数
     */
    READ,
    /**
     * 评论
     */
    PING,
    /**
     * 赞
     */
    PRAISE,
    /**
     * 踩
     */
    TREAD,
    /**
     * 增长
     */
    INCREASE;

    /**
     * 根据字符串返回对应类型的枚举变量
     *
     * @param sortType 类型的字符串描述
     */
    public static BlogSortTypeEnum fromTypeName(String sortType) {
        if (Blog.READ.equals(sortType)) {
            return READ;
        } else if (Blog.PING.equals(sortType)) {
            return PING;
        } else if (Blog.INCREASE.equals(sortType)) {
            return INCREASE;
        } else if (Blog.PRAISE.equals(sortType)) {
            return PRAISE;
        } else if (Blog.TREAD.equals(sortType)) {
            return TREAD;
        }
        return null;
    }
}
