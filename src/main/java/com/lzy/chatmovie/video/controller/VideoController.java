package com.lzy.chatmovie.video.controller;

/**
 * 视频文件控制层
 * @author lzy
 * @since 2020.2.2
 */

import com.lzy.chatmovie.util.Result;
import com.lzy.chatmovie.util.StatusCode;
import com.lzy.chatmovie.video.model.dto.VideoDto;
import com.lzy.chatmovie.video.model.po.Video;
import com.lzy.chatmovie.video.model.vo.VideoVo;
import com.lzy.chatmovie.video.service.VideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class VideoController{

    @Autowired
    private VideoService videoService;

    @GetMapping("/videosByName")
    @ApiOperation(value = "按名称查找所有视频", notes = "按名称查找所有视频")
    public List<Video> findAll(@ApiParam(value = "视频名称")
                                   @RequestParam String name)
    {
        return videoService.findAll(name);
    }

    @GetMapping("/videos")
    @ApiOperation(value = "查找所有视频", notes = "查找所有视频")
    public List<Video> findAll()
    {
        return videoService.findAll();
    }

    /**
     * 删除
     */
    @DeleteMapping("/video")
    @ApiOperation(value = "按ID删除视频",notes = "按ID删除视频")

    public Result deleteById(
            @ApiParam(value = "视频ID")
            @RequestParam long id)
    {
        videoService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功！");

    }

    /**
     * 添加视频
     * @param videoDto
     * @return
     */
    @PostMapping("/video")
    @ApiOperation(value = "添加视频",notes = "添加视频")
    public Result addVideo(
            @ApiParam(value = "视频信息")
            @RequestBody VideoDto videoDto
    )
    {
        VideoVo videoVo = VideoVo.builder()
                .c_user(videoDto.getC_user())
                .c_username(videoDto.getC_username())
                .name(videoDto.getName())
                .description(videoDto.getDescription())
                .path(videoDto.getPath())
                .build();
        return videoService.addVideo(videoVo);
    }

    public VideoService getVideoService() {
        return videoService;
    }

    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }
}
