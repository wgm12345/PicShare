package com.picshare.dao;

import com.picshare.entity.Image;
import com.picshare.entity.User;
import org.hibernate.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by wgm on 17/3/24.
 */
public interface ImageDao {
    public void addImage(Image image);
    public Image getImageById(long imageId);
    //limit:取得数据限制，column 排序字段，isDesc 是否降序排
    public List<Image> getImagesBy(int limit,String column,boolean isDesc);
    //limit:取得数据限制，column 排序字段，isDesc 是否降序排，genre ：图片类型是genre
    public List<Image> getImagesBy(int limit,String column,String genre,boolean isDesc);
    public List<Image> getImagesByKey(String key);
    public void update(Image image);
    public void deleteImage(Image image);
}
