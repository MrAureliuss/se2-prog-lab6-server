package Collection;

import BasicClasses.*;
import Commands.ConcreteCommands.Info;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Менеджер коллекцией. Описывает логику команд, выполняющих работу с коллекцией.
 */
public class CollectionManager {
    private static LinkedList<StudyGroup> linkedList;
    private static ZonedDateTime creationDate;
    private static List res = new ArrayList();

    public static void initList() {
        if (linkedList == null) { linkedList = new LinkedList<>(); creationDate = ZonedDateTime.now(); }
    }

    public static LinkedList<StudyGroup> getLinkedList() {
        return linkedList;
    }

    public static void add(StudyGroup studyGroup) {
        linkedList.add(studyGroup);
    }

    public static void addJsonObject(StudyGroup studyGroup) {
        studyGroup.setId(IDGenerator.generateID(studyGroup.getId()));
        linkedList.add(studyGroup);
    }

    public static String getInfo() {
        String info = "";
        info += "Тип коллекции – " + linkedList.getClass().getName() + "\n";
        info += "Дата инициализации коллекции – " + creationDate + "\n";
        info += "Количество элементов в коллекции – " + linkedList.size() + "\n";
        info += "_________________________________________________________\n";

        return info;
    }

    public static String show() {
        String info = linkedList.stream().map(CollectionUtils::display).collect(Collectors.joining(", "));
        return info;
    }

    public static void update(StudyGroup groupToUpdate, Integer elementId) {
        linkedList.forEach(studyGroup -> {
            if (studyGroup.getId().equals(elementId)) {
                studyGroup.setName(groupToUpdate.getName());
                studyGroup.setCoordinates(groupToUpdate.getCoordinates());
                studyGroup.setStudentsCount(groupToUpdate.getStudentsCount());
                studyGroup.setFormOfEducation(groupToUpdate.getFormOfEducation());
                studyGroup.setSemesterEnum(groupToUpdate.getSemesterEnum());
                studyGroup.setGroupAdmin(groupToUpdate.getGroupAdmin());
            }
        });
    }

    public static void removeById(Integer groupId) {
        linkedList.forEach(studyGroup -> {
            if (studyGroup.getId().equals(groupId)) { linkedList.remove(studyGroup); }
        });
    }

    public static void clear() {
        linkedList.clear();
    }

    public static String head() {
        String res;
        if (linkedList.size() > 0) { res = CollectionUtils.display(linkedList.getFirst()); }
        else { res = "Коллекция пуста."; }
        return res;
    }

    public static String removeGreater(StudyGroup studyGroup) {
        res.clear();
        linkedList.forEach(listStudyGroup -> {
            if (listStudyGroup.compareTo(studyGroup) > 0) {
                appendToList(listStudyGroup.getId());
                linkedList.remove(listStudyGroup);
            }
        });

        if (res.isEmpty()) return "Таких элементов не найдено";
        return "Из коллекции удалены элементы с ID: " + res.toString().replaceAll("[\\[\\]]", "");
    }

    public static String removeLower(StudyGroup studyGroup) {
        res.clear();
        linkedList.forEach(listStudyGroup -> {
            if (listStudyGroup.compareTo(studyGroup) < 0) {
                appendToList(listStudyGroup.getId());
                linkedList.remove(listStudyGroup);
            }
        });

        if (res.isEmpty()) return "Таких элементов не найдено";
        return "Из коллекции удалены элементы с ID: " + res.toString().replaceAll("[\\[\\]]", "");
    }

    public static void minBySemesterEnum() {
        if (linkedList.size() > 0) {
            CollectionUtils.display(Collections.min(linkedList,
                    Comparator.comparingInt(studyGroup -> studyGroup.getSemesterEnum().getValue())));
        } else { System.out.println("Коллекция пуста."); }
    }

    public static void maxByGroupAdmin() {
        if (linkedList.size() > 0) {
            CollectionUtils.display(Collections.max(linkedList,
                    Comparator.comparingInt(studyGroup -> studyGroup.getGroupAdmin().compareValue())));
        } else { System.out.println("Коллекция пуста."); }
    }

    public static String countByGroupAdmin(Person groupAdmin) {
        return Long.toString(linkedList.stream().filter(studyGroup -> studyGroup.getGroupAdmin().equals(groupAdmin)).count());
    }

    public static void appendToList(Object o){
        res.add(o);
    }
}
