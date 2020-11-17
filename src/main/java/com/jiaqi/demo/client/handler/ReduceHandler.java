package com.jiaqi.demo.client.handler;

import com.google.gson.Gson;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ReduceHandler {

    private CountDownLatch cd;
    private AtomicInteger sum;

    public ReduceHandler(int capacity) {
        cd = new CountDownLatch(capacity);
        sum = new AtomicInteger(0);
    }

    public void callback(String resp) {
        Map<String, Double> map = new Gson().fromJson(resp, Map.class);
        Integer i = map.get("data").intValue();
        sum.getAndAdd(i);
        cd.countDown();
    }

    public Integer getSum() throws InterruptedException {
        cd.await();
        return sum.intValue();
    }
}
