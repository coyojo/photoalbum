package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    //album 정보 조회하기 메서드 만들기
    public Album getAlbum(Long albumId){
        Optional<Album> res = albumRepository.findById(albumId);
        //AlbumRepository에서 Album ID로 조회했을 때 찾지 못해서 반환이 되지 않는 경우를 대비해서 Optional<Album> 리턴값을 갖습니다.
        if(res.isPresent()){
            return res.get();
            //isPresent()로 값이 있는지 확인후 있는 경우 res.get()으로 Album엔티티를 반환
        }else {
            throw new EntityNotFoundException(String.format("앨범 아이디 %d로 조회되지 않았습니다", albumId));
            // 없는 경우 EntityNotFoundException를 에러 메세지와 함께 throw 해준다.
        }
    }
}
