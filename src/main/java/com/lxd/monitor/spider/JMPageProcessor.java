package com.lxd.monitor.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class JMPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
//            .addCookie("gvid", "fe53510bc626e393088eb53026e3af73")
//            .addCookie("session_id", "5bc4319de81805065")
//            .addCookie("cookie_uid", "d10b19b6c41539584414")
//            .addCookie("sensorsdata_is_new_user", "true")
//            .addCookie("account", "U3UYcJGxSoUgfwPgz56ugcoTyAtZRsJZPdq99VKV9ESCp2vKsUG9mo7q93jRa%2FIQ5g6ksQ15MxPZf%2BAZEUW%2BVNhv%2BkJrEdrFhc81LeYbdBHdNPPlg2zVsa6BM2wgaHI5bt3DOY%2FYLurYWrbY4svtFXx%2BYc0OEpa6XaqXBQDYfwRPZCsBxZpCjsX0T3FvbEFWbiAIEsjkwn34WedlvN%2Fveg%3D%3D")
//            .addCookie("tk", "26ee0a83b839c90755337e0db7a946fa1faa194a")
//            .addCookie("uid", "257798892")
//            .addCookie("v_uid", "257798892")
//            .addCookie("nickname", "JM1BarzBhcPH1")
//            .addCookie("token", "QXCWvDrjT6tNbteF7y8h1b9UXpkSNPV2MfwZESoLvOGZ9gRUaI0JQAidczJ7AcfkmCVFW52wxjhluI3aGgLPBTRKoHrYYydxqpHMO4mis8qs4uDEl60Kne3BzAVUCIZB")
//            .addCookie("session", "sTBn5qxNFWDUyP6jAOJiYZk9QDYyPSwL")
//            .addCookie("privilege_group", "0")
//            .addCookie("register_time", "1538723940")
//            .addCookie("cookie_ver", "1")
//            .addCookie("login_account_name", "13352868581")
//            .addCookie("last_reg", "1539584466")
//            .addCookie("login_mode", "h5_dynamic_login")
//            .addCookie("sensorsdata2015jssdkcross", "%7B%22distinct_id%22%3A%221667661d1cc705-0f816fc0097811-6114147a-2073600-1667661d1cd262%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%7D%7D");

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
        page.putField("result", page.getHtml());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("gvid", "fe53510bc626e393088eb53026e3af73");
        map.put("session_id", "5bc4319de81805065");
        map.put("cookie_uid", "d10b19b6c41539584414");
        map.put("sensorsdata_is_new_user", "true");
        map.put("account", "U3UYcJGxSoUgfwPgz56ugcoTyAtZRsJZPdq99VKV9ESCp2vKsUG9mo7q93jRa%2FIQ5g6ksQ15MxPZf%2BAZEUW%2BVNhv%2BkJrEdrFhc81LeYbdBHdNPPlg2zVsa6BM2wgaHI5bt3DOY%2FYLurYWrbY4svtFXx%2BYc0OEpa6XaqXBQDYfwRPZCsBxZpCjsX0T3FvbEFWbiAIEsjkwn34WedlvN%2Fveg%3D%3D");
        map.put("tk", "26ee0a83b839c90755337e0db7a946fa1faa194a");
        map.put("uid", "257798892");
        map.put("v_uid", "257798892");
        map.put("nickname", "JM1BarzBhcPH1");
        map.put("token", "QXCWvDrjT6tNbteF7y8h1b9UXpkSNPV2MfwZESoLvOGZ9gRUaI0JQAidczJ7AcfkmCVFW52wxjhluI3aGgLPBTRKoHrYYydxqpHMO4mis8qs4uDEl60Kne3BzAVUCIZB");
//        map.put();
//        map.put();
        String url = "http://x.jumei.com/activity/WshiguangVideo/ajaxGetIndex?rand=" + System.currentTimeMillis()/1000;
        Request request = new Request();

        System.out.println("request url: " + url);
        Spider.create(new JMPageProcessor())
                .addUrl(url)
                .thread(5)
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
