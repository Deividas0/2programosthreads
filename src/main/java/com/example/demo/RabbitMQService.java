package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private static final String QUEUE_NAME = "pavyzdine_eile";
    private static final String HOST = "localhost";
    private final ConnectionFactory factory;
    private final ObjectMapper objectMapper;

    public RabbitMQService() {
        this.factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        this.objectMapper = new ObjectMapper();
    }

    public void sendObjectToQueue(Object obj) throws Exception {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String jsonMessage = objectMapper.writeValueAsString(obj);

            channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes());
        }
    }

    public <T> T receiveObjectFromQueue(Class<T> clazz) throws Exception {
        final T[] result = (T[]) new Object[1]; // Kazkoks hackas išvengti bereikalingų problemų.

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            Object lock = new Object();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String jsonMessage = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received JSON: " + jsonMessage);

                try {
                    result[0] = objectMapper.readValue(jsonMessage, clazz);
                    synchronized (lock) {
                        lock.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

            synchronized (lock) {
                lock.wait();
            }
        }

        return result[0];
    }

    public <T> void continuousReceiveAndProcess(Class<T> clazz) throws Exception {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String jsonMessage = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received JSON: " + jsonMessage);

                try {
                    T obj = objectMapper.readValue(jsonMessage, clazz);
                    System.out.println(" [x] Deserialized Object: " + obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

            System.out.println("Waiting for messages... Press Ctrl+C to exit.");
            while (true) {
                Thread.sleep(1000);
            }
        }
    }


}
