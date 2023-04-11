package com.squarecross.photoalbum.service;

import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.domain.Photo;
import com.squarecross.photoalbum.dto.*;
import com.squarecross.photoalbum.repository.AlbumRepository;
import com.squarecross.photoalbum.repository.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AlbumServiceTest {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AlbumService albumService;

    @Autowired
    PhotoRepository photoRepository;

    @Test
    void getAlbum() {
        Album album = new Album();
        album.setAlbumName("테스트");
        Album savedAlbum = albumRepository.save(album);

        AlbumDto resAlbum = albumService.getAlbum(savedAlbum.getAlbumId());
        assertEquals("테스트", resAlbum.getAlbumName());

    }

    @Test
    void getAlbumName(){
        Album album = new Album();
        album.setAlbumName("테스트2");
        Album savedAlbum = albumRepository.save(album);

        Album resAlbum = albumService.getAlbumName(savedAlbum.getAlbumName());
        assertEquals("테스트2", resAlbum.getAlbumName());

    }

    @Test
    void testPhotoCount(){
        //1. 앨범 생성
        Album album = new Album();
        album.setAlbumName("테스트3");
        Album savedAlbum = albumRepository.save(album);

        //사진을 생성하고 , setAlbum을 통해 앨범을 지정해준 이후, repository에 사진을 저장
        Photo photo1 = new Photo();
        photo1.setFileName("사진1");
        photo1.setAlbum(savedAlbum);
        photoRepository.save(photo1);

        Photo photo2 = new Photo();
        photo2.setFileName("사진2");
        photo2.setAlbum(savedAlbum);
        Photo savedPhoto = photoRepository.save(photo2);

        AlbumDto albumDto = albumService.getAlbum(savedAlbum.getAlbumId());
        assertEquals(2,albumDto.getCount());
    }


}