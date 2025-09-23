package com.naruto.reservebankend.controller;

import com.naruto.reservebankend.common.R;
import com.naruto.reservebankend.pojo.RequestVo;
import com.naruto.reservebankend.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping(path = "/reserveEvaporation")
    public R reserveEvaporation(@RequestBody RequestVo requestVo) {
        reserveService.reserveEvaporation(requestVo);
        return R.success();
    }
}
