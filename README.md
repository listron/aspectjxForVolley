# aspectjxForVolley
此套aspectjx方案代替原有的aop编译方式，可实现java文件、jar包等的全工程织入；

只需更改gradle编译脚本，对代码无侵入；

插件开源：https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx/blob/master/README-zh.md


1 在添加aop代码的module build.gradle中添加依赖：compile 'org.aspectj:aspectjrt:1.8.9'

ucarnew的aop代码，统一加在base_common下，此module依赖已添加完成。

此依赖相当于原来的aspectjrt.jar，如不添加此依赖，编译不通过

2 app module下，升级android gradle插件版本到1.5.0以上，并添加

classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.0.8'

apply plugin: 'android-aspectjx' 

此插件在gradle编译，transform一步中遍历jar中及代码的class文件，织入aop代码

3 gradle-wrapper.properties，升级gradle版本到2.14.1以上：

distributionUrl=https\://services.gradle.org/distributions/gradle-2.14.1-all.zip
