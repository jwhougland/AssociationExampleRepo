import java.util.ArrayList;
import java.util.List;

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
}
