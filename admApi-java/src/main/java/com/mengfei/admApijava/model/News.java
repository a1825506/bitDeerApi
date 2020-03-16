package com.mengfei.admApijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 新闻数据表
 */
@Entity

public class News {

    @Id
    @GeneratedValue
    private Long id;

    public String getNewsUuid() {
        return newsUuid;
    }

    public void setNewsUuid(String newsUuid) {
        this.newsUuid = newsUuid;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_summary() {
        return news_summary;
    }

    public void setNews_summary(String news_summary) {
        this.news_summary = news_summary;
    }

    public String getNews_summary_img() {
        return news_summary_img;
    }

    public void setNews_summary_img(String news_summary_img) {
        this.news_summary_img = news_summary_img;
    }

    public String getNews_create_time() {
        return news_create_time;
    }

    public void setNews_create_time(String news_create_time) {
        this.news_create_time = news_create_time;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int news_type) {
        this.newsType = news_type;
    }

    public String getNews_author() {
        return news_author;
    }

    public void setNews_author(String news_author) {
        this.news_author = news_author;
    }

    public int getNews_show_index_page() {
        return news_show_index_page;
    }

    public void setNews_show_index_page(int news_show_index_page) {
        this.news_show_index_page = news_show_index_page;
    }

    private String newsUuid;

    private String news_title;//新闻标题

    private String news_summary;//新闻摘要

    private String news_summary_img;//新闻摘要图片地址

    private String news_create_time;//创建时间

    private int newsType;//新闻类型 1:新闻、2:博客

    private String news_author;//归宿作者

    private int news_show_index_page;//新闻是否展示在首页0:不展示、1：展示

    private String news_url;

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsUuid='" + newsUuid + '\'' +
                ", news_title='" + news_title + '\'' +
                ", news_summary='" + news_summary + '\'' +
                ", news_summary_img='" + news_summary_img + '\'' +
                ", news_create_time='" + news_create_time + '\'' +
                ", newsType=" + newsType +
                ", news_author='" + news_author + '\'' +
                ", news_show_index_page=" + news_show_index_page +
                ", news_url=" + news_url +


                '}';
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url = news_url;
    }
}
