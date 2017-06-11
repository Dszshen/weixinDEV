'use strict';
//引入gulp，项目文件中安装的gulp的引入方式
var gulp =require('gulp');
//引入组件
var watchify = require('watchify');//持续监视文件的改动
var watch = require('gulp-watch');//只重新编译更改过的文件
var changed = require('gulp-changed');//只重新编译更改过的文件
var browserify = require('browserify');//代码自动注入刷新浏览器
var browserSync = require('browser-sync').create();
var rename = require("gulp-rename");//文件重命名
var jshint = require("gulp-jshint");//js代码校验组件
var eslint = require('gulp-eslint');//js代码校验
var uglify = require("gulp-uglify");//js代码压缩
var copy = require("gulp-copy");//文件拷贝
var concat = require("gulp-concat");//文件合并
var cssmin = require("gulp-cssmin");//css压缩
var htmlmin = require("gulp-minify-html");//html压缩
var gutil = require("gulp-util");//最基础的工具，可以用来打日志...
//var sass = require("gulp-sass");//编译sass文件
var less = require('gulp-less');//编译less文件
var jade = require('gulp-jade');//编译jade2html
var sequence = require('gulp-sequence');//序列化task，保证执行顺序
var gulpif = require('gulp-if');//
var clean = require('gulp-clean');//清理
var del = require('del');//与gulp-clean一样，使用一个即可
var revAppend = require("gulp-rev-append");//给页面引用的静态文件增加hash后缀，避免被浏览器缓存。如果是使用 CDN，这个就不行了
var sourcemaps = require('gulp-sourcemaps');//
var order = require("gulp-order");//文件排序
//定义全局变量
var projectName = "app";//项目名称
//task名称
var task = {
    clean:projectName+"-clean",
    api:projectName+"-api",
    js:projectName+"-js",
    css:projectName+"-css",
    sass:projectName+"-sass",
    html:projectName+"-html",
    less:projectName+"-less",
    watch:projectName+"-watch",
    fonts:projectName+"-fonts",
    images:projectName+"-images",
    webserver:projectName+"-webserver",
    jade:projectName+"-jade",
    lib:projectName+"-lib",
    plugins:projectName+"-plugins",
    scripts:projectName+"-scripts",
    copy:projectName+"-copy",
    mock:projectName+"-mock",
    dev:projectName+"-dev",
    deploy:projectName+"-deploy"
};

var SRC = "./src/main/resources/";//所有资源文件的源文件目录
var DEST_ASSETS = "./src/main/webapp/WEB-INF/assets/";//编译后静态文件的目录

//资源文件路径
var resourceFilesPath = {
    jade:SRC+"views/**/*.jade",
    fonts:SRC+"assets/fonts/**",
    less:SRC+"assets/less/**/*.less",
    //css:SRC+"assets/css/**/*.css",
    js:SRC+"assets/js/**/**.js",
    libs:"./bower_components/**",
    plugins:SRC+"assets/plugins/**",
    scripts:SRC+"assets/scripts/**/*.js",
    images:SRC+"assets/images/**/*",
    md:SRC+"md/**"
};

//目标文件路径
var destFilesPath = {
    html:"./src/main/webapp/WEB-INF/views",
    fonts:DEST_ASSETS+"fonts",
    css:DEST_ASSETS+"css",
    js:DEST_ASSETS+"js",
    libs:DEST_ASSETS+"libs",
    scripts:DEST_ASSETS+"scripts",
    images:DEST_ASSETS+"images",
    md:"./src/main/webapp/WEB-INF/md"
};

var ENV = "dev";//开发环境
var OP = "deploy";//发布

//task配置
//--每次运行gulp的时候清理target文件
gulp.task(task.clean, function() {
    gutil.log("[logs]"+task.clean);
    return del.sync([DEST_ASSETS,'./src/main/webapp/WEB-INF/views/']);
});

//api   md文件
gulp.task(task.api,function(){
  gutil.log("[logs]"+task.api+"正在运行...");
  return gulp.src(resourceFilesPath.md)
      .pipe(gulp.dest(destFilesPath.md));
});


