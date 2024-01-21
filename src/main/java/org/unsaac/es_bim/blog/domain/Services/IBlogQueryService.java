package org.unsaac.es_bim.blog.domain.Services;

import org.unsaac.es_bim.blog.domain.model.queries.DeleteBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetPageOfBlogs;
import org.unsaac.es_bim.blog.domain.model.queries.GetRecommendedBlogsQuery;
import org.unsaac.es_bim.blog.interfaces.Resource.BlogPageResource;
import org.unsaac.es_bim.blog.interfaces.Resource.GetBlogResource;
import org.unsaac.es_bim.blog.interfaces.Resource.GetPreviewBlogResource;
import java.util.List;

public interface IBlogQueryService {
    public GetBlogResource handle(GetBlogByIdQuery query);

    public BlogPageResource handle(GetPageOfBlogs query);
    public List<GetPreviewBlogResource> handle();

    public List<GetPreviewBlogResource> handle(GetRecommendedBlogsQuery query);

    public Long hadle(DeleteBlogByIdQuery query);
}
