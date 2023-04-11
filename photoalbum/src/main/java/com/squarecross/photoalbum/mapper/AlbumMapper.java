package com.squarecross.photoalbum.mapper;
//Mapper는 DTO에서 Domain 또는 Domain에서 DTO로 변환해주는 컴포넌트
//예를 들어 cilent에서 받은 데이터를 DB에 넣기 위해 Domain으로 변환하거나
//DB에서 받은 값을 Client에게 돌려 주기 위해 Domain에서 DTO로 변환하는 작업을 담당
import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.dto.AlbumDto;

public class AlbumMapper {

    //mapper는 파일 내 Domain을 받아서 DTO를 리턴하는 스태틱 메서드를 만든다.
    public static AlbumDto convertToDto(Album album){
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAlbumId(album.getAlbumId());
        //album도메인에서 Id를 받아와서 DTO에 저장
        albumDto.setAlbumName(album.getAlbumName());
        // album 도메인에서 name을 받아와 DTO에 저장
        albumDto.setCreatedAt(album.getCreatedAt());
        return albumDto;
    }
    // Count 값은 Model인 Domain에 없기 때문에 별도로 쿼리해서 Dto에 세팅 필요
    //그러기 위해서는 Album ID에 해당하는 Photo 레코드가 몇개 있는지 확인해야 하므로 Photo 테이블을
    //조회 할 수 있는 PhotoRepository를 만들어야 한다.
}
