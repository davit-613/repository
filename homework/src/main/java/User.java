public class User {
    private String fullName;
    private String nationality;
    private int age;
    public User(String name, String nationality, int age){
        this.fullName=name;
        this.nationality=nationality;
        this.age=age;
    }
    public String getFullName(){
        return fullName;
    }
    public String getNationality(){
        return nationality;
    }
    public int getAge(){
        return age;
    }
}
