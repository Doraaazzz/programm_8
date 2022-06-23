package com.example.programm_8.Utility;

import java.sql.*;

public class DataBaseHandler {
    // Table names
    public static final String MOVIE_TABLE = "movie_table";
    public static final String USER_TABLE = "my_users";
    // MOVIE_TABLE column names
    public static final String MOVIE_TABLE_ID_COLUMN = "id";
    public static final String MOVIE_TABLE_NAME_COLUMN = "name";
    public static final String MOVIE_TABLE_CREATION_DATE_COLUMN = "creation_date";
    public static final String MOVIE_TABLE_OSCARSCOUNT_COLUMN = "oscars_count";
    public static final String MOVIE_TABLE_GOLDENPALM_COLUMN = "golden_palm_count";
    public static final String MOVIE_TABLE_GENRE_COLUMN = "genre";
    public static final String MOVIE_TABLE_MPAA_RATING_COLUMN = "mpaa_rating";
    public static final String MOVIE_TABLE_COORDINATES_X_COLUMN = "coordinates_x";
    public static final String MOVIE_TABLE_COORDINATES_Y_COLUMN = "coordinates_y";
    public static final String MOVIE_TABLE_OPERATOR_NAME_COLUMN = "operator_name";
    public static final String MOVIE_TABLE_OPERATOR_HEIGHT_COLUMN = "operator_height";
    public static final String MOVIE_TABLE_OPERATOR_EYE_COLOR_COLUMN = "operator_eye_color";
    public static final String MOVIE_TABLE_OPERATOR_HAIR_COLOR_COLUMN = "operator_hair_color";
    public static final String MOVIE_TABLE_USER_ID_COLUMN = "user_id";
    // USER_TABLE column names
    public static final String USER_TABLE_ID_COLUMN = "id";
    public static final String USER_TABLE_USERNAME_COLUMN = "username";
    public static final String USER_TABLE_PASSWORD_COLUMN = "password";

    private final String JDBC_DRIVER = "org.postgresql.Driver";

    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DataBaseHandler(String databaseHost, int databasePort, String user, String password, String databaseName) {
        this.url = "jdbc:postgresql://" + databaseHost + ":"+databasePort+"/"+databaseName;
        this.user = user;
        this.password = password;
        connectToDataBase();
    }

    /**
     * A class for connect to database.
     */
    private void connectToDataBase() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при подключении к базе данных!");
        } catch (ClassNotFoundException exception) {
            System.out.println("Драйвер управления базой дынных не найден!");
        }
    }

    /**
     * @param sqlStatement SQL statement to be prepared.
     * @param generateKeys Is keys needed to be generated.
     * @return Pprepared statement.
     * @throws SQLException When there's exception inside.
     */
    public PreparedStatement getPreparedStatement(String sqlStatement, boolean generateKeys) throws SQLException {
        PreparedStatement preparedStatement;
        try {
            if (connection == null) throw new SQLException();
            int autoGeneratedKeys = generateKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS;
            preparedStatement = connection.prepareStatement(sqlStatement, autoGeneratedKeys);
            return preparedStatement;
        } catch (SQLException exception) {
            if (connection == null) System.out.println("Соединение с базой данных не установлено!");
            throw new SQLException(exception);
        }
    }

    /**
     * Close prepared statement.
     *
     * @param sqlStatement SQL statement to be closed.
     */
    public void closePreparedStatement(PreparedStatement sqlStatement) {
        if (sqlStatement == null) return;
        try {
            sqlStatement.close();
        } catch (SQLException exception) {
        }
    }

    /**
     * Close connection to database.
     */
    public void closeConnection() {
        if (connection == null) return;
        try {
            connection.close();
            System.out.println("Соединение с базой данных разорвано.");
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при разрыве соединения с базой данных!");
        }
    }

    /**
     * Set commit mode of database.
     */
    public void setCommitMode() {
        try {
            if (connection == null) throw new SQLException();
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при установлении режима транзакции базы данных!");
        }
    }

    /**
     * Set normal mode of database.
     */
    public void setNormalMode() {
        try {
            if (connection == null) throw new SQLException();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при установлении нормального режима базы данных!");
        }
    }

    /**
     * Commit database status.
     */
    public void commit() {
        try {
            if (connection == null) throw new SQLException();
            connection.commit();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при подтверждении нового состояния базы данных!");
        }
    }

    /**
     * Roll back database status.
     */
    public void rollback() {
        try {
            if (connection == null) throw new SQLException();
            connection.rollback();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при возврате исходного состояния базы данных!");
        }
    }

    /**
     * Set save point of database.
     */
    public void setSavepoint() {
        try {
            if (connection == null) throw new SQLException();
            connection.setSavepoint();
        } catch (SQLException exception) {
            System.out.println("Произошла ошибка при сохранении состояния базы данных!");
        }
    }
}
