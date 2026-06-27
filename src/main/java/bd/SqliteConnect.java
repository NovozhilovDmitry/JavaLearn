package bd;

import bd.sqliteselect.SelectResultsTkStatus;
import json.tkstatus.fieldsdiscription.TestCaseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteConnect {
    private Connection conn = null;

    public void connect(String dbPath) {
        try {
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            System.out.println("Соединение с БД установлено.");
        } catch (SQLException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }

    public void insertTkStatuses(TestCaseStatus info) {
        String sql = """
                INSERT INTO tk_statuses
                (
                id, name, description
                )
                VALUES
                (?,?,?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, info.getId());
            ps.setString(2, info.getName());
            ps.setString(3, info.getDescription());
            ps.executeUpdate();     // Выполняем запись
        } catch (SQLException e) {
            System.out.println("Ошибка добавления: " + e.getMessage());
        }
    }

    // 2. Метод для создания таблиц (выполнение CREATE, INSERT, UPDATE, DELETE)
    public void executeUpsert(String sql) {
        if (conn == null) {
            System.out.println("Нет соединения с базой данных!");
            return;
        }
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Запрос успешно выполнен.");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    // 3. Пример метода для выполнения SELECT скриптов (tk_statuses)
    public void getResultSelectForTkStatuses(String sql) {
        if (conn == null) {
            System.out.println("Нет соединения с базой данных!");
            return;
        }
        // Используем try-with-resources для автоматического закрытия Statement и ResultSet
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Обработка результатов
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка чтения данных: " + e.getMessage());
        }
    }

    public List<SelectResultsTkStatus> getTkStatuses() {
        List<SelectResultsTkStatus> statuses = new ArrayList<>();
        String sql = "SELECT * FROM tk_statuses";
        if (conn == null) {
            System.out.println("Нет соединения!");
            return statuses;
        }
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                statuses.add(new SelectResultsTkStatus(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return statuses;
    }

    // 4. Отдельный метод для закрытия соединения
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Соединение с БД успешно закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}
