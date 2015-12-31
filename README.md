#MBase-Android 基础框架
* 项目类型：Library
* 版本 version 0.1.4


----
#MBase 简介
* MBase整合了网络请求（[OkHttp](https://github.com/square/okhttp)）、图片加载（[Fresco](https://github.com/facebook/fresco)）、Sqlite ORM框架（[LiteORM](https://github.com/litesuits/android-lite-orm)）、常用工具类等
* OkHttp的网络请求非常棒，所以本人也不再重复造轮，直接将OkHttp引入进来。只是它的网络请求代码有些繁琐，如果不封装一下，用起来很痛苦
* Fresco的图片异步加载，是目前为止使用过最牛的框架，目前简单的封装了几个请求参数方法
* LiteORM直接被引入，理由与上条相同，写的很不错，本人长期使用，也很稳定
* 其它工具类，是为了方便使用，如：T(Toast)类、Scale(尺寸)类、StringUtils(字符串工具)类等


## 使用 MBase 框架需要有以下权限：

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
* 访问网络权限
* 读取文件权限


## 如何使用

* 请看示例代码

# 关于作者

* 陈磊（Chen Lei）
* Email:monch.chen@yahoo.com