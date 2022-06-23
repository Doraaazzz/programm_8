package com.example.programm_8.Utility;

import com.example.programm_8.Data.*;
import com.example.programm_8.exceptions.IncorrectData;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class FileManager {
    /**
     * остальные поля нужны для сборки коллекции из данных, полученных с файла
     */
    private static LinkedList<Movie> MoviesCollection;
    private static final String pathFolder = System.getProperty("user.dir"); // путь до папки проекта
    private static String fileName = "Collection.json";


    /**
     * Метод читает коллекцию из файла
     * @return Возвращает готовую коллекцию типа LinkedList<Movie>
     * @throws IOException
     */
    public static LinkedList<Movie> readCollection() {
        while (true) {
            try {
                FileInputStream fileInputStream = new FileInputStream(pathFolder + "/" + fileName);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                BufferedReader reader = new BufferedReader(new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8));
                String line = reader.readLine(); //первая "{"

                while (reader.ready()) {
                    line = reader.readLine();
                    Parser.parseJSON(line);
                }
                if (MoviesCollection==null){
                    throw new Exception();
                }
                return MoviesCollection;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Загружаемая коллекция пуста... =/");
                return new LinkedList<>();
            } catch (IOException e) {
                System.out.println("Некорректное имя файла, загружена пустая коллекция.");
                return new LinkedList<>();
            } catch (Exception e) {
                System.out.println("Ваш файлик не json...");
                return new LinkedList<>();
            }
        }
    }


    /**
     * Метод сохраняет имеющуюся коллекцию в файл
     *
     * @param collection принимает коллекцию, которую нужно сохранить
     * @throws IOException
     */
    public static void saveCollection(LinkedList<Movie> collection) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathFolder + "/" + fileName);
        String data = Parser.parseToJSON(collection);
        byte[] buffer = data.getBytes();
        fos.write(buffer);
    }

    /**
     * Метод устанавливает имя файла
     * @param name имя файла
     */
    public static void setFileName(String name) {
        fileName = name;
    }

    /**
     * Метод открывает скрипт
     * @param filename имя файла со скриптом
     * @return Возвращает объект типа Scanner
     * @throws IOException
     */
    public static Scanner scriptOpen(String filename) throws IOException {
        return new Scanner(Paths.get(pathFolder + "/" + filename)).useDelimiter(System.getProperty("line.separator"));
    }

    private static class Parser {

        private static Movie movie;
        private static Integer movieid;
        private static String moviename;
        private static Long coordinates_x;
        private static Double coordinates_y;
        private static Coordinates coordinates;
        private static ZonedDateTime creationDate;
        private static Integer movieOscarsCount;
        private static Long movieGoldenPalmCount;
        private static MovieGenre movieGenre;
        private static MpaaRating movieMpaaRating;
        private static String operatorName;
        private static Double operatorheight;
        private static Color operatorEyeColor;
        private static Color operatorHairColor;
        private static Person operator;
        private static Coordinates rawCoordinates;
        private static Person rawOperator;
        private static Movie rawMovie;


        /**
         * Метод разбирает строку на элементы, записывает полученные данные (заполняет поля, создаёт объекты, добавляет объекты в коллекцию)
         * @param line получает на вход строку с данными в формате JSON
         */
        private static void parseJSON(String line) {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter("\\s");
            ArrayList<String> elements = new ArrayList<>();
            while (scanner.hasNext()) {
                String element = scanner.next();
                if (!(element.equals(""))) {
                    elements.add(element);
                }
            }
            int caser = whatNeedToDo(elements);
            switch (caser) {
                case (1): {
                    MoviesCollection = new LinkedList<>();
                    clearAll();
                    break;
                }
                case (2): {
                    if (elements.get(0).equals("{")) {
                        rawMovie = new Movie();
                    } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("coordinates")) {
                        rawCoordinates = new Coordinates();
                    } else if (elements.get(0).substring(1, elements.get(0).length() - 2).equals("operator")) {
                        rawOperator = new Person();
                    }
                    break;
                }
                case (3): {
                    loadObject();
                    break;
                }
                case (4): {
                    String argument = elements.get(0).substring(1, elements.get(0).length() - 2);
                    String data = elements.get(1).substring(1, elements.get(1).length() - 1);
                    if (argument.equals("id")) {
                        try {
                            movieid = Integer.parseInt(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать id из файла, элемент будет null");
                        }
                    } else if (argument.equals("name")) {
                        try {
                            moviename = data;
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать name из файла, элемент будет null");
                        }
                    } else if (argument.equals("oscarsCount")) {
                        try {
                            if (!(data.equals("null"))) {
                                movieOscarsCount = Integer.valueOf(data);
                            }
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать oscarsCount из файла, элемент будет null");
                        }
                    } else if (argument.equals("goldenPalmCount")) {
                        try {
                            if (!(data.equals("null"))) {
                                movieGoldenPalmCount = Long.valueOf(data);
                            }
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать GoldenPalmCount из файла, элемент будет null");
                        }
                    } else if (argument.equals("genre")) {
                        try {
                            movieGenre = MovieGenre.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать genre из файла, элемент будет null");
                        }
                    } else if (argument.equals(("mpaaRating"))) {
                        try {
                            movieMpaaRating = MpaaRating.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать MpaaRating из файла, элемент будет null");
                        }
                    } else if (argument.equals("coordinates_x")) {
                        try {
                            coordinates_x = Long.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать coordinates_x из файла, элемент будет null");
                        }
                    } else if (argument.equals("coordinates_y")) {
                        try {
                            coordinates_y = Double.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать coordinates_y из файла, элемент будет null");
                        }
                    } else if (argument.equals("operator_name")) {
                        try {
                            operatorName = data;
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать operatorName из файла, элемент будет null");
                        }
                    } else if (argument.equals("operator_height")) {
                        try {
                            operatorheight = Double.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать operatorHeight из файла, элемент будет null");
                        }
                    } else if (argument.equals("operator_eyeColor")) {
                        try {
                            operatorEyeColor = Color.valueOf(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать operatorEyeColor из файла, элемент будет null");
                        }
                    } else if (argument.equals("operator_hairColor")) {
                        try {
                            if (!(data.equals("null"))) {
                                operatorHairColor = Color.valueOf(data);
                            }
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать operatorHairColor из файла, элемент будет null");
                        }
                    } else if (argument.equals("creationDate")) {
                        try {
                            creationDate = ZonedDateTime.parse(data);
                        } catch (Exception e) {
                            System.out.println("Не удалось прочитать creationDate из файла, элемент будет null");
                        }
                    }
                    break;
                }
                case (5): {
                    loadObject();
                }
            }
        }

        /**
         * Метод проходит по полученным символам из строки, ищет соответствие со спецсимволами, возвращает номер в зависимости от того, что найдёт
         * @param elements получает на вход ArrayList<String>
         * @return Возвращает int значение для cases.
         */
        private static int whatNeedToDo(ArrayList<String> elements) {
            for (String s : elements) {
                if (s.indexOf("[") == 0) {
                    return 1;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("{") == -1)) {
                    return 2;
                }
            }

            for (String s : elements) {
                if (!(s.indexOf(":") == -1)) {
                    return 4;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("},") == -1)) {
                    return 5;
                }
            }
            for (String s : elements) {
                if (!(s.indexOf("]") == -1)) {
                    return 3;
                }
            }

            return 0;
        }

        /**
         * Метод конвертирует коллекцию в текстовый формат JSON
         * @param collection получает на вход коллекцию, которую необходимо сконвертировать
         * @return Возвращает строку String, которая содержит данные коллекции в формате JSON
         */

        private static String parseToJSON(LinkedList<Movie> collection) {
            String data = "";
            data += "{\n\tMovieCollection: [";
            for (Movie m : collection) {
                data += "\n\t\t{\n\t\t\"hashCode\": \"" + m.hashCode() + "\"\n\t\t\"id\": \"" + m.getId() + "\"\n\t\t\"name\": \"" + m.getName() + "\"\n\t\t\"" +
                        "coordinates\": {\n\t\t\t\"coordinates_x\": \"" + m.getCoordinates().getX() + "\"\n\t\t\t\"coordinates_y" +
                        "\": \"" + m.getCoordinates().getY() + "\"\n\t\t\t}\n\t\t\"creationDate\": \"" + m.getCreationDate() + "\"\n" +
                        "\t\t\"oscarsCount\": \"" + m.getOscarsCount() + "\"\n" + "\t\t\"goldenPalmCount\": \"" + m.getGoldenPalmCount() + "\"\n" +
                        "\t\t\"genre\": \"" + m.getGenre() + "\"\n\t\t\"mpaaRating\": \"" + m.getMpaaRating() + "\"\n\t\t\"" +
                        "operator\": {\n\t\t\t\"operator_name\": \"" + m.getOperator().getName() + "\"\n\t\t\t\"operator_height\": \""
                        + m.getOperator().getHeight() + "\"\n\t\t\t\"operator_eyeColor\": \"" + m.getOperator().getEyeColor() + "\"\n" +
                        "\t\t\t\"operator_hairColor\": \"" + m.getOperator().getHairColor() + "\"\n\t\t\t}\n\t\t},";
            }
            data = data.substring(0, data.length() - 1);
            data += "\n\t]\n}";
            return data;
        }

        /**
         * метод очищает все поля, то есть присваивает значение null
         */
        private static void clearAll() {
            movie = null;
            movieid = null;
            moviename = null;
            coordinates_x = null;
            coordinates_y = null;
            coordinates = null;
            creationDate = null;
            movieOscarsCount = null;
            movieGoldenPalmCount = null;
            movieGenre = null;
            movieMpaaRating = null;
            operatorName = null;
            operatorheight = null;
            operatorEyeColor = null;
            operatorHairColor = null;
            operator = null;
            rawCoordinates = null;
            rawOperator = null;
            rawMovie = null;
        }

        /**
         * Метод загружает объект и очищает поля
         */
        private static void loadObject() {
            try {
                rawCoordinates.setX(coordinates_x);
                rawCoordinates.setY(coordinates_y);
                coordinates = rawCoordinates;
            } catch (IncorrectData e) {
                System.out.println("При загрузке объекта Coordinates встретились некорректные данные, объект пропущен.");
            }
            try {
                rawOperator.setName(operatorName);
                rawOperator.setEyeColor(operatorEyeColor);
                rawOperator.setHairColor(operatorHairColor);
                rawOperator.setHeight(operatorheight);
                operator = rawOperator;
            } catch (IncorrectData e) {
                System.out.println("При загрузке объекта Person встретились некорректные данные, объект пропущен.");
            }
            try {
                rawMovie.setId(movieid);
                rawMovie.setName(moviename);
                rawMovie.setGenre(movieGenre);
                rawMovie.setMpaaRating(movieMpaaRating);
                rawMovie.setCreationDate(creationDate);
                rawMovie.setOscarsCount(movieOscarsCount);
                rawMovie.setGoldenPalmCount(movieGoldenPalmCount);
                rawMovie.setCoordinates(coordinates);
                rawMovie.setOperator(operator);
                movie = rawMovie;
                MoviesCollection.add(movie);
            } catch (IncorrectData e) {
                System.out.println("При загрузке объекта Movie встретились некорректные данные, объект пропущен.");
            }
            clearAll();
        }
    }
}
