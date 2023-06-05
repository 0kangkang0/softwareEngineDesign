package com.kangkang.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kangkang.mapper.RouteMapper;
import com.kangkang.mapper.TicketMapper;
import com.kangkang.mapper.TimetableMapper;
import com.kangkang.pojo.Route;
import com.kangkang.pojo.Ticket;
import com.kangkang.pojo.Timetable;
import com.kangkang.pojo.xiecheng.flightInfo.*;
import com.kangkang.pojo.xiecheng.routeid.Data;
import com.kangkang.pojo.xiecheng.routeid.RoutedId;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class GetInfoFromXieChen {

    private static TimetableMapper timetableMapper;
    private static RouteMapper routeMapper;
    private static TicketMapper ticketMapper;
    private static PlatformTransactionManager txManager;
    public GetInfoFromXieChen() {

    }

    @Autowired
    public void setTimetableMapper(TimetableMapper timetableMapper) {
        GetInfoFromXieChen.timetableMapper = timetableMapper;
    }
    @Autowired
    public  void setRouteMapper(RouteMapper routeMapper) {
        GetInfoFromXieChen.routeMapper = routeMapper;
    }
    @Autowired
    public void setTicketMapper(TicketMapper ticketMapper) {
        GetInfoFromXieChen.ticketMapper = ticketMapper;
    }
    @Autowired
    public void setTxManager(PlatformTransactionManager txManager) {
        GetInfoFromXieChen.txManager = txManager;
    }
    //user-agent信息
    private static final List<String> userAgent = List.of("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1500.55 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.197.11 Safari/532.0",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/532.4 (KHTML, like Gecko) Chrome/4.0.237.0 Safari/532.4 Debian",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/12.0.702.0 Safari/534.24",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/532.1 (KHTML, like Gecko) Chrome/4.0.219.3 Safari/532.1",
            "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.30 Safari/525.13",
            "Mozilla/5.0 ArchLinux (X11; U; Linux x86_64; en-US) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.100",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.25 (KHTML, like Gecko) Chrome/12.0.706.0 Safari/534.25",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.11 (KHTML, like Gecko) Ubuntu/11.04 Chromium/17.0.963.65 Chrome/17.0.963.65 Safari/535.11",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.211.2 Safari/532.0",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Ubuntu/11.04 Chromium/14.0.825.0 Chrome/14.0.825.0 Safari/535.1",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.42 Safari/525.19",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/540.0 (KHTML, like Gecko) Ubuntu/10.10 Chrome/8.1.0.0 Safari/540.0",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_0; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.99 Safari/533.4",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.91 Chromium/12.0.742.91 Safari/534.30",
            "Mozilla/5.0 (X11; U; Linux i686 (x86_64); en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.196.0 Safari/532.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Ubuntu/10.10 Chromium/10.0.648.0 Chrome/10.0.648.0 Safari/534.16",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.39 Safari/525.19",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/534.13 (KHTML, like Gecko) Chrome/9.0.597.0 Safari/534.13",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.203.2 Safari/532.0",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.1 SUSE/6.0.428.0 (KHTML, like Gecko) Chrome/6.0.428.0 Safari/534.1",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; en-US) AppleWebKit/530.9 (KHTML, like Gecko) Chrome/ Safari/530.9",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36",
            "Mozilla/5.0 (X11; U; Linux i686 (x86_64); en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.634.0 Safari/534.16",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.15 (KHTML, like Gecko) Chrome/10.0.612.1 Safari/534.15",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.24 (KHTML, like Gecko) Ubuntu/10.10 Chromium/12.0.703.0 Chrome/12.0.703.0 Safari/534.24",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.18 (KHTML, like Gecko) Chrome/11.0.661.0 Safari/534.18",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.11 (KHTML, like Gecko) Ubuntu/11.04 Chromium/17.0.963.65 Chrome/17.0.963.65 Safari/535.11",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.201.0 Safari/532.0",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/534.36 (KHTML, like Gecko) Chrome/13.0.766.0 Safari/534.36",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.211.2 Safari/532.0",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.815.10913 Safari/535.1",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.2 (KHTML, like Gecko) Chrome/4.0.222.2 Safari/532.2",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.220 Safari/535.1",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.125 Safari/533.4",
            "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1468.0 Safari/537.36",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/534.15 (KHTML, like Gecko) Chrome/10.0.613.0 Safari/534.15",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.133 Safari/534.16",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/530.7 (KHTML, like Gecko) Chrome/2.0.176.0 Safari/530.7",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.198 Safari/532.0",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.813.0 Safari/535.1",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Ubuntu/10.10 Chromium/10.0.642.0 Chrome/10.0.642.0 Safari/534.16",
            "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.463.0 Safari/534.3",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/534.1 (KHTML, like Gecko) Chrome/6.0.417.0 Safari/534.1",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/532.2 (KHTML, like Gecko) Chrome/4.0.223.2 Safari/532.2",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.13 (KHTML, like Gecko) Ubuntu/9.10 Chromium/9.0.592.0 Chrome/9.0.592.0 Safari/534.13",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_7; en-US) AppleWebKit/532.1 (KHTML, like Gecko) Chrome/4.0.212.1 Safari/532.1",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.195.17 Safari/532.0",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.53 Safari/525.19",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/530.5 (KHTML, like Gecko) Chrome/2.0.172.6 Safari/530.5",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_4) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/532.4 (KHTML, like Gecko) Chrome/4.0.241.0 Safari/532.4",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/532.2 (KHTML, like Gecko) Chrome/4.0.221.8 Safari/532.2",
            "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.195.21 Safari/532.0",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.2 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.792.0 Safari/535.1",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/528.8 (KHTML, like Gecko) Chrome/2.0.156.1 Safari/528.8",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.1 (KHTML, like Gecko) Chrome/6.0.428.0 Safari/534.1",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_7) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.813.0 Safari/535.1",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_0; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.202.0 Safari/532.0",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_4; en-US) AppleWebKit/534.2 (KHTML, like Gecko) Chrome/6.0.451.0 Safari/534.2",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.204.0 Safari/532.0",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.20 Safari/535.1",
            "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.20 Safari/535.1",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/530.6 (KHTML, like Gecko) Chrome/2.0.174.0 Safari/530.6",
            "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2866.71 Safari/537.36",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.215 Safari/535.1",
            "Mozilla/5.0 (X11; U; Linux x86_64; en-US) AppleWebKit/533.4 (KHTML, like Gecko) Chrome/5.0.375.99 Safari/533.4",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13",
            "Mozilla/5.0 (X11; U; Linux i686; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.460.0 Safari/534.3",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.212.0 Safari/532.0",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.30 (KHTML, like Gecko) Ubuntu/11.04 Chromium/12.0.742.112 Chrome/12.0.742.112 Safari/534.30",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/530.5 (KHTML, like Gecko) Chrome/2.0.172.23 Safari/530.5",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.23 (KHTML, like Gecko) Chrome/11.0.686.3 Safari/534.23",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/4.0.202.0 Safari/532.0",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/534.25 (KHTML, like Gecko) Chrome/12.0.706.0 Safari/534.25",
            "Mozilla/5.0 (X11; U; Linux i686 (x86_64); en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.198.0 Safari/532.0",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.10 (KHTML, like Gecko) Chrome/8.0.552.215 Safari/534.10",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/532.0 (KHTML, like Gecko) Chrome/3.0.196.2 Safari/532.0",
            "Mozilla/5.0 (Windows NT 6.0; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.220 Safari/535.1",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.6 (KHTML, like Gecko) Chrome/16.0.897.0 Safari/535.6",
            "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.14 (KHTML, like Gecko) Chrome/24.0.1292.0 Safari/537.14",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.93 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/530.6 (KHTML, like Gecko) Chrome/2.0.174.0 Safari/530.6",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/534.24 (KHTML, like Gecko) Chrome/11.0.696.14 Safari/534.24",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.50 Safari/525.19",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.54 Safari/535.2",
            "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/528.11 (KHTML, like Gecko) Chrome/2.0.157.0 Safari/528.11");

    /**
     * 获取cookie中的bfa
     * @return cookie中的bfa
     */
    private static String getCookie() {
        String random_str = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder random_id = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            random_id.append(random_str.charAt(random.nextInt(random_str.length())));
        }
        String t = String.valueOf(Math.round((double) (System.currentTimeMillis() / 1000)*1000)-random.nextInt(800000)-900000);

        String[] bfa_list = new String[]{"1", t, random_id.toString(), "1", t, t, "1", "1","1"};
        StringBuilder bfa = new StringBuilder();
        for (int i = 0; i < bfa_list.length; i++) {
            if (i != 0) {
                bfa.append(".");
            }
            bfa.append(bfa_list[i]);
        }
