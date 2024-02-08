package com.teach.java_grading.service;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttGlobalPublishFilter;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.teach.java_grading.jpa.entity.Grade;
import jakarta.annotation.PostConstruct;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MqttConsumerService {

    @Autowired
    private GradeService gradeService;

    private final JSONParser parser = new JSONParser();

    @PostConstruct
    public void subscribeToTopic() {
        Mqtt5AsyncClient client = MqttClient.builder()
                .useMqttVersion5()
                .identifier("consumer-id")
                .serverHost(System.getenv("HIVEMQ_URI")) // "broker.hivemq.com" System.getenv("HIVEMQ_URI")
                .serverPort(1883) // Default MQTT port
                .automaticReconnectWithDefaultConfig() // Enable automatic reconnect
                .buildAsync();

        client.connectWith()
                .cleanStart(true)
                .sessionExpiryInterval(500)
                .send()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        System.out.println("Connection failed: " + throwable.getMessage());
                    } else {
                        System.out.println("Connected successfully");
                        // Subscribe to a topic
                        client.subscribeWith()
                                .topicFilter("TEACHING")
                                .qos(MqttQos.AT_LEAST_ONCE)
                                .send()
                                .whenComplete((subAck, subThrowable) -> {
                                    if (subThrowable != null) {
                                        System.out.println("Subscribe failed: " + subThrowable.getMessage());
                                    } else {
                                        System.out.println("Subscribed successfully");
                                    }
                                });

                        // Set up callback for incoming messages
                        client.toAsync().publishes(MqttGlobalPublishFilter.ALL, publish -> {
                            try {
                                String message = new String(publish.getPayloadAsBytes());
                                JSONObject json = (JSONObject) parser.parse(message);
                                Grade grade = new Grade();
                                grade.setName(json.get("name").toString());
                                grade.setGrade(Integer.parseInt(json.get("grade").toString()));
                                grade.setCreatedAt(LocalDateTime.now());
                                gradeService.saveGrade(grade);
                            } catch (Exception e) {
                                System.out.println("Failed to process message: " + e.getMessage());
                            }
                        });
                    }
                });
    }
}
