package com.google.firebase.database.tubesock;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\tubesock\WebSocketMessage.smali */
public class WebSocketMessage {
    private byte[] byteMessage;
    private byte opcode = 1;
    private String stringMessage;

    public WebSocketMessage(byte[] message) {
        this.byteMessage = message;
    }

    public WebSocketMessage(String message) {
        this.stringMessage = message;
    }

    public boolean isText() {
        return this.opcode == 1;
    }

    public boolean isBinary() {
        return this.opcode == 2;
    }

    public byte[] getBytes() {
        return this.byteMessage;
    }

    public String getText() {
        return this.stringMessage;
    }
}
