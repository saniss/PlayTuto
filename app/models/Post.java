package models;

import org.hibernate.annotations.Cascade;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Brams
 * Date: 24/03/14
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Post extends Model {

    public String title;
    public Date postedAt;

    @Lob
    public String content;

    @ManyToOne
    public User author;


    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;

    public Post(String content, String title, User author) {
        this.comments = new ArrayList<Comment>();
        this.content = content;
        this.title = title;
        this.author = author;
        postedAt = new Date();
    }

    public Post addComment(String author , String content){
        System.out.println("- author - >" + author );
        System.out.println("- content - >" + content );
        System.out.println("- this - >" + this.title);
        Comment newcomment = new Comment(this , author ,content);
        this.comments.add(newcomment);
        this.save();
        return this;

    }
}
