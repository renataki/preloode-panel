package com.preloode.panel.model.global;

public class WebSocketResponse extends BaseResponse {


    private String header;

    private String inputMessage;

    private String outputMessage;

    private String source;


    public WebSocketResponse() {


    }


    public WebSocketResponse(String response, boolean result, String header, String inputMessage, String outputMessage, String source) {

        super(response, result);
        this.header = header;
        this.inputMessage = inputMessage;
        this.outputMessage = outputMessage;
        this.source = source;

    }


    public String getHeader() {

        return header;

    }


    public void setHeader(String header) {

        this.header = header;

    }


    public String getInputMessage() {

        return inputMessage;

    }


    public void setInputMessage(String inputMessage) {

        this.inputMessage = inputMessage;

    }


    public String getOutputMessage() {

        return outputMessage;

    }


    public void setOutputMessage(String outputMessage) {

        this.outputMessage = outputMessage;

    }


    public String getSource() {

        return source;

    }


    public void setSource(String source) {

        this.source = source;

    }


}
