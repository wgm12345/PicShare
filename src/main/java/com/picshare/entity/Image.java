package com.picshare.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wgm on 17/3/24.
 */
@Entity
@Table(name = "Image")
public class Image {

    //properties
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "persistenceGenerator")
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")//定义生成策略，可以不用在数据库中设置自增
    private long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genreId",
            foreignKey = @ForeignKey(name = "FK_Image_genreId")
    )
    private Genre genre;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uploaderId",
            foreignKey = @ForeignKey(name = "FK_Image_uploaderId")
    )
    private User uploader;

    @CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    private String briefIntro;

    private String imgPath;

    private int totalHits;
    private int commentsSum;
    private int collectionSum;
    private int weekHits;

    private boolean isDeleted;
    private boolean isPassed;

    private int likeSum;

    @OneToMany(mappedBy = "image",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    //constructor


    //getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }


    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public int getCommentsSum() {
        return commentsSum;
    }

    public void setCommentsSum(int commentsSum) {
        this.commentsSum = commentsSum;
    }

    public int getCollectionSum() {
        return collectionSum;
    }

    public void setCollectionSum(int collectionSum) {
        this.collectionSum = collectionSum;
    }

    public int getWeekHits() {
        return weekHits;
    }

    public void setWeekHits(int weekHits) {
        this.weekHits = weekHits;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setIsPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

    public int getLikeSum() {
        return likeSum;
    }

    public void setLikeSum(int likeSum) {
        this.likeSum = likeSum;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        return getId() == image.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
