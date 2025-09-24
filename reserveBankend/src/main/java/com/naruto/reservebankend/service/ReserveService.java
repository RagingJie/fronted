package com.naruto.reservebankend.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.naruto.reservebankend.enums.TimeSliceEnum;
import com.naruto.reservebankend.pojo.Record;
import com.naruto.reservebankend.pojo.RequestVo;
import com.naruto.reservebankend.pojo.ReserveRequestVo;
import com.naruto.reservebankend.pojo.ResponseVo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ReserveService {

    private static final String URL = "https://d2.xksyun.com/api3/personal_services/create_service_record_v2?app=weixin_reserve";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 使用系统默认时区
    ZoneId zoneId = ZoneId.systemDefault();

    // 任务结束时间
    LocalTime taskEndTime = LocalTime.of(9, 0, 15, 0);

    private static final String SUCCESS_CODE = "1200";
    private static final String FAIL_CODE = "1404";

    /**
     * 预约蒸镀
     *
     * @param requestVo 请求参数
     */
    public List<ResponseVo> reserveEvaporation(RequestVo requestVo) {
        List<ResponseVo> list = new ArrayList<>();
        // 任务开始时间
        LocalTime taskStartTime = requestVo.getTaskStartTime().toInstant().atZone(zoneId).toLocalTime();
        System.out.println("开始执行预约任务...");
        while (true) {
            System.out.println("当前时间：" + dateTimeFormat.format(new Date()));
            while (LocalTime.now().isAfter(taskStartTime) && LocalTime.now().isBefore(taskEndTime)) {
                // 开始执行预约
                executeTask(requestVo, list);
            }
            if (LocalTime.now().isAfter(taskEndTime)) {
                // 跳出循环
                break;
            }
        }
        return list;
    }

    /**
     * 执行预约任务
     *
     * @param requestVo 预约参数
     */
    private static void executeTask(RequestVo requestVo, List<ResponseVo> list) {
        List<String> reserveTimesList = requestVo.getReserveTimesList();
        if (reserveTimesList != null && !reserveTimesList.isEmpty()) {
            for (String time : reserveTimesList) {
                // 预约时间段id
                String id = TimeSliceEnum.getIdByName(time);
                // 随机数
                String code = UUID.randomUUID().toString().replaceAll("-", "");
                // 微信用户id
                String wxUserId = requestVo.getWx_session_user_id();
                // 预约人姓名
                String userName = requestVo.getService_record().getEkcmdfazkbhm();
                // 预约日期
                String reserveDate = dateFormat.format(requestVo.getService_record().getWx_reserve_date());
                // 服务id
                String serviceId = requestVo.getService_id();
                // 获取预约对象
                ReserveRequestVo reserveRequestVo = initRequestVo(serviceId, wxUserId, userName, reserveDate, id, time, code);
                // 预约
                String result = reserve(reserveRequestVo);
                System.out.println(time + " 预约结果 ==> " + result);
                JSONObject resultJSON = JSONUtil.parseObj(result);
                if (SUCCESS_CODE.equalsIgnoreCase(resultJSON.getStr("code"))) {
                    // 预约成功
                    ResponseVo responseVo = new ResponseVo(reserveDate, time, 1);
                    list.add(responseVo);
                } else if (FAIL_CODE.equalsIgnoreCase(resultJSON.getStr("code"))) {
                    // 预约失败
                    if (StrUtil.isBlank(resultJSON.getStr("wx_session_user_id"))) {
                        throw new RuntimeException("【微信用户ID】不正确，请重新填写！");
                    } else {
                        ResponseVo responseVo = new ResponseVo(reserveDate, time, 0);
                        list.add(responseVo);
                    }
                }
            }
        } else {
            throw new RuntimeException("预约时间段未选择！");
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
    private static ReserveRequestVo initRequestVo(String serviceId, String wxUserId, String userName, String reserveDate, String reserveId, String reserveName, String code) {
        ReserveRequestVo requestVo = new ReserveRequestVo();
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
     * 预约
     *
     * @param requestVo 请求参数
     * @return String 请求结果
     */
    private static String reserve(ReserveRequestVo requestVo) {
        try {
            String requestParamsJson = JSONUtil.toJsonStr(requestVo);
            HttpResponse response = HttpRequest.post(URL).timeout(1000).body(requestParamsJson).execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
