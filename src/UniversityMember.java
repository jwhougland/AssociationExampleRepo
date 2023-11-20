import java.util.Objects;

/**
 * Defines the characteristics that are common for university members.
 */
public abstract class UniversityMember {

    /**
     * Name of the university member
     */
    private String name;

    /**
     * ID of the university member
     */
    private int id;

    /**
     * Creates a fully initialized university member using the given data.
     * @param name Name of the university member
     * @param id   ID of the university member
     */
    public UniversityMember(String name, int id) {

        // Run a null check on the name
        if (name == null) {
            throw new IllegalArgumentException("Unable to create a university member with a null name field");
        }

        this.name = name;
        this.id = id;
    }

    /**
     * Returns the name of the university member
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the university member
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the string representation of the university member
     */
    @Override
    public String toString() {
        return "UniversityMember{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    /**
     * Determines if this university member instance is equal to the other university member instance
     * @param o The other university member instance
     * @return True if this university member instance is equal to the other university member instance, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityMember that = (UniversityMember) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    /**
     * Computes and returns the hash code for this university member instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
