package com.duo.controller;

import com.duo.entity.Music;
import com.duo.entity.Musictype;
import com.duo.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @ResponseBody
    public List<Musictype> getAllMusicType() {
        return musicService.getAllMusicType();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertMusicType(@RequestBody Musictype musicType) {
        int result = musicService.insertMusicType(musicType);
        if(result != 0){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateMusicTypeById(@RequestBody Musictype musicType) {
        int result = musicService.updateMusicTypeById(musicType);
        if(result != 0){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteMusicTypeById(@RequestBody Musictype musicType) {
        int result = musicService.deleteMusicTypeById(musicType.getMusictypeid());
        return "success";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Music> getAllMusic() {
        return musicService.getAllMusic();
    }

    //    @CrossOrigin
    @RequestMapping(value = "/insertMusic", method = RequestMethod.POST)
//    @PostMapping(value = "insertMusic")
    @ResponseBody
    public String insertMusic(@RequestParam("musicname") String musicName, @RequestParam("musictypename") String musicTypeName, @RequestParam("musichot") Integer musicHot, @RequestParam("singername") String singerName, @RequestParam("lyricurl") String lyricUrl, @RequestParam("img") MultipartFile myFile, @RequestParam("music") MultipartFile myMusic) throws IOException {
        return musicService.insertMusic(musicName, musicTypeName, musicHot, singerName, lyricUrl, myFile, myMusic);
    }

    @RequestMapping(value = "/findMusic", method = RequestMethod.POST)
    @ResponseBody
    public Music selectMusicById(@RequestBody Music music) {
        return musicService.selectMusicById(music.getMusicid());
    }

    @RequestMapping(value = "/deleteMusic", method = RequestMethod.POST)
    @ResponseBody
    public String deleteMusicById(@RequestBody Music music) {
        return musicService.deleteMusicById(music.getMusicid());
    }

    //    @CrossOrigin
    @RequestMapping(value = "/updateMusic", method = RequestMethod.POST)
//    @PostMapping(value = "updateMusic")
    @ResponseBody
    public String updateMusic(@RequestParam("musicid") Integer musicId, @RequestParam("musicname") String musicName, @RequestParam("musictypename") String musicTypeName, @RequestParam("musichot") Integer musicHot, @RequestParam("singername") String singerName, @RequestParam("lyricurl") String lyricUrl, @RequestParam("img") MultipartFile myFile, @RequestParam("music") MultipartFile myMusic) throws IOException {
        return musicService.updateMusic(musicId, musicName, musicTypeName, musicHot, singerName, lyricUrl, myFile, myMusic);
    }

    @RequestMapping(value = "/deletesMusic", method = RequestMethod.POST)
    @ResponseBody
    public String deletesMusic(@RequestBody Integer[] ids) {
        musicService.deletesMusic(ids);
        return "success";
    }

    @RequestMapping(value = "/random", method = RequestMethod.POST)
    @ResponseBody
    public List<Music> randomMusic() {
        return musicService.randomMusic();
    }

    @RequestMapping(value = "/typeMusic", method = RequestMethod.POST)
    @ResponseBody
    public List<Music> typeMusic(@RequestBody Musictype musicType) {
        return musicService.typeMusic(musicType);
    }

    @RequestMapping(value = "/hot", method = RequestMethod.POST)
    @ResponseBody
    public List<Music> hotMusic() {
        return musicService.hotMusic();
    }

    @RequestMapping(value = "/deletesMusicType", method = RequestMethod.POST)
    @ResponseBody
    public String deletesMusicType(@RequestBody Integer[] ids) {
        musicService.deletesMusicType(ids);
        return "success";
    }

    @RequestMapping(value = "/sameMusic", method = RequestMethod.POST)
    @ResponseBody
    public List<Music> sameMusic(@RequestBody Music music) {
        return musicService.sameMusic(music);
    }

    @RequestMapping(value = "/singer", method = RequestMethod.POST)
    @ResponseBody
    public List<Music> selectMusicBySingerid(@RequestBody Music music) {
        return musicService.selectMusicBySingerid(music);
    }

    @RequestMapping(value = "/searchMusic", method = RequestMethod.POST)
    @ResponseBody
    public Music selectMusicByName(@RequestBody Music music) {
        return musicService.selectMusicByName(music);
    }
}
