package com.onion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 *
 * @author yu
 */

@MapperScan("com.onion.project.*.dao")
@SpringBootApplication
public class SimpleApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication.run(SimpleApplication.class, args);
        long endTime = System.currentTimeMillis();
        System.out.println("O(∩_∩)O~启动成功用时"+(endTime-startTime)+"毫秒");
    }

}
