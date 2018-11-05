package com.bigyoung.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class testController {
    @ResponseBody
    @RequestMapping("/hello")
    public JSONObject hello() {
        WebClient webClient = null;
        JSONObject data = new JSONObject();
        data.put("time",System.currentTimeMillis());
        JSONObject map1 = new JSONObject();
        JSONObject map2 = new JSONObject();
        try {
            webClient = new WebClient(BrowserVersion.CHROME);    //定义一个默认的WebClient
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setTimeout(50000);
            HtmlPage page = webClient.getPage("http://api.api68.com/lottery/getLotteryListByCategory.do?category=1&isContainsHot=0&isUse=1&name=&lotCode=&pageNo=&pageSize=10000");    //从指定URL获取HtmlPage
            JSONObject json = JSONObject.fromObject(page.asText());
            JSONArray ja = JSONArray.fromObject(JSONObject.fromObject(json.get("result")).get("data"));
            System.out.println(ja);


                    //当前期数
            System.out.println(JSONObject.fromObject(ja.get(0)).get("preDrawIssue"));
            //当前开奖时间
            System.out.println(JSONObject.fromObject(ja.get(0)).get("preDrawTime"));
            //开奖号码
            System.out.println(JSONObject.fromObject(ja.get(0)).get("preDrawCode"));

                    //下一期 期数
            System.out.println(JSONObject.fromObject(ja.get(0)).get("drawIssue"));
            //下一期 开奖时间
            System.out.println(JSONObject.fromObject(ja.get(0)).get("drawTime"));
            // 开奖间隔

            //延迟


            map1.put("periodNumber",JSONObject.fromObject(ja.get(0)).get("drawIssue"));
            map1.put("awardTime",JSONObject.fromObject(ja.get(0)).get("drawTime"));
            map1.put("awardTimeInterval","5");
            map1.put("delayTimeInterval","15");

            map2.put("periodNumber",JSONObject.fromObject(ja.get(0)).get("preDrawIssue"));
            map2.put("awardTime",JSONObject.fromObject(ja.get(0)).get("preDrawTime"));
            String[] code=JSONObject.fromObject(ja.get(0)).get("preDrawCode").toString().split(",");
            StringBuffer sb=new StringBuffer();
            for (int i = 0; i <code.length ; i++) {

                if(!code[i].equals("10")){
                    if(i==code.length-1){
                        sb.append(code[i].replace("0",""));
                    }else{
                        sb.append(code[i].replace("0","")+",");
                    }

                }else{
                    if(i==code.length-1){
                        sb.append(code[i]);
                    }else{
                        sb.append(code[i]+",");
                    }

                }
            }

                map2.put("awardNumbers",sb.toString());


            data.put("current", map2);
            data.put("next", map1);





        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            webClient.close();    //关闭客户端
        }
        return  data;
    }

    @ResponseBody
    @RequestMapping("/jssc")
    public JSONObject jssc() {
        WebClient webClient = null;
        JSONObject jsons = new JSONObject();
        try {
            webClient = new WebClient(BrowserVersion.CHROME);    //定义一个默认的WebClient
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setTimeout(50000);
            HtmlPage page = webClient.getPage("http://api.api68.com/lottery/getLotteryListByCategory.do?category=1&isContainsHot=0&isUse=1&name=&lotCode=&pageNo=&pageSize=10000");    //从指定URL获取HtmlPage
            JSONObject json = JSONObject.fromObject(page.asText());
            JSONArray ja = JSONArray.fromObject(JSONObject.fromObject(json.get("result")).get("data"));
            for (int i=0;i<ja.size();i++) {
                if(JSONObject.fromObject(ja.get(i)).get("lotName").equals("极速赛车")){
                    JSONObject data = new JSONObject();
                    data.put("number", JSONObject.fromObject(ja.get(i)).get("preDrawCode"));
                    data.put("dateline", JSONObject.fromObject(ja.get(i)).get("preDrawTime"));
                    jsons.put(JSONObject.fromObject(ja.get(i)).get("drawIssue"), data);
                }

            }


            System.out.println(jsons);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            webClient.close();    //关闭客户端
        }
        return  jsons;
    }





}

