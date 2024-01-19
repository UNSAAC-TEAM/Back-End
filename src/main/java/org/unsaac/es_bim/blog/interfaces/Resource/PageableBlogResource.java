package org.unsaac.es_bim.blog.interfaces.Resource;

import java.util.Date;

public record PageableBlogResource(Long id, String author, String label, String imageUrl, String title, String description,
                                   Date publishDate) {
}
