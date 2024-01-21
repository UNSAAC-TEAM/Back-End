package org.unsaac.es_bim.blog.interfaces.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unsaac.es_bim.blog.domain.Services.IBlogCommandService;
import org.unsaac.es_bim.blog.domain.Services.IBlogQueryService;
import org.unsaac.es_bim.blog.domain.model.commands.CreateBlogCommand;
import org.unsaac.es_bim.blog.domain.model.queries.DeleteBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetBlogByIdQuery;
import org.unsaac.es_bim.blog.domain.model.queries.GetPageOfBlogs;
import org.unsaac.es_bim.blog.domain.model.queries.GetRecommendedBlogsQuery;
import org.unsaac.es_bim.blog.interfaces.Resource.BlogPageResource;
import org.unsaac.es_bim.blog.interfaces.Resource.CreateBlogResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping(value = "api/v1/blog",produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {
    private final IBlogCommandService blogCommandService;
    private final IBlogQueryService blogQueryService;
    private static final Logger logger = LogManager.getLogger(BlogController.class);
    public BlogController(IBlogCommandService blogCommandService, IBlogQueryService blogQueryService) {
        this.blogCommandService = blogCommandService;
        this.blogQueryService = blogQueryService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Long> createBlog(@PathVariable("userId")Long userId, @RequestBody CreateBlogResource createBlogResource){
        logger.info(createBlogResource.toString());
        var command=new CreateBlogCommand(createBlogResource.title(), createBlogResource.label(), createBlogResource.imageUrl(), createBlogResource.description(), createBlogResource.content(),userId);
        logger.info(command.toString());
        var response=this.blogCommandService.handle(command);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get/{blogId}")
    public ResponseEntity<?> getBlogById(@PathVariable("blogId")Long blogId){
        var query=new GetBlogByIdQuery(blogId);
        var response=this.blogQueryService.handle(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/Blogs")
    public ResponseEntity<?> getAllBlogs(){
        var response=this.blogQueryService.handle();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{blogQuantity}/recommended")
    public ResponseEntity<?> getRecommendedBlogs(@PathVariable("blogQuantity")int blogQuantity){
        var response=this.blogQueryService.handle(new GetRecommendedBlogsQuery(blogQuantity));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/page/")
    public ResponseEntity<?> getBlogsByPage(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "9")int itemsPerPage){
        var query=new GetPageOfBlogs(page,itemsPerPage);
        BlogPageResource response=this.blogQueryService.handle(query);
        return ResponseEntity.ok(response);

    }


    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<?> deleteBlogById(@PathVariable("blogId")Long blogId){
        var query=new DeleteBlogByIdQuery(blogId);
        var response= this.blogQueryService.hadle(query);
        return ResponseEntity.ok(response);

    }
}
