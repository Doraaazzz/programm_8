package com.example.programm_8.Utility;

import com.example.programm_8.Data.Movie;
import com.example.programm_8.Data.User;
import com.example.programm_8.exceptions.DatabaseAuthorizationException;
import com.example.programm_8.exceptions.DatabaseHandlingException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * класс, объект которого хранит в себе коллекцию и управляет ей.
 */
public class CollectionManager {

    private DataBaseCollectionManager dataBaseCollectionManager;

    /**
     * поле коллекция фильмов
     */
    private CopyOnWriteArrayList<Movie> MoviesCollection = new CopyOnWriteArrayList<>();
    /**
     * поле тип
     */
    private final String type;
    /**
     * поле время инициализации
     */
    private final LocalDateTime initTime;

    /**
     * Конструктор класса. Загружает коллекцию из файла.
     * Записывает время инициализации коллекции и её тип.
     *
     * @throws IOException
     */
    public CollectionManager(DataBaseCollectionManager dataBaseCollectionManager) {
        this.dataBaseCollectionManager = dataBaseCollectionManager;
        loadCollection();
        type = "CopyOnWriteArrayList";
        initTime = LocalDateTime.now();
    }

    /**
     * Метод обращается к Файл-Менеджеру, тот читает коллекцию и передаёт её методу, метод записывает полученную коллекцию в своё поле.
     *
     * @throws IOException
     * @see FileManager#readCollection()
     */
    public void loadCollection() {
        MoviesCollection = dataBaseCollectionManager.getCollection();
        sortCollection();
    }

    /**
     * Метод выполняет замену элемента с указанным id на новый (с тем же id)
     *
     * @param newMovie объект Movie, который необходимо добавить в коллекцию на замену старому.
     * @param id       id элемента, который нужно заменить
     */
    public String replaceElementByID(Movie newMovie, Integer id) {
        if (checkMatchingID(id)) {
            if (newMovie.getOwner().getUsername().equals(findElementByID(id).getOwner().getUsername())) {
                try {
                    dataBaseCollectionManager.updateMovieById(id, newMovie);
                    removeElement(findElementByID(id));
                    MoviesCollection.add(newMovie);
                    sortCollection();
                    return "Элемент обновлён.";
                } catch (DatabaseHandlingException exception) {
                    exception.printStackTrace();
                    return "Произошла непредвиденная ошибка при обновлении элемента.";
                }
            } else {
                return "Элемент, который вы хотите редактировать, не принадлежит вам.";
            }
        } else {
            return "Отсутствует элемент для обновления.";
        }
    }

    /**
     * Метод добавляет объект в коллекцию, предварительно устанавливая ему id минимальный из тех, что не будет повторён.
     *
     * @param movie полуает на вход объект типа Movie, который необходимо добавить
     */
    public String addElement(Movie movie) {
        String answer = "";
        try {
            MoviesCollection.add(dataBaseCollectionManager.insertMovie(movie, movie.getOwner()));
            sortCollection();
            answer += "Элемент добавлен.";
        } catch (DatabaseHandlingException e) {
            e.printStackTrace();
            answer += "Не удалось добавить элемент.";
        }
        return answer;
    }

    /**
     * Метод удаляет объект из коллекции.
     *
     * @param movie объект, который нужно удалить из коллекции.
     */
    public void removeElement(Movie movie) {
        MoviesCollection.remove(movie);
        sortCollection();
    }

    /**
     * Метод удаляет элемент из коллекции по указанному ID
     *
     * @param id ID объекта, который нужно удалить
     */
    public String removeElementByID(Integer id, User user) {
        if (!(findElementByID(id) == null)) {
            if (findElementByID(id).getOwner().getUsername().equals(user.getUsername())) {
                try {
                    dataBaseCollectionManager.deleteMovieById(id);
                    removeElement(findElementByID(id));
                    sortCollection();
                } catch (DatabaseHandlingException e) {
                    e.printStackTrace();
                    return "Произошла ошибка при удалении элемента.";
                }
            } else {
                return "Введённый элемент не может быть удалён, т.к. был создан не вами.";
            }
            return "Элемент удалён.";
        } else {
            return "Элемента с таким id нет в коллекции.";
        }
    }

