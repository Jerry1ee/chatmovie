# chatmovie

chatmovie

前端端口8888

后端端口8080

dashboard端口8000



# 项目初始化

项目数据库，缓存，消息队列依赖的组件均已部署到服务器上，无须本地配置

需要下载前端Vue框架，具体参见Vue官网https://cn.vuejs.org/v2/guide/

具体配置如下：

- 安装 npm
- 如果没有VPN，可以安装cnpm 
- 安装 vue-cli  
- 安装webpack

以上步骤参考网上教程，安装完毕后，安装脚手架，配置组件库等，参考教程：

https://www.bilibili.com/video/av74592164?p=5



# 项目启动-开发阶段

- 控制台运行 vue ui
- dashboard 页面中点击任务——serve——运行
- 启动成功后在右上角第四行点击 启动app
- 后台springboot项目启动

# 开发编码

- 编码前先从远程仓库pull最新版代码
- 开发一个功能，就要新建一个分支（branch），在这个分支上编码，不要再master分支上编码，比如要开发一个房间模块，新建一个分支命名为 room
- 编码结束后，commit提交检查
- 检查无误后，将本地仓库push到远程仓库的分支上，比如当前为room
- 在GitHub上发起 pull Request请求
- 当分支功能开发完毕并通过测试后，Merge到master分支上

# 部署信息
- 前端后端都部署到南大的云服务器上了，如果要访问，挂一下VPN
- IP:172.19.240.176
- 前端页面 172.19.240.176:8888
- 后端接口测试:http://172.19.240.176:8080/swagger-ui.html#/
