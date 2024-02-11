package persistance;

import java.io.IOException;

/**
 * @author Victor Correal Ramos
 * Hold the persistance information, Singeltone class
 */
public class PersistanceController {
    public static final String directory = "bd";

    private static PersistanceController instance = null;

    private BD user_persistance, operations_persistance;

    public static PersistanceController getInstance() {
        if (instance == null) {
            try {
                instance = new PersistanceController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    private PersistanceController() throws IOException {
        user_persistance = new BD(directory, "users");
    }

    public BD get_persistance_user() {return user_persistance;}
}
