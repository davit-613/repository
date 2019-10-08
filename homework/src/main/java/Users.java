import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> users = new ArrayList<User>();
    public Users(){
        users.add(new User("John","English",14));
        users.add(new User("Edward","English",56));
        users.add(new User("Hayk","Armenian",34));
        users.add(new User("Arsen","Armenian",23));
        users.add(new User("Ann","American",32));
    }

    public ArrayList<User> search(String name){
        ArrayList<User> result=new ArrayList<User>();
        for(User user : users){
            if(user.getFullName().contains(name))
                result.add(user);
        }
        return result;
    }

    public String toJSON(ArrayList<User> users){
        StringBuilder result=new StringBuilder();

        ObjectMapper objectMapper;

        objectMapper = new ObjectMapper();


        result.append("[");
        for(User user : users){
            try{
                result.append(objectMapper.writeValueAsString(user));
            }
            catch(Exception e){
                e.printStackTrace();
            }
            result.append(",");
        }
        result=new StringBuilder(result.substring(0,result.length()-1));
        result.append("]");
        return result.toString();
    }
}
