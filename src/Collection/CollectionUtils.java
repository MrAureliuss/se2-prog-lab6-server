package Collection;

import BasicClasses.StudyGroup;

/**
 * Класс, содержащий утилиты для работы с коллекцией.
 */
public class CollectionUtils {
    public static boolean checkExist(Integer ID) {
        for (StudyGroup studyGroup:CollectionManager.getLinkedList()) {
            return studyGroup.getId().equals(ID);
        }
        return false;
    }

    static void display(StudyGroup studyGroup) {
        System.out.println("ID элемента коллекции – " + studyGroup.getId());
        System.out.println("Название группы – " + studyGroup.getName());
        System.out.println("Координата X – " + studyGroup.getCoordinates().getX());
        System.out.println("Координата Y – " + studyGroup.getCoordinates().getY());
        System.out.println("Дата и время создания элемента – " + studyGroup.getCreationDate());
        System.out.println("Количество студентов в группе – " + studyGroup.getStudentsCount());
        System.out.println("Форма обучения – " + studyGroup.getFormOfEducation());
        System.out.println("Номер семестра – " + studyGroup.getSemesterEnum());
        System.out.println("Имя админа группы – " + studyGroup.getGroupAdmin().getName());
        System.out.println("Рост админа группы – " + studyGroup.getGroupAdmin().getHeight());
        System.out.println("Цвет глаз админа группы – " + studyGroup.getGroupAdmin().getEyeColor());
        System.out.println("Цвет волос админа группы – " + studyGroup.getGroupAdmin().getHairColor());
        System.out.println("Национальность админа группы – " + studyGroup.getGroupAdmin().getNationality());
        System.out.println("_________________________________________________________\n");
    }
}
