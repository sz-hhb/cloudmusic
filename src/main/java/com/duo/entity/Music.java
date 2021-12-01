package com.duo.entity;

import java.util.Date;

public class Music {
    private Integer musicid;

    private String musicname;

    private String musicphotourl;

    private Integer singerid;

    private Integer musichot;

    private String lyricurl;

    private Integer musictypeid;

    private String songurl;

    private Date time;

    private String musictime;

    private Singer singer;

    private Musictype musictype;

    public Integer getMusicid() {
        return musicid;
    }

    public void setMusicid(Integer musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname == null ? null : musicname.trim();
    }

    public String getMusicphotourl() {
        return musicphotourl;
    }

    public void setMusicphotourl(String musicphotourl) {
        this.musicphotourl = musicphotourl == null ? null : musicphotourl.trim();
    }

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public Integer getMusichot() {
        return musichot;
    }

    public void setMusichot(Integer musichot) {
        this.musichot = musichot;
    }

    public String getLyricurl() {
        return lyricurl;
    }

    public void setLyricurl(String lyricurl) {
        this.lyricurl = lyricurl == null ? null : lyricurl.trim();
    }

    public Integer getMusictypeid() {
        return musictypeid;
    }

    public void setMusictypeid(Integer musictypeid) {
        this.musictypeid = musictypeid;
    }

    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl) {
        this.songurl = songurl == null ? null : songurl.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMusictime() {
        return musictime;
    }

    public void setMusictime(String musictime) {
        this.musictime = musictime == null ? null : musictime.trim();
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Musictype getMusictype() {
        return musictype;
    }

    public void setMusictype(Musictype musictype) {
        this.musictype = musictype;
    }
}