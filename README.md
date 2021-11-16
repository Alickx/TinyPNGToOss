# TinyPngToOss
### 项目简介：

一个Springboot+Vue项目，上传图片到tinyPNG进行压缩，然后自动上传至阿里云Oss，获取Oss文件链接



项目目前已开发进度：

- [x] 注册登陆功能模块

- [x] 用户Oss信息功能

- [x] 用户基本功能模块

- [x] 上传图片模块

- [x] 压缩图片模块

- [ ] 上传历史记录模块

- [ ] 多远程对象存储选择模块



#### 使用文档：

1. 把目录下的sql文件导入数据库
2. 修改application.yml文件
2. 修改application.properties文件，添加腾讯验证码api相关信息
3. 项目默认端口为9451
4. 运行项目成功，注册登陆后在个人中心，设置自己的阿里云oss信息
5. bucket只需写目录名，不需要添加/，如 tiny ,  site/img

### 实例图片：

首页：

![image-20211116095649100](https://songtiancloud-1300061766.cos.ap-guangzhou.myqcloud.com/img/202111160956519.png)

上传成功:

![image-20211116095721316](https://songtiancloud-1300061766.cos.ap-guangzhou.myqcloud.com/img/202111160957818.png)

oss信息：

![image-20211116095735693](https://songtiancloud-1300061766.cos.ap-guangzhou.myqcloud.com/img/202111160957545.png)

修改密码：

![image-20211116095754968](https://songtiancloud-1300061766.cos.ap-guangzhou.myqcloud.com/img/202111160957962.png)

注册界面：

![image-20211116095807897](https://songtiancloud-1300061766.cos.ap-guangzhou.myqcloud.com/img/202111160958908.png)

### 注意事项：

项目已对敏感数据进行加密处理，其中阿里云oss的AccessKey使用了AES对称加密，如需自定义密钥请在util下的encryption中进行修改。

项目需要远程下载压缩后的文件，请赋予项目文件夹读写操作权限，否则可能会出现一些意想不到的问题。
