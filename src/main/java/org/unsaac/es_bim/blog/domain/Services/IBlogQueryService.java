package org.unsaac.es_bim.blog.domain.Services;

import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.queries.DeleteBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetPageOfBlogs;
import org.unsaac.es_bim.blog.interfaces.Resource.BlogPageResource;
import org.unsaac.es_bim.blog.interfaces.Resource.GetBlogResource;

import java.util.Optional;

public interface IBlogQueryService {
    public GetBlogResource handle(GetBlogByIdQuery query);

    public BlogPageResource handle(GetPageOfBlogs query);

    public Long hadle(DeleteBlogByIdQuery query);
}
