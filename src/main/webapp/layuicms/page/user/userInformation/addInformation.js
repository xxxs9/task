layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'tree','$api', 'jquery', 'ajaxExtention', '$tool','upload'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;
        upload = layui.upload;//上传组件

    /**
     * 页面初始化
     * */
    function init() {
        //初始化发布者
        initInformation();
    }

    init();

    /**
     * 初始化发布者
     */
    function  initInformation() {
        //获取登陆的用户名
        $("#loginName").val(window.sessionStorage.getItem("sysUser"));
    }

    /**
     * 自动加载编译器
     */
    $(function () {
        var html = '<div id="myEditor" name="informationContent"></div>';
        $('#informationContent').append(html);
        ResetEditor();
        var ue = UE.getEditor('myEditor',{
            zIndex:0
        });
        ue.ready(function(){
            ue.setContent("");
        });
    });
    /**
     * 关于编译器
     */
    function ResetEditor() {
        UEDITOR_CONFIG.UEDITOR_HOME_URL ='/task/layuicms/ueditor/'; //一定要用这句话，否则你需要去
        UE.getEditor('myEditor', {
            initialFrameHeight : 300,
            initialFrameWidth : 800,
            enableAutoSave : false,
            elementPathEnabled : false,
            wordCount : false,
            /*  toolbars: [
             [
             'fontfamily', 'fontsize', 'forecolor', 'backcolor', 'bold', 'italic', 'underline', '|',
             'link', '|',
             ]
             ]  */
        });
    }


    /**
     * 表单提交
     * */
    form.on("submit(addInformation)", function (data) {
        var loginName = data.field.loginName;
        var informationTitle = data.field.informationTitle;
        var isTop = data.field.isTop;
        var informationImg = data.field.informationImg;
        var informationContent = UE.getEditor('myEditor').getContent();
        var informationType = data.field.informationType;



        //请求
        var req = {
            loginName: loginName,
            informationTitle: informationTitle,
            isTop: isTop,
            informationImg: informationImg,
            informationContent: informationContent,
            informationType: informationType
        };

        $api.AddInformation(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("资讯信息添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

    /**
     * 文件上传
     */
        //普通图片上传(编辑表单的)
    var uploadInst = upload.render({
            elem: '#chooseFile_add',//选择绑定选择文件的那个按钮
            url: $tool.getContext() + 'common/upload.do',
            auto: false, //选择文件后不自动上传
            bindAction: '#beginFile_add',//绑定开始上传按钮
            accept: 'images',//上传时校验文件类型
            acceptMime: 'image/*',//打开选择框只显示图片文件
            size: 1024 * 5,//设置文件上传的限制大小单位KB
            choose: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#photo_img').attr('src', result); //图片链接（base64）
                });
            },
            done: function (res) {
                //如果上传失败
                if (res.code == 500) {
                    return layer.msg('上传失败');
                }
                //上传成功
                if (res.code != 500) {
                    //绑定新的路径给存图片路径的input框
                    $('#informationImg').val(res.data);
                    return layer.msg('上传成功');
                }
            },
            //关于失败重传可以写也可以不写参照layui官方文档
            error: function () {
                //演示失败状态，并实现重传
                var textTip = $('#textTip');
                textTip.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                textTip.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });


});


