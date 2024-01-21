package org.unsaac.es_bim.blog.application.internal.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unsaac.es_bim.blog.domain.Services.IBlogQueryService;
import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;
import org.unsaac.es_bim.blog.domain.model.queries.DeleteBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetPageOfBlogs;
import org.unsaac.es_bim.blog.infrastructure.jpa.BlogRepository;
import org.unsaac.es_bim.blog.interfaces.Resource.BlogPageResource;
import org.unsaac.es_bim.blog.interfaces.Resource.GetBlogResource;
import org.unsaac.es_bim.blog.interfaces.Resource.GetManageableBlogResource;
import org.unsaac.es_bim.blog.interfaces.Resource.PageableBlogResource;
import org.unsaac.es_bim.profiles.application.external.ProfileFacade;

import java.util.List;
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
    public GetBlogResource handle(GetBlogByIdQuery query) {
        var blog=this.blogRepository.findById(query.blogId());
        if(blog.isEmpty()){
            throw new RuntimeException("Blogs doesn't exist");
        }
        var response=new GetBlogResource(blog.get().getId(),blog.get().getTitle(),blog.get().getLabel(),blog.get().getImageUrl(),blog.get().getDescription(),blog.get().getContent(),blog.get().getPublishDate(),blog.get().getProfile().getId(),blog.get().getProfile().getFullName());
        return response;
    }

    @Override
    public List<GetManageableBlogResource> handle() {
        List<Blog> blogs = this.blogRepository.findAll();

        // Convertir la lista de entidades Blog a una lista de recursos GetManageableBlogResource
        List<GetManageableBlogResource> manageableBlogs = blogs.stream()
                .map(this::convertToManageableBlogResource)
                .collect(Collectors.toList());

        return manageableBlogs;
    }
    private GetManageableBlogResource convertToManageableBlogResource(Blog blog) {
        return new GetManageableBlogResource(
                blog.getId(),
                blog.getTitle(),
                blog.getLabel(),
                blog.getImageUrl(),
                blog.getPublishDate()
        );
    }
    @Override
    public BlogPageResource handle(GetPageOfBlogs query) {
        Pageable pageable= PageRequest.of(query.page(), query.itemsPerPage() );
        var pageBlogs=this.blogRepository.findAll(pageable);
        if(query.page()>=pageBlogs.getTotalPages()){
            throw new IllegalArgumentException("Requested page is out of range");
        }
        var listBlogs=pageBlogs.getContent().stream().map(this::convertToPageableBlogResource).toList();
        BlogPageResource response=new BlogPageResource(this.blogRepository.findAll().size(),listBlogs);
        return response;
    }

    @Override
    public Long hadle(DeleteBlogByIdQuery query) {
        var blog =this.blogRepository.findById(query.blogId());
        if(blog.isEmpty()){
            throw new RuntimeException("Blog doesn't exist!!");
        }
        this.blogRepository.delete(blog.get());
        return 1L;
    }

    private PageableBlogResource convertToPageableBlogResource(Blog blog){
        int totalBlogsQuantity=this.blogRepository.findAll().size();
        PageableBlogResource blogResource=new PageableBlogResource(blog.getId(), totalBlogsQuantity,blog.getLabel(),blog.getImageUrl(), blog.getTitle(), blog.getDescription(),blog.getPublishDate(),blog.getProfile().getFullName());
        return blogResource;
    }
}
