package com.naruto.reservebankend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ReserveBankendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReserveBankendApplication.class, args);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println(dateFormat.format(new Date()) + "   - 预约蒸镀服务启动成功...");
    }

}
