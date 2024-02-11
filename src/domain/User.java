package domain;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable, Cloneable {

    String user_name, user_pass;
    Integer user_total_operations, user_total_exceptions;
    List<operation_result> historial;

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.user_pass = password;
    }

    public User(String user_name, String password, Integer total_ops, Integer total_excp){
        this(user_name, password);
        this.user_total_operations = total_ops;
        this.user_total_exceptions = total_excp;
    }


    protected void add_operation(){
        this.user_total_operations += 1;
    }
    protected void add_exception(){
        this.user_total_exceptions += 1;
    }

    public String getUserName() {return user_name;}

    public void setUserName(String user_name) {this.user_name = user_name;}

    public String getUserPass() {return user_pass;}

    public void setUserPass(String user_pass) {this.user_pass = user_pass;}

    public Integer getUser_total_operations() {
        return user_total_operations;
    }

    public void setUser_total_operations(Integer user_total_operations) {
        this.user_total_operations = user_total_operations;
    }

    public Integer getUser_total_exceptions() {
        return user_total_exceptions;
    }

    public void setUser_total_exceptions(Integer user_total_exceptions) {
        this.user_total_exceptions = user_total_exceptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return user_name.equals(user.user_name);
    }

    @Override
    public int hashCode() {
        return user_name.hashCode();
    }

    @Override
    public User clone() {
        return new User( this.user_name, this.user_pass, this.user_total_operations, this.user_total_exceptions);
    }

}
