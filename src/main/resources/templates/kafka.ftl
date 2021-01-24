<!doctype html>
<html>
<head>
    <meta charSet="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0, minimum-scale=1.0"/>
    <title>kafka测试</title>
</head>
<body>
<button onclick="action('click')">点击</button>
<button onclick="action('jobCollect')">收藏</button>
<button onclick="action('cvSend')">投简历</button>
<button onclick="action('cvUpload')">上传简历</button>

<script type="text/javascript" src='https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js'></script>
<script>
    function action(_action){
        var info = {};
        info.userId = '123456';
        info.actTime = new Date();
        info.action = _action;
        info.jobCode = "lagou";
        $.ajax({
            type: "post",
            url: "http://linux123:80/log",
            data: JSON.stringify(info),
            success: function(res){

            }
        });
    }
</script>
</body>
</html>