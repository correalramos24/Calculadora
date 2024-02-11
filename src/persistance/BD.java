package persistance;

import java.io.*;
import java.nio.file.Files;
import java.util.Set;
import java.util.TreeMap;

/**
 * Contains all the information to maintain a DB of an object
 * in byte format.
 * @author Victor Correal Ramos
 */
public class BD {


    /** Set of objects  */
    private TreeMap<String, File> identifiers;
    /** File to store the information */
    File ids;
    /** Path to save the data */
    private String path;
    /** Total number of objects */
    private int obj_count;

    /**
     * Database builder
     * @param S Path where the DB files will be placed.
     * @param name Name of the directory
     */
    public BD(String S, String name) throws IOException {
        System.out.println("PERSISTANCE: loading internal data for "+ name + " into the system...");
        this.path = S+File.separator+name;
        identifiers = new TreeMap<>();
        File f = new File(this.path);
        f.mkdirs();
        ids = new File(this.path+File.separator+"metadata.bin");
        if(!ids.exists()){
            System.out.println("PERSISTANCE: generating new database...");
            ids.createNewFile();
            saveMap();
            obj_count = identifiers.size();
        }
        else getMap();
    }

    /**
     * Save a new object into the databasse
     * @param id Unique ID of the object
     * @param bytes Object information in byte format
     */
    public void saveNewRecord(String id, byte[] bytes) throws Exception {
        if(exists(id)) throw new Exception("ID already in use");
        try {
            File f = new File(path+File.separator+id+".bin");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.close();
            identifiers.put(id, f);
            saveMap();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieve object
     * @param id Unique ID of the object
     * @return Object in byte format
     */
    public byte[] getObject(String id){
        byte[] ret = null;
        try{
            final File obj = identifiers.get(id);
            //FileInputStream fis = new FileInputStream(obj);
            ret = Files.readAllBytes(obj.toPath());
        } catch (Exception e){

        }
        return ret;
    }

    /**
     * Delete object by ID
     * @param id Unique ID of the object
     */
    public void deletObject(String id) throws Exception {
        if(!exists(id)) throw new Exception("ID object doesn't exist");
        final File f = identifiers.get(id);
        f.delete();
        identifiers.remove(id);
        saveMap();
    }

    /**
     * Object modifier
     * @param id Unique ID of the object
     * @param bytes Object information in byte format
     */
    public void modifyObject(String id, byte[] bytes){
        try {
            deletObject(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            saveNewRecord(id, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Does this id exist in the BD?
     * @param id Unique ID of the object
     * @return Booleano que indica la existencia del objeto
     */
    public boolean exists(String id) {
        return identifiers.containsKey(id);
    }

    /**
     * Get the number of objects stored into the BD
     * @return Number of objects
     */
    public int getNumberOfObjs(){return obj_count;}

    /**
     *
     */
    private void saveMap(){
        try {
            FileOutputStream fos = new FileOutputStream(ids);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(identifiers);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load identifiers
     */
    private void getMap(){
        try{
            FileInputStream fis = new FileInputStream(ids);
            ObjectInputStream ois = new ObjectInputStream(fis);
            identifiers =  (TreeMap<String, File>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getAllEntities() {
        return identifiers.keySet();
    }

}