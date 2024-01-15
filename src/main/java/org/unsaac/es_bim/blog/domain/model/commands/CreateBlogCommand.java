package org.unsaac.es_bim.blog.domain.model.commands;


public record CreateBlogCommand(String title, String label, String imageUrl, String description, String content,
                                Long authorId) {
}
