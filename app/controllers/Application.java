package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {

        Post frontPost = Post.find("order by postedAt desc").first();
         List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);

        System.out.println("Yop");

        System.out.println("< --- "+ frontPost.title + " --- >");
        System.out.println("< --- "+ olderPosts.get(0).title + " --- >");
        render(frontPost,olderPosts);
    }

}