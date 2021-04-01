import java.util.ArrayList;
import java.util.List;
public class Tweet implements Comparable <Tweet>{
    String text;
    int likes;
    int id;
    String user;
    List <User> likers = new ArrayList<User>();
    public Tweet(int id, String text, String user){
        this.text = text;
        this.id = id;
        this.likes = 0;
        this.user = user;
    }
    public int getId(){
        return this.id;
    }
    public String toString(){
        return this.id + "\n"+ this.text + "\n"+ this.likes + " likes\n";
    }
    @Override
    public int compareTo(Tweet t) {
        return Integer.compare(this.getId(), t.getId());
    }
    public boolean liking(User user){
        for(int i = 0;i < likers.size();i++){
            if (user.getUsername().equals(likers.get(i).getUsername())){
                return false;
            }
        }
        this.likes++;
        this.likers.add(user);
        return true;
    }
}