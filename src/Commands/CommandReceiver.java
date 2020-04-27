package Commands;

import java.io.*;

/**
 * Ресивер(получатель), отправляет серилизованные объекты на сервер.
 */
public class CommandReceiver {
    public static void info() throws IOException {
        System.out.println("INFO");
    }

    public static void show() {
        //CollectionManager.show();
    }

    public static void add() {
        /*CollectionManager.add(ElementCreator.createStudyGroup());
        System.out.println("Элемент добавлен в коллекцию."); */
    }

    /**
     *
     * @param ID - апдейт элемента по ID.
     */
    public static void update(String ID) {
        /*Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) { CollectionManager.update(ElementCreator.createStudyGroup(), groupId); }
            else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        } */
    }

    /**
     *
     * @param ID - удаление по ID.
     */
    public static void removeById(String ID) {
        /*Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) {
                CollectionManager.remove_by_id(groupId);
                System.out.println("Элемент с ID " + groupId + " успешно удален из коллекции.");
            } else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }*/
    }

    public static void clear() {
        /*CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");*/
    }

    public static void head() {
       // CollectionManager.head();
    }

    public static void remove_greater() {
        //CollectionManager.remove_greater(ElementCreator.createStudyGroup());
    }

    public static void remove_lower() {
        //CollectionManager.remove_lower(ElementCreator.createStudyGroup());
    }

    public static void min_by_semester_enum() {
        //CollectionManager.min_by_semester_enum();
    }

    public static void maxByGroupAdmin() {
        //CollectionManager.maxByGroupAdmin();
    }

    public static void countByGroupAdmin() {
        //CollectionManager.countByGroupAdmin(ElementCreator.createPerson());
    }
}
