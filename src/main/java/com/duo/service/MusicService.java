package com.duo.service;

import com.duo.entity.Music;
import com.duo.entity.Musictype;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface MusicService {

    //获取歌曲类型
    List<Musictype> getAllMusicType();

    //添加歌曲类型
    int insertMusicType(Musictype musicType);

    //更新歌曲类型
    int updateMusicTypeById(Musictype musicType);

    //删除歌曲类型
    int deleteMusicTypeById(Integer id);

    //删除多条歌曲类型
//    int deletesMusicTypeById(Integer id);

    //获取所有歌曲
    List<Music> getAllMusic();

    //添加歌曲
    String insertMusic(String musicName, String musicTypeName, Integer musicHot, String singerName, String lyricUrl, MultipartFile myFile, MultipartFile myMusic) throws IOException;

    //根据id查询歌曲
    Music selectMusicById(Integer musicid);
    
    //根据id删除歌曲
    String deleteMusicById(Integer musicid);

    //添加歌曲
    String updateMusic(Integer musicid, String musicName, String musicTypeName, Integer musicHot, String singerName, String lyricUrl, MultipartFile myFile, MultipartFile myMusic) throws IOException;

    //批量删除歌曲
    void deletesMusic(Integer[] ids);

    List<Music> randomMusic();

    List<Music> typeMusic(Musictype musicType);

    List<Music> hotMusic();

    void deletesMusicType(Integer[] ids);

    List<Music> sameMusic(Music music);

    List<Music> selectMusicBySingerid(Music music);

    Music selectMusicByName(Music music);
}
