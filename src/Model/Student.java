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
public final class Student {
   
    private int studentID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int score;
    private int sticker;
    private boolean darkTheme;
    
    


    
    public Student(int studentID, String firstName, String lastName, String username, String password, int score, int sticker, boolean darkTheme){
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.score = score;
        this.sticker=sticker;
        this.darkTheme = darkTheme;
    }

    /**
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the sticker
     */
    public int getSticker() {
        return sticker;
    }

    /**
     * @param sticker the sticker to set
     */
    public void setSticker(int sticker) {
        this.sticker = sticker;
    }

    /**
     * @return the darkTheme
     */
    public boolean isDarkTheme() {
        return darkTheme;
    }

    /**
     * @param darkTheme the darkTheme to set
     */
    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }


}