    /**
     * Метод перебирает элементы коллекции и ищет соответствие по ID
     *
     * @param id ID элемента, который нужно найти
     * @return Возвращает объект типа Movie, которому соответствует искомый ID. Если элемент не найден, вернёт null
     */
    public Movie findElementByID(int id) {
        return MoviesCollection.stream().filter((m) -> m.getId().equals(id)).findAny().get();
    }

    /**
     * @return геттер на коллекцию
     */
    public CopyOnWriteArrayList<Movie> getMoviesCollection() {
        return MoviesCollection;
    }

    /**
     * Метод удаляет все элементы в коллекции
     */
    public String removeAllElements(User user) {
        LinkedList<Movie> listForRemove = new LinkedList<>();
        MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername())).forEach(listForRemove::add);
        MoviesCollection.removeAll(listForRemove);
        return "Удалено " + listForRemove.size() + " элементов.";
    }

//    /**
//     * Метод генерирует имя файла для сохранения, передаёт его и имеющуюся коллекцию Файл-Менеджеру
//     *
//     * @throws IOException
//     * @see FileManager#saveCollection(LinkedList)
//     */
//    public String saveCollection() {
//        try {
//            FileManager.saveCollection(MoviesCollection);
//            return "Коллекция сохранена";
//        } catch (IOException e) {
//            return "Ошибка сохранения коллекции.";
//        }
//    }

    /**
     * Метод выводит по-очерёдно строковое представление объектов коллекции.
     */
    public String printAscendingCollection() {
        final String[] answer = {""};
        sortCollectionByComp(hashcodeComparator);
        MoviesCollection.forEach((M) -> answer[0] += M.toString() + "\n");
        return answer[0];
    }

    /**
     * Метод выводит информацию о коллекции: тип, дата инициализации, количество элементов и выполняет перебор всех элементов с выводом их номера ID
     */
    public String printInfo() {
        final String[] answer = {"Тип коллекции: " + type + ". \n" + "Дата инициализации: " + initTime + ". \n" + "Количество элементов: "
                + MoviesCollection.size() + ". \n" + "Элементы коллекции по хэшкодам: \n"};
        sortCollectionByComp(hashcodeComparator);
        MoviesCollection.stream().forEach((M) -> answer[0] += M.hashCode() + " ");
        answer[0] += "\n";
        sortCollection();
        return answer[0];
    }

    /**
     * Метод выполняет проверку, имеется ли объект с заданным ID в коллекции
     *
     * @param id заданный ID
     * @return Возвращает True, если объект найден, и False, если нет.
     */
    public boolean checkMatchingID(Integer id) {
        if (!(MoviesCollection.isEmpty())) {
            return MoviesCollection.stream().anyMatch((m) -> m.getId().equals(id));
        }
        return false;
    }

    /**
     * Метод сравнивает два объекта по хешкоду
     */
    public static Comparator<Movie> hashcodeComparator = new Comparator<Movie>() {

        @Override
        public int compare(Movie c1, Movie c2) {
            return c1.hashCode() - c2.hashCode();
        }
    };

    /**
     * Метод получения минимального нового id
     *
     * @return новое значение id
     */
//    public Integer getNewID() {
//        sortCollection();
//        int id = 1;
//        while (id > 0) {
//            if (!checkMatchingID(id)) {
//                break;
//            } else {
//                id += 1;
//            }
//        }
//        return id;
//    }

    /**
     * Метод получения первого элемента коллекции, если она не пуста
     *
     * @return возвращает первый элемент коллекции  или null
     */
    public Movie getHead() {
        sortCollection();
        return MoviesCollection.get(0);
    }

    /**
     * Метод выводит MPAA RATINGS элементов коллекции в порядке убывания
     */
    public String printDescendingMpaaRatings() {
        final String[] answer = {""};
        sortCollectionByComp(MpaaRatingsComparator);
        answer[0] += "Вывожу MPAA RATINGS элементов коллекции в порядке убывания: \n";
        MoviesCollection.stream().forEach((Movie) -> answer[0] += Movie.getMpaaRating() + "\n");
        sortCollection();
        return answer[0];
    }


    public static Comparator<Movie> MpaaRatingsComparator = new Comparator<Movie>() {

        /**
         * Метод сравнивает объекты по значению возрастного рейтинга
         * @return разность значений
         */
        @Override
        public int compare(Movie m1, Movie m2) {
            return m2.getMpaaRating().ordinal() - m1.getMpaaRating().ordinal();
        }
    };

    /**
     * метод удаляет элементы коллекции, значение поля goldenpalmcount которых равно переданному аргументу
     *
     * @param goldenpalmcount число goldenpalmcount, элементы имеющие поля с таким значением должны быть удалены
     */
    public String removeAllByGoldenPalmCount(long goldenpalmcount, User user) {
        LinkedList<Movie> listForRemove = new LinkedList<>();
        MoviesCollection.stream().filter((Movie) -> Movie.getOwner().getUsername().equals(user.getUsername()) && Movie.getGoldenPalmCount().equals(goldenpalmcount)).forEach(listForRemove::add);
        try {
            for (Movie movie : listForRemove) {
                dataBaseCollectionManager.deleteMovieById(movie.getId());
                removeElement(movie);
            }
            return "Команда выполнена. Удалено " + listForRemove.size() + " элементов.";
        } catch (DatabaseHandlingException e) {
            e.printStackTrace();
            return "Ошибка при удалении элементов.";
        }
    }

    /**
     * метод удаляет первый элемент колеекции
     */
    public String removeFirst(User user) {
        if (!(getHead() == null)) {
            if (getHead().getOwner().getUsername().equals(user.getUsername())) {
                try {
                    dataBaseCollectionManager.deleteMovieById(getHead().getId());
                    removeElement(getHead());
                } catch (DatabaseHandlingException e) {
                    e.printStackTrace();
                    return "Не удалось удалить первый элемент из-за непредвиденной ошибки.";
                }
            } else {
                return "Не удалось удалить первыый элемент, т.к. он создан не вами.";
            }
            return "Элемент удалён.";
        } else {
            return "Коллекция пуста.";
        }
    }

    /**
     * метод выводит строковое представление элементов коллекции
     */
    public String printCollection() {
        if (MoviesCollection.isEmpty()) {
            return "Коллекция пуста.";
        } else {
            sortCollection();
            final String[] answer = {"Вывожу строковое представление элементов коллекции: \n"};
            MoviesCollection.forEach((m) -> answer[0] += m.toString() + "\n");
            return answer[0];
        }
    }

    public void sortCollection() {
        if (!MoviesCollection.isEmpty()) {
            LinkedList<Movie> listForSort = new LinkedList<>(MoviesCollection);
            MoviesCollection.removeAll(listForSort);
            Collections.sort(listForSort);
            listForSort.forEach(MoviesCollection::add);
        }
    }

    public void sortCollectionByComp(Comparator<Movie> comparator) {
        if (!MoviesCollection.isEmpty()) {
            LinkedList<Movie> listForSort = new LinkedList<>(MoviesCollection);
            MoviesCollection.removeAll(listForSort);
            listForSort.sort(comparator);
            listForSort.forEach(MoviesCollection::add);
        }
    }

    public String auth(User user) throws DatabaseAuthorizationException {
        DataBaseUserManager userManager = dataBaseCollectionManager.getDatabaseUserManager();
        String answer = "";
        try {
            if (user.isSignUp()) {
                try {
                    userManager.getUserByUsername(user.getUsername());
                    throw new DatabaseAuthorizationException("This login is already exists.");
                } catch (SQLException e) {
                    userManager.insertUser(user);
                    return "Registration and authorization succeeded";
                }
            } else {
                if (!userManager.checkUserByUsernameAndPassword(user)) throw new DatabaseHandlingException();
            }
        } catch (DatabaseHandlingException exception) {
            throw new DatabaseAuthorizationException("Authorization declined");
        }
        return "Authorization succeeded";
    }

    public synchronized String getTable() {
        LinkedList<String[]> table = dataBaseCollectionManager.getTable();
        final String[] ans = {""};
        table.forEach(strings -> {
            for (String s : strings) {
                ans[0] += s + "\n";
            }
        });
        return ans[0];
    }

}
