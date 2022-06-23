package com.example.programm_8.Utility;

import com.example.programm_8.Data.*;
import com.example.programm_8.exceptions.IncorrectData;

import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * класс, который запрашивает информацию у пользователя и передаёт её программе.
 */
public class Inquiry {

    /**
     * Метод спрашивает id
     * @return возвращает полученное от пользователя значение id
     */
    public static int askID() {
        boolean success = false;
        int id = 0;
        while (!success) {
            try {
                System.out.print("Введите номер id (целое число больше нуля): ");
                Scanner in = new Scanner(System.in);
                id = in.nextInt();
                while (!(id > 0)) {
                    System.out.println("Введено неположительное число. Повторите ввод.");
                    in = new Scanner(System.in);
                    id = in.nextInt();
                }
                success = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return id;
    }

    /**
     * Запрашивает у пользователя имя объекта Movie
     * запрашивает повторный ввод, если пользователь пытается ввести null
     * @return Возвращает имя в формате String
     */
    public static String askMovieName() {
        System.out.print("Введите имя фильма: ");
        Scanner in = new Scanner((System.in));
        String MovieName = in.nextLine();
        while (MovieName == null || MovieName.equals("")) {
            System.out.println("Поле не может быть пустым. Повторите ввод.");
            in = new Scanner((System.in));
            MovieName = in.nextLine();
        }
        return MovieName;
    }

    /**
     * Запрашивает у пользователя команду
     * @return Возвращает введённую пользователем строку
     */
    public static String askCommand() {
        String command = "";
        while (command.equals("")) {
            System.out.print("Введите команду: ");
            Scanner in = new Scanner((System.in));
            command = in.nextLine();
        }
        return command;
    }

    /**
     * Запрашивает у пользователя количество оскаров
     * @return введенное пользователем значение
     */
    public static Integer askOscarsCount() {
        boolean success = false;
        Integer oscarsCount = 0;
        while (!success) {
            try {
                System.out.print("Введите количество оскаров (целое число больше нуля, поле может быть null): ");
                Scanner in = new Scanner(System.in);
                String data = in.nextLine();
                if (data.equals("")) {
                    oscarsCount = null;
                } else {
                    oscarsCount = Integer.parseInt(data);
                    if (!(oscarsCount > 0)) {
                        throw new InputMismatchException("Неположительное число.");
                    }
                }
                success = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return oscarsCount;
    }

    /**
     * Запрашивает у пользователя количество золотых пальм
     * @return введенное пользователем значение
     */
    public static Long askGoldenPalmCount() {
        boolean success = false;
        Long GoldenPalmCount = 0l;
        while (!success) {
            try {
                System.out.print("Введите количество золотых пальм (целое число больше нуля, поле может быть null): ");
                Scanner in = new Scanner(System.in);
                String data = in.nextLine();
                if (data.equals("")) {
                    GoldenPalmCount = null;
                } else {
                    GoldenPalmCount = Long.parseLong(data);
                    if (!(GoldenPalmCount > 0)) {
                        throw new InputMismatchException("Неположительное число.");
                    }
                }
                success = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return GoldenPalmCount;
    }

    /**
     * Выводит жанры и запрашивает у пользователя ввод желаемого варианта.
     * Будет запрашивать повторный ввод, пока не получит корректное слово, соответствующее одному из представленных жанров
     * @return Возвращает выбранный пользователем жанр формата MovieGenre
     */
    public static MovieGenre askGenre() {
        boolean success = false;
        MovieGenre genre = null;
        while (!success) {
            try {
                System.out.println("Возможные жанры: WESTERN, " + "ADVENTURE, " + "TRAGEDY, " + "FANTASY, " + "SCIENCE_FICTION.");
                System.out.print("Введите желаемый жанр в соответствии с возможными: ");
                Scanner in = new Scanner(System.in);
                String answer = in.nextLine();
                if (answer.equals("")) {
                    throw new IllegalArgumentException("genre cant be null");
                } else {
                    genre = MovieGenre.valueOf(answer);
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return genre;
    }

    /**
     * Выводит доступные рейтинги и запрашивает у пользователя ввод желаемого варианта.
     * Будет запрашивать повторный ввод, пока не получит корректное слово, соответствующее одному из представленных рейтингов
     * @return Возвращает выбранный пользователем рейтинг в формате MpaaRating
     */
    public static MpaaRating askRating() {
        boolean success = false;
        MpaaRating mpaaRating = null;
        while (!success) {
            try {
                System.out.println("Возможный возрастной рейтинг: G, PG, PG_13, R, NC_17");
                System.out.print("Введите желаемый рейтинг: ");
                Scanner in = new Scanner(System.in);
                String answer = in.nextLine();
                if (answer.equals("")) {
                    throw new IllegalArgumentException("rating cant be null");
                } else {
                    mpaaRating = MpaaRating.valueOf(answer);
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return mpaaRating;
    }

    /**
     * Запрашивает у пользователя число, большее -901, для координаты X объекта Movie.
     * Запрашивает повторный ввод, пока не будут введены корректные данные
     * @return Возвращает полученное от пользователя число в формате Long
     */
    public static Long askCoordinatesX() {
        boolean success = false;
        Long coordinateX = null;
        while (!success) {
            try {
                System.out.print("Введите координату Х (целое число): ");
                Scanner in = new Scanner(System.in);
                coordinateX = in.nextLong();
                success = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return coordinateX;
    }

    /**
     * Запрашивает у пользователя целое число, большее -312, для координаты Y объекта Movie.
     * Запрашивает повторный ввод, пока не будут введены корректные данные
     * @return Возвращает полученное от пользователя число в формате Double
     */
    public static Double askCoordinatesY() {
        boolean success = false;
        Double coordinateY = null;
        while (!success) {
            try {
                System.out.print("Введите координату Y (дробное число целая часть отделяется через точку): ");
                Scanner in = new Scanner(System.in);
                coordinateY = in.nextDouble();
                success = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return coordinateY;
    }

    /**
     * Запрашивает у пользователя имя оператора.
     * Пока строка пустая, запрашивает повторный ввод.
     * @return Возвращает имя, полученное от пользователя, в формате String
     */
    public static String askOperatorName() {
        System.out.print("Введите имя оператора: ");
        Scanner in = new Scanner(System.in);
        String operatorName = in.nextLine();
        while ((operatorName == null) || (operatorName.equals(""))) {
            System.out.println("Неверные данные. Повторите ввод.");
            in = new Scanner(System.in);
            operatorName = in.nextLine();
        }
        return operatorName;
    }

    /**
     * Запрашивает у пользователя рост режиссёра (положительное число).
     * Запрашивает повторный ввод, пока не получится распознать введённые данные.
     * @return Возвращает полученное у пользователя значение роста в формате double
     */
    public static double askOperatorHeight() {
        boolean success = false;
        double height = 0.0;
        while (!success) {
            try {
                System.out.print("Введите рост оператора (дробное число больше нуля, целую часть разделять через точку): ");
                Scanner in = new Scanner(System.in);
                height = in.nextDouble();
                while (!(height > 0) || height > 250) {
                    System.out.println("Введены фантастические данные. Введите более реальные.");
                    in = new Scanner(System.in);
                    height = in.nextDouble();
                }
                success = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return height;
    }

    /**
     * Запрашивает у пользователя цвет глаз режиссёра.
     * Выводит доступные варианты цвета, запрашивает у пользователя повторный ввод, если не удаётся сопоставить введённые пользователем данные с представленными цветами.
     * @return Возвращает перечисляемый тип Color
     */
    public static Color askOperatorEyeColor() {
        boolean success = false;
        Color eyeColor = null;
        while (!success) {
            try {
                System.out.println("Возможные цвета: GREEN, " + "RED, " + "BLUE, " + "YELLOW, " + "BROWN, " + "ORANGE.");
                System.out.print("Выберите желаемый цвет глаз оператора: ");
                Scanner in = new Scanner(System.in);
                eyeColor = Color.valueOf(in.nextLine());
                while (eyeColor == null) {
                    System.out.println("Не может быть null. Повторите ввод.");
                    in = new Scanner(System.in);
                    eyeColor = Color.valueOf(in.nextLine());
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return eyeColor;
    }

    /**
     * Запрашивает у пользователя цвет волос режиссёра.
     * Выводит доступные варианты цвета, запрашивает у пользователя повторный ввод, если не удаётся сопоставить введённые пользователем данные с представленными цветами.
     * @return Возвращает перечисляемый тип Color или null
     */
    public static Color askOperatorHairColor() {
        boolean success = false;
        Color hairColor = null;
        while (!success) {
            try {
                System.out.println("Возможные цвета: GREEN, RED, BLUE, YELLOW, BROWN, ORANGE. Может быть null (для выбора null введите пустую строку)");
                System.out.print("Выберите желаемый цвет волос оператора: ");
                Scanner in = new Scanner(System.in);
                String s = in.nextLine();
                if (s.equals("")) {
                    hairColor = null;
                } else {
                    hairColor = Color.valueOf(s);
                }
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверные данные, повторите ввод.");
            }
        }
        return hairColor;
    }


    /**
     * Запрашивает у пользователя имя файла со скриптом.
     * @return Возвращает полученную строку, обработка исключений происходит выше.
     */
    public static String askFilenameForExecuteScript() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Метод запрашивает у пользователя данные для элемента коллекции
     * @return возвращает newMovie
     * @throws IncorrectData
     */
    public static Movie askMovie() throws IncorrectData {
        Movie newMovie = new Movie();
        newMovie.setName(askMovieName());
        newMovie.setCoordinates(new Coordinates(askCoordinatesX(), askCoordinatesY()));
        newMovie.setCreationDate(ZonedDateTime.now());
        newMovie.setOscarsCount(askOscarsCount());
        newMovie.setGoldenPalmCount(askGoldenPalmCount());
        newMovie.setGenre(askGenre());
        newMovie.setMpaaRating(askRating());
        Person operator = new Person();
        operator.setName(askOperatorName());
        operator.setHeight(askOperatorHeight());
        operator.setEyeColor(askOperatorEyeColor());
        operator.setHairColor(askOperatorHairColor());
        newMovie.setOperator(operator);
        return newMovie;
    }

    public static int askPort(){
        boolean success = false;
        int port = 0;
        System.out.println("Введите порт подключения.");
        while ((!success)){
            try{
                Scanner scanner = new Scanner(System.in);
                port = Integer.parseInt(scanner.nextLine());
                if(!(port>=1000 && port<30000)){
                    throw new RuntimeException("Неверный порт, повторите ввод.");
                }
                success=true;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return port;
    }
}

