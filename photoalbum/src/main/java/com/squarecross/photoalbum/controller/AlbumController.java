package com.squarecross.photoalbum.controller;


import com.squarecross.photoalbum.domain.Album;
import com.squarecross.photoalbum.dto.AlbumDto;
import com.squarecross.photoalbum.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/query",method=RequestMethod.GET)
    public ResponseEntity<AlbumDto> getAlbumByQuery(@RequestParam final long albumId){
        AlbumDto album = albumService.getAlbum(albumId);
        return new ResponseEntity<>(albumService.getAlbum(albumId),HttpStatus.OK);
    }

    //주소창에 http://localhost:8088/albums/query?albumId=3 과 같이 입력

    @PostMapping("/json_body")
    public ResponseEntity<AlbumDto> getAlbumByJson(@RequestBody final AlbumDto albumDto){
        AlbumDto album = albumService.getAlbum(albumDto.getAlbumId());
        return new ResponseEntity<>(album,HttpStatus.OK);
    }

    // http://localhost:8088/albums/json_body
}