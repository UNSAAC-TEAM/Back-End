package org.unsaac.es_bim.blog.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unsaac.es_bim.blog.domain.Services.IBlogCommandService;
import org.unsaac.es_bim.blog.domain.model.commands.CreateBlogCommand;
import org.unsaac.es_bim.blog.interfaces.Resource.CreateBlogResource;


@RestController
@RequestMapping(value = "api/v1/blog",produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {
    private final IBlogCommandService blogCommandService;

    public BlogController(IBlogCommandService blogCommandService) {
        this.blogCommandService = blogCommandService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Long> createBlog(@PathVariable("userId")Long userId, @RequestBody CreateBlogResource createBlogResource){
        var command=new CreateBlogCommand(createBlogResource.title(), createBlogResource.label(), createBlogResource.imageUrl(), createBlogResource.description(), createBlogResource.content(),userId);
        var response=this.blogCommandService.handle(command);
        return ResponseEntity.ok(response);
    }
}
