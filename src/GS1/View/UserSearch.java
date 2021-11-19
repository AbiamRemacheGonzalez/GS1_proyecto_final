/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.View;

import GS1.Model.User;
import java.util.ArrayList;

/**
 *
 * @author hugob
 */
public interface UserSearch {
    
    public ArrayList<String> search(String search,User currentUser);
    public void addFriend(String friend);
    
}
