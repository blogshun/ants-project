package com.ants.project.core.solr;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Solr客户端简单封装
 * @version 1.0
 * @Date 2016-01-27 14:30
 */
@Service
public class SolrClientService {

    protected static final Logger logger = LoggerFactory.getLogger(SolrClientService.class);

    @Value("${http.solr.server.base.url:\"\"}")
    private String serverBaseUrl;


    /**
     * 根据core 名称拼接完整 url
     *
     * @param coreName
     * @return
     */
    private String getHttpSolrServerUrl(String coreName) {
        if (StringUtils.isEmpty(this.serverBaseUrl)) {
            throw new RuntimeException("请配置: http.solr.server.base.url");
        }
        return serverBaseUrl + coreName + "/";
    }

    /**
     * 查询 solr,
     *
     * @param coreName         core 名称, 如: core1 core0
     * @param solrSearchParams 查询参数, 与 solr 后台 一 一对应
     * @return
     */
    public QueryResponse query(String coreName, SolrSearchParams solrSearchParams) {
        if (StringUtils.isEmpty(coreName) || null == solrSearchParams) {
            logger.error("没有传递有效参数!!");
            return null;
        }
        HttpSolrServer server = new HttpSolrServer(getHttpSolrServerUrl(coreName));
        SolrQuery params = new SolrQuery();

        if (StringUtils.isNoneEmpty(solrSearchParams.getQ())) {
            params.add("q", solrSearchParams.getQ());
        }
        if (StringUtils.isNotEmpty(solrSearchParams.getSort())) {
            params.add("sort", solrSearchParams.getSort());
        }
        if (null != solrSearchParams.getWt()) {
            params.add("wt", solrSearchParams.getWt().getType());
        }

        // 分页查询
        if (null == solrSearchParams.getPageNum() || solrSearchParams.getPageNum() <= 0) {
            solrSearchParams.setPageNum(1);
        }
        if (null == solrSearchParams.getPageSize() || solrSearchParams.getPageSize() <= 0) {
            solrSearchParams.setPageSize(10);
        }
        params.add("start", String.valueOf(((solrSearchParams.getPageNum() - 1) * solrSearchParams.getPageSize())));
        params.add("rows", solrSearchParams.getPageSize().toString());

        QueryResponse rsp = null;
        try {
            rsp = server.query(params);
        } catch (SolrServerException e) {
            logger.error("solr query 异常, e:{}", e.getMessage());
        }

        return rsp;
    }
}
