import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        int tweetId = 1;
        User currentUser = new User("username", "password");
        boolean isLogedin = false;
        List <User> users = new ArrayList<User>();
        Scanner scan = new Scanner(System.in);
        do{
            boolean quited = false;
            System.out.println("Enter a command:");
            switch(scan.nextLine()){
                case "Signup":{
                    boolean uniqe = false;
                    String username = "";
                    String password;
                    System.out.println("Enter a username:");
                    while(uniqe == false){
                        uniqe = true;
                        username = scan.nextLine();
                        for(int i = 0; i < users.size(); i++){
                            if(username.equals(users.get(i).getUsername())){
                                uniqe = false;
                                System.out.println("A user with this username already exists enter another:");
                                break;
                            }

                        }
                    }
                    while(true){
                        System.out.println("Enter password:");
                        password = scan.nextLine();
                        System.out.println("Confirm password:");
                        String confirmPassword = scan.nextLine();
                        if(password.equals(confirmPassword)){
                            break;
                        }
                        else{
                            System.out.println("passwords does not match");
                        }
                    }
                    User user = new User(username, password);
                    users.add(user);
                    System.out.println("User created successfully");
                    break;
                }
                case "Quit":{
                    quited = true;
                    break;

                }
                case "Login":{
                    if(isLogedin){
                        System.out.println("Logout first");
                        break;
                    }
                    System.out.println("Enter username:");
                    String username = scan.nextLine();
                    System.out.println("Enter password:");
                    String password = scan.nextLine();
                    boolean success = false;
                    for(int i = 0; i < users.size();i++){
                        if(users.get(i).validate(username, password)){
                            success = true;
                            currentUser = users.get(i);
                            isLogedin = true;
                            break;
                        }
                    }
                    if(success){
                        System.out.println("Welcome "+ currentUser.getUsername());
                    }
                    else{
                        System.out.println("Login was not successfull");
                    }
                    break;
                }
                case "Logout":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    isLogedin = false;
                    break;
                }
                case "Tweet":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    System.out.println("Tweet!!!:");
                    String text = scan.nextLine();
                    Tweet tweet = new Tweet(tweetId, text, currentUser.getUsername());
                    currentUser.addTweet(tweet);
                    tweetId++;
                    System.out.println("tweeted!!!!!!");
                    break;
                }
                case "MyProfile":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    System.out.println("Here is your profile "+ currentUser.getUsername());
                    for(int i = currentUser.tweets.size()-1;i >=0;i--){
                        System.out.println(currentUser.tweets.get(i));
                    }
                    break;

                }
                case "Follow":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    System.out.println("Enter username:");
                    String user = scan.nextLine();
                    boolean exists = false;
                    for(int i = 0;i < users.size(); i++){
                        if(user.equals(users.get(i).getUsername())){
                            exists = true;
                            currentUser.addFollowing(users.get(i));
                            users.get(i).addFollowers(currentUser);
                            System.out.println("User followed");
                            break;
                        }
                    }
                    if (!exists){
                        System.out.println("User does not exists");
                    }
                    break;

                }
                case "Unfollow":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    System.err.println("Enter username");
                    String user = scan.nextLine();
                    boolean exists = false;
                    for(int i = 0;i < currentUser.following.size(); i++){
                        if(user.equals(currentUser.following.get(i).getUsername())){
                            exists = true;
                            currentUser.removeFollowing(i);
                            System.out.println("User unfollowed");
                            for(int k = 0; k <users.size();k++){
                                System.out.println(k);
                                if(users.get(k).getUsername().equals(user)){
                                    for(int j=0;j< users.get(k).followers.size();j++){
                                        System.out.println("this user:"+ users.get(k).followers.get(j).getUsername());
                                        System.out.println("current user:"+ currentUser.getUsername());
                                        if(users.get(k).followers.get(j).getUsername().equals(currentUser.getUsername())){
                                            users.get(k).removeFollowers(j);
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    if (!exists){
                        System.out.println("You do not follow this user");
                    }
                    break;
                }
                case "Following":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    for(int i = 0; i < currentUser.following.size();i++){
                        System.out.println(currentUser.following.get(i).getUsername());
                    }
                    break;
                }
                case "Followers":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    for(int i = 0; i < currentUser.followers.size();i++){
                        System.out.println(currentUser.followers.get(i).getUsername());
                    }
                    break;
                }
                case "Timeline":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    List<Tweet> myAndMyFollowingTweets = currentUser.tweets;

                    for(int i=0; i<currentUser.following.size();i++){
                        for(int j=0; j<currentUser.following.get(i).tweets.size();j++){
                            myAndMyFollowingTweets.add(currentUser.following.get(i).tweets.get(j));
                        }
                    }
                    Collections.sort(myAndMyFollowingTweets, Collections.reverseOrder());
                    for(int i=0;i< myAndMyFollowingTweets.size();i++){
                        System.out.println("Username: "+ myAndMyFollowingTweets.get(i).user);
                        System.out.println(myAndMyFollowingTweets.get(i));
                    }
                    break;
                }
                case "Profile":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    System.out.println("Enter username:");
                    String user = scan.nextLine();
                    boolean exists = false;
                    for(int i = 0;i < users.size(); i++){
                        if(user.equals(users.get(i).getUsername())){
                            exists = true;
                            for(int j =users.get(i).tweets.size()-1;j >= 0;j--){
                                System.out.println(users.get(i).tweets.get(j));
                            }
                            break;
                        }
                    }
                    if (!exists){
                        System.out.println("User does not exists");
                    }

                    break;
                }
                case "Like":{
                    if(!isLogedin){
                        System.out.println("Login first");
                        break;
                    }
                    System.out.println("Enter tweet id:");
                    int id = scan.nextInt();
                    boolean exists = false;
                    for(int i = 0; i< users.size();i++){
                        for(int j = 0; j <users.get(i).tweets.size();j++){
                            if(id == users.get(i).tweets.get(i).getId()){
                                exists = true;
                                boolean isLiked = users.get(i).tweets.get(j).liking(currentUser);
                                if(isLiked){
                                    System.out.println("Tweet liked");
                                }
                                else System.out.println("You already liked the tweet");
                                break;
                            }
                        }
                    }
                    if (!exists){
                        System.out.println("Tweet does not exists");
                    }

                    break;
                }
            }
            if(quited){
                break;
            }
        }while(true);
    }
}
