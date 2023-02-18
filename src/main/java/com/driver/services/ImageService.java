package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;

    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blog,description,dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String [] screenArray = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        String [] imageArray = imageDimensions.split("X");

        int screenLength = Integer.parseInt(screenArray[0]);
        int screenBreadth = Integer.parseInt(screenArray[1]);

        int imageLength = Integer.parseInt(imageArray[0]);
        int imageBreadth = Integer.parseInt(imageArray[1]);

        return noOfImages(screenLength,screenBreadth,imageLength,imageBreadth);
    }
    private int noOfImages(int screenLength, int screenBreadth, int imageLength, int imageBreadth) {
        int lenC = screenLength/imageLength;
        int lenB = screenBreadth/imageBreadth;
        return lenC*lenB;
    }
}
