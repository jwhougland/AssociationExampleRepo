import java.util.*;

/**
 * Defines the characteristics of a student.
 */
public class Student extends UniversityMember {

    /**
     * Collection of the student's majors.
     */
    private List<Enums.Discipline> majors;

    /**
     * Collection of the student's minors.
     */
    private List<Enums.Discipline> minors;

    /**
     * Map where the key is a professor's university ID and the
     * value is the professor that is associated with this student.
     */
    private Map<Integer, Professor> professors = new HashMap<>();

    /**
     * Creates a fully initialized university member using the given data.
     *
     * @param name Name of the university member
     * @param id   ID of the university member
     */
    public Student(String name, int id, List<Enums.Discipline> majors, List<Enums.Discipline> minors) {
        super(name, id);

        // Run a null check on the majors collection
        if (majors == null) {
            throw new IllegalArgumentException("Cannot create a student without a major");
        }

        // Save the reference to the majors collection
        this.majors = majors;

        // Run a null check on the minors collection
        if (minors == null) {
            // It is acceptable for the constructor to be passed a null minors collection.
            // For the sake of robustness elsewhere, let's initialize the minors object
            // to an empty collection to avoid a null reference issue.
            minors = new ArrayList<>();
        }

        // Save a reference to the minors collection
        this.minors = minors;
    }

    /**
     * Returns the student's list of majors.
     */
    public List<Enums.Discipline> getMajors() {
        return majors;
    }

    /**
     * Returns the student's list of minors.
     * Note: this will be an empty list if the student does not have a minor.
     */
    public List<Enums.Discipline> getMinors() {
        return minors;
    }

    /**
     * Adds the related professors to the list that are associated with the student.
     *
     * @param relatedProfessors Professors to add to the list that are associated with the student.
     */
    public void addProfessorAssociations(List<Professor> relatedProfessors) {

        // Run a null check on the related professors collection
        if (relatedProfessors == null) {
            throw new IllegalArgumentException("Cannot associate a null professors list with a student");
        }

        relatedProfessors.forEach(this::addProfessorAssociation);
    }

    /**
     * Removes the given professors from the student's list of associated professors.
     *
     * @param unrelatedProfessors Professors to remove from the student's list of associated professors
     */
    public void removeProfessorAssociations(List<Professor> unrelatedProfessors) {

        // Run a null check on the unrelated professors collection
        if (unrelatedProfessors == null) {
            throw new IllegalArgumentException("Invalid input.  Cannot disassociate professors from student");
        }

        unrelatedProfessors.forEach(this::removeProfessorAssociation);
    }

    /**
     * Adds the professor to the list that are associated with the student.
     *
     * @param professor Professor to add to the list that are associated with the student.
     */
    private void addProfessorAssociation(Professor professor) {

        // Run a null check on the professor parameter
        if (professor == null) {
            throw new IllegalArgumentException("Cannot associate a null professor with a student");
        }

        // Add the professor to the map if the professor is not already in the map
        professors.putIfAbsent(professor.getId(), professor);
    }

    /**
     * Removes the professor from the list that are associated with the student.
     *
     * @param professor Professor to remove from the list that are associated with the student.
     */
    private void removeProfessorAssociation(Professor professor) {

        // Run a null check on the professor parameter
        if (professor == null) {
            throw new IllegalArgumentException("Cannot disassociate a null professor from a student");
        }

        // Remove the professor from the map if the professor is present in the map
        professors.remove(professor.getId());
    }

    /**
     * Returns a string representation of the student
     */
    @Override
    public String toString() {
        return "Student{" +
                "majors=" + majors +
                ", minors=" + minors +
                ", professors=" + professors +
                '}';
    }

    /**
     * Determines if this student is equal to the other student
     * @param o The other university member instance
     * @return True if this student is equal to the other student, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(majors, student.majors) &&
                Objects.equals(minors, student.minors) &&
                Objects.equals(professors, student.professors);
    }

    /**
     * Computes and returns the hash code for this student.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), majors, minors, professors);
    }
}
