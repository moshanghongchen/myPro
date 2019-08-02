<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>

    <script src="/js/index.js"></script>
</head>
<body>
<form role="form" class="center-pill" id="uploadFile" action="/fileController/upLoadFile" enctype="multipart/form-data" method="post">
    <div class="form-group">
        <label for="file">请选择要上传的文件：</label>
        <input type="file" id="file" name="file">
    </div>


    <button type="submit" class="btn btn-default" onclick="downloadFile()" >提交</button>

    <table id="fileList" class="table table-bordered" >
        <tr>
            <td>文件名</td><td>文件大小</td><td>更新日期</td>
        </tr>
    </table>
    <div id="toolbar">
        <button id="download" type="button" onclick="downloadFile()">下载</button>
    </div>


</form>
</body>
</html>