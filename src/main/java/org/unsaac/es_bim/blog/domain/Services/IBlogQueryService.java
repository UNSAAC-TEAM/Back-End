package org.unsaac.es_bim.blog.domain.Services;

import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;

public interface IBlogQueryService {
    public Long handle(GetBlogByIdQuery query);
}
