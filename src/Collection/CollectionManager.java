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
    private LinkedList<StudyGroup> linkedList;
    private ZonedDateTime creationDate;
    private List res = new ArrayList();
    private static CollectionManager collectionManager;

    private CollectionManager() {}

    public static CollectionManager getCollectionManager() {
        if (collectionManager == null){
            collectionManager = new CollectionManager();
        }
        return collectionManager;
    }

    public void initList() {
        if (linkedList == null) { linkedList = new LinkedList<>(); creationDate = ZonedDateTime.now(); }
    }

    public LinkedList<StudyGroup> getLinkedList() {
        return linkedList;
    }

    public void add(StudyGroup studyGroup) {
        linkedList.add(studyGroup);
    }

    public void addJsonObject(StudyGroup studyGroup) {
        studyGroup.setId(IDGenerator.generateID(studyGroup.getId()));
        linkedList.add(studyGroup);
    }

    public String getInfo() {
        String info = "";
        info += "Тип коллекции – " + linkedList.getClass().getName() + "\n";
        info += "Дата инициализации коллекции – " + creationDate + "\n";
        info += "Количество элементов в коллекции – " + linkedList.size() + "\n";
        info += "_________________________________________________________\n";

        return info;
    }

    public String show() {
        linkedList.sort(Comparator.comparing(StudyGroup::getName));  // Сортировка коллекции по алфавиту.

        String info = linkedList
                .stream()
                .map(CollectionUtils::display)
                .collect(Collectors.joining(", ")    );
        if (info.equals("")) { info = "На данный момент коллекция пуста."; }
        return info;
    }

    public void update(StudyGroup groupToUpdate, Integer elementId) {
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

    public void removeById(Integer groupId) {
        linkedList.forEach(studyGroup -> {
            if (studyGroup.getId().equals(groupId)) { linkedList.remove(studyGroup); }
        });
    }

    public void clear() {
        linkedList.clear();
    }

    public String head() {
        if (linkedList.size() > 0) { return CollectionUtils.display(linkedList.getFirst()); }
        else { return  "Коллекция пуста."; }
    }

    public String removeGreater(StudyGroup studyGroup) {
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

    public String removeLower(StudyGroup studyGroup) {
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

    public String minBySemesterEnum() {
        if (linkedList.size() > 0) {
            return CollectionUtils.display(Collections.min(linkedList,
                    Comparator.comparingInt(studyGroup -> studyGroup.getSemesterEnum().getValue())));
        } else { return "Коллекция пуста."; }
    }

    public String maxByGroupAdmin() {
        if (linkedList.size() > 0) {
            return CollectionUtils.display(Collections.max(linkedList,
                    Comparator.comparingInt(studyGroup -> studyGroup.getGroupAdmin().compareValue())));
        } else { return "Коллекция пуста."; }
    }

    public String countByGroupAdmin(Person groupAdmin) {
        return Long.toString(linkedList.stream().filter(studyGroup -> studyGroup.getGroupAdmin().equals(groupAdmin)).count());
    }

    public void appendToList(Object o){
        res.add(o);
    }
}