//编译js文件
gulp.task(task.js,function(){
    return gulp.src(resourceFilesPath.js)
        .pipe(order([
            'main.js',
            'src/main/resources/assets/js/config/**/*.js',
            'src/main/resources/assets/js/factories/**/*.js',
            //'src/main/resources/assets/js/config/route.js',
            'src/main/resources/assets/js/directives/**/*.js',
            'src/main/resources/assets/js/controllers/**/*.js'
        ]))
        .pipe(jshint())
        .on('error', function(error)  {
            gutil.log("[jshint logs]:"+error);
        })
        .pipe(concat("app.js"))
        .pipe(gulp.dest(destFilesPath.js));
});

//编译plugins文件,自己添加的插件
gulp.task(task.scripts,function(){
    gutil.log("[logs]"+task.scripts+"正在运行...");
    return gulp.src(resourceFilesPath.scripts)
        .pipe(gulp.dest(destFilesPath.scripts));
});

//编译images文件
gulp.task(task.images,function(){
    gutil.log("[logs]"+task.images+"正在运行...");
    return gulp.src(resourceFilesPath.images)
        .pipe(gulp.dest(destFilesPath.images));
});

//编译css文件
// gulp.task(task['css'],function(){
//     gutil.log("[logs]"+task['css']+"正在运行...");
//     return gulp.src(resourceFilesPath.css)
//         .pipe(gulp.dest(destFilesPath.css))
// });

//编译less文件
gulp.task(task.less,function(){
    gutil.log("[logs]"+task.less+"正在运行...");
    return gulp.src(resourceFilesPath.less)
        .pipe(less())
        .on('error', function(error)  {
            console.dir(error);
            this.emit('end');
        })
        .pipe(gulp.dest(destFilesPath.css));
});

//编译jade文件,
gulp.task(task.jade,function(){
    gutil.log("[logs]"+task.jade+"正在运行...");
    return gulp.src(resourceFilesPath.jade)
        .pipe(changed(destFilesPath.html,{hasChanged:changed.compareSha1Digest,extension:".ftl"}))
        .pipe(jade({petty: true}))
        .pipe(rename(function(path){
            path.extname = ".ftl";
        }))
        .pipe(gulp.dest(destFilesPath.html));
});

//编译html文件
/*gulp.task(task['html'],function(){
 gulp.src(resourceFilesPath.less)
 });*/

//编译lib文件,用到的插件库
gulp.task(task.lib,function(){
    gutil.log("[logs]"+task.lib+"正在运行...");
    return gulp.src(resourceFilesPath.libs)
        .pipe(gulp.dest(destFilesPath.libs));
});

//编译plugins文件,自己添加的插件
gulp.task(task.plugins,function(){
    gutil.log("[logs]"+task.plugins+"正在运行...");
    return gulp.src(resourceFilesPath.plugins)
        .pipe(gulp.dest(destFilesPath.libs));
});

//编译plugins文件,自己添加的插件
gulp.task(task.copy,function(){
    return gulp.src(DEST_ASSETS)
        .pipe(gulp.dest("./target"));
});

//watch监控文件变化并刷新浏览器
gulp.task(task.watch,function(){
    gulp.watch(resourceFilesPath.less, [task.less]);
    gulp.watch(resourceFilesPath.jade, [task.jade]);
    gulp.watch(resourceFilesPath.js, [task.js]);
    gulp.watch(resourceFilesPath.images, [task.images]);
    gulp.watch(resourceFilesPath.plugins, [task.plugins]);
    gulp.watch(resourceFilesPath.scripts, [task.scripts]);
    gulp.watch(resourceFilesPath.scripts, [task.lib]);
    gulp.watch(resourceFilesPath.md, [task.api]);
    gulp.watch("./gulpfile.js", ['default']);
    //gulp.watch(destFilesPath, [task['copy']]);

    /*gulp.watch(SRC + '/!**!/!*', { read: false }).on('change', function(event)  {
        browserSync.init({
         server : "./"
         })
        browserSync.reload();
    });*/
});

//默认的task
gulp.task("default",[task.clean,task.less,task.js,task.images,task.lib,task.plugins,task.scripts,task.jade,task.api]);

//开发
gulp.task(task.dev,['default'],function(){
    gutil.log("dev-----------------");
    //return gulp.watch(task['watch']);
});

//发布
gulp.task(task.deploy,function(){

});

//如果有必要，启动server运行代码，由于这里使用jetty插件，所以不使用server
gulp.task("server",[]);
