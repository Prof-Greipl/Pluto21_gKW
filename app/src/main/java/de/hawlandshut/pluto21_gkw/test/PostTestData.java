package de.hawlandshut.pluto21_gkw.test;

import java.util.ArrayList;
import java.util.List;

import de.hawlandshut.pluto21_gkw.model.Post;
// TODO: This class is for testing only
public class PostTestData {
    static String body ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu porttitor neque, non sodales quam.";

    public static List<Post> postTestList = new ArrayList<Post>();

    public static void createTestData(){
        long time = new java.util.Date().getTime();

        postTestList.add( new Post("1", "Author 1", "Title 1", body, time++ ));
        postTestList.add( new Post("2", "Author 2", "Title 2", body, time++ ));
        postTestList.add( new Post("3", "Author 3", "Title 3", body, time++ ));
        postTestList.add( new Post("4", "Author 4", "Title 4", body, time++ ));
        postTestList.add( new Post("5", "Author 5", "Title 5", body, time ));

    }
}
