package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Brams
 * Date: 24/03/14
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Comment extends Model {
    public String author;
    public Date postedAt;

    @Lob
    public String content;

    @ManyToOne
    public Post post;

    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
}
