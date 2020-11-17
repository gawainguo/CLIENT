package com.jiaqi.demo.client.controller;


import com.jiaqi.demo.client.handler.ClientHandler;
import com.jiaqi.demo.client.handler.ReduceHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class ClientController {

    @Autowired
    private ClientHandler handler;

    @RequestMapping("/manager")
    public Map<String, Object> getManager(@RequestParam Integer n, @RequestParam String url) throws InterruptedException {
        ReduceHandler reduce = new ReduceHandler(n);
        for (int i = 0; i < n; i++) {
            handler.getRandom(url, reduce);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", reduce.getSum());
        return map;
    }
}
