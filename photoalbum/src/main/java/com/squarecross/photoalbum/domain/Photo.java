package com.squarecross.photoalbum.domain;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

//@Entity는 DB테이블과 1-1로 매핑되는 클래스 단위로 Entity 객체 하나가 DB하나의 레코드 즉 하나의 ROW를 의미
@Entity
@Table(name = "photo", schema="photo_album",uniqueConstraints = {@UniqueConstraint(columnNames = "photo_id")})
public class Photo {

    //@Id는 해당 Entity의 Primary Key로 사용한다는 의미
    //@Id는 같은 테이블내애서 중복될 값이 있을 수 없다.
    //@GenerateValue는 @id 값을 새롭게 부여할 때 사용하는 방법에 대한 정보를 입력
    //strategy = GenerationType.IDENTITY눈
    //가장 최근 id에 +1 을 해서 다음 아이디를 생성합니다.
    //@Column은 album 테이블의 매핑되는 column 정보를 입력
    //name은 컬럼명, unique는 중복불가, nullable은 null값 허용 여부

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_id", unique = true,nullable = false)
    private Long photoId;

    @Column(name = "file_name", unique = false,nullable = true)
    private String fileName;

    @Column(name = "thumb_url", unique = false, nullable = true)
    private String thumbUrl;

    @Column(name = "original_url", unique = false, nullable = true)
    private String originalUrl;

    @Column(name = "file_size", unique = false, nullable = true)
    private int fileSize;


    //  @CreationTimestamp는 새로운 photo를 업로드하여 DB INSERT할 때 자동으로 현재 시간을 입력해줍니다
    @Column(name = "uploaded_at", unique = false, nullable = true)
    @CreationTimestamp
    private Date uploadedAt;



    //Foreign Key 설정
    //Photo 여러장이 (Many) 하나의 앨범 (One) 에 속하므로 ManyToOne
    //FetchType.LAZY는 Album 정보가 필요할 때만 불러오는 방식
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id") // album의 PK를 참조하므로 name="album_id"를 적어준다.
    private Album album;

    public Photo(){};

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
