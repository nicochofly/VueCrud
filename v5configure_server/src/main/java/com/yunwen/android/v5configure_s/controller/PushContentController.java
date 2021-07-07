package com.yunwen.android.v5configure_s.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yunwen.android.v5configure_s.pojo.JPushEntity;
import com.yunwen.android.v5configure_s.pojo.PushContentResult;
import com.yunwen.android.v5configure_s.pojo.Pushcontent;
import com.yunwen.android.v5configure_s.pojo.RequestResult;
import com.yunwen.android.v5configure_s.service.PushContentInfoService;
import com.yunwen.android.v5configure_s.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

@Controller
public class PushContentController {


    @Autowired
    PushContentInfoService pushContentService;


    private final static String APP_KEY = "ceca885bc36140618e190d15";
    private final static String MASTER_SECRET = "6e633b2f1926bab5a17f8fc6";

    String jpushURL = "https://api.jpush.cn/v3/push";

    @CrossOrigin
    @GetMapping(value = "/api/queryallcontent")
    @ResponseBody
    public PushContentResult getAllPushcontent() {
        List<Pushcontent> result = pushContentService.getAllPushcontent();

        if (result == null) {
            return new PushContentResult(200, null);
        } else {
            return new PushContentResult(200, result.toArray(new Pushcontent[result.size()]));
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/appendcontent")
    @ResponseBody
    public RequestResult addPushContent(@RequestBody Pushcontent ci) {
        boolean flag = pushContentService.addPushContent(ci);
        return new RequestResult(flag ? 200 : 400);
    }


    @CrossOrigin
    @PostMapping(value = "/api/updatecontent")
    @ResponseBody
    public RequestResult updatePushContent(@RequestBody Pushcontent ci) {
//        if (ci.getUuid() == null || ci.getUuid().isEmpty()) {
//            ci.setUuid(UUID.randomUUID().toString());
//        }
        boolean flag = pushContentService.updateInfo(ci);
        return new RequestResult(flag ? 200 : 400);
    }

    @CrossOrigin
    @DeleteMapping("/api/removecontent/{id}")
    @ResponseBody
    public RequestResult removePushContent(@PathVariable("id") int id) {
        try {
            pushContentService.removePushContentById(id);
            return new RequestResult(200);
        } catch (Exception e) {
            return new RequestResult(400);
        }
    }

//    @CrossOrigin
//    @GetMapping(value = "/api/findcontentbyuuid")
//    @ResponseBody
//    public ChannelResult getPushContentByUuid(@RequestParam("uuid") String uuid) {
//        ChannelInfo result = pushContentService.findByUuid(uuid);
//        if (result == null) {
//            return new ChannelResult(400, new ChannelInfo[]{});
//        } else {
//        return new ChannelResult(200, new ChannelInfo[]{result});
//        }
//        return null;
//    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("api/uploadfile")
    public String fileUpload(MultipartFile file) throws Exception {
        String folder = "/home/tv/img/";
//        System.out.println("OriginalFilename >> " + file.getOriginalFilename());
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
//            String imgURL = "http://localhost:8082/api/file/" + f.getName();
            String imgURL = f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    @CrossOrigin
    @PostMapping(value = "/api/pushrequest")
    @ResponseBody
    public RequestResult doPushRequest(@RequestBody JPushEntity ci) {
        doRealJushRequest(ci);
        return new RequestResult(true ? 200 : 400);
    }


    public String doGetCidRequest() {
        try {
            URL url = new URL("https://api.jpush.cn/v3/push/cid?count=1");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(true);
            String headerParam = getBase64Result(APP_KEY, MASTER_SECRET);
            urlConnection.setRequestProperty("Authorization", headerParam);
            urlConnection.setRequestProperty("Content-Type", "text/plain");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();
//            // POST请求
//            DataOutputStream out = new
//                    DataOutputStream(urlConnection.getOutputStream());
//            String jsonParams = generatorJson();
//
//            out.write(jsonParams.getBytes());
//            out.flush();
//            out.close();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(urlConnection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = URLDecoder.decode(lines, "utf-8");
                sb.append(lines);
            }

            reader.close();
            // 断开连接
            urlConnection.disconnect();
            System.out.println(sb.toString());
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return "";
    }


    public void doRealJushRequest(JPushEntity jPushEntity) {

        try {
            URL url = new URL(jpushURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            String headerParam = getBase64Result(APP_KEY, MASTER_SECRET);
            urlConnection.setRequestProperty("Authorization", headerParam);
//            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();
            // POST请求
            DataOutputStream out = new
                    DataOutputStream(urlConnection.getOutputStream());
            String jsonParams = generatorJson(jPushEntity);
            out.write(jsonParams.getBytes());
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(urlConnection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = URLDecoder.decode(lines, "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public String getBase64Result(String appKey, String masterSecret) {
        String params = appKey + ":" + masterSecret;
        String base64 = Base64.getEncoder().encodeToString(params.getBytes());


//        System.out.println("base 64 " + base64);

        return "Basic " + base64;
    }


    private String generatorJson(JPushEntity jPushEntity) {
        JSONObject result = new JSONObject();

        if (jPushEntity.getPlatform().length > 1) {
            JSONArray platforms = new JSONArray();
            for (String s : jPushEntity.getPlatform()) {
                platforms.add(s);
            }
            result.put("platform", platforms);
        } else {
            result.put("platform", jPushEntity.getPlatform()[0]);
        }
//        result.put("plat")
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add("android");
//        jsonArray.add("ios");
//        result.put("platform",jsonArray);
//        result.put("cid",doGetCidRequest());

        JSONObject audience = new JSONObject();

        JSONArray registration_id = new JSONArray();
        for (String s : jPushEntity.getRegisterid()) {
            registration_id.add(s);
        }
        audience.put("registration_id", registration_id);

        JSONArray alias = new JSONArray();
        for (String s : jPushEntity.getAlias()) {
            alias.add(s);
        }
        audience.put("alias", alias);
        result.put("audience", audience);
        JSONObject message = new JSONObject();
        message.put("msg_content", jPushEntity.getMessageContent());
        message.put("content_type", jPushEntity.getContentType());
        message.put("title", jPushEntity.getTitle());

        JSONObject extras = new JSONObject();
        Map aa = jPushEntity.getExtras();
        Set<Map.Entry<String, String[]>> entries = aa.entrySet();
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            extras.put(entry.getKey(), entry.getValue());
        }

        message.put("extras", extras);
        result.put("message", message);


        System.out.println(result.toJSONString());


        return result.toJSONString();
    }


}
