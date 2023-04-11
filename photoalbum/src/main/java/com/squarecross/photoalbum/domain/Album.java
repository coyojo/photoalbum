package com.squarecross.photoalbum.domain;
// Domain은 DB안에서 쓰일 필드들을 정의, 해당 클래스를 만들면 DB에 테이블이 생성된다.
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
import java.util.List;

//uniqueConstraints: 반복되면 안되는 제약조건 정의
@Entity
@Table(name="album", schema="photo_album", uniqueConstraints = {@UniqueConstraint(columnNames = "album_id")})
public class Album {

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "album", cascade = CascadeType.ALL)
    private List<Photo> photos;
    // album 객체에서 자기 album에 포함된 모든 photo를 조회하려면 List<Photo> 타입의 필드를 가져야 한다.
    //OneToMany를 쓴 이유는 album은 하나인데 여러 Photo를 포함하닌 일대다 매핑을 해준다.
    //mappedBy는 FK의 주인이 아닐 경우 사용합니다.

    @Id  //해당 Entity의 Primary Key로 사용한다는 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", unique = true, nullable = false)
    private Long albumId;

    //@GeneratedValue는 @id 값을 새롭게 부여할 때 사용하는 방법에 대한 정보를 입력합니다,strategy = GenerationType.IDENTITY
    // 가장 최근 id에 +1 을 해서 다음 아이디를 생성합니다.
    //@Column  - album 테이블의 매핑되는 column 정보를 입력합니다



    @Column(name = "album_name", unique = false, nullable = false)
    private String albumName;

    @Column(name="created_at", unique = false, nullable = true)
    @CreationTimestamp
    private Date createdAt;
    //@CreationTimestamp 새로운 앨범을 생성해 DB INSERT할 때 자동으로 현재 시간을 입력해줍니다.


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