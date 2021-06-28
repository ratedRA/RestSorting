package com.luceneMonitor;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.monitor.Monitor;
import org.apache.lucene.monitor.MonitorConfiguration;
import org.apache.lucene.monitor.MonitorQuery;
import org.apache.lucene.monitor.TermFilteredPresearcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemoryTest {
    public static void main(String[] args) throws IOException, ParseException {
        List<String> queries = new ArrayList<>();
        MonitorConfiguration monitorConfiguration = new MonitorConfiguration();
        Monitor monitor = new Monitor(new StandardAnalyzer(), new TermFilteredPresearcher(), monitorConfiguration);

        for(int i=0; i<1000; i++) {
            QueryParser queryParser = new QueryParser(String.valueOf(i), new StandardAnalyzer());
            Query parse1 = queryParser.parse("doc_type:item AND marketplace_publication_status:PUBLISHED AND seller_vertical:art AND creator_url_labels:(mark-beard) AND seller_status:(active+nopay) AND seller_vertical:art AND item_status:(COMPLETE+UNAVAILABLE)  AND unavailable_reason_code:(ON_HOLD+ON_HOLD_CUSTOM)");
            monitor.register(new MonitorQuery(String.valueOf(i), parse1));
        }
        HeapDump.dumpHeap("/Users/amanprasad/Documents/heapdump/dump.hprof", true);

    }
    private static String getFq(String solrSearchString){
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int strLen = solrSearchString.length();
        while(i < strLen){
            boolean flag = false;
            if(i+1 < strLen && i+2 < strLen && i+3< strLen && solrSearchString.charAt(i) == 'f' && solrSearchString.charAt(i+1) == 'q' && solrSearchString.charAt(i+2) == '='){
                int j;
                for(j = i+3; j<strLen && solrSearchString.charAt(j) != '&'; j++){
                    stringBuilder.append(solrSearchString.charAt(j));
                }
                if(solrSearchString.charAt(j) == '&'){
                    stringBuilder.append(" AND ");
                }
                flag = true;
                i += j - i;
            }
            if(!flag){
                i+=1;
            }

        }
        if(stringBuilder.toString().endsWith(" AND ")) {
            stringBuilder.delete(stringBuilder.length() - 1 - 4, stringBuilder.length());
        }
        String queryString = stringBuilder.toString();
        queryString = queryString.replaceAll("\\+", " ");

        return "doc_type:item AND marketplace_publication_status:PUBLISHED AND seller_vertical:art AND creator_url_labels:(mark-beard) AND seller_status:(active+nopay) AND seller_vertical:art AND item_status:(COMPLETE+UNAVAILABLE)  AND unavailable_reason_code:(ON_HOLD+ON_HOLD_CUSTOM)";
    }
}
