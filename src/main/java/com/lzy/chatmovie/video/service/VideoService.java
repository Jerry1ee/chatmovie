package com.lzy.chatmovie.video.service;

import com.lzy.chatmovie.util.Result;
import com.lzy.chatmovie.util.StatusCode;
import com.lzy.chatmovie.video.dao.VideoRepository;
import com.lzy.chatmovie.video.model.dto.VideoDto;
import com.lzy.chatmovie.video.model.po.Video;
import com.lzy.chatmovie.video.model.vo.VideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    /**
     * 添加
     * @param videoVo
     * @return
     */
    public Result addVideo(VideoVo videoVo)
    {
        Video video =  Video.builder()
                .name(videoVo.getName())
                .path(videoVo.getPath())
                .c_user(videoVo.getC_user())
                .mtime(LocalDateTime.now())
                .ctime(LocalDateTime.now())
                .deleted(false)
                .description(videoVo.getDescription())
                .c_username(videoVo.getC_username())
                .build();
        Video video1 = videoRepository.saveAndFlush(video);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 按名称查找
     * @param name
     * @return
     */
    public Video findByName(String name)
    {
        Video video = videoRepository.findByName(name);
        return video;
    }

    /**
     * 查找全部
     * @return
     */
    public List<Video> findAll(String name)
    {
        return videoRepository.findAllByNameLike("%"+name+"%");
    }
    /**
     * 查找全部
     * @return
     */
    public List<Video> findAll()
    {
        return videoRepository.findAll();
    }
    /**
     * 删除
     */
    public Result deleteById(long id)
    {
        videoRepository.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功！");

    }
}
