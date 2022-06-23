package com.example.programm_8.Utility;

import com.example.programm_8.Data.User;
import com.example.programm_8.exceptions.DatabaseHandlingException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseUserManager {
    // USER_TABLE
    private final String SELECT_USER_BY_ID = "SELECT * FROM " + DataBaseHandler.USER_TABLE +
            " WHERE " + DataBaseHandler.USER_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_USER_BY_USERNAME = "SELECT * FROM " + DataBaseHandler.USER_TABLE +
            " WHERE " + DataBaseHandler.USER_TABLE_USERNAME_COLUMN + " = ?";
    private final String SELECT_USER_BY_USERNAME_AND_PASSWORD = SELECT_USER_BY_USERNAME + " AND " +
            DataBaseHandler.USER_TABLE_PASSWORD_COLUMN + " = ?";
    private final String INSERT_USER = "INSERT INTO " +
            DataBaseHandler.USER_TABLE + " (" +
            DataBaseHandler.USER_TABLE_ID_COLUMN + ", " +
            DataBaseHandler.USER_TABLE_USERNAME_COLUMN + ", " +
            DataBaseHandler.USER_TABLE_PASSWORD_COLUMN + ") VALUES (?, ?, ?)";

    private final String GET_USER_COUNT = "SELECT COUNT(1) FROM " + DataBaseHandler.USER_TABLE;

    private DataBaseHandler databaseHandler;

    public DataBaseUserManager(DataBaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    /**
     * @param userId User id.
     * @return User by id.
     * @throws SQLException When there's exception inside.
     */
    public User getUserById(long userId) throws SQLException {
        User user;
        PreparedStatement preparedSelectUserByIdStatement = null;
        try {
            preparedSelectUserByIdStatement =
                    databaseHandler.getPreparedStatement(SELECT_USER_BY_ID, false);
            preparedSelectUserByIdStatement.setLong(1, userId);
            ResultSet resultSet = preparedSelectUserByIdStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString(DataBaseHandler.USER_TABLE_USERNAME_COLUMN),
                        resultSet.getString(DataBaseHandler.USER_TABLE_PASSWORD_COLUMN),
                        false
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByIdStatement);
        }
        return user;
    }

    public User getUserByUsername(String username) throws SQLException {
        User user;
        PreparedStatement preparedSelectUserByIdStatement = null;
        try {
            preparedSelectUserByIdStatement =
                    databaseHandler.getPreparedStatement(SELECT_USER_BY_USERNAME, false);
            preparedSelectUserByIdStatement.setString(1, username);
            ResultSet resultSet = preparedSelectUserByIdStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString(DataBaseHandler.USER_TABLE_USERNAME_COLUMN),
                        resultSet.getString(DataBaseHandler.USER_TABLE_PASSWORD_COLUMN),
                        false
                );
            } else throw new SQLException();
        } catch (SQLException exception) {
            throw new SQLException(exception);
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByIdStatement);
        }
        return user;
    }

    /**
     * Check user by username and password.
     *
     * @param user User.
     * @return Result set.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public boolean checkUserByUsernameAndPassword(User user) throws DatabaseHandlingException {
        PreparedStatement preparedSelectUserByUsernameAndPasswordStatement = null;
        try {
            preparedSelectUserByUsernameAndPasswordStatement =
                    databaseHandler.getPreparedStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD, false);
            preparedSelectUserByUsernameAndPasswordStatement.setString(1, user.getUsername());
            preparedSelectUserByUsernameAndPasswordStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedSelectUserByUsernameAndPasswordStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByUsernameAndPasswordStatement);
        }
    }

    /**
     * Get user id by username.
     *
     * @param user User.
     * @return User id.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public int getUserIdByUsername(User user) throws DatabaseHandlingException {
        int userId;
        PreparedStatement preparedSelectUserByUsernameStatement = null;
        try {
            preparedSelectUserByUsernameStatement =
                    databaseHandler.getPreparedStatement(SELECT_USER_BY_USERNAME, false);
            preparedSelectUserByUsernameStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedSelectUserByUsernameStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(DataBaseHandler.USER_TABLE_ID_COLUMN);
            } else userId = -1;
            return userId;
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectUserByUsernameStatement);
        }
    }

    /**
     * Insert user.
     *
     * @param user User.
     * @return Status of insert.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public boolean insertUser(User user) throws DatabaseHandlingException {
        PreparedStatement preparedInsertUserStatement = null;
        try {
            if (getUserIdByUsername(user) != -1) return false;
            PreparedStatement preparedGetUserCount = databaseHandler.getPreparedStatement(GET_USER_COUNT, false);
            ResultSet resultSet = preparedGetUserCount.executeQuery();
            Integer userId = 1;
            if(resultSet.next()){
                userId=resultSet.getInt("count")+1;
            }
            preparedInsertUserStatement =
                    databaseHandler.getPreparedStatement(INSERT_USER, false);
            preparedInsertUserStatement.setInt(1, userId);
            preparedInsertUserStatement.setString(2, user.getUsername());
            preparedInsertUserStatement.setString(3, user.getPassword());
            if (preparedInsertUserStatement.executeUpdate() == 0) throw new SQLException();
            return true;
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedInsertUserStatement);
        }
    }
}
