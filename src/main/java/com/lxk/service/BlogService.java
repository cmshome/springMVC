package com.lxk.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lxk.constants.OrderingConstants;
import com.lxk.model.Blog;
import com.lxk.model.BlogSortTypeEnum;
import com.lxk.repository.BlogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lxk on 2017/5/18
 */
@Service("blogService")
public class BlogService {

    @Resource(name = "blogRepository")
    private BlogRepository dao;

    /**
     * 获得所有博客信息集合
     */
    public List<Blog> findAllBlog() {
        return dao.findAll();
    }

    /**
     * 根据文章标题获得博客信息
     *
     * @param title 博客文章标题
     */
    public List<Blog> findBlogByTitle(String title) {
        return dao.findByTitle(title);
    }

    /**
     * 获得top n，并按某类型排序。
     *
     * @param topN 前n个
     * @param type 类型：read or ping
     */
    public List<Blog> getTopNBlog(Integer topN, String type) {
        if (Strings.isNullOrEmpty(type)) {
            return null;
        }

        List<Blog> all = findAllBlog();
        if (all == null || all.isEmpty()) {
            return null;
        }
        sortAllBlog(type, all);
        List<Blog> result = Lists.newArrayList();
        if (topN != null && topN > 0) {
            result = all.subList(0, topN);
        }
        return result;
    }

    /**
     * 根据某类型排序集合
     *
     * @param type 类型，read或者ping{@link Blog#READ,Blog#PING}
     * @param list 博客信息集合
     */
    public void sortAllBlog(String type, List<Blog> list) {
        if (BlogSortTypeEnum.READ == BlogSortTypeEnum.fromTypeName(type)) {
            list.sort(OrderingConstants.BLOG_READ_ORDERING);
        } else {
            list.sort(OrderingConstants.BLOG_PING_ORDERING);
        }
    }

    /**
     * 循环保存博客信息集合，名称重复则不保存。
     *
     * @param blogList 博客信息集合
     */
    public Boolean saveList(List<Blog> blogList) {
        List<String> repeat = Lists.newArrayList();
        for (Blog blog : blogList) {
            if (!checkNameIsNotRepetition(blog.getTitle())) {
                repeat.add(blog.getTitle());
                continue;
            }
            Blog result = dao.save(blog);
            if (result == null) {
                System.out.println("save failed , title is :" + blog.getTitle());
                return false;
            }
        }
        System.out.println(repeat.size());
        return true;
    }

    /**
     * 判断每次不重复,true：不重复，false：重复
     */
    private boolean checkNameIsNotRepetition(String title) {
        List<Blog> allByName = findBlogByTitle(title);
        return (allByName == null || allByName.size() == 0);
    }

}
