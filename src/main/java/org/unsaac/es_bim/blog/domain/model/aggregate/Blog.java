package org.unsaac.es_bim.blog.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.unsaac.es_bim.profiles.domain.model.aggregates.Profile;

import java.util.Date;

@Getter
@Entity
public class Blog extends AbstractAggregateRoot<Blog> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String label;

    private String imageUrl;

    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private Date publishDate;


    @ManyToOne
    @JoinColumn(name="profile_id")
    private Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setAuthor(Profile author){
        this.profile=author;
    }
    public Blog(){}
    public Blog(String title,String label,String imageUrl,String description,String content){
        this.title=title;
        this.label=label;
        this.imageUrl=imageUrl;
        this.description=description;
        this.content=content;
        this.publishDate=new Date();

    }


}
