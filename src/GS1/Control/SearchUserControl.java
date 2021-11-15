/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GS1.Control;

import GS1.App.SearchFriend.SearchUserDisplay;
import GS1.App.SearchFriend.SearchUserSeeker;
import GS1.View.UserSearch;


public class SearchUserControl {
    
    private SearchUserDisplay searchUserDisplay;
    private UserSearch userSearch = new SearchUserSeeker();

    public SearchUserControl(SearchUserDisplay searchUserDisplay) {
        this.searchUserDisplay = searchUserDisplay;
        this.searchUserDisplay.on(setFriendSearchDisplay());
    }
    
    private SearchUserDisplay.Events setFriendSearchDisplay(){
        return new SearchUserDisplay.Events(){
           
            @Override
            public String[] search(String search) {
                return userSearch.search(search);
            }
            
        };
    }
    
    
}
