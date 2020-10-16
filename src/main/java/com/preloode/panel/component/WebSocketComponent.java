package com.preloode.panel.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.enumeration.global.WebSocketAction;
import com.preloode.panel.model.global.WebSocketResponse;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Component
public class WebSocketComponent extends WebSocketListener {


    private static final Logger logger = LoggerFactory.getLogger(WebSocketComponent.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private WebSocketAction action;

    private boolean connected;

    private String header;

    private String message;

    private WebSocket webSocket;


    @PostConstruct
    public void initialize() {

        this.connected = false;

    }


    public WebSocketAction getAction() {

        return action;

    }


    public void setAction(WebSocketAction action) {

        this.action = action;

    }


    public boolean isConnected() {

        return connected;

    }


    public void setConnected(boolean connected) {

        this.connected = connected;

    }


    public String getHeader() {

        return header;

    }


    public void setHeader(String header) {

        this.header = header;

    }


    public String getMessage() {

        return message;

    }


    public void setMessage(String message) {

        this.message = message;

    }


    public WebSocket getWebSocket() {

        return webSocket;

    }


    public void setWebSocket(WebSocket webSocket) {

        this.webSocket = webSocket;

    }


    public void connect(String url, String path, int timeout) {

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(timeout, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder().url(url + path).build();
        client.newWebSocket(request, this);

    }


    public <T> T messageToModel(String message) {

        T result = null;

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(message, new TypeReference<>() {
            });

            logger.info("Web socket client message converted to model");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    public <T> String messageToString(T message) {

        String result = "";

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(message);

            logger.info("Web socket client message converted to string");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        return result;

    }


    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

        connected = false;

    }


    @Override
    public void onOpen(WebSocket webSocket, Response response) {

        this.webSocket = webSocket;

        connected = true;

        if(!message.isEmpty()) {

            webSocket.send(message);

            logger.info(message);

        }

        logger.info("Web socket connected");

    }


    @Override
    public void onMessage(WebSocket webSocket, String text) {

        if(action == WebSocketAction.BinanceStream) {

            simpMessagingTemplate.convertAndSend(
                    "/output-stream/cryptocurrency/binance/stream",
                    new WebSocketResponse(
                            "Message sent to client",
                            true,
                            header,
                            message,
                            text,
                            "Binance Stream"
                    )
            );

        }

    }


}
