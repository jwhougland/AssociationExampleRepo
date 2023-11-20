/**
 * Defines enums that can be used within the app.
 */
public class Enums {

    /**
     * Private constructor to prevent instantiation
     */
    private Enums() {
        // No processing
    }

    /**
     * Defines a discipline (could be used to identify an area of study or teaching)
     */
    public enum Discipline {
        COMPUTER_SCIENCE("Computer Science"),
        ELECTRICAL_ENGINEERING("Electrical Engineering"),
        MECHANICAL_ENGINEERING("Mechanical Engineering"),
        BIOLOGY("Biology"),
        PSYCHOLOGY("Psychology"),
        BUSINESS_ADMINISTRATION("Business Administration"),
        ENGLISH("English"),
        MATHEMATICS("Mathematics"),
        HISTORY("History"),
        NURSING("Nursing"),
        CHEMISTRY("Chemistry"),
        PHYSICS("Physics");

        /**
         * User-friendly name for a discipline
         */
        private final String disciplineName;

        /**
         * Facilitates the association of a user-friendly discipline name and an enumeration
         */
        Discipline(String disciplineName) {
            this.disciplineName = disciplineName;
        }

        /**
         * Returns the user-friendly discipline name
         */
        public String getDisciplineName() {
            return disciplineName;
        }
    }

    /**
     * Defines job roles for a professor
     */
    public enum ProfessorJobRole {
        ASSISTANT_PROFESSOR("Assistant Professor"),
        ASSOCIATE_PROFESSOR("Associate Professor"),
        PROFESSOR("Professor");

        /**
         * User-friendly job role name
         */
        private final String roleName;

        /**
         * Facilitates the association of a user-friendly job role name and an enumerated job role
         *
         * @param roleName User-friendly job role name
         */
        ProfessorJobRole(String roleName) {
            this.roleName = roleName;
        }

        /**
         * Returns the user-friendly job role name
         */
        public String getRoleName() {
            return roleName;
        }
    }
}
