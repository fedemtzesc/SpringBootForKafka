package com.kafka.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class MessageController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/msg-send")
    public String sendMsg(@RequestBody MsgRequest msgRequest){
        try {
            kafkaTemplate.send(msgRequest.topic(), msgRequest.msg());
        }catch(Exception e){
            return e.getMessage();
        }
        return "mensaje Enviado con exito!!!";
    }
}
