package org.unsaac.es_bim.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.unsaac.es_bim.iam.domain.model.aggregates.User;
import org.unsaac.es_bim.profiles.domain.model.valueObjects.Genre;

import java.util.Date;

@Getter
@Entity
public class Profile extends AbstractAggregateRoot<Profile> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String profileImageUrl;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private String country;

    private String city;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String phoneNumber;

    private String description;

    public void setAccount(User user){
        this.user=user;
    }
    public Profile(){
        this.user=new User();
    }
    public Profile(String imageUrl,String firstName,String lastName,Date birthDay,String country,String city,String genre,String phoneNumber,String des){

        this.profileImageUrl=imageUrl;
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthDay=birthDay;
        this.country=country;
        this.city=city;
        this.genre=Genre.valueOf(genre);
        this.phoneNumber=phoneNumber;
        this.description=des;
    }

    public void updateNumber(String newNumber){
        this.phoneNumber=newNumber;
    }

}
