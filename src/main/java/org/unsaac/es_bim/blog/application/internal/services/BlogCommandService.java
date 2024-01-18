package org.unsaac.es_bim.blog.application.internal.services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.blog.domain.Services.IBlogCommandService;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.commands.CreateBlogCommand;
import org.unsaac.es_bim.blog.infrastructure.jpa.BlogRepository;

import org.unsaac.es_bim.profiles.domain.services.IProfileCommandServices;


@Service
public class BlogCommandService implements IBlogCommandService {
    private final BlogRepository blogRepository;
    private final IProfileCommandServices profileCommandServices;
    private static final Logger logger = LogManager.getLogger(BlogCommandService.class);
    public BlogCommandService(BlogRepository blogRepository, IProfileCommandServices profileCommandServices) {
        this.blogRepository = blogRepository;

        this.profileCommandServices = profileCommandServices;
    }

    @Override
    public Long handle(CreateBlogCommand command) {
        logger.info("Dentro del servicio de blog: "+command.toString());
        var response=this.profileCommandServices.handle(command);
        return response;
    }
}
