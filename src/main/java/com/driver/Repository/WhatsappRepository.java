package com.driver.Repository;

import com.driver.Group;
import com.driver.Message;
import com.driver.User;

import java.util.*;

public class WhatsappRepository {


    Map<String, User> userdb=new HashMap<>();
    HashMap<Group,List<User>> groupdb=new HashMap<>();
    HashMap<Group,List<Message>> grpMsgdb=new HashMap<>();
    List<Message>messageList=new ArrayList<>();
    HashMap<User,List<Message>> userMsgdb=new HashMap<>();

    private int gCount=0,mCount=0;


    public String createUser(String name, String mobile) throws Exception {

        if(userdb.containsValue(mobile)) throw new Exception("User already exists");

        User user=new User(name,mobile);
        return "Success";
    }
    public Group createGroup(List<User> users){
        if(users.size()==2){
            Group group=new Group(users.get(1).getName(),users);
            groupdb.put(group,users);

            return group;
        }
        else {
            Group group=new Group(users.get(1).getName()+ ++gCount,users);
            groupdb.put(group,users);
            return group;
        }
    }
    public int createMessage(String content){
     Message message=new Message(++mCount,content,new Date());
     messageList.add(message);
     return mCount;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
       if(!groupdb.containsKey(group)) {
           throw new Exception("Group does not exist");
       }
       boolean flag=false;
       for (User user:groupdb.get(group)){
           if(user==sender){
               flag=true;
               break;
           }
       }

       if(flag==true){
           throw new Exception("You are not allowed to send message");
       }


       if(userMsgdb.containsKey(sender))
           userMsgdb.get(sender).add(message);
       else {
           List<Message> lm=new ArrayList<>();
           lm.add(message);
           userMsgdb.put(sender,lm);
       }

       if (groupdb.containsKey(group))
       groupdb.get(group).add(sender);
       else {
           List<User> lm=new ArrayList<>();
           lm.add(sender);
           groupdb.put(group,lm);
       }

       return groupdb.size();
    }


    public int removeUser(User user) throws Exception {
        boolean flag=false;
        Group group1=null;
        User user2=null;
        for(Group group:groupdb.keySet()){

            for (User user1:groupdb.get(group)){
                if(user1==user){
                    flag=true;
                   group1=group;
                   user2=user1;
                    break;
                }
            }
        }

  if(flag==false){
      throw new Exception("User not found");
  }

  if (groupdb.get(group1).get(0)==user){
      throw new Exception("Cannot remove admin");
  }



        return 0;
    }

}
