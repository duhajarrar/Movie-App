package edu.birzeit.projectMovies;

import androidx.annotation.NonNull;

public class Comment {
    private  String Email;
    private  String textCom;
    public Comment() {

    }
    public Comment(String email, String textCom) {
        Email = email;
        this.textCom = textCom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTextCom() {
        return textCom;
    }

    public void setTextCom(String textCom) {
        this.textCom = textCom;
    }

    @NonNull
    @Override
    public String toString() {
        return Email+" :"+"\n"+textCom+"\n";
    }
}
