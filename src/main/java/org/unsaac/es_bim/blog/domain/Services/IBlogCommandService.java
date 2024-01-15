package org.unsaac.es_bim.blog.domain.Services;

import org.unsaac.es_bim.blog.domain.model.commands.CreateBlogCommand;

public interface IBlogCommandService {
    public Long handle(CreateBlogCommand command);
}
