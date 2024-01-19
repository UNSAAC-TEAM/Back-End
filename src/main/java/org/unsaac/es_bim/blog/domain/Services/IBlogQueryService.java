package org.unsaac.es_bim.blog.domain.Services;

import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetPageOfBlogs;
import org.unsaac.es_bim.blog.interfaces.Resource.BlogPageResource;

import java.util.Optional;

public interface IBlogQueryService {
    public Optional<Blog> handle(GetBlogByIdQuery query);

    public BlogPageResource handle(GetPageOfBlogs query);
}
