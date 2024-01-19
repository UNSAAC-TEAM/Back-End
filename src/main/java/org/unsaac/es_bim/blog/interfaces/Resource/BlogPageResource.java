package org.unsaac.es_bim.blog.interfaces.Resource;

import org.unsaac.es_bim.blog.domain.model.aggregate.Blog;

import java.util.List;

public record BlogPageResource(int currentPage, List<PageableBlogResource> content) {
}
