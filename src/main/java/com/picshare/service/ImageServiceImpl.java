package com.picshare.service;

import com.picshare.dao.ImageDao;
import com.picshare.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageDao imageDao;

    @Override
    public void addImage(Image image) {
        this.imageDao.addImage(image);
    }

    @Override
    public Image getImageById(long imageId) {
        return  this.imageDao.getImageById(imageId);
    }

    @Override
    public List<Image> getImagesBy(int limit, String column, boolean isDesc) {
        return  this.imageDao.getImagesBy(limit, column, isDesc);
    }
    @Override
    public List<Image> getImagesBy(int limit, String column,String genre, boolean isDesc) {
        return  this.imageDao.getImagesBy(limit,column,genre,isDesc);
    }

    @Override
    public List<Image> getImagesByKey(String key) {
        return this.imageDao.getImagesByKey(key);
    }

    @Override
    public void update(Image image) {
        this.imageDao.update(image);
    }

    @Override
    public void deleteImage(Image image) {
        this.imageDao.deleteImage(image);
    }
}
