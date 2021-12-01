package com.duo.controller;

import com.duo.entity.Singer;
import com.duo.entity.Singertype;
import com.duo.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @ResponseBody
    public List<Singertype> getSingerTypeList() {
        return singerService.getSingerTypeList();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertSingerType(@RequestBody Singertype singertype) {
        int result = singerService.insertSingerType(singertype);
        if (result != 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateSingerType(@RequestBody Singertype singertype) {
        int result = singerService.updateSingerType(singertype);
        if (result != 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteSingerType(@RequestBody Singertype singertype) {
        singerService.deleteSingerType(singertype);
        return "success";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<Singer> getSingerList() {
        return singerService.getSingerList();
    }

    @RequestMapping(value = "/insertSinger", method = RequestMethod.POST)
    @ResponseBody
    public String insertSinger(@RequestParam("singername") String singerName, @RequestParam("singertype") String singerType, @RequestParam("singerhot") Integer singerHot, @RequestParam("singeraddress") String singerAddress, @RequestParam("img") MultipartFile myFile) throws IOException {
        return singerService.insertSinger(singerName, singerType, singerHot, singerAddress, myFile);
    }

    @RequestMapping(value = "/deletesSinger", method = RequestMethod.POST)
    @ResponseBody
    public String deletesSinger(@RequestBody Integer[] ids) {
        singerService.deletesSinger(ids);
        return "success";
    }

    @RequestMapping(value = "/findSinger", method = RequestMethod.POST)
    @ResponseBody
    public Singer findSinger(@RequestBody Singer singer) {
        return singerService.findSinger(singer.getSingerid());
    }

    @RequestMapping(value = "/deleteSinger", method = RequestMethod.POST)
    @ResponseBody
    public String deleteSinger(@RequestBody Singer singer) {
        singerService.deleteSinger(singer.getSingerid());
        return "success";
    }

    @RequestMapping(value = "/updateSinger", method = RequestMethod.POST)
    @ResponseBody
    public String updateSinger(@RequestParam("singerid") Integer singerId, @RequestParam("singername") String singerName, @RequestParam("singertype") String singerType, @RequestParam("singerhot") Integer singerHot, @RequestParam("singeraddress") String singerAddress, @RequestParam("img") MultipartFile myFile) throws IOException {
        return singerService.updateSinger(singerId, singerName, singerType, singerHot, singerAddress, myFile);
    }

    @RequestMapping(value = "/hot", method = RequestMethod.POST)
    @ResponseBody
    public List<Singer> hotSinger() {
        return singerService.hotSinger();
    }

    @RequestMapping(value = "/deletesSingerType", method = RequestMethod.POST)
    @ResponseBody
    public String deletesSingerType(@RequestBody Integer[] ids) {
        singerService.deletesSingerType(ids);
        return "success";
    }

    @RequestMapping(value = "/sameSinger", method = RequestMethod.POST)
    @ResponseBody
    public List<Singer> sameSinger(@RequestBody Singer singer) {
        return singerService.sameSinger(singer);
    }

    @RequestMapping(value = "/typename", method = RequestMethod.POST)
    @ResponseBody
    public List<Singer> typename(@RequestBody Singertype typename) {
        return singerService.typename(typename);
    }

    @RequestMapping(value = "/searchSinger", method = RequestMethod.POST)
    @ResponseBody
    public Singer searchSinger(@RequestBody Singer singer) {
        return singerService.selectByName(singer);
    }
}