//        # e.g. _bfa=1.1639722810158.u3jal2.1.1639722810158.1639722810158.1.1
        return "_bfa="+ bfa;
    }

    /**
     * 获取路线信息
     * @param departureCityCode 到达城市的机场编码
     * @param arrivalCityCode 出发城市的机场编码
     * @param departureDate 出发日期
     * @return  请求后的数据，包含路线ID和获取机票信息要的数据
     * @throws IOException 调用请求时可能发生异常
     */
    private static Data getTransaction(String departureCityCode, String arrivalCityCode, String departureDate) throws IOException {
        String flight_list_url = "https://flights.ctrip.com/international/search/api/flightlist"+
        "/oneway-"+departureCityCode+"-"+arrivalCityCode+"?_=1&depdate="+departureDate+"&cabin="+ "Y_S_C_F" +"&containstax=1" ;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(flight_list_url);
        HttpResponse execute = httpClient.execute(httpGet);
        String s = EntityUtils.toString(execute.getEntity());
        Gson gson = new Gson();
        RoutedId routedId = gson.fromJson(s, RoutedId.class);
        return routedId.getData();
    }

    /**
     * 获取调用携程 API 查询航班接口 Header 中所需的参数 sign
     * @param transaction_id 路线id
     * @param departureCityCode 出发城市代码
     * @param arrivalCityCode 到达城市代码
     * @param departure_date 出发时间
     * @return header中的sign
     * @throws NoSuchAlgorithmException
     */
    private static String getSign(String transaction_id,String departureCityCode,String arrivalCityCode,String departure_date) throws NoSuchAlgorithmException {
        departureCityCode = (departureCityCode.equals("PEK")||departureCityCode.equals("PKX"))?"BJS":departureCityCode;
        arrivalCityCode = (arrivalCityCode.equals("PEK")||arrivalCityCode.equals("PKX"))?"BJS":arrivalCityCode;
        departureCityCode = departureCityCode.equals("PVG")?"SHA":departureCityCode;
        arrivalCityCode = arrivalCityCode.equals("PVG")?"SHA":arrivalCityCode;
        departureCityCode = departureCityCode.equals("TFU")?"CTU":departureCityCode;
        arrivalCityCode = arrivalCityCode.equals("TFU")?"CTU":arrivalCityCode;
        String sign_value = transaction_id + departureCityCode + arrivalCityCode + departure_date;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(sign_value.getBytes(StandardCharsets.UTF_8));
        return new BigInteger(1, md.digest()).toString(16);
    }

    /**
     * 获取两地某一天的机票数据
     * @param start 出发城市iataCode
     * @param end 到达城市idtaCode
     * @param time 出发日期
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void getInfo(String start, String end, Date time) throws IOException, NoSuchAlgorithmException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取 transactionID 及航线数据
        Data transactionData = getTransaction(start, end, simpleDateFormat.format(time));
        if(transactionData==null) {
            System.out.println("transactionData=null");
            return;
        }
        //获取调用携程 API 查询航班接口 Header 中所需的参数 sign
        String sign = getSign(transactionData.getTransactionID(), start, end, simpleDateFormat.format(time));
        String search_url = "https://flights.ctrip.com/international/search/api/search/batchSearch";
        Gson gson = new Gson();
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(search_url);
        httpPost.setEntity(new StringEntity(gson.toJson(transactionData)));
        //请求头
        httpPost.setHeader("transactionid", transactionData.getTransactionID());
        httpPost.setHeader("sign", sign);
        httpPost.setHeader("scope", transactionData.getScope());
        httpPost.setHeader("origin","https://flights.ctrip.com");
        httpPost.setHeader("referer","https://flights.ctrip.com/online/list/oneway-"+start+
                "-"+end+"?_=1&depdate="+simpleDateFormat.format(time)+"&cabin=Y_S_C_F&containstax=1");
        httpPost.setHeader("content-type", "application/json;charset=UTF-8");
        httpPost.setHeader("user-agent", userAgent.get(new Random().nextInt(userAgent.size())));
        httpPost.setHeader("cookie", getCookie());
        HttpResponse execute = httpClient.execute(httpPost);
        String s = EntityUtils.toString(execute.getEntity());
        FlightInfo flightInfo=null;
        try {
            flightInfo = gson.fromJson(s, FlightInfo.class);
        }catch (Exception e){
            FileOutputStream fileOutputStream = new FileOutputStream("price.json");
            fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
            e.printStackTrace();
        }
        if(flightInfo==null) {
            System.out.println("flightInfo=null");
            return;
        }
        List<FlightItineraryListItem> flightItineraryList = flightInfo.getData().getFlightItineraryList();
        if(flightItineraryList == null || flightItineraryList.isEmpty())return;
        //开启事务
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(transaction);
        try {
            for (FlightItineraryListItem flightItineraryListItem : flightItineraryList) {
//              飞机路线、编号信息
                FlightSegmentsItem flightSegmentsItem = flightItineraryListItem.getFlightSegments().get(0);
                if(flightSegmentsItem.getTransferCount()>0)continue;
//              机票信息
                List<PriceListItem> priceList = flightItineraryListItem.getPriceList();
                FlightListItem flightListItem = flightSegmentsItem.getFlightList().get(0);
//              出发时间
                Timetable startTime = new Timetable();
                startTime.setScheduled(flightListItem.getDepartureDateTime());
                startTime.setType(0);
                timetableMapper.insertAll(startTime);
//              到达时间
                Timetable arriveTime = new Timetable();
                arriveTime.setScheduled(flightListItem.getArrivalDateTime());
                arriveTime.setType(1);
                timetableMapper.insertAll(arriveTime);
//               路线
                Route route = new Route();
                route.setAircraftCode(flightListItem.getFlightNo());
                route.setStartAirportCode(flightListItem.getDepartureAirportCode());
                route.setArriveAirportCode(flightListItem.getArrivalAirportCode());
                route.setStartTimeId(startTime.getId());
                route.setArriveTimeId(arriveTime.getId());
                route.setRouteCode(simpleDateFormat.format(time)+"+"+route.getAircraftCode());
                routeMapper.insert(route);
//              路线的价格
                for (PriceListItem priceListItem : priceList) {
                    Ticket ticket = new Ticket();
                    ticket.setRouteId(route.getId());
                    ticket.setPrice((double) priceListItem.getAdultPrice());
                    String cabin = priceListItem.getCabin();
//                  处理机舱信息
                    cabin = cabin.equals("C")? "公务舱":cabin;
                    cabin = cabin.equals("Y")? "经济舱":cabin;
                    cabin = cabin.equals("F")? "头等舱":cabin;
                    ticket.setType(cabin);
                    ticket.setNum((long) priceListItem.getTicketCount());
                    if(ticket.getNum()==0){
                        ticket.setNum((long) (new Random().nextInt(10)+1));
                    }
                    ticketMapper.insert(ticket);
                }
            }
        }catch (Exception e){
            txManager.rollback(status);
            e.printStackTrace();
            return;
        }
        txManager.commit(status);
//        System.out.println(s);

        /*// 要调用的接口方法

        String url = "https://flights.ctrip.com/itinerary/api/12808/products";
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String json = "{\n" +
                "    \"flightWay\":\"Oneway\",\n" +
                "    \"army\":\"false\",\n" +
                "    \"flightWay\":\"Oneway\",\n" +
                "    \"army\":\"false\",\n" +
                "    \"classType\":\"ALL\",\n" +
                "    \"hasChild\":\"false\",\n" +
                "    \"hasBaby\":\"false\",\n" +
                "    \"searchIndex\":1,\n" +
                "    \"token\":\"c91cf603fd7191a9c762fcc27b4cbf29\",\n" +
                "    \"airportParams\":[{\n" +
                "        \"dcity\": \"" + start + "\", \n" +
                "        \"acity\": \"" + end + "\", \n" +
                "        \"date\": \"" + simpleDateFormat.format(time) + "\"\n" +
                "        }]\n" +
                "}";
        httpPost.setEntity(new StringEntity(json));
        httpPost.setHeader("User-agent", userAgent.get(new Random().nextInt(userAgent.size())));
        httpPost.setHeader("Cookie", getCookie());
        httpPost.setHeader("Content-Type", "application/json");
        HttpResponse execute = httpClient.execute(httpPost);
        String s = EntityUtils.toString(execute.getEntity());
//        正则去除无用数据
        String reg = "\"basicModelData\":.*}}},";
        s = s.replaceAll(reg, "");
        reg = "\"couponDesc\":.*\"},";
        s = s.replaceAll(reg, "");
        System.out.println(s);
        Gson gson = new Gson();
        Response response=null;
        try {
            response = gson.fromJson(s, Response.class);
        }catch (Exception e){
            FileOutputStream fileOutputStream = new FileOutputStream("price.json");
            fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
            e.printStackTrace();
        }
        if(response==null)
            return;
        List<RouteListItem> routeList = response.getData().getRouteList();
        if(routeList == null || routeList.isEmpty()){
            return;
        }
        //存入表
        //开启事务
        DefaultTransactionDefinition transaction = new DefaultTransactionDefinition();
        transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(transaction);
        try {
            for (RouteListItem routeListItem : routeList) {
                List<LegsItem> legs = routeListItem.getLegs();
                for (LegsItem leg : legs) {
                    Flight flight = leg.getFlight();
                    //出发时间
                    Timetable startTime = new Timetable();
                    startTime.setType(0);
                    startTime.setScheduled(flight.getDepartureDate());
                    timetableMapper.insertAll(startTime);
                    //到达时间
                    Timetable arriveTime = new Timetable();
                    arriveTime.setType(1);
                    arriveTime.setScheduled(flight.getArrivalDate());
                    timetableMapper.insertAll(arriveTime);
                    //路线
                    Route route = new Route();
                    route.setAircraftCode(flight.getFlightNumber());
                    route.setStartAirportCode(flight.getDepartureAirportInfo().getAirportTlc());
                    route.setArriveAirportCode(flight.getArrivalAirportInfo().getAirportTlc());
                    route.setArriveTimeId(arriveTime.getId());
                    route.setStartTimeId(startTime.getId());
                    route.setRouteCode(simpleDateFormat.format(time) + "+" + route.getAircraftCode());
                    routeMapper.insert(route);
                    //设置机票
                    for (CabinsItem cabin : leg.getCabins()) {
                        Ticket ticket = new Ticket();
                        ticket.setRouteId(route.getId());
                        ticket.setPrice((double) cabin.getPrice().getPrice());
                        ticket.setType(cabin.getSaleType() + "(" + cabin.getPriceClass() + ")");
                        ticket.setNum((long) cabin.getSeatCount());
                        ticketMapper.insert(ticket);
                    }
                }
            }
        }catch (Exception e){
            //出现异常进行回退
            txManager.rollback(status);
            e.printStackTrace();
            return;
        }
        txManager.commit(status);*/
    }
    public static String getRouteInfo(String flightNo) throws IOException {
        String body="{\"flightNo\":\""+flightNo+"\"}";
        String search_url = "https://flights.ctrip.com/schedule/getScheduleByFlightNo";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(search_url);
        httpPost.setEntity(new StringEntity(body));
        httpPost.setHeader("content-type", "application/json;charset=UTF-8");
        httpPost.setHeader("user-agent", userAgent.get(new Random().nextInt(userAgent.size())));
        httpPost.setHeader("cookie", getCookie());
        HttpResponse execute = httpClient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        return EntityUtils.toString(entity);
    }
}
