package com.squarecross.photoalbum.service;
// service 레이어는 비즈니스 로직을 처리
//Controller에서 DTO를 받아 데이터가 맞게 들어왔는지 검증 후 DB요청이 필요한 경우
// Repository를 호출

import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.mapper.AlbumMapper;
import com.squarecross.photoalbum.repository.AlbumRepository;
import com.squarecross.photoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    //album 정보 조회하기 메서드 만들기
    public AlbumDto getAlbum(Long albumId){
        Optional<Album> res = albumRepository.findById(albumId);
        //AlbumRepository에서 Album ID로 조회했을 때 찾지 못해서 반환이 되지 않는 경우를 대비해서 Optional<Album> 리턴값을 갖습니다.
        // DB에 접근하기 위한 메서드는 Repository에 있으니 albumRepository를 호출한 것
        if(res.isPresent()){ //isPresernt가 true일 경우
           // res.get();의 리턴값은 albumId에 해당하는 album 도메인 이므로 그것을 DTO로 변환해 줘야 한다.
            // -> Albummapper에서 converToDto 메서드 정의해놓았으니 그것을 호출해서 사용하면 된다.
            AlbumDto albumDto = AlbumMapper.convertToDto(res.get());
            albumDto.setCount(photoRepository.countByAlbum_AlbumId(albumId));
            return albumDto;
            //isPresent()로 값이 있는지 확인후 있는 경우 res.get()으로 Album엔티티를 반환
        }else {
            throw new EntityNotFoundException(String.format("앨범 아이디 %d로 조회되지 않았습니다", albumId));
            // 없는 경우 EntityNotFoundException를 에러 메세지와 함께 throw 해준다.
        }
    }

    public Album getAlbumName(String albumName){
        Optional<Album> res = albumRepository.findByAlbumName(albumName);
        if (res.isPresent()){
            return res.get();
        }else {
            throw new EntityNotFoundException(String.format("앨범 명 %s로 조회되지 않았습니다",albumName));
        }

    }


}


