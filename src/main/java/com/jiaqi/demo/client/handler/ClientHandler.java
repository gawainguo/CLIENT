package com.jiaqi.demo.client.handler;

import io.netty.channel.ChannelOption;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.TcpClient;

@Service
public class ClientHandler {
    private WebClient client;

    @Value("${connections.max}")
    private Integer maxConnections;

    @PostConstruct
    private void init() {
//        HttpClient httpClient = HttpClient.create(ConnectionProvider.create("myConnector", 14000));
        TcpClient tcpClient = TcpClient.create(ConnectionProvider.create("tcpConnector", maxConnections))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 50000);
        client = WebClient.builder().clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))).build();
    }

    public void getRandom(String url, ReduceHandler reduce) {
        client.get().uri(url).retrieve().bodyToMono(String.class)
                .subscribe(i -> reduce.callback(i));
    }
}
