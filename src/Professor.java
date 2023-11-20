import java.util.List;

/**
 * Defines the characteristics of a professor.
 */
public class Professor extends UniversityMember {

    /**
     * Collection of disciplines that the professor teaches and/or researches.
     */
    private List<Enums.Discipline> disciplines;

    /**
     * Creates a fully initialized professor using the given data.
     *
     * @param name         Name of the professor
     * @param id           ID of the professor
     * @param disciplines  Collection of disciplines that the professor teaches and/or researches.
     */
    public Professor(String name, int id, List<Enums.Discipline> disciplines) {
        super(name, id);

        // Run a null check on the disciplines collection
        if (disciplines == null) {
            throw new IllegalArgumentException("Cannot create a professor without teaching/research disciplines");
        }

        this.disciplines = disciplines;
    }

    /**
     * Returns the list of disciplines that the professor teaches and/or researches.
     */
    public List<Enums.Discipline> getDisciplines() {
        return disciplines;
    }
}
