import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public  void testUpdate()
    {
        new User("aniss.serhane@brams.net" ,"aqwxsz123" ,"Aniss Serhane").save();
        User aniss = User.find("byEmail" , "aniss.serhane@brams.net" ).first() ;
        aniss.email= "aniss.serhane@gmail.com";
        aniss.save();

        User aniss2 = User.find("byFullname" , "Aniss Serhane" ).first() ;
        System.out.println("----------->>> : "+aniss2.email);
        assertNotNull(aniss2);



    }

     @Test
    public void createAndRetrieveUser(){
        User aniss = new User("aniss.serhane@brams.net" ,"aqwxsz123" ,"Aniss Serhane").save();



         assertNotNull(User.connect("aniss.serhane@brams.net", "aqwxsz123"));
         assertNull(User.connect("bob@gmail.com", "badpassword"));
         assertNull(User.connect("tom@gmail.com", "aqwxsz123"));
    }

    @Test
    public void createPost() {
        // Create a new user and save it
        User aniss = new User("aniss.serhane@gmail.com", "secret", "Aniss Serhane").save();

        // Create a new post
        new Post("My first post", "Hello world" ,aniss).save();

        // Test that the post has been created
        assertEquals(1, Post.count());

        // Retrieve all posts created by Bob
        List<Post> anissPosts = Post.find("byAuthor", aniss).fetch();

        // Tests
        assertEquals(1, anissPosts.size());
        Post firstPost = anissPosts.get(0);
        assertNotNull(firstPost);
        assertEquals(aniss, firstPost.author);
        assertEquals("My first post", firstPost.content);
        assertEquals("Hello world", firstPost.title);
        assertNotNull(firstPost.postedAt);
    }

    @Test
    public void postComments() {
        // Create a new user and save it
        User aniss = new User("aniss.serhane@gmail.com", "secret", "Aniss Serhane").save();

        // Create a new post
        Post anissPost = new Post( "My first post", "Hello world", aniss).save();

        // Post a first comment
        new Comment(anissPost, "Jeff", "Nice post").save();
        new Comment(anissPost, "Tom", "I knew that !").save();

        // Retrieve all comments
        List<Comment> anissPostComments = Comment.find("byPost", anissPost).fetch();

        // Tests
        assertEquals(2, anissPostComments.size());

        Comment firstComment = anissPostComments.get(0);
        assertNotNull(firstComment);
        assertEquals("Jeff", firstComment.author);
        assertEquals("Nice post", firstComment.content);
        assertNotNull(firstComment.postedAt);

        Comment secondComment =     anissPostComments.get(1);
        assertNotNull(secondComment);
        assertEquals("Tom", secondComment.author);
        assertEquals("I knew that !", secondComment.content);
        assertNotNull(secondComment.postedAt);
    }

    @Test
    public void useTheCommentsRelation() {
        // Create a new user and save it
        User aniss = new User("aniss.serhane@gmail.com", "secret", "Aniss Serhane").save();

        // Create a new post
        Post anissPost = new Post("My first post", "Hello world" , aniss).save();

        // Post a first comment
        anissPost.addComment("Jeff", "Nice post");
        anissPost.addComment("Tom", "I knew that !");

        // Count things
        assertEquals(1, User.count());
        assertEquals(1, Post.count());
        assertEquals(2, Comment.count());

        // Retrieve Bob's post
        anissPost = Post.find("byAuthor", aniss).first();
        assertNotNull(anissPost);

        // Navigate to comments
        assertEquals(2, anissPost.comments.size());
        System.out.println(" anissPost.comments.get(0).author ---->>>>" +anissPost.comments.get(0));
        assertEquals("Jeff", anissPost.comments.get(0).author);

        // Delete the post
        anissPost.delete();

        // Check that all comments have been deleted
        assertEquals(1, User.count());
        assertEquals(0, Post.count());
        assertEquals(0, Comment.count());
    }


}
