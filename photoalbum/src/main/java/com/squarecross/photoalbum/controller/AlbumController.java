package com.squarecross.photoalbum.controller;


import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Spring에서 관리하는 Controller라는 것을 나타내고 Rest API목적으로 사용할 것임을 나타냄
@RequestMapping("/albums")  //https://<url>/albums 으로 들어오는 모든 요청은 해당 컨트롤러의 메서드에서 처리하게 된다.
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @RequestMapping(value = "/{albumId}",method = RequestMethod.GET)
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable("albumId") final long albumId){
        AlbumDto album = albumService.getAlbum(albumId);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }
}
