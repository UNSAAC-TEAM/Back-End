package org.unsaac.es_bim.blog.domain.model.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Blog extends AbstractAggregateRoot<Blog> {

    @Id
    private Long id;

    private String title;


}
