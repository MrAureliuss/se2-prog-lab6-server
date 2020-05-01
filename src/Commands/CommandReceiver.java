package Commands;

import BasicClasses.StudyGroup;
import Collection.CollectionManager;
import Collection.CollectionUtils;
import Commands.ConcreteCommands.Add;
import Commands.ConcreteCommands.Head;
import Commands.ConcreteCommands.Info;
import Commands.ConcreteCommands.Update;
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

        out.writeObject(new SerializedArgumentCommand(new Info(), CollectionManager.getInfo()));
        System.out.println("INFO");
    }

    public void show() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedArgumentCommand(new Info(), CollectionManager.show()));
        System.out.println("SHOW");
    }

    public void add(Object o) throws IOException {
        StudyGroup studyGroup = (StudyGroup) o;
        CollectionManager.add(studyGroup);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedArgumentCommand(new Add(), "Элемент добавлен в коллекцию."));
        System.out.println("ADD");
    }

    /**
     *
     * @param ID - апдейт элемента по ID.
     */
    public void update(String ID, StudyGroup studyGroup) throws IOException {
        Integer groupId;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) {
                CollectionManager.update(studyGroup, groupId);
                out.writeObject(new SerializedArgumentCommand(new Update(), "Команда update выполнена."));
            }
            else {out.writeObject(new SerializedArgumentCommand(new Update(), "Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedArgumentCommand(new Update(), "Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        System.out.println("UPDATE");
    }

    /**
     *
     * @param ID - удаление по ID.
     */
    public void removeById(String ID) throws IOException {
        Integer groupId;
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) {
                CollectionManager.removeById(groupId);
                out.writeObject(new SerializedArgumentCommand(new Add(), "Элемент с ID " + groupId + " успешно удален из коллекции."));
            } else { out.writeObject(new SerializedArgumentCommand(new Add(), "Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedArgumentCommand(new Add(), "Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        System.out.println("REMOVE_BY_ID");
    }

    public void clear() {
        /*CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");*/

        System.out.println("CLEAR");
    }

    public void head() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedArgumentCommand(new Head(), CollectionManager.head()));
        System.out.println("HEAD");
    }

    public void removeGreater() {
        //CollectionManager.remove_greater(ElementCreator.createStudyGroup());

        System.out.println("REMOVE_GREATER");
    }

    public void removeLower() {
        //CollectionManager.remove_lower(ElementCreator.createStudyGroup());

        System.out.println("REMOVE_LOWER");
    }

    public void minBySemesterEnum() {
        //CollectionManager.min_by_semester_enum();

        System.out.println("MIN_BY_SEMESTER_ENUM");
    }

    public  void maxByGroupAdmin() {
        //CollectionManager.maxByGroupAdmin();

        System.out.println("MAX_BY_GROUP_ADMIN");
    }

    public void countByGroupAdmin() {
        //CollectionManager.countByGroupAdmin(ElementCreator.createPerson());

        System.out.println("COUNT_BY_GROUP_ADMIN");
    }
}
