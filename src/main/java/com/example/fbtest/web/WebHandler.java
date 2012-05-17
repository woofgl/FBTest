package com.example.fbtest.web;


import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.handler.annotation.WebModelHandler;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;


import java.util.ArrayList;
import java.util.List;

public class WebHandler {

    //public static final String ACCESS_TOKEN = "AAACEdEose0cBAOFIuGIxOCLcj1jKZBkxHaiOjaYd71xJh1KZA5uotxFRUHl876gaiMqTVPfvJA9pK9ycQNrM4xiP7XJZCGkw1FjfoU2UAegP8861ZBWU";

    @WebModelHandler(startsWith = "/friends")
     public void getFriends( @WebParam("token") String token, RequestContext rc) {
        FacebookClient facebookClient = new DefaultFacebookClient(token);
        //fetch friend from facebook, limit is 10
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("limit",10));
        List<User> users = new ArrayList<User>();
        for (List<User> myFriend : myFriends) {
            for (User user : myFriend) {
                users.add(user);

            }
        }
        rc.getWebModel().put("_jsonData", users);
     }
}
