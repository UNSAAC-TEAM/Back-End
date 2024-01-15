package org.unsaac.es_bim.blog.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;

public interface BlogRepository extends JpaRepository<Blog,Long> {
}
