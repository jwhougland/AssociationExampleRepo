import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the characteristics of a professor.
 */
public class Professor extends UniversityMember {

    /**
     * Collection of disciplines that the professor teaches and/or researches.
     */
    private List<Enums.Discipline> disciplines;

    /**
     * Defines the professor's job role
     */
    private Enums.ProfessorJobRole jobRole;

    /**
     * Map where the key is a student's university ID and the
     * value is the student that is associated with this professor.
     */
    private Map<Integer, Student> students = new HashMap<>();

    /**
     * Creates a fully initialized professor using the given data.
     *
     * @param name         Name of the professor
     * @param id           ID of the professor
     * @param disciplines  Collection of disciplines that the professor teaches and/or researches.
     * @param jobRole      Defines the professor's job role
     */
    public Professor(String name, int id, List<Enums.Discipline> disciplines, Enums.ProfessorJobRole jobRole) {
        super(name, id);

        // Run a null check on the disciplines collection
        if (disciplines == null) {
            throw new IllegalArgumentException("Cannot create a professor without teaching/research disciplines");
        }

        this.disciplines = disciplines;
        this.jobRole = jobRole;
    }

    /**
     * Returns the list of disciplines that the professor teaches and/or researches.
     */
    public List<Enums.Discipline> getDisciplines() {
        return disciplines;
    }

    /**
     * Returns the professor's job role.
     */
    public Enums.ProfessorJobRole getJobRole() {
        return jobRole;
    }

    /**
     * Adds the student to the list that are associated with the professor.
     *
     * @param student Student to add to the list that are associated with the professor.
     */
    public void addStudentAssociation(Student student) {

        // Run a null check on the student parameter
        if (student == null) {
            throw new IllegalArgumentException("Cannot associate a null student with a professor");
        }

        // Add the student to the map
        students.putIfAbsent(student.getId(), student);
    }

    /**
     * Removes the student from the list that are associated with the professor.
     *
     * @param student Student to remove from the list that are associated with the professor.
     */
    public void removeStudentAssociation(Student student) {

        // Run a null check on the student parameter
        if (student == null) {
            throw new IllegalArgumentException("Cannot disassociate a null student from a professor");
        }

        // Remove the student from the map
        students.remove(student.getId());
    }
}
