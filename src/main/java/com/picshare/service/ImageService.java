package com.picshare.service;

import com.picshare.entity.Image;

import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
public interface ImageService {
    //一周最热、最新上传、个性化推荐、板块分二：游戏、动画
    public void addImage(Image image);
    public Image getImageById(long imageId);
    //public List<Image> getImageOrderByColumnWithLimit(boolean isDesc ,String column,int Limit);
    public List<Image> getImagesBy(int limit,String column,boolean isDesc);
    public List<Image> getImagesBy(int limit,String column,String genre,boolean isDesc);
    public List<Image> getImagesByKey(String key);
    public void update(Image image);
    public void deleteImage(Image image);
}
