/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Nicholas
 */
public final class Model {
    

    private final UserList uL;
    private final Student student1;
    private final Student student2;
    private final Student student3;
    private final Teacher teacher1;
    
    public Model() {
    
    
    student1 = new Student(01,"Nick","Bui", 90, "Nick", "password");
    student2 = new Student(02,"Dan","Supper", 85, "Quack", "password");
    student3 = new Student(03,"Brad","Riddle", 89, "Brad", "password");
    
    teacher1 = new Teacher(01,"Matt","Mcgee","Matt", "password");
    
    uL = new UserList();

    
    this.addUserToList(student1.getUsername());
    this.addPassToList(student1.getPassword());
    
    this.addUserToList(student2.getUsername());
    this.addPassToList(student2.getPassword());
    
    this.addUserToList(student3.getUsername());
    this.addPassToList(student3.getPassword());
    
    }


    /**
     * @return the uL
     */
    public UserList getuL() {
        return uL;
    }
    
     public void addUserToList(String user) {
        uL.getAuthenticatedUsers().add(user);
    }
    
    public void addPassToList(String pass) {
        uL.getAuthenticatedPassword().add(pass);
    }
    
}