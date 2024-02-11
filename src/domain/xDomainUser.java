package domain;

import persistance.BD;
import persistance.PersistanceController;

import java.io.*;
import java.util.Set;
import java.util.TreeMap;

import static java.lang.System.exit;

public class xDomainUser {
    final private TreeMap<String, User> user_instances;
    final private BD users_bd;
    private User current_user;

    public xDomainUser() {
        user_instances = new TreeMap<>();
        users_bd = PersistanceController.getInstance().get_persistance_user();
    }

    public void init_user_controller(){
        Set<String> allIdEntidades = users_bd.getAllEntities();
        System.out.println("DOMAIN: loading users...");
        System.out.println(allIdEntidades);
        for(String id : allIdEntidades){
            System.out.println("DOMAIN: loading " + id + " user");
            final byte[] obj = users_bd.getObject(id);
            User aux = build_object(obj);
            user_instances.put(aux.getUserName(), aux.clone());
        }
        current_user = null;
    }

    //=============================================Controller methods===================================================

    public boolean loginUser(String user_name, String user_password){
        try{
           User aux = checkAndGetUser(user_name);
           boolean login = aux.getUserPass().equals(user_password);
           if(login) current_user = aux;
           return login;
        }
        catch (Exception e){
            return false;
        }
    }
    public String getCurrentUserName(){return current_user.getUserName();}


    protected User getCurrentUser(){return current_user;}

    //==================================================================================================================
    //==============================================Private methods=====================================================
    private User build_object(byte[] bytes){
        try{
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (User) ois.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            exit(1);
        }
        return null;
    }

    private ByteArrayOutputStream deconstruct_object(User u){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bs2 = new BufferedOutputStream(new ByteArrayOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(bs2);
            oos.writeObject(u);
            oos.close();
            baos.close();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User checkAndGetUser(String userName) throws Exception{
        if(user_instances.containsKey(userName)) throw new Exception("User " + userName + "not found");
        return user_instances.get(userName);
    }

    //==================================================================================================================
}
