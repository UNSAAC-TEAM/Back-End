package org.unsaac.es_bim.blog.application.internal.services;

import org.springframework.stereotype.Service;
import org.unsaac.es_bim.blog.domain.Services.IBlogCommandService;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.commands.CreateBlogCommand;
import org.unsaac.es_bim.blog.infrastructure.jpa.BlogRepository;
import org.unsaac.es_bim.profiles.application.external.ProfileFacade;

@Service
public class BlogCommandService implements IBlogCommandService {
    private final BlogRepository blogRepository;
    private final ProfileFacade profileFacade;
    public BlogCommandService(BlogRepository blogRepository, ProfileFacade profileFacade) {
        this.blogRepository = blogRepository;
        this.profileFacade = profileFacade;
    }

    @Override
    public Long handle(CreateBlogCommand command) {
        return null;
    }
}
