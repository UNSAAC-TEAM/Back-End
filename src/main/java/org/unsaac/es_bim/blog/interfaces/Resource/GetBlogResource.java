package org.unsaac.es_bim.blog.interfaces.Resource;

import java.util.Date;

public record GetBlogResource (Long id, String title, String label, String imageUrl, String description, String content,
                               Date publishDate,Long authorId,String authorFullName){
}
