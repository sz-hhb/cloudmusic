package com.duo.service.impl;

import com.duo.entity.Singer;
import com.duo.entity.Singertype;
import com.duo.mapper.SingerMapper;
import com.duo.mapper.SingertypeMapper;
import com.duo.service.SingerService;
import com.duo.util.EncodingTool;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("singerService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private SingertypeMapper singerTypeMapper;

    public List<Singer> getSingerList() {
        List<Singer> singers = singerMapper.getSingerList();
        for (int i = 0; i < singers.size(); i++) {
            Singertype singertype = singerTypeMapper.selectByPrimaryKey(singers.get(i).getTypeid());
            singers.get(i).setSingertype(singertype);
        }
        return singers;
    }

    public List<Singertype> getSingerTypeList() {
        return singerTypeMapper.getSingerTypeList();
    }

    public int insertSingerType(Singertype singertype) {
        if (singerTypeMapper.selectBySingerTypeName(singertype.getTypename()).size() != 0) {
            return 0;
        }
        return singerTypeMapper.insert(singertype);
    }

    public int updateSingerType(Singertype singertype) {
        if (singerTypeMapper.selectBySingerTypeName(singertype.getTypename()).size() != 0) {
            return 0;
        }
        return singerTypeMapper.updateByPrimaryKey(singertype);
    }

    public int deleteSingerType(Singertype singertype) {
        return singerTypeMapper.deleteByPrimaryKey(singertype.getTypeid());
    }

    public String insertSinger(String singerName, String singerType, Integer singerHot, String singerAddress, MultipartFile myFile) throws IOException {
        Singer singer = new Singer();
        singer.setSingername(EncodingTool.encodeStr(singerName));
        if (singerMapper.selectByName(singer) != null) {
            return "error";
        }
        String fname = "images/" + EncodingTool.encodeStr(myFile.getOriginalFilename());
        File file = new File("D:\\musicstatic", fname);
        FileUtils.copyInputStreamToFile(myFile.getInputStream(), file);

        singer.setSingerphotourl(fname);

        singer.setSingerhot(singerHot);
        singer.setAddress(EncodingTool.encodeStr(singerAddress));

        List<Singertype> singerTypes = singerTypeMapper.selectBySingerTypeName(EncodingTool.encodeStr(singerType));
        if (singerTypes.size() > 0) {
            singer.setTypeid(singerTypes.get(0).getTypeid());
        }

        singerMapper.insert(singer);
        return "success";
    }

    public void deletesSinger(Integer[] ids) {
        for (Integer id : ids) {
            singerMapper.deleteByPrimaryKey(id);
        }
    }

    public Singer findSinger(Integer singerid) {
        Singer singer = singerMapper.selectByPrimaryKey(singerid);
        Singertype singertype = singerTypeMapper.selectByPrimaryKey(singer.getTypeid());
        singer.setSingertype(singertype);
        return singer;
    }

    public int deleteSinger(Integer singerid) {
        return singerMapper.deleteByPrimaryKey(singerid);
    }

    public String updateSinger(Integer singerId, String singerName, String singerType, Integer singerHot, String singerAddress, MultipartFile myFile) throws IOException {
        Singer singer = new Singer();
        singer.setSingerid(singerId);
        singer.setSingername(EncodingTool.encodeStr(singerName));
        if (!singerMapper.selectByName(singer).getSingerid().equals(singerId)) {
            return "error";
        }
        Singer oldSinger = singerMapper.selectByPrimaryKey(singerId);

        File f1 = new File("D:\\musicstatic", oldSinger.getSingerphotourl());
        if (f1.exists()) {
            f1.delete();
        }

        String fname = "images/" + EncodingTool.encodeStr(myFile.getOriginalFilename());
        File file = new File("D:\\musicstatic", fname);
        FileUtils.copyInputStreamToFile(myFile.getInputStream(), file);

        singer.setSingerphotourl(fname);
        singer.setSingerhot(singerHot);
        singer.setAddress(EncodingTool.encodeStr(singerAddress));

        List<Singertype> singerTypes = singerTypeMapper.selectBySingerTypeName(EncodingTool.encodeStr(singerType));
        if (singerTypes.size() > 0) {
            singer.setTypeid(singerTypes.get(0).getTypeid());
        }

        singerMapper.updateByPrimaryKey(singer);
        return "success";
    }

    public List<Singer> hotSinger() {
        List<Singer> singer = singerMapper.findSingerOrderByHot();
        List<Singer> singers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            singers.add(singer.get(i));
        }
        return singers;
    }

    public void deletesSingerType(Integer[] ids) {
        for (Integer id : ids) {
            singerTypeMapper.deleteByPrimaryKey(id);
        }
    }

    public List<Singer> sameSinger(Singer singer) {
        List<Singer> singers = singerMapper.selectByType(singer.getTypeid());
        Random r = new Random();
//        存放歌手的id
        List<Integer> lists = new ArrayList<Integer>();
//        产生的随机数
        List<Integer> index = new ArrayList<Integer>();

        List<Singer> sameSinger = new ArrayList<Singer>();

        for (int i = 0; i < singers.size(); i++) {
            lists.add(singers.get(i).getSingerid());
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
            Singer singer1 = singerMapper.selectByPrimaryKey(lists.get(index.get(i)));
            sameSinger.add(singer1);
        }
        return sameSinger;
    }

    public List<Singer> typename(Singertype singertype) {
        List<Singertype> singertypes = singerTypeMapper.selectBySingerTypeName(singertype.getTypename());
        List<Singer> singers = singerMapper.selectByType(singertypes.get(0).getTypeid());
        return singers;
    }

    public Singer selectByName(Singer singer) {
        return singerMapper.selectByName(singer);
    }

}
