package com.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", exchange -> respond(exchange, 200,
            "{\"status\":\"ok\",\"app\":\"java-demo-calculator\",\"version\":\"1.0.0\"}"));

        server.createContext("/health", exchange -> respond(exchange, 200,
            "{\"status\":\"healthy\"}"));

        server.createContext("/calc/add", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            try {
                int a = Integer.parseInt(param(query, "a"));
                int b = Integer.parseInt(param(query, "b"));
                int result = new Calculator().add(a, b);
                respond(exchange, 200, "{\"result\":" + result + "}");
            } catch (Exception e) {
                respond(exchange, 400, "{\"error\":\"" + e.getMessage() + "\"}");
            }
        });

        server.createContext("/calc/subtract", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            try {
                int a = Integer.parseInt(param(query, "a"));
                int b = Integer.parseInt(param(query, "b"));
                int result = new Calculator().subtract(a, b);
                respond(exchange, 200, "{\"result\":" + result + "}");
            } catch (Exception e) {
                respond(exchange, 400, "{\"error\":\"" + e.getMessage() + "\"}");
            }
        });

        server.createContext("/calc/multiply", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            try {
                int a = Integer.parseInt(param(query, "a"));
                int b = Integer.parseInt(param(query, "b"));
                int result = new Calculator().multiply(a, b);
                respond(exchange, 200, "{\"result\":" + result + "}");
            } catch (Exception e) {
                respond(exchange, 400, "{\"error\":\"" + e.getMessage() + "\"}");
            }
        });

        server.createContext("/calc/divide", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            try {
                double a = Double.parseDouble(param(query, "a"));
                double b = Double.parseDouble(param(query, "b"));
                double result = new Calculator().divide(a, b);
                respond(exchange, 200, "{\"result\":" + result + "}");
            } catch (Exception e) {
                respond(exchange, 400, "{\"error\":\"" + e.getMessage() + "\"}");
            }
        });

        server.start();
        System.out.println("Calculator API running on port " + port);
    }

    private static void respond(HttpExchange ex, int status, String body) throws IOException {
        byte[] bytes = body.getBytes();
        ex.getResponseHeaders().set("Content-Type", "application/json");
        ex.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static String param(String query, String key) {
        if (query == null) return "";
        for (String p : query.split("&")) {
            String[] kv = p.split("=", 2);
            if (kv.length == 2 && kv[0].equals(key)) return kv[1];
        }
        return "";
    }
}
