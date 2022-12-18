<%@taglib uri="/struts-tags" prefix="s"%><%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>职工管理后台</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="./ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor1.4.3.3/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="./ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        div{
            width:100%;
        }
    </style>
    <script type="text/javascript">
        function getPhotoSize(obj){
            photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();
            if(photoExt!='.jpg'&&photoExt!='.png'&&photoExt!='.jpeg'){
                alert("文件格式错误！");
                var nf = obj.cloneNode(true);
                nf.value='';
                obj.parentNode.replaceChild(nf, obj);
                return false;
            }
            var fileSize = 0;
            var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
            if (isIE && !obj.files) {
                var filePath = obj.value;
                var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
                var file = fileSystem.GetFile (filePath);
                fileSize = file.Size;
            }else {
                fileSize = obj.files[0].size;
            }
            fileSize=Math.round(fileSize/1024*100)/100; //取得图片文件的大小
            if(fileSize>2048){
                alert("不接受超过2m的图片！");
                return false;
            }
        }
    </script>
</head>
<body>
<div>
    <h1>添加员工</h1>
    <form method="post" action="addEmp" enctype="multipart/form-data">
        <input type="submit" value="提交"><br>
        员工姓名：<input type="text" name="empName"><br>
        员工部门：<s:select name="empDept" list="addempDept" listKey="deptId" listValue="deptName" >
        </s:select><br>
        员工照片：<input type="hidden" class="filename" readonly/>
        <input type="file" name="empPhoto" accept="image/*"
               onchange="getPhotoSize(this)" size="60"/><br>
        员工简介：<script id="editor" type="text/plain" name="empMainBody"
                     style="width:100%;height:500px;"></script>
        </form>
        </div>
        <script type="text/javascript">
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var ue = UE.getEditor('editor');
        </script>
</body>
</html>
