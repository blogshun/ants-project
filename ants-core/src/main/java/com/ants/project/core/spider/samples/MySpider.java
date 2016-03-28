package com.ants.project.core.spider.samples;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author liushun
 * @version 1.0
 * @Date 2015-12-22
 */

public class MySpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(3000).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0");


    private static int count = 0;

    private static Properties config = null;

    private static List<String> fields = new ArrayList<String>();

  //  private static String PATH =
    /**
     * 加载配置文件
     */
    static {
        try {
            config = new Properties();
         //   String PATH = new MySpider().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
          //  File file = new File(PATH+"..//config.properties");
           // InputStream in = new FileInputStream(file.getCanonicalPath());
            InputStream in = Object.class.getResourceAsStream("/config.properties");
            config.load(in);
            String[] fie = config.getProperty("fields").split(",");
            for(String s:fie)
                fields.add(s);
            JSON.toJSON(fields);
        } catch (Exception e) {
            System.out.println("加载同级目录config.properties配置文件出错！！！");
            e.printStackTrace();
        }
    }


    //回调储存到数据库
    public static class DataFilePipeline implements Pipeline {
        @Override
        public void process(ResultItems resultItems, Task task) {
            Map map = resultItems.getAll();
            List<String> objValue = new ArrayList<String>();
            for(int i=0;i<fields.size();i++){
                String val = fields.get(i);
                if(map.get(val)==null)
                    objValue.add("");
                else
                    objValue.add(map.get(val).toString());
            }
            if(map.size()!=0) {
               // System.out.println(new JsonFormatTool().formatJson(map.toString()));
              //  MySQLJDBC.insert(objValue);
            }
        }
    }

    // 去标签方法
    public static String deleteTag(String content, String... tags) {
        if(StringUtils.isEmpty(content))
            return "";
        for (String tag : tags) { //div a li
            if(StringUtils.isEmpty(tag))
                break;
            else {
                while (content.indexOf("<" + tag) != -1) {
                    int start = content.indexOf("<" + tag);
                    int end = content.indexOf(">", start + tag.length() + 1);
                    String a = content.substring(start, end + 1);
                    content = content.replaceAll(a, "");
                    content = content.replaceAll("</" + tag + ">", "");
                }
            }
        }
        return content.replaceAll("\n", "").trim();
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        count++;
        Selectable sel = html.links().regex("("+URLREG+"[a-zA-Z0-9\\d]+.shtml)");
        page.addTargetRequests(sel.all());
        if(count!=1) {
            for(String f:fields){
                String key = config.getProperty(f);
                if(!"".equals(key)) {
                    String pro = config.getProperty(f+"_deleteTag");
                    if(pro!=null) {
                        String[] tags = pro.split(",");
                        String value = html.xpath(key).toString();
                        if("content".equals(f))
                            page.putField(f,value);
                        else
                            page.putField(f, deleteTag(value, tags).replaceAll(config.getProperty(f + "_replaceStr"), ""));
                    }
                }
            }
            try {
                page.putField("area", new String(Area.getBytes("ISO-8859-1"),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("############=====> "+count);
    }

    @Override
    public Site getSite() {
        return site;
    }

    private String URLREG = "";
    private String Area = "";

    public String setArea(String Area){
        return this.Area = Area;
    }
    public String setURLREG(String URLREG){
        return this.URLREG = URLREG;
    }

    public static void main(String[] ags) {
        String[] args =  new String[]{"baoxianfuwu","test","20"};
//        //创建数据表 初始化链接
//        MySQLJDBC.createTable(config.getProperty("host")
//                ,config.getProperty("database")
//                ,args[1]
//                ,config.getProperty("user")
//                ,config.getProperty("pass")
//                ,fields);

        int k = 0;
        if(args.length!=3)
            System.out.println("error ， 请输入2个参数 标签 和 线程数!");
        else if(args.length==3) {
            try {
                k = Integer.parseInt(args[2]);
                String[] china = config.getProperty("chinas").split(",");
                String[] eglish = config.getProperty("eglishs").split(",");
                for (int i = 0; i < china.length; i++) {
                    for (int j = 1; j <= 3; j++) {
                        MySpider ms = new MySpider();
                        ms.setArea(china[i]); //.addPipeline(new DataFilePipeline())
                        ms.setURLREG("http://" + eglish[i] + ".58.com/" + args[0] + "/");
                        Spider.create(ms).addUrl("http://" + eglish[i] + ".58.com/" + args[0] + "/pn" + j).thread(k).run();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("error ，线程数请输入数字!");
            }

        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@程序运行结束------");

    }


}
