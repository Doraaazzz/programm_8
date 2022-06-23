package com.example.programm_8.Utility;

import com.example.programm_8.Data.*;
import com.example.programm_8.exceptions.DatabaseHandlingException;
import com.example.programm_8.exceptions.IncorrectData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Operates the database collection itself.
 */
public class DataBaseCollectionManager {
    // MOVIE_TABLE
    private final String SELECT_ALL_MOVIES = "SELECT * FROM " + DataBaseHandler.MOVIE_TABLE;
    private final String SELECT_MOVIE_BY_ID = SELECT_ALL_MOVIES + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_MOVIE_BY_ID_AND_USER_ID = SELECT_MOVIE_BY_ID + " AND " +
            DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN + " = ?";
    private final String INSERT_MOVIE = "INSERT INTO " +
            DataBaseHandler.MOVIE_TABLE + " (" +
//            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_NAME_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_GOLDENPALM_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_NAME_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_HEIGHT_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_EYE_COLOR_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_HAIR_COLOR_COLUMN + ", " +
            DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN + ") VALUES (" +
//            "?," +
            " ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_MOVIE_BY_ID = "DELETE FROM " + DataBaseHandler.MOVIE_TABLE +
            " WHERE " + DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_NAME_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_CREATION_DATE_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_OSCARSCOUNT_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_GOLDENPALM_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_GOLDENPALM_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_GENRE_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_MPAA_RATING_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_COORDINATES_X_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_COORDINATES_Y_BY_ID = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_OPERATOR_NAME_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_OPERATOR_HEIGHT_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_HEIGHT_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_OPERATOR_EYE_COLOR_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_EYE_COLOR_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_MOVIE_OPERATOR_HAIR_COLOR_COLUMN = "UPDATE " + DataBaseHandler.MOVIE_TABLE + " SET " +
            DataBaseHandler.MOVIE_TABLE_OPERATOR_HAIR_COLOR_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.MOVIE_TABLE_ID_COLUMN + " = ?";
    private DataBaseHandler databaseHandler;
    private DataBaseUserManager databaseUserManager;

    public DataBaseCollectionManager(DataBaseHandler databaseHandler, DataBaseUserManager databaseUserManager) {
        this.databaseHandler = databaseHandler;
        this.databaseUserManager = databaseUserManager;
    }

    /**
     * Create Movie.
     *
     * @param resultSet Result set parametres of Movie.
     * @return New Movie.
     * @throws SQLException When there's exception inside.
     */
    private Movie createMovie(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(DataBaseHandler.MOVIE_TABLE_ID_COLUMN);
            String name = resultSet.getString(DataBaseHandler.MOVIE_TABLE_NAME_COLUMN);
            ZonedDateTime creationDate = ZonedDateTime.parse(resultSet.getString(DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN));
            Integer oscarsCount = resultSet.getInt(DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN);
            Long goldenPalm = resultSet.getLong(DataBaseHandler.MOVIE_TABLE_GOLDENPALM_COLUMN);
            MovieGenre movieGenre = MovieGenre.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN));
            MpaaRating mpaaRating = MpaaRating.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN));
            Coordinates coordinates = new Coordinates(resultSet.getLong(DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN), resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN));
            Person director = new Person(resultSet.getString(DataBaseHandler.MOVIE_TABLE_OPERATOR_NAME_COLUMN),
                    resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_OPERATOR_HEIGHT_COLUMN),
                    Color.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_OPERATOR_EYE_COLOR_COLUMN)),
                    Color.valueOf(resultSet.getString(DataBaseHandler.MOVIE_TABLE_OPERATOR_HAIR_COLOR_COLUMN)));
            User owner = databaseUserManager.getUserById(resultSet.getLong(DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN));
            return new Movie(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    oscarsCount,
                    goldenPalm,
                    movieGenre,
                    mpaaRating,
                    director,
                    owner
            );
        } catch (SQLException e){
            return null;
        }
    }

    /**
     * @return List of Movies.
     */
    public CopyOnWriteArrayList<Movie> getCollection() {
        CopyOnWriteArrayList<Movie> moviesCollection = new CopyOnWriteArrayList<>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = databaseHandler.getPreparedStatement(SELECT_ALL_MOVIES, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                moviesCollection.add(createMovie(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectAllStatement);
        }
        return moviesCollection;
    }

    /**
     * @param newMovie Movie raw.
     * @param user     User.
     * @return Movie.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public Movie insertMovie(Movie newMovie, User user) throws DatabaseHandlingException {
        PreparedStatement preparedInsertMovieStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();
            preparedInsertMovieStatement = databaseHandler.getPreparedStatement(INSERT_MOVIE, true);
//            preparedInsertMovieStatement.setLong(1, newMovie.getId());
            preparedInsertMovieStatement.setString(1, newMovie.getName());
            preparedInsertMovieStatement.setString(2, newMovie.getCreationDate().toString());
            preparedInsertMovieStatement.setInt(3, newMovie.getOscarsCount());
            preparedInsertMovieStatement.setLong(4, newMovie.getGoldenPalmCount());
            preparedInsertMovieStatement.setString(5, newMovie.getGenre().toString());
            preparedInsertMovieStatement.setString(6, newMovie.getMpaaRating().toString());
            preparedInsertMovieStatement.setLong(7, newMovie.getCoordinates().getX());
            preparedInsertMovieStatement.setDouble(8, newMovie.getCoordinates().getY());
            preparedInsertMovieStatement.setString(9, newMovie.getOperator().getName());
            preparedInsertMovieStatement.setDouble(10, newMovie.getOperator().getHeight());
            preparedInsertMovieStatement.setString(11, String.valueOf(newMovie.getOperator().getEyeColor()));
            preparedInsertMovieStatement.setString(12, String.valueOf(newMovie.getOperator().getHairColor()));
            preparedInsertMovieStatement.setInt(13, databaseUserManager.getUserIdByUsername(user));
            if (preparedInsertMovieStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedMoviekeys = preparedInsertMovieStatement.getGeneratedKeys();
            Integer movieID;
            if(generatedMoviekeys.next()){
                movieID = generatedMoviekeys.getInt(1);
            } else throw new SQLException();
            databaseHandler.commit();
            newMovie.setOwner(user);
            newMovie.setId(movieID);
            return newMovie;
        } catch (SQLException exception) {
            exception.printStackTrace();
            databaseHandler.rollback();
            throw new DatabaseHandlingException();
        } catch (IncorrectData e) {
            throw new RuntimeException(e);
        } finally {
            databaseHandler.closePreparedStatement(preparedInsertMovieStatement);
            databaseHandler.setNormalMode();
        }
    }

    /**
     * @param newMovie movie raw.
     * @param movieId  Id of Movie.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void updateMovieById(int movieId, Movie newMovie) throws DatabaseHandlingException {
        PreparedStatement preparedUpdateMovieNameByIdStatement = null;
        PreparedStatement preparedUpdateMovieCreationDateByIdStatement = null;
        PreparedStatement preparedUpdateMovieOscarsCountByIdStatement = null;
        PreparedStatement preparedUpdateMovieGoldenPalmByIdStatement = null;
        PreparedStatement preparedUpdateMovieGenreByIdStatement = null;
        PreparedStatement preparedUpdateMovieMpaaRatingByIdStatement = null;
        PreparedStatement preparedUpdateMovieCoordinatesXByIdStatement = null;
        PreparedStatement preparedUpdateMovieCoordinatesYByIdStatement = null;
        PreparedStatement preparedUpdateMovieOperatorNameByIdStatement = null;
        PreparedStatement preparedUpdateMovieOperatorHeightByIdStatement = null;
        PreparedStatement preparedUpdateMovieOperatorEyeColorByIdStatement = null;
        PreparedStatement preparedUpdateMovieOperatorHairColorByIdStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();

            preparedUpdateMovieNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_NAME_BY_ID, false);
            preparedUpdateMovieCreationDateByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_CREATION_DATE_BY_ID, false);
            preparedUpdateMovieOscarsCountByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_OSCARSCOUNT_BY_ID, false);
            preparedUpdateMovieGoldenPalmByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_GOLDENPALM_BY_ID, false);
            preparedUpdateMovieGenreByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_GENRE_BY_ID, false);
            preparedUpdateMovieMpaaRatingByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_MPAA_RATING_BY_ID, false);
            preparedUpdateMovieCoordinatesXByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_COORDINATES_X_BY_ID, false);
            preparedUpdateMovieCoordinatesYByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_COORDINATES_Y_BY_ID, false);
            preparedUpdateMovieOperatorNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_OPERATOR_NAME_COLUMN, false);
            preparedUpdateMovieOperatorHeightByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_OPERATOR_HEIGHT_COLUMN, false);
            preparedUpdateMovieOperatorEyeColorByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_OPERATOR_EYE_COLOR_COLUMN, false);
            preparedUpdateMovieOperatorHairColorByIdStatement = databaseHandler.getPreparedStatement(UPDATE_MOVIE_OPERATOR_HAIR_COLOR_COLUMN, false);

            if (newMovie.getName() != null) {
                preparedUpdateMovieNameByIdStatement.setString(1, newMovie.getName());
                preparedUpdateMovieNameByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieNameByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getCreationDate() != null) {
                preparedUpdateMovieCreationDateByIdStatement.setString(1, newMovie.getCreationDate().toString());
                preparedUpdateMovieCreationDateByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieCreationDateByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getOscarsCount() > 0) {
                preparedUpdateMovieOscarsCountByIdStatement.setInt(1, newMovie.getOscarsCount());
                preparedUpdateMovieOscarsCountByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieOscarsCountByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getGoldenPalmCount() > 0) {
                preparedUpdateMovieGoldenPalmByIdStatement.setLong(1, newMovie.getGoldenPalmCount());
                preparedUpdateMovieOscarsCountByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieOscarsCountByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getGenre() != null) {
                preparedUpdateMovieGenreByIdStatement.setString(1, String.valueOf(newMovie.getGenre()));
                preparedUpdateMovieGenreByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieGenreByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getMpaaRating() != null) {
                preparedUpdateMovieMpaaRatingByIdStatement.setString(1, String.valueOf(newMovie.getMpaaRating()));
                preparedUpdateMovieMpaaRatingByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieMpaaRatingByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newMovie.getCoordinates() != null) {
                preparedUpdateMovieCoordinatesXByIdStatement.setLong(1, newMovie.getCoordinates().getX());
                preparedUpdateMovieCoordinatesXByIdStatement.setInt(2, movieId);
                preparedUpdateMovieCoordinatesYByIdStatement.setDouble(1, newMovie.getCoordinates().getY());
                preparedUpdateMovieCoordinatesYByIdStatement.setInt(2, movieId);
                if (preparedUpdateMovieCoordinatesXByIdStatement.executeUpdate() == 0 || preparedUpdateMovieCoordinatesYByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
            }
            if (newMovie.getOperator() != null) {
                if (newMovie.getOperator().getName() != null) {
                    preparedUpdateMovieOperatorNameByIdStatement.setString(1, newMovie.getOperator().getName());
                    preparedUpdateMovieOperatorNameByIdStatement.setInt(2, movieId);
                    if (preparedUpdateMovieOperatorNameByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if (newMovie.getOperator().getHeight() > 0){
                    preparedUpdateMovieOperatorHeightByIdStatement.setDouble(1, newMovie.getOperator().getHeight());
                    preparedUpdateMovieOperatorHeightByIdStatement.setInt(2, movieId);
                    if(preparedUpdateMovieOperatorHeightByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if(newMovie.getOperator().getEyeColor() != null){
                    preparedUpdateMovieOperatorEyeColorByIdStatement.setString(1, String.valueOf(newMovie.getOperator().getEyeColor()));
                    preparedUpdateMovieOperatorEyeColorByIdStatement.setInt(2, movieId);
                    if(preparedUpdateMovieOperatorEyeColorByIdStatement.executeUpdate() == 0) throw new SQLException();
                }
                if(newMovie.getOperator().getHairColor() != null){
                    preparedUpdateMovieOperatorHairColorByIdStatement.setString(1,String.valueOf(newMovie.getOperator().getHairColor()));
                    preparedUpdateMovieOperatorHairColorByIdStatement.setInt(2,movieId);
                    if(preparedUpdateMovieOperatorHairColorByIdStatement.executeUpdate() == 0) throw new SQLException();
                }

            }
            databaseHandler.commit();
        } catch (SQLException exception) {
            databaseHandler.rollback();
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedUpdateMovieNameByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieCreationDateByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieOscarsCountByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieGoldenPalmByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieGenreByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieMpaaRatingByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieCoordinatesXByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieCoordinatesYByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieOperatorNameByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieOperatorHeightByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieOperatorEyeColorByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdateMovieOperatorHairColorByIdStatement);
            databaseHandler.setNormalMode();
        }
    }

    /**
     * Delete Movie by id.
     *
     * @param movieId Id of Movie.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void deleteMovieById(int movieId) throws DatabaseHandlingException {
        PreparedStatement preparedDeleteMovieByIdStatement = null;
        try {
            preparedDeleteMovieByIdStatement = databaseHandler.getPreparedStatement(DELETE_MOVIE_BY_ID, false);
            preparedDeleteMovieByIdStatement.setInt(1, movieId);
            if (preparedDeleteMovieByIdStatement.executeUpdate() == 0) throw new DatabaseHandlingException();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedDeleteMovieByIdStatement);
        }
    }
    private String[] createTableMovie(ResultSet resultSet) {
        String[] oneRow = new String[14];
        try {
            oneRow[0] = ((Integer) resultSet.getInt(DataBaseHandler.MOVIE_TABLE_ID_COLUMN)).toString();
            oneRow[1] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_NAME_COLUMN);
            oneRow[2] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_CREATION_DATE_COLUMN);
            oneRow[3] = ((Integer) resultSet.getInt(DataBaseHandler.MOVIE_TABLE_OSCARSCOUNT_COLUMN)).toString();
            oneRow[4]=  ((Long) resultSet.getLong(DataBaseHandler.MOVIE_TABLE_GOLDENPALM_COLUMN)).toString();
            oneRow[5] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_GENRE_COLUMN);
            oneRow[6] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_MPAA_RATING_COLUMN);
            oneRow[7] = ((Long) resultSet.getLong(DataBaseHandler.MOVIE_TABLE_COORDINATES_X_COLUMN)).toString();
            oneRow[8] = ((Double) resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_COORDINATES_Y_COLUMN)).toString();
            oneRow[9] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_OPERATOR_NAME_COLUMN);
            oneRow[10] = ((Double) resultSet.getDouble(DataBaseHandler.MOVIE_TABLE_OPERATOR_HEIGHT_COLUMN)).toString();
            oneRow[11] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_OPERATOR_EYE_COLOR_COLUMN);
            oneRow[12] = resultSet.getString(DataBaseHandler.MOVIE_TABLE_OPERATOR_HAIR_COLOR_COLUMN);
            oneRow[13] = databaseUserManager.getUserById(resultSet.getInt(DataBaseHandler.MOVIE_TABLE_USER_ID_COLUMN)).getUsername();
            System.out.println(Arrays.toString(oneRow));
            return oneRow;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public LinkedList<String[]> getTable() {
        LinkedList<String[]> moviesTable = new LinkedList<>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = databaseHandler.getPreparedStatement(SELECT_ALL_MOVIES, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                moviesTable.add(createTableMovie(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectAllStatement);
        }
        return moviesTable;
    }

    /**
     * Checks Movie user id.
     *
     * @param movieId Id of Movie.
     * @param user     Owner of marine.
     * @return Is everything ok.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public boolean checkMovieUserId(int movieId, User user) throws DatabaseHandlingException {
        PreparedStatement preparedSelectMovieByIdAndUserIdStatement = null;
        try {
            preparedSelectMovieByIdAndUserIdStatement = databaseHandler.getPreparedStatement(SELECT_MOVIE_BY_ID_AND_USER_ID, false);
            preparedSelectMovieByIdAndUserIdStatement.setInt(1, movieId);
            preparedSelectMovieByIdAndUserIdStatement.setInt(2, databaseUserManager.getUserIdByUsername(user));
            ResultSet resultSet = preparedSelectMovieByIdAndUserIdStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectMovieByIdAndUserIdStatement);
        }
    }

    /**
     * Clear the collection.
     *
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void clearCollection() throws DatabaseHandlingException {
        CopyOnWriteArrayList<Movie> moviesCollection = getCollection();
        for (Movie movie : moviesCollection) {
            deleteMovieById(movie.getId());
        }
    }

    public DataBaseUserManager getDatabaseUserManager() {
        return databaseUserManager;
    }
}
