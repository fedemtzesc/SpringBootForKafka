package com.kafka.provider.controller;

public record MsgRequest(String msg,
                         String topic) {
}
