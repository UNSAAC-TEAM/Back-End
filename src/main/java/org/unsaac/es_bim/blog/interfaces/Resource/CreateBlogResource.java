package org.unsaac.es_bim.blog.interfaces.Resource;

public record CreateBlogResource(String title, String label, String imageUrl, String description, String content) {
}
