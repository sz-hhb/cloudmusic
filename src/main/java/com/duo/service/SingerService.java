package com.duo.service;

import com.duo.entity.Singer;
import com.duo.entity.Singertype;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SingerService {

    List<Singer> getSingerList();

    List<Singertype> getSingerTypeList();

    int insertSingerType(Singertype singertype);

    int updateSingerType(Singertype singertype);

    int deleteSingerType(Singertype singertype);

    String insertSinger(@RequestParam("singername") String singerName, @RequestParam("singertype") String singerType, @RequestParam("singerhot") Integer singerHot, @RequestParam("singeraddress") String singerAddress, @RequestParam("img") MultipartFile myFile) throws IOException;

    void deletesSinger(Integer[] ids);

    Singer findSinger(Integer singerid);

    int deleteSinger(Integer singerid);

    String updateSinger(@RequestParam("singerid") Integer singerId, @RequestParam("singername") String singerName, @RequestParam("singertype") String singerType, @RequestParam("singerhot") Integer singerHot, @RequestParam("singeraddress") String singerAddress, @RequestParam("img") MultipartFile myFile) throws IOException;

    List<Singer> hotSinger();

    void deletesSingerType(Integer[] ids);

    List<Singer> sameSinger(Singer singer);

    List<Singer> typename(Singertype typename);

    Singer selectByName(Singer singer);
}
