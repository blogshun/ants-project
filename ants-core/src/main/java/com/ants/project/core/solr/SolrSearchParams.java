package com.ants.project.core.solr;

import java.io.Serializable;

/**
 * 简介: solr 查询参数封装
 * @version 1.0
 * @Date 2015-12-24 16:18
 */
public class SolrSearchParams implements Serializable {

    private String q; //条件
    private String fq;
    private String sort;//排序
    private Integer pageNum = 1;//第几页
    private Integer pageSize = 10;//每页条数
    private String fl;
    private String df;
    private String rawQueryParameters;

    private WT wt = WT.JSON; //默认json数据格式

    private Boolean indent;
    private Boolean debugQuery;

    public SolrSearchParams() {
    }

    public SolrSearchParams(String q, String sort, Integer pageNum, Integer pageSize) {
        this.q = q;
        this.sort = sort;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public SolrSearchParams(String q, Integer pageNum, Integer pageSize) {
        this.q = q;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getRawQueryParameters() {
        return rawQueryParameters;
    }

    public void setRawQueryParameters(String rawQueryParameters) {
        this.rawQueryParameters = rawQueryParameters;
    }

    public WT getWt() {
        return wt;
    }

    public void setWt(WT wt) {
        this.wt = wt;
    }

    public Boolean getIndent() {
        return indent;
    }

    public void setIndent(Boolean indent) {
        this.indent = indent;
    }

    public Boolean getDebugQuery() {
        return debugQuery;
    }

    public void setDebugQuery(Boolean debugQuery) {
        this.debugQuery = debugQuery;
    }

    public enum WT {

        JSON("json"),
        XML("xml"),
        PYTHON("python"),
        RUBY("ruby"),
        PHP("php"),
        CSV("csv");

        private String type;

        private WT(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
