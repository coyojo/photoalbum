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

import java.util.List;
import java.util.concurrent.TimeUnit;

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


    @Test
    void testAlbumRepository() throws InterruptedException {
        Album album1 = new Album();
        Album album2 = new Album();
        album1.setAlbumName("aaaa");
        album2.setAlbumName("aaab");

        albumRepository.save(album1);
        TimeUnit.SECONDS.sleep(1);//앨범이 생성되는 시간차를 벌리기위해 두번째 앨범 생성 1초 딜레이
        albumRepository.save(album2);

        //최신순 정렬, 두번째로 생성한 앨범이 먼저 나와야 한다.
        List<Album> resDate = albumRepository.findByAlbumNameContainingOrderByCreatedAtDesc("aaa");
        assertEquals("aaab",resDate.get(0).getAlbumName());//0번쨰 index가 두번째 앨범명 aaab인지 체크
        assertEquals("aaaa",resDate.get(1).getAlbumName());//1번째 index가 첫번째 앨범명 aaaa인지 체크
        assertEquals(2,resDate.size());// aaa 이름을 가진 다른 앨범이 없다는 가정하에 , 검색 키워드에 해당하는 앨범 필터링 체크


        //앨범명 정렬, aaaa -> aaab 기준으로 나와야합니다
        List<Album> resName = albumRepository.findByAlbumNameContainingOrderByAlbumNameAsc("aaa");
        assertEquals("aaaa", resName.get(0).getAlbumName()); // 0번째 Index가 두번째 앨범명 aaaa 인지 체크
        assertEquals("aaab", resName.get(1).getAlbumName()); // 1번째 Index가 두번째 앨범명 aaab 인지 체크
        assertEquals(2, resName.size()); // aaa 이름을 가진 다른 앨범이 없다는 가정하에, 검색 키워드에 해당하는 앨범 필터링 체크
    }

    }


