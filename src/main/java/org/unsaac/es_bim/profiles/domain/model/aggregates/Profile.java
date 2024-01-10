package org.unsaac.es_bim.profiles.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
@public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private String profileImageUrl;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private String Country;

    private String City;




}
