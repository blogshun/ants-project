package com.ants.project.core.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-11
 */
public class TestSolr {

    public static void main(String[] agrs){
        HttpSolrServer server = new HttpSolrServer("http://121.41.42.83:8100/solr/core0");
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);
        query.setRows(1);
        try {
            QueryResponse response = server.query(query);
            SolrDocumentList results = response.getResults();
            System.out.println(results);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
