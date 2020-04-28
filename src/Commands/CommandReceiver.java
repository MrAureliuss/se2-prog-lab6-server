package Commands;

import Commands.ConcreteCommands.Info;
import Commands.SerializedCommands.*;

import java.io.*;
import java.net.Socket;

/**
 * Ресивер(получатель), отправляет серилизованные объекты на сервер.
 */
public class CommandReceiver {
    private final Socket socket;

    public CommandReceiver(Socket socket) {
        this.socket = socket;
    }

    public void info() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println(socket);
        out.writeObject(new SerializedArgumentCommand(new Info(), "Cock\nABC\nrrrrr"));
        System.out.println("INFO");
    }

    public void show() {
        //CollectionManager.show();
    }

    public void add() {
        /*CollectionManager.add(ElementCreator.createStudyGroup());
        System.out.println("Элемент добавлен в коллекцию."); */
    }

    /**
     *
     * @param ID - апдейт элемента по ID.
     */
    public void update(String ID) {
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
    public void removeById(String ID) {
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

    public void clear() {
        /*CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");*/
    }

    public void head() {
       // CollectionManager.head();
    }

    public void removeGreater() {
        //CollectionManager.remove_greater(ElementCreator.createStudyGroup());
    }

    public void removeLower() {
        //CollectionManager.remove_lower(ElementCreator.createStudyGroup());
    }

    public void minBySemesterEnum() {
        //CollectionManager.min_by_semester_enum();
    }

    public  void maxByGroupAdmin() {
        //CollectionManager.maxByGroupAdmin();
    }

    public void countByGroupAdmin() {
        //CollectionManager.countByGroupAdmin(ElementCreator.createPerson());
    }
}
