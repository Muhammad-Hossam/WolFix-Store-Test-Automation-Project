package com.graduationproject.apis;
import com.graduationproject.utils.TimeManager;
import com.graduationproject.utils.logs.LogsManager;

import static io.restassured.RestAssured.given;



public class LoginApi {
    private final String timestamp = TimeManager.getSimpleTimestamp();
    private static POJO data;

    public void register() {
        if (data == null) {
            data = new POJO();
            String uniqueEmail = "owl" + timestamp + "@gmail.com";
            data.setName("owl");
            data.setEmail(uniqueEmail);
            data.setPassword("P@ssw0rd");

            System.out.println("Registering new user: " + data.getEmail());

            given()
                    .contentType("application/json")
                    .body(data)
                    .when()
                    .post("https://api.sersawy.com/auth/?action=register")
                    .then()
                    .log().all();
        } else {
            LogsManager.info("User already registered: " + data.getEmail());
        }
    }

    public void login() {
        if (data == null) {
            LogsManager.error("User not registered yet! Call register() first.");
        }

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://api.sersawy.com/auth/?action=login")
                .then()
                .log().all();
    }


    public static String getEmail() {
        return data != null ? data.getEmail() : null;
    }

    public static String getPassword() {
        return data != null ? data.getPassword() : null;
    }
}







