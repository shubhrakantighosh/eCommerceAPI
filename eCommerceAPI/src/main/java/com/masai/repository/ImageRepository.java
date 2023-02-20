package com.masai.repository;

import com.masai.DTO.ImageDTO;
import com.masai.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Images,Integer> {

    @Query("select new com.masai.DTO.ImageDTO(image.imageId,image.imageURL) from Images image where image.product.productId=?1")
    List<ImageDTO>getImagesByProductId(Integer productId);
}
