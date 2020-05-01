package Commands;

import BasicClasses.Person;
import BasicClasses.StudyGroup;
import Collection.CollectionManager;
import Collection.CollectionUtils;
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

        out.writeObject(new SerializedMessage(CollectionManager.getInfo()));
        System.out.println("INFO");
    }

    public void show() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(CollectionManager.show()));
        System.out.println("SHOW");
    }

    public void add(Object o) throws IOException {
        StudyGroup studyGroup = (StudyGroup) o;
        CollectionManager.add(studyGroup);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage("Элемент добавлен в коллекцию."));
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
                out.writeObject(new SerializedMessage("Команда update выполнена."));
            }
            else {out.writeObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
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
                out.writeObject(new SerializedMessage("Элемент с ID " + groupId + " успешно удален из коллекции."));
            } else { out.writeObject(new SerializedMessage("Элемента с таким ID нет в коллекции."));}
        } catch (NumberFormatException e) {
            out.writeObject(new SerializedMessage("Команда не выполнена. Вы ввели некорректный аргумент."));
        }

        System.out.println("REMOVE_BY_ID");
    }

    public void clear() throws IOException {
        CollectionManager.clear();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage("Коллекция успешно очищена."));
        System.out.println("CLEAR");
    }

    public void head() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(CollectionManager.head()));
        System.out.println("HEAD");
    }

    public void removeGreater(StudyGroup studyGroup) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(CollectionManager.removeGreater(studyGroup)));
        System.out.println("REMOVE_GREATER");
    }

    public void removeLower(StudyGroup studyGroup) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(CollectionManager.removeLower(studyGroup)));
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

    public void countByGroupAdmin(Person groupAdmin) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject(new SerializedMessage(CollectionManager.countByGroupAdmin(groupAdmin)));
        System.out.println("COUNT_BY_GROUP_ADMIN");
    }
}
