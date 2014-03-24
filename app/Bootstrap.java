import models.Comment;
import models.Post;
import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Created with IntelliJ IDEA.
 * User: Brams
 * Date: 24/03/14
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
@OnApplicationStart
public class Bootstrap extends Job {
    @Override
    public void doJob() throws Exception {
        if(User.count()==0){
            System.out.println("-------------- >>>>> : " + User.count());

            User aniss = new User("aniss.serhane@brams.net", "pass" , "Aniss Serhane");
            aniss.save();

            User said= new User("said.azzou@brams.net", "pass" , "Said Aazzou");
            said.save();

            Post postAniss = new Post("Post content Aniss" , "titre Aniss" , aniss);
            postAniss.save();
            Post postSaid = new Post("Post content Said" , "titre Said" , said);
            postSaid.save();

            postAniss.addComment("Aniss Serhane", " Comment 1 content aniss");
            postAniss.addComment("Aniss Serhane", " Comment  2 content aniss");
            postAniss.addComment("Aniss Serhane", " Comment  3 content aniss");
            postSaid.addComment("Said Aazzzou", " Comment 1 content said");
            postSaid.addComment("Said Aazzzou", " Comment 2  content said");


            // Fixtures.loadModels("initial-data.yml");
        }
    }
}
