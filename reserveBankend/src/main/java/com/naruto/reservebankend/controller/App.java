package com.naruto.reservebankend.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.naruto.reservebankend.enums.TimeSliceEnum;
import com.naruto.reservebankend.pojo.Record;
import com.naruto.reservebankend.pojo.RequestVo;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*{
    "wx_session_user_id":"ZyNkNJ7o552l0jS7yWnkAA",
    "service_id":"68bafee6156d84e54d84ae55",
    "service_record_id":null,
    "service_record":{
    "ekcmdfazkbhm":"徐康",
    "wx_reserve_date":"2025-09-18",
    "wx_reserve_name":"7.30-8.30",
    "wx_reserve_id":"6zund8ga"
    },
    "child_service_records":[
    ],
    "code":"0c1Zzl2w3tFJB53n4Z0w3CoW8D1Zzl2W"
}*/

public class App {

    // 微信用户id
    private static final String wxUserId = "HKdwO0qxVXQjcB0_QXTA8Q";
    // 服务id
    private static final String serviceId = "68bafee6156d84e54d84ae55";
    // 预约人
    private static final String userName = "徐康";
    // 时间格式
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 创建线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    // 预约url接口地址
    private static final String URL = "https://d2.xksyun.com/api3/personal_services/create_service_record_v2?app=weixin_reserve";
    // 预约成功结果
    private static List<String> successResults = new ArrayList<>();

    public static void main(String[] args) {
        // 开始时间
        long startTime = System.currentTimeMillis();
        String startDateTime = timeFormat.format(new Date());
        // 自定义时间
        LocalTime setTime = LocalTime.of(21, 18, 30, 0);
        // 预约日期，预约后天的日期
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, 2);
        Date time = instance.getTime();
        String requestDate = new SimpleDateFormat("yyyy-MM-dd").format(time);
        // 15-17时间段
        Callable<String> oneCallable = () -> {
            RequestVo requestVo = initRequestVo(serviceId, wxUserId, userName, requestDate, TimeSliceEnum.SIX.getId(), TimeSliceEnum.SIX.getName(), UUID.randomUUID().toString().replaceAll("-", ""));
            return reserve(requestVo);
        };
        // 17-19时间段
        Callable<String> twoCallable = () -> {
            RequestVo requestVo = initRequestVo(serviceId, wxUserId, userName, requestDate, TimeSliceEnum.SEVEN.getId(), TimeSliceEnum.SEVEN.getName(), UUID.randomUUID().toString().replaceAll("-", ""));
            return reserve(requestVo);
        };
        // 统计尝试次数
        int count = 0;
        while (LocalTime.now().isBefore(setTime)) {
            count++;
            Future<String> oneFuture = executorService.submit(oneCallable);
            Future<String> twoFuture = executorService.submit(twoCallable);
            try {
                String oneResult = oneFuture.get();
                String twoResult = twoFuture.get();
                parseResult(oneResult, timeFormat.format(new Date()) + " -- 预约【" + requestDate + " 15~17】时间段结果");
                parseResult(twoResult, timeFormat.format(new Date()) + " -- 预约【" + requestDate + " 17~19】时间段结果");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 关闭线程池
        executorService.shutdown();

        // 结束时间
        long endTime = System.currentTimeMillis();
        String endDateTime = timeFormat.format(new Date());
        System.out.println("从 " + startDateTime + " 开始，至 " + endDateTime + " ，共计【" + count + "】次尝试预约，累计耗时：" + (endTime - startTime) + "毫秒");
        System.out.println();
        System.out.println("预约成功结果：");
        if (successResults.size() == 0) System.out.println("无预约成功结果~~~");
        for (String successResult : successResults) {
            System.out.println(successResult);
        }
    }

    /**
     * 预约
     *
     * @param requestVo 请求参数
     * @return String 请求结果
     */
    private static String reserve(RequestVo requestVo) {
        try {
            String requestParamsJson = JSONUtil.toJsonStr(requestVo);
            HttpResponse response = HttpRequest.post(URL)
                    .timeout(1000)
                    .body(requestParamsJson)
                    .execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 初始化请求参数
     *
     * @param serviceId   服务id
     * @param wxUserId    微信用户id
     * @param userName    预约人姓名
     * @param reserveId   预约id
     * @param reserveDate 预约日期，如：2025-01-01
     * @param reserveName 预约时间段，如：7.30-8.30
     * @param code        预约码
     * @return RequestVo
     */
    private static RequestVo initRequestVo(String serviceId, String wxUserId, String userName, String reserveDate, String reserveId, String reserveName, String code) {
        RequestVo requestVo = new RequestVo();
        requestVo.setService_id(serviceId);
        requestVo.setCode(code);
        requestVo.setChild_service_records(new ArrayList<>());
        requestVo.setWx_session_user_id(wxUserId);
        requestVo.setService_record_id(null);
        Record record = new Record();
        record.setEkcmdfazkbhm(userName);
        record.setWx_reserve_date(reserveDate);
        record.setWx_reserve_name(reserveName);
        record.setWx_reserve_id(reserveId);
        requestVo.setService_record(record);
        return requestVo;
    }

    /**
     * 解析结果
     *
     * @param result    结果
     * @param logPrefix 日志前缀
     */
    private static void parseResult(String result, String logPrefix) {
        JSONObject entry = JSONUtil.parseObj(result);
        String code = entry.getStr("code");
        if ("1200".equalsIgnoreCase(code)) {
            System.out.println(logPrefix + " -> 预约成功~~~");
            successResults.add(logPrefix + " -> 预约成功!!!");
        } else {
            System.out.println(logPrefix + " -> 预约失败~，系统反馈信息 -> " + entry.getStr("msg"));
        }
    }

}
