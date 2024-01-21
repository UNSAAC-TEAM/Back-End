package org.unsaac.es_bim.blog.interfaces.Resource;

import java.util.Date;

public record GetPreviewBlogResource(Long id, String title, String label, String imageUrl,
                                     Date publishDate) {
}
