package com.squarecross.photoalbum.domain;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="album", schema="photo_album", uniqueConstraints = {@UniqueConstraint(columnNames = "album_id")})
public class Album {

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "album", cascade = CascadeType.ALL)
    private List<Photo> photos;
    // album 객체에서 자기 album에 포함된 모든 photo를 조회하려면 List<Photo> 타입의 필드를 가져야 한다.
    //OneToMany를 쓴 이유는 album은 하나인데 여러 Photo를 포함하닌 일대다 매핑을 해준다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", unique = true, nullable = false)
    private Long albumId;

    @Column(name = "album_name", unique = false, nullable = false)
    private String albumName;

    @Column(name="created_at", unique = false, nullable = true)
    @CreationTimestamp
    private Date createdAt;

    public Album(){};

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

}