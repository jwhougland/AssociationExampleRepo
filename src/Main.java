import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main class for the association example console app.
 */
public class Main {
    /**
     * Entry point for the association example console app.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        // Create an atomic integer that can be passed to helper methods to
        // ensure we create unique IDs for students and professors
        AtomicInteger id = new AtomicInteger(1);

        // Create a list of students with a variety of majors/minors
        List<Student> students = createStudents(id);

        // Create a list of professors with a variety of teaching/research disciplines
        List<Professor> professors = createProfessors(id);

        // Create student-professor associations based on common disciplines
        createStudentProfessorAssociations(students, professors);
    }

    /**
     * Returns a collection of students with a variety of majors/minors.
     *
     * @param id University member ID that we should 'increment and get' for assigning a unique ID to a student
     */
    public static List<Student> createStudents(AtomicInteger id) {

        // Create a student majoring in computer science and minoring in math
        Student compSciMathStudent1 = new Student(
                "Beth Smith",
                id.get(),
                new ArrayList<>(List.of(Enums.Discipline.COMPUTER_SCIENCE)),
                new ArrayList<>(List.of(Enums.Discipline.MATHEMATICS)));

        // Create a student majoring in electrical engineering and minoring in physics and math
        Student eePhysicsMathStudent1 = new Student(
                "Ben Jones",
                id.incrementAndGet(),
                new ArrayList<>(List.of(Enums.Discipline.ELECTRICAL_ENGINEERING)),
                new ArrayList<>(List.of(Enums.Discipline.PHYSICS, Enums.Discipline.MATHEMATICS)));

        // Create a student majoring in business administration
        Student businessStudent1 = new Student(
                "Sarah Simpson",
                id.incrementAndGet(),
                new ArrayList<>(List.of(Enums.Discipline.BUSINESS_ADMINISTRATION)),
                null);

        // Return a list of students
        return new ArrayList<>(List.of(compSciMathStudent1, eePhysicsMathStudent1, businessStudent1));
    }

    /**
     * Returns a collection of professors with a variety of teaching/research disciplines
     *
     * @param id University member ID that we should 'increment and get' for assigning a unique ID to a professor
     */
    public static List<Professor> createProfessors(AtomicInteger id) {

        // Create a professor who teaches math
        Professor mathProfessor1 = new Professor(
                "Tom Reynolds",
                id.get(),
                new ArrayList<>(List.of(Enums.Discipline.MATHEMATICS)),
                Enums.ProfessorJobRole.ASSISTANT_PROFESSOR);

        // Create a professor who teaches computer science
        Professor compSciProfessor1 = new Professor(
                "Shelly Robertson",
                id.incrementAndGet(),
                new ArrayList<>(List.of(Enums.Discipline.COMPUTER_SCIENCE)),
                Enums.ProfessorJobRole.PROFESSOR);

        // Create a professor who teaches electrical engineering
        Professor eeProfessor1 = new Professor(
                "Leo Miller",
                id.incrementAndGet(),
                new ArrayList<>(List.of(Enums.Discipline.ELECTRICAL_ENGINEERING)),
                Enums.ProfessorJobRole.ASSOCIATE_PROFESSOR);

        // Create a professor who teaches physics and researches computer sciences
        Professor physicsCompSciProfessor1 = new Professor(
                "Randy Gibbons",
                id.incrementAndGet(),
                new ArrayList<>(List.of(Enums.Discipline.PHYSICS, Enums.Discipline.COMPUTER_SCIENCE)),
                Enums.ProfessorJobRole.PROFESSOR);

        // Create a professor who teaches business administrations
        Professor businessProfessor1 = new Professor(
                "Shannon Meadows",
                id.incrementAndGet(),
                new ArrayList<>(List.of(Enums.Discipline.BUSINESS_ADMINISTRATION)),
                Enums.ProfessorJobRole.PROFESSOR);

        // Return a list of professors
        return new ArrayList<>(List.of(compSciProfessor1, eeProfessor1, physicsCompSciProfessor1, businessProfessor1));
    }

    /**
     * Finds commonalities between student majors/minors and professor teaching/research disciplines.
     * When commonalities are found a student's collection of associated professors will be updated.
     * Likewise, in the case of a commonality a professor's collection of associated students will be updated.
     *
     * @param students    Collection of students with known majors/minors
     * @param professors  Collection of professors with known teaching/research disciplines
     */
    public static void createStudentProfessorAssociations(List<Student> students, List<Professor> professors) {

        // Create a set that will contain a distinct list of disciplines spanning the
        // majors/minors of students and the teaching/research disciplines of professors
        Set<Enums.Discipline> disciplines = findDistinctDisciplines(students, professors);

        // Iterate over the disciplines
        for (Enums.Discipline discipline : disciplines) {

            // Query for the students who have a major or a minor matching the current discipline
            List<Student> matchingStudents = students
                    .stream()
                    .filter(s -> s.getMajors().contains(discipline) || s.getMinors().contains(discipline))
                    .toList();

            // Query for the professors who have a teaching/research discipline matching the current discipline
            List<Professor> matchingProfessors = professors
                    .stream()
                    .filter(p -> p.getDisciplines().contains(discipline))
                    .toList();

            // For each student returned in the query, update its list of associated professors
            matchingStudents.forEach(ms -> ms.addProfessorAssociations(matchingProfessors));

            // For each professor returned in the query, update its list of associated students
            matchingProfessors.forEach(mp -> mp.addStudentAssociations(matchingStudents));
        }
    }

    /**
     * Iterates over the given students and professors, and returns a set that contains
     * a disctinct list of disciplines spanning the majors/minors of students and the
     * teaching/research disciplines of professors.
     *
     * @param students    Students with known majors/minors
     * @param professors  Professors with known teaching/research disciplines
     */
    public static Set<Enums.Discipline> findDistinctDisciplines(List<Student> students, List<Professor> professors) {

        // Create a set that will contain a distinct list of
        // disciplines spanning the majors/minors of students and
        // the teaching/research disciplines of professors
        Set<Enums.Discipline> disciplines = new HashSet<>();

        // Iterate over the students and attempt to add their majors
        // to the set of distinct disciplines
        students.forEach(s -> disciplines.addAll(s.getMajors()));

        // Iterate over the students and attempt to add their minors
        // to the set of distinct disciplines
        students.forEach (s -> disciplines.addAll(s.getMinors()));

        // Iterate over the professors and attempt to add their
        // teaching/research disciplines to the set of distinct disciplines
        professors.forEach(p -> disciplines.addAll(p.getDisciplines()));

        return disciplines;
    }
}