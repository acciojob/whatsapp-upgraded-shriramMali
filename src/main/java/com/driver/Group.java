package com.driver;

import java.util.List;


public class Group {
    private String name;
    private int numberOfParticipants;

    private List<User> userList;

    public Group(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }
}
