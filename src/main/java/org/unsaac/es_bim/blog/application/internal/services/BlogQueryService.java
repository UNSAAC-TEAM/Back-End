package org.unsaac.es_bim.blog.application.internal.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.blog.domain.Services.IBlogQueryService;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetPageOfBlogs;
import org.unsaac.es_bim.blog.infrastructure.jpa.BlogRepository;
import org.unsaac.es_bim.blog.interfaces.Resource.BlogPageResource;
import org.unsaac.es_bim.blog.interfaces.Resource.PageableBlogResource;
import org.unsaac.es_bim.profiles.application.external.ProfileFacade;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogQueryService implements IBlogQueryService {
    private final BlogRepository blogRepository;
    private final ProfileFacade profileFacade;
    public BlogQueryService(BlogRepository blogRepository, ProfileFacade profileFacade) {
        this.blogRepository = blogRepository;
        this.profileFacade = profileFacade;
    }

    @Override
    public Optional<Blog> handle(GetBlogByIdQuery query) {
        var blog=this.blogRepository.findById(query.blogId());

        return blog;
    }

    @Override
    public BlogPageResource handle(GetPageOfBlogs query) {
        Pageable pageable= PageRequest.of(query.page(), query.itemsPerPage() );
        var pageBlogs=this.blogRepository.findAll(pageable);
        if(query.page()>=pageBlogs.getTotalPages()){
            throw new IllegalArgumentException("La página solicitada está fuera de rango.");
        }
        var listBlogs=pageBlogs.getContent().stream().map(this::convertToPageableBlogResource).toList();
        BlogPageResource response=new BlogPageResource(this.blogRepository.findAll().size(),listBlogs);
        return response;
    }

    private PageableBlogResource convertToPageableBlogResource(Blog blog){
        int totalBlogsQuantity=this.blogRepository.findAll().size();
        PageableBlogResource blogResource=new PageableBlogResource(blog.getId(), totalBlogsQuantity,blog.getLabel(),blog.getImageUrl(), blog.getTitle(), blog.getDescription(),blog.getPublishDate());
        return blogResource;
    }
}
