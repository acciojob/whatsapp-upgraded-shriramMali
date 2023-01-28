package com.driver.Service;

import com.driver.Group;
import com.driver.Message;
import com.driver.Repository.WhatsappRepository;
import com.driver.User;

import java.util.Date;
import java.util.List;

public class WhatsappService {
    WhatsappRepository whatsappRepository=new WhatsappRepository();


    public String createUser(String name, String mobile) throws Exception{

        return whatsappRepository.createUser(name,mobile);
    }

    public Group createGroup(List<User> users){

        return  whatsappRepository.createGroup(users);

    }

    public int createMessage(String content){

        return whatsappRepository.createMessage(content);
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        return whatsappRepository.sendMessage(message,sender,group);
    }

    public String changeAdmin(User approver, User user, Group group){

        return "";
    }

    public int removeUser(User user) throws Exception {

        return whatsappRepository.removeUser(user);
    }
    public String findMessage(Date start, Date end, int K){

        return "";
    }


}
