package com.example.blog.mappers;

import com.example.blog.domain.PostStatus;
import com.example.blog.domain.dtos.CategoryResponsedto;
import com.example.blog.domain.dtos.CreateCategoryRequestDto;
import com.example.blog.domain.entities.Category;
import com.example.blog.domain.entities.Post;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target="postCount",source = "posts",qualifiedByName = "calculatePostCount")
    CategoryResponsedto toCategoryResponse(Category category);

    Category toCategory(CreateCategoryRequestDto request);
    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(posts==null){
            return 0;
        }
        long count=posts.stream().filter(post-> PostStatus.PUBLISHED.equals(post.getStatus())).count();
        return count;
    }
}
