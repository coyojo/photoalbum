package com.squarecross.photoalbum.repository;

// Count 값은 Model인 Domain에 없기 때문에 별도로 쿼리해서 Dto에 세팅 필요
//그러기 위해서는 Album ID에 해당하는 Photo 레코드가 몇개 있는지 확인해야 하므로 Photo 테이블을
//조회 할 수 있는 PhotoRepository를 만들어야 한다.

import com.squarecross.photoalbum.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    int countByAlbum_AlbumId(Long AlbumId);

    //countBy 뒤에 붙는 Album_AlbumId는 참조하는 Album 엔터티의 AlbumId 값을 확인해 일치하는 Photo 레코드를 찾는다.
}
