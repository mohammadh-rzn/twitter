import java.util.ArrayList;
import java.util.List;
public class User {
    String username;
    String password;
    List <User> following = new ArrayList<User>();
    List <User> followers = new ArrayList<User>();
    List <Tweet> tweets = new ArrayList<Tweet>();
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
    }
    public boolean validate(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            return true;
        }
        else return false;
    }
    public void addTweet(Tweet tweet){
        this.tweets.add(tweet);
    }
    public void addFollowing(User user){
        this.following.add(user);
    }
    public void addFollowers(User user){
        this.followers.add(user);
    }
    public void removeFollowing(int i){
        this.following.remove(i);
    }
    public void removeFollowers(int i){
        this.followers.remove(i);
    }
}
