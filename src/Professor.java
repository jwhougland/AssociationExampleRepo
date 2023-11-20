import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
     * Adds the related students to the list that are associated with the professor.
     *
     * @param relatedStudents Students to add to the list that are associated with the professor.
     */
    public void addStudentAssociations(List<Student> relatedStudents) {

        // Run a null check on the related students collection
        if (relatedStudents == null) {
            throw new IllegalArgumentException("Cannot associate a null students list with a professor");
        }

        // Add each student to the list that are associated with the professor.
        relatedStudents.forEach(this::addStudentAssociation);
    }

    /**
     * Removes the given students from the professor's list of associated students.
     *
     * @param unrelatedStudents Students to remove from the professor's list of associated students
     */
    public void removeStudentAssociations(List<Student> unrelatedStudents) {

        // Run a null check on the unrelated students collection
        if (unrelatedStudents == null) {
            throw new IllegalArgumentException("Invalid input.  Cannot disassociate students from professor");
        }

        // Remove each student from the professor's list of associated students
        unrelatedStudents.forEach(this::removeStudentAssociation);
    }

    /**
     * Adds the student to the list that are associated with the professor.
     *
     * @param student Student to add to the list that are associated with the professor.
     */
    private void addStudentAssociation(Student student) {

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
    private void removeStudentAssociation(Student student) {

        // Run a null check on the student parameter
        if (student == null) {
            throw new IllegalArgumentException("Cannot disassociate a null student from a professor");
        }

        // Remove the student from the map
        students.remove(student.getId());
    }

    /**
     * Returns a string representation of the professor
     */
    @Override
    public String toString() {
        return "Professor{" +
                "disciplines=" + disciplines +
                ", jobRole=" + jobRole +
                ", students=" + students +
                '}';
    }

    /**
     * Determines if this professor equals the other professor
     * @param o The other professor
     * @return True if this professor equals the other professor, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(disciplines, professor.disciplines) && jobRole == professor.jobRole && Objects.equals(students, professor.students);
    }

    /**
     * Compute and returns the hash code for this professor.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), disciplines, jobRole, students);
    }
}
