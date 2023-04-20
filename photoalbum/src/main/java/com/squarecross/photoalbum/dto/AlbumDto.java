package com.squarecross.photoalbum.dto;
//DTO는 Data Transfer Object
//DTO는 API 내부에서 데이터를 들고다니는 캐리어로 비유할 수 있다.
//API의 여러 레이어 안에서 어디서든지 DTO 안에 있는 데이터를 조회할 수 있도록 하는 Object이다.
import java.util.Date;
import java.util.List;

public class AlbumDto {
    Long albumId;
    String albumName;
    Date createdAt;
    int count;

    private List<String> thumbUrls;


    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getThumbUrls() {
        return thumbUrls;
    }

    public void setThumbUrls(List<String> thumbUrls) {
        this.thumbUrls = thumbUrls;
    }

}
