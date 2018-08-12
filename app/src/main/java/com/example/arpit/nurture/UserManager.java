package com.example.arpit.nurture;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class UserManager{

    public ArrayList<User> user;

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    public void createNewUser(String name, String pass, String email, String mob, Bitmap dp)
    {
        User temp = new User( );
        temp.setCredits(0);
        temp.setName(name);
        temp.setPass(pass);
        temp.setEmail(email);
        temp.setMobNo(mob);
        temp.setProfilePicture(dp);

        user.add(temp);

        User.count++;
    }


    public int findUserIndexByEmail(String email)
    {
        for(int index=0; index<user.size(); index++)
        {
            if(email.equals(user.get(index).getEmail()))
                return index;
            else
                return -1;
        }
        return 0;
    }

    public User findUserByEmail(String email){
        int id = findUserIndexByEmail(email);
        return user.get(id);
    }

    public void removeUserByEmail(String email)
    {
        int index = findUserIndexByEmail(email);
        user.remove(index);
        User.count--;
    }
}
