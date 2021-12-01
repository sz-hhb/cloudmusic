package com.duo.service.impl;

import com.duo.entity.Music;
import com.duo.entity.Musictype;
import com.duo.entity.Singer;
import com.duo.mapper.MusicMapper;
import com.duo.mapper.MusictypeMapper;
import com.duo.mapper.SingerMapper;
import com.duo.service.MusicService;
import com.duo.util.EncodingTool;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service("musicService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private MusictypeMapper musictypeMapper;

    @Autowired
    private SingerMapper singerMapper;

    public List<Musictype> getAllMusicType() {
        return musictypeMapper.selectAllMusicType();
    }

    public int insertMusicType(Musictype musicType) {
        if (musictypeMapper.selectByMusicTypeName(musicType.getMusictypename()).size() != 0) {
            return 0;
        }
        return musictypeMapper.insert(musicType);
    }

    public int updateMusicTypeById(Musictype musicType) {
        if (musictypeMapper.selectByMusicTypeName(musicType.getMusictypename()).size() != 0) {
            return 0;
        }
        return musictypeMapper.updateByPrimaryKey(musicType);
    }

    public int deleteMusicTypeById(Integer id) {
        return musictypeMapper.deleteByPrimaryKey(id);
    }

    public List<Music> getAllMusic() {
        List<Music> musiclist = musicMapper.selectAllMusic();
        for (int i = 0; i < musiclist.size(); i++) {
            Singer singer = singerMapper.selectByPrimaryKey(musiclist.get(i).getSingerid());
            Musictype musictype = musictypeMapper.selectByPrimaryKey(musiclist.get(i).getMusictypeid());
            musiclist.get(i).setSinger(singer);
            musiclist.get(i).setMusictype(musictype);
        }
        return musiclist;
    }

    public String insertMusic(String musicName, String musicTypeName, Integer musicHot, String singerName, String lyricUrl, MultipartFile myFile, MultipartFile myMusic) throws IOException {
        Music music = new Music();

        music.setMusicname(EncodingTool.encodeStr(musicName));
        if (musicMapper.selectByName(music) != null) {
            return "error";
        }
        ;
        String fname = "images/" + EncodingTool.encodeStr(myFile.getOriginalFilename());
        //创建file类  来确定上传文件的路径
        File file = new File("D:\\musicstatic", fname);
        //上传文件的功能
        FileUtils.copyInputStreamToFile(myFile.getInputStream(), file);

        String fmusic = "musics/" + EncodingTool.encodeStr(myMusic.getOriginalFilename());
        File file1 = new File("D:\\musicstatic", fmusic);
        //上传文件的功能
        FileUtils.copyInputStreamToFile(myMusic.getInputStream(), file1);


        music.setMusichot(musicHot);
        //歌曲类型
        List<Musictype> musictypes = musictypeMapper.selectByMusicTypeName(EncodingTool.encodeStr(musicTypeName));
        if (musictypes.size() > 0) {
            music.setMusictypeid(musictypes.get(0).getMusictypeid());
        } else {
            music.setMusictypeid(19);
        }
        //设置日期
        music.setTime(new Date());
        //设置图片地址
        music.setMusicphotourl(fname);
        //歌曲地址
        music.setSongurl(fmusic);
        //歌词地址
        music.setLyricurl(EncodingTool.encodeStr(lyricUrl));
        //查询歌手
        List<Singer> singers = singerMapper.selectBySingerName(EncodingTool.encodeStr(singerName));
        if (singers.size() > 0) {
            music.setSingerid(singers.get(0).getSingerid());
        } else {
            music.setSingerid(126);
        }

        musicMapper.insert(music);
        return "success";
    }

    public Music selectMusicById(Integer musicid) {
        Music music = musicMapper.selectByPrimaryKey(musicid);
        Singer singer = singerMapper.selectByPrimaryKey(music.getSingerid());
        Musictype musictype = musictypeMapper.selectByPrimaryKey(music.getMusictypeid());
        music.setSinger(singer);
        music.setMusictype(musictype);
        return music;
    }

    public String deleteMusicById(Integer musicid) {
        int result = musicMapper.deleteByPrimaryKey(musicid);
        return "success";
    }

    public String updateMusic(Integer musicid, String musicName, String musicTypeName, Integer musicHot, String singerName, String lyricUrl, MultipartFile myFile, MultipartFile myMusic) throws IOException {
        Music music = new Music();
        music.setMusicid(musicid);
        music.setMusicname(EncodingTool.encodeStr(musicName));
        if (!musicMapper.selectByName(music).getMusicid().equals(musicid)) {
            return "error";
        }
        Music oldMusic = musicMapper.selectByPrimaryKey(musicid);
        //删除原有图片
        File f1 = new File("D:\\musicstatic", oldMusic.getMusicphotourl());
        f1.delete();

        String fname = "images/" + EncodingTool.encodeStr(myFile.getOriginalFilename());
        //创建file类  来确定上传文件的路径
        File file = new File("D:\\musicstatic", fname);
        //上传文件的功能
        FileUtils.copyInputStreamToFile(myFile.getInputStream(), file);

        File f2 = new File("D:\\musicstatic", oldMusic.getSongurl());
        f2.delete();

        String fmusic = "musics/" + EncodingTool.encodeStr(myMusic.getOriginalFilename());
        File file1 = new File("D:\\musicstatic", fmusic);
        //上传文件的功能
        FileUtils.copyInputStreamToFile(myMusic.getInputStream(), file1);


        music.setMusicname(EncodingTool.encodeStr(musicName));
        music.setMusichot(musicHot);
        //歌曲类型
        List<Musictype> musictypes = musictypeMapper.selectByMusicTypeName(EncodingTool.encodeStr(musicTypeName));
        if (musictypes.size() > 0) {
            music.setMusictypeid(musictypes.get(0).getMusictypeid());
        } else {
            music.setMusictypeid(126);
        }
        //设置日期
        music.setTime(new Date());
        //设置图片地址
        music.setMusicphotourl(fname);
        //歌曲地址
        music.setSongurl(fmusic);
        //歌词地址
        music.setLyricurl(EncodingTool.encodeStr(lyricUrl));
        //查询歌手
        List<Singer> singers = singerMapper.selectBySingerName(EncodingTool.encodeStr(singerName));
        if (singers.size() > 0) {
            music.setSingerid(singers.get(0).getSingerid());
        } else {
            music.setSingerid(126);
        }

        musicMapper.updateByPrimaryKey(music);
        return "success";
    }

    public void deletesMusic(Integer[] ids) {
        for (Integer id : ids) {
            musicMapper.deleteByPrimaryKey(id);
        }
//        musicMapper.deletesByPrimaryKey(ids);
    }

    public List<Music> randomMusic() {
        Random r = new Random();
//        获取所有音乐
        List<Music> musics = musicMapper.selectAllMusic();
//        存放音乐的id
        List<Integer> lists = new ArrayList<Integer>();
//        产生的随机数
        List<Integer> index = new ArrayList<Integer>();

        List<Music> music = new ArrayList<Music>();

        for (int i = 0; i < musics.size(); i++) {
            lists.add(musics.get(i).getMusicid());
        }

        for (int i = 0; i < 10; i++) {
//            产生0-lists.size()-1之间的随机数
            int num = r.nextInt(lists.size());
            int num1 = -1;
//            随机数去重，不重复则添加至数组
            if (!index.contains(num)) {
                index.add(num);
            } else {
//                如果这次产生的随机数是重复的，则进入循环
                do {
                    num1 = r.nextInt(lists.size());
//                    判断这次产生的随机数有没有重复
                    if (!index.contains(num1)) {
                        index.add(num1);
                        break;
                    }
                } while (index.contains(num1));
            }
        }
        for (int i = 0; i < index.size(); i++) {
            Music music1 = musicMapper.selectByPrimaryKey(lists.get(index.get(i)));
            Singer singer = singerMapper.selectByPrimaryKey(music1.getSingerid());
            Musictype musictype = musictypeMapper.selectByPrimaryKey(music1.getMusictypeid());
            music1.setSinger(singer);
            music1.setMusictype(musictype);
            music.add(music1);
        }
        return music;
    }

    public List<Music> typeMusic(Musictype musicType) {
        Random r = new Random();
//        存放音乐的id
        List<Integer> lists = new ArrayList<Integer>();
//        产生的随机数
        List<Integer> index = new ArrayList<Integer>();

        List<Music> music = new ArrayList<Music>();

//        获取音乐类型
        List<Musictype> musictypes = musictypeMapper.selectByMusicTypeName(musicType.getMusictypename());

//        根据音乐类型id得到的所有音乐
        List<Music> musics = musicMapper.selectByTypeid(musictypes.get(0).getMusictypeid());

        for (int i = 0; i < musics.size(); i++) {
            lists.add(musics.get(i).getMusicid());
        }

        for (int i = 0; i < 10; i++) {
//            产生0-lists.size()-1之间的随机数
            int num = r.nextInt(lists.size());
            int num1 = -1;
//            随机数去重，不重复则添加至数组
            if (!index.contains(num)) {
                index.add(num);
            } else {
//                如果这次产生的随机数是重复的，则进入循环
                do {
                    num1 = r.nextInt(lists.size());
//                    判断这次产生的随机数有没有重复
                    if (!index.contains(num1)) {
                        index.add(num1);
                        break;
                    }
                } while (index.contains(num1));
            }
        }
        for (int i = 0; i < index.size(); i++) {
            Music music1 = musicMapper.selectByPrimaryKey(lists.get(index.get(i)));
            Singer singer = singerMapper.selectByPrimaryKey(music1.getSingerid());
            Musictype musictype = musictypeMapper.selectByPrimaryKey(music1.getMusictypeid());
            music1.setSinger(singer);
            music1.setMusictype(musictype);
            music.add(music1);
        }
        return music;

    }

    public List<Music> hotMusic() {
        List<Music> musics = musicMapper.selectByHot();
        List<Music> music = new ArrayList<Music>();
        for (int i = 0; i < 10; i++) {
            Music music1 = musicMapper.selectByPrimaryKey(musics.get(i).getMusicid());
            Singer singer = singerMapper.selectByPrimaryKey(music1.getSingerid());
            Musictype musictype = musictypeMapper.selectByPrimaryKey(music1.getMusictypeid());
            music1.setSinger(singer);
            music1.setMusictype(musictype);
            music.add(music1);
        }
        return music;
    }

    public void deletesMusicType(Integer[] ids) {
        for (Integer id : ids) {
            musictypeMapper.deleteByPrimaryKey(id);
        }
//        musicMapper.deletesByPrimaryKey(ids);
    }

    public List<Music> sameMusic(Music music) {
        List<Music> musics = musicMapper.selectByTypeid(music.getMusictypeid());
        Random r = new Random();
//        存放音乐的id
        List<Integer> lists = new ArrayList<Integer>();
//        产生的随机数
        List<Integer> index = new ArrayList<Integer>();

        List<Music> sameMusic = new ArrayList<Music>();

        for (int i = 0; i < musics.size(); i++) {
            lists.add(musics.get(i).getMusicid());
        }

        for (int i = 0; i < 3; i++) {
//            产生0-lists.size()-1之间的随机数
            int num = r.nextInt(lists.size());
            int num1 = -1;
//            随机数去重，不重复则添加至数组
            if (!index.contains(num)) {
                index.add(num);
            } else {
//                如果这次产生的随机数是重复的，则进入循环
                do {
                    num1 = r.nextInt(lists.size());
//                    判断这次产生的随机数有没有重复
                    if (!index.contains(num1)) {
                        index.add(num1);
                        break;
                    }
                } while (index.contains(num1));
            }
        }
        for (int i = 0; i < index.size(); i++) {
            Music music1 = musicMapper.selectByPrimaryKey(lists.get(index.get(i)));
            Singer singer = singerMapper.selectByPrimaryKey(music1.getSingerid());
            Musictype musictype = musictypeMapper.selectByPrimaryKey(music1.getMusictypeid());
            music1.setSinger(singer);
            music1.setMusictype(musictype);
            sameMusic.add(music1);
        }
        return sameMusic;
    }

    public List<Music> selectMusicBySingerid(Music music) {
        List<Music> musics = musicMapper.selectBySinger(music.getSingerid());
        for (int i = 0; i < musics.size(); i++) {
            Singer singer = singerMapper.selectByPrimaryKey(musics.get(i).getSingerid());
            musics.get(i).setSinger(singer);
        }
        return musics;
    }

    public Music selectMusicByName(Music music) {
        return musicMapper.selectByName(music);
    }


}
