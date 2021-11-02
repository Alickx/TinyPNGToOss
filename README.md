# TinyPngToOss
### 项目简介：

一个Springboot+thymeleaf项目，上传图片到tinyPNG进行压缩，然后自动上传至阿里云Oss，获取Oss文件链接



项目目前已开发进度：

- [x] 注册登陆功能模块

- [x] 用户Oss信息功能

- [x] 用户基本功能模块

- [x] 上传图片模块

- [x] 压缩图片模块

- [ ] 上传文件名自定义

- [ ] 上传历史记录模块

- [ ] 多远程对象存储选择模块

  

#### 使用文档：

目前使用Thymeleaf+Bootstrap+Bootstrap fileinpu，尽管基本功能都完成，但是体验依旧不是很好。



1. 把目录下的sql文件导入数据库
2. 修改application.yml文件
3. 项目默认端口为9451
4. 运行项目成功，注册登陆后在个人中心，设置自己的阿里云oss信息
5. bucket只需写目录名，不需要添加/，如 tiny ,  site/img



#### 后期计划：

前端更换为vue，并且ui，提示更加友善，上传速度有待加快，添加公共图床。
