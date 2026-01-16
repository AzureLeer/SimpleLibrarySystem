<%@ page contentType="text/html;charset=UTF-8"  language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书馆管理系统 - 登录</title>
    <!-- 引入Bootstrap及依赖（保持原有路径） -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/js.cookie.js"></script>
    <style>
        /* 全局样式重置+基础配置 */
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
        body {
            /* 背景图优化：居中+覆盖+固定，适配不同屏幕 */
            background: url("img/timg.jpg") no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh; /* 铺满屏幕高度 */
            padding-top: 30px;
        }
        /* 登录面板美化：响应式宽度+阴影+圆角 */
        #login {
            width: 100%;
            max-width: 420px; /* PC端最大宽度 */
            margin: 0 auto;
            margin-top: 5vh; /* 垂直居中适配 */
            background: #fff;
            border-radius: 10px; /* 圆角 */
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15); /* 立体阴影 */
            overflow: hidden; /* 裁剪圆角外的内容 */
        }
        /* 登录面板头部 */
        .login-header {
            background: #337ab7; /* Bootstrap主色调 */
            color: #fff;
            padding: 15px 20px;
            margin: 0;
            font-size: 18px;
            font-weight: 500;
        }
        /* 登录面板主体 */
        .login-body {
            padding: 30px 25px;
        }
        /* 表单组间距优化 */
        .form-group {
            margin-bottom: 20px;
        }
        /* 标签样式优化 */
        .form-group label {
            font-weight: 500;
            color: #333;
            margin-bottom: 8px;
            display: block;
        }
        /* 输入框美化：圆角+边框色 */
        .form-control {
            border-radius: 6px;
            border: 1px solid #ddd;
            height: 42px;
            padding: 0 15px;
            transition: border-color 0.3s;
        }
        .form-control:focus {
            border-color: #337ab7;
            box-shadow: 0 0 5px rgba(51, 122, 183, 0.2);
            outline: none;
        }
        /* 记住密码复选框 */
        .checkbox {
            margin-bottom: 25px;
        }
        .checkbox label {
            color: #666;
            font-weight: normal;
            cursor: pointer;
        }
        /* 提示信息样式 */
        #info {
            color: #d9534f; /* Bootstrap危险色 */
            font-size: 13px;
            height: 20px;
            line-height: 20px;
            margin-bottom: 10px;
            display: block;
        }
        /* 登录按钮美化 */
        #loginButton {
            background: #337ab7;
            border: none;
            border-radius: 6px;
            height: 45px;
            font-size: 16px;
            font-weight: 500;
            transition: background-color 0.3s;
        }
        #loginButton:hover {
            background: #286090; /* hover加深 */
        }
        /* 标题样式优化 */
        .library-title {
            text-align: center;
            color: #fff;
            font-family: "华文行楷", "微软雅黑", sans-serif;
            font-size: 4rem;
            margin-bottom: 20px;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); /* 文字阴影增强可读性 */
        }
        /* 响应式适配：小屏幕标题缩小 */
        @media (max-width: 768px) {
            .library-title {
                font-size: 2.5rem;
            }
            .login-body {
                padding: 20px 20px;
            }
        }
    </style>
</head>
<body>
<!-- 后端错误提示 -->
<c:if test="${!empty error}">
    <script>
        alert("${error}");
        window.location.href="login.html";
    </script>
</c:if>

<!-- 图书馆标题 -->
<h2 class="library-title">图 书 馆 管 理 系 统</h2>

<!-- 登录面板（替换原有panel结构，更灵活） -->
<div id="login">
    <div class="login-header">请登录</div>
    <div class="login-body">
        <div class="form-group">
            <label for="id">账号</label>
            <input type="text" class="form-control" id="id" placeholder="请输入数字账号">
        </div>
        <div class="form-group">
            <label for="passwd">密码</label>
            <input type="password" class="form-control" id="passwd" placeholder="请输入密码">
        </div>
        <div class="checkbox text-left">
            <label>
                <input type="checkbox" id="remember"> 记住账号
            </label>
        </div>

        <!-- 提示信息 -->
        <span id="info"></span>

        <!-- 登录按钮 -->
        <button id="loginButton" class="btn btn-primary btn-block">登录</button>
    </div>
</div>

<script>
    // 账号实时校验：仅数字
    $("#id").keyup(function () {
        var idVal = $(this).val().trim();
        if (idVal && isNaN(idVal)) {
            $("#info").text("提示:账号只能为数字");
        } else {
            $("#info").text("");
        }
    })

    // 记住登录信息（优化：仅存账号，不存密码，提升安全）
    function rememberLogin(username, checked) {
        Cookies.set('loginStatus', {
            username: username,
            remember: checked
        }, { expires: 30, path: '/' }) // path改为/，全站生效
    }

    // 页面加载时填充记住的账号
    function setLoginStatus() {
        var loginStatusText = Cookies.get('loginStatus')
        if (loginStatusText) {
            var loginStatus
            try {
                loginStatus = JSON.parse(loginStatusText);
                if (loginStatus.remember && loginStatus.username) {
                    $('#id').val(loginStatus.username);
                    $("#remember").prop('checked', true);
                }
            } catch (e) {
                Cookies.remove('loginStatus', { path: '/' }); // 解析失败清空Cookie
            }
        }
    }

    // 初始化填充账号
    setLoginStatus();

    // 登录按钮点击事件
    $("#loginButton").click(function () {
        var id = $("#id").val().trim();
        var passwd = $("#passwd").val().trim();
        var remember = $("#remember").prop('checked');

        // 清空原有提示
        $("#info").text("");

        // 表单校验
        if (id === '') {
            $("#info").text("提示:账号不能为空");
            return;
        }
        if (passwd === '') {
            $("#info").text("提示:密码不能为空");
            return;
        }
        if (isNaN(id)) {
            $("#info").text("提示:账号必须为数字");
            return;
        }

        // AJAX登录请求
        $.ajax({
            type: "POST",
            url: "/api/loginCheck",
            data: { id: id, passwd: passwd },
            dataType: "json",
            timeout: 5000, // 超时时间5秒
            success: function (data) {
                var stateCode = (data.stateCode || "").trim();
                if (stateCode === "0") {
                    $("#info").text("提示:账号或密码错误！");
                } else if (stateCode === "1") {
                    $("#info").text("提示:登录成功，跳转中...");
                    // 延迟跳转，提升体验
                    setTimeout(function () {
                        window.location.href = "/admin_main.html";
                    }, 1000);
                } else if (stateCode === "2") {
                    // 读者登录：处理记住账号
                    if (remember) {
                        rememberLogin(id, remember);
                    } else {
                        Cookies.remove('loginStatus', { path: '/' });
                    }
                    $("#info").text("提示:登录成功，跳转中...");
                    setTimeout(function () {
                        window.location.href = "/reader_main.html";
                    }, 1000);
                }
            },
            // 新增错误处理：网络异常/接口报错
            error: function (xhr, status, error) {
                $("#info").text("提示:登录失败，请检查网络！");
                console.error("登录请求失败：", status, error);
            }
        });
    })

    // 回车提交登录（增强体验）
    $(document).keydown(function (e) {
        if (e.keyCode === 13) {
            $("#loginButton").click();
        }
    });
</script>
</body>
</html>
