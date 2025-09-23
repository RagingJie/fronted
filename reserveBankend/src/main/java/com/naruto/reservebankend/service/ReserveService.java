package com.naruto.reservebankend.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.naruto.reservebankend.pojo.RequestVo;
import com.naruto.reservebankend.pojo.ReserveRequestVo;
import org.springframework.stereotype.Service;

@Service
public class ReserveService {

    private static final String URL = "https://d2.xksyun.com/api3/personal_services/create_service_record_v2?app=weixin_reserve";

    /**
     * 预约蒸镀
     *
     * @param requestVo 请求参数
     */
    public void reserveEvaporation(RequestVo requestVo) {

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
}
