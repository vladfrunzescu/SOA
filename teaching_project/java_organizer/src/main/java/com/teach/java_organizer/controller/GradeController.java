package com.teach.java_organizer.controller;

import com.teach.java_organizer.domain.MessageResponse;
import com.teach.java_organizer.service.MqttPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GradeController {

    @Autowired
    private MqttPublisherService mqttPublisherService;

    @GetMapping("/submit")
    public ResponseEntity<MessageResponse> sendGradesThroughMqtt(@RequestParam String name, @RequestParam Integer grade) {
        String message = "Request received from website with name: " + name + " and grade: " + grade;
        System.out.println(message);
        mqttPublisherService.publishMessage("{\"name\": \"" + name + "\", \"grade\": \"" + grade + "\"}");
        MessageResponse response = new MessageResponse(message);
        return ResponseEntity.ok(response);
    }
}
