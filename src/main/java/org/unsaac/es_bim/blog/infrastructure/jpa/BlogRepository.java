package org.unsaac.es_bim.blog.infrastructure.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("SELECT b FROM Blog b ORDER BY b.publishDate DESC")
    List<Blog> findTopNByOrderByPublishDateDesc(int blogQuantity);  // Agregar parámetro blogQuantity
}
