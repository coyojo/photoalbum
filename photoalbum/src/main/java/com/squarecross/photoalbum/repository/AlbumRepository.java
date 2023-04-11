package com.squarecross.photoalbum.repository;
//Repository는 DB에 접근하기 위한 메서드들을 정의
//Domain에서 DB객체를 정의했다면 Repository에서는
// 해당 객체들을 저장,추출,검색 등 다양한 DB에 접근하는 기능들을 정의
import com.squarecross.photoalbum.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
    // JpaRepository < entity 클래스이름, primary key의 type > 이 들어간다.

    //앨범명으로 앨범 테이블을 검색하는 Repository 메서드
    Optional<Album> findByAlbumName(String name);
    //Optinal은 Null 값을 가질 수 없다.
}

