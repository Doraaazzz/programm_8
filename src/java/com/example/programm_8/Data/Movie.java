package com.example.programm_8.Data;

import com.example.programm_8.exceptions.IncorrectData;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * элемент воллекции
 */
public class Movie implements Comparable<Movie>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле может быть null
    private Long goldenPalmCount; //Значение поля должно быть больше 0, Поле может быть null
    private MovieGenre genre; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person operator; //Поле не может быть null
    private User owner;

    /**
     * конструктор
     *
     * @param id              номер
     * @param name            имя фильма
     * @param coordinates     координаты
     * @param creationDate    дата создания
     * @param oscarsCount     количество оскаров
     * @param goldenPalmCount количество золотых пальм
     * @param genre           жанр
     * @param mpaaRating      возрастной рейтинг
     * @param operator        оператор
     */
    public Movie(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, Integer oscarsCount, Long goldenPalmCount,
                 MovieGenre genre, MpaaRating mpaaRating, Person operator, User user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.goldenPalmCount = goldenPalmCount;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
        this.owner = user;
    }

    /**
     * конструктор без параметров
     */
    public Movie() {
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    /**
     * Метод получения значения поля {@link Movie#id}
     *
     * @return возращает id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Метод получения значения поля {@link Movie#name}
     *
     * @return возращает имя
     */
    public String getName() {
        return name;
    }

    /**
     * Метод получения значения поля {@link Movie#coordinates}
     *
     * @return возращает координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод получения значения поля {@link Movie#creationDate}
     *
     * @return возращает дату создания
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Метод получения значения поля {@link Movie#oscarsCount}
     *
     * @return возращает количество оскаров
     */
    public Integer getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Метод получения значения поля {@link Movie#goldenPalmCount}
     *
     * @return возращает количество золотых пальм
     */
    public Long getGoldenPalmCount() {
        return goldenPalmCount;
    }

    /**
     * Метод получения значения поля {@link Movie#genre}
     *
     * @return возращает жанр
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Метод получения значения поля {@link Movie#mpaaRating}
     *
     * @return возращает возрастной рейтинг
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Метод получения значения поля {@link Movie#operator}
     *
     * @return возращает оператора
     */
    public Person getOperator() {
        return operator;
    }

    /**
     * Метод устанавливает значение поля {@link Movie#id}
     *
     * @param id устанавливает номер
     * @throws IncorrectData
     */
    public void setId(Integer id) throws IncorrectData {

        if (!(id > 0) || id == null) {
            throw new IncorrectData("id <= 0>");
        } else {
            this.id = id;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#name}
     *
     * @param name устанавливает имя
     * @throws IncorrectData
     */
    public void setName(String name) throws IncorrectData {
        if (name.equals("") || name == null) {
            throw new IncorrectData("Incorrect name");
        } else {
            this.name = name;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#coordinates}
     *
     * @param coordinates устанавливает координаты
     * @throws IncorrectData
     */
    public void setCoordinates(Coordinates coordinates) throws IncorrectData {
        if (coordinates == null) {
            throw new IncorrectData("coordinates is null");
        } else {
            this.coordinates = coordinates;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#creationDate}
     *
     * @param creationDate устанавливает дату создания
     * @throws IncorrectData
     */
    public void setCreationDate(ZonedDateTime creationDate) throws IncorrectData {
        if (creationDate == null) {
            throw new IncorrectData("creationDate is null");
        } else {
            this.creationDate = creationDate;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#oscarsCount}
     *
     * @param oscarsCount устанавливает количество оскаров
     * @throws IncorrectData
     */
    public void setOscarsCount(Integer oscarsCount) throws IncorrectData {
        if (!(oscarsCount == null) && !(oscarsCount > 0)) {
            throw new IncorrectData("oscarsCount <= 0");
        } else {
            this.oscarsCount = oscarsCount;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#goldenPalmCount}
     *
     * @param goldenPalmCount устанавливает количество золотых пальм
     * @throws IncorrectData
     */
    public void setGoldenPalmCount(Long goldenPalmCount) throws IncorrectData {
        if (!(goldenPalmCount == null) && !(goldenPalmCount > 0)) {
            throw new IncorrectData("goldenPalmCount <= 0");
        } else {
            this.goldenPalmCount = goldenPalmCount;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#genre}
     *
     * @param genre устанавливает жанр
     * @throws IncorrectData
     */
    public void setGenre(MovieGenre genre) throws IncorrectData {
        if (genre == null) {
            throw new IncorrectData("MovieGenre is null");
        } else {
            this.genre = genre;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#mpaaRating}
     *
     * @param mpaaRating устанавливает возрастной рейтинг
     * @throws IncorrectData
     */
    public void setMpaaRating(MpaaRating mpaaRating) throws IncorrectData {
        if (mpaaRating == null) {
            throw new IncorrectData("Mpaa Rating is null");
        } else {
            this.mpaaRating = mpaaRating;
        }
    }

    /**
     * Метод устанавливает значение поля {@link Movie#operator}
     *
     * @param operator устанавливает оператора
     * @throws IncorrectData
     */
    public void setOperator(Person operator) throws IncorrectData {
        if (operator == null) {
            throw new IncorrectData("Operator is null");
        } else {
            this.operator = operator;
        }
    }


    /**
     * Мето сравнения с объектом
     *
     * @param o объект, с которым сравниваем
     * @return Возвращает значение разности полей id объектов
     */
    @Override
    public int compareTo(Movie o) {
        return this.getId() - o.getId();
    }

    /**
     * Метод строкового представления объекта
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "ID: " + getId() + ". \nНазвание фильма: " + getName() + ". \n" + coordinates.toString() + "Дата создания: " + creationDate +
                ". \nКоличество оскаров: " + getOscarsCount() + ". \nКоличество золотых пальм: " + getGoldenPalmCount() +
                ". \nЖанр фильма: " + getGenre().toString() + ". \nВозрастной рейтинг: " + getMpaaRating().toString() + ". \n" + operator.toString()
                + "\nВладелец объекта: " + getOwner().getUsername() + "\nРазмер объекта: " + hashCode() + "\n";
    }

    /**
     * Метод переопределяющий хешкод объекта
     *
     * @return интовое значение хешкода
     */
    @Override
    public int hashCode() {
        return (int) (coordinates.getX() + coordinates.getY());
    }
}
