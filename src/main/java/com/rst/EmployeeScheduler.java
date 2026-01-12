package com.mt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmployeeScheduler {

    private static final String DB_URL = "jdbc:mysql://mysql:3306/employees";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            insertEmployee();
        }, 0, 30, TimeUnit.SECONDS); // toutes les 30 secondes
    }

    private static void insertEmployee() {
        try {
            String name = "Emp-" + UUID.randomUUID().toString().substring(0, 30);

            Connection connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO employee(name) VALUES (?)"
            );
            ps.setString(1, name);
            ps.executeUpdate();

            System.out.println("Inserted employee: " + name);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
