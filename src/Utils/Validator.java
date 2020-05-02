package Utils;

import BasicClasses.*;

import java.util.Arrays;

public class Validator {
    private static boolean checkExistColor(String toContains) {
        return Arrays.stream(Color.values()).anyMatch((color) -> color.name().equals(toContains));
    }

    private static boolean checkExistCountry(String toContains) {
        return Arrays.stream(Country.values()).anyMatch((country) -> country.name().equals(toContains));
    }

    private static boolean checkExistFormOfEducation(String toContains) {
        return Arrays.stream(FormOfEducation.values()).anyMatch((formOfEducation) -> formOfEducation.name().equals(toContains));
    }

    private static boolean checkExistSemester(String toContains) {
        return Arrays.stream(Semester.values()).anyMatch((semester) -> semester.name().equals(toContains));
    }

    public static boolean validateStudyGroup(StudyGroup studyGroup) {
        return studyGroup.getId() != null &&
            (!studyGroup.getName().equals("") && studyGroup.getName() != null) &&
            studyGroup.getCoordinates().getX() <= 531 &&
            studyGroup.getCoordinates().getY() > -653f &&
            (studyGroup.getStudentsCount() > 0 && studyGroup.getStudentsCount() != null) &&
            (checkExistFormOfEducation(studyGroup.getFormOfEducation().toString()) || studyGroup.getFormOfEducation() == null) &&
            checkExistSemester(studyGroup.getSemesterEnum().toString()) &&
            validatePerson(studyGroup.getGroupAdmin());
    }

    public static boolean validatePerson(Person person) {
        return (!person.getName().equals("") && person.getName() != null) &&
                person.getHeight() > 0 &&
                checkExistColor(person.getEyeColor().toString()) &&
                checkExistColor(person.getHairColor().toString()) &&
                checkExistCountry(person.getNationality().toString());
    }
}
