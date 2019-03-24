layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'tree','$api', 'jquery', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;
        var ue; //百度编译器

    /**
     * 页面初始化
     * */
    function init() {
        //初始化发布者
        initInformationInfo();
    }

    init();

    /**
     * 初始化资讯信息
     */
    /**
     * 初始化用户信息
     * */
    function initInformationInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

        var req = {
            id:id
        };

        $api.GetDynamic(req,function (res) {
            var data = res.data;
            $("[name='loginName']").val(data.loginName);
            //初始化填充富文本编辑器
            ue.ready(function(){
                ue.setContent(data.dynamicContent);
            });
            form.render();//重新绘制表单，让修改生效
        });
    }


    /**
     * 自动加载编译器
     */
    $(function () {
        var html = '<div id="myEditor" name="dynamicContent"></div>';
        $('#dynamicContent').append(html);
        ResetEditor();
        ue = UE.getEditor('myEditor',{
            zIndex:0
        });
        // ue.ready(function(){
        //     ue.setContent("");
        // });
    });
    /**
     * 关于编译器
     */
    function ResetEditor() {
        UEDITOR_CONFIG.UEDITOR_HOME_URL ='/stone/layuicms/ueditor/'; //一定要用这句话，否则你需要去
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
    form.on("submit(updateInformation)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var loginName = data.field.loginName;
        var dynamicContent = UE.getEditor('myEditor').getContent();



        //请求
        var req = {
            id: id,
            loginName: loginName,
            dynamicContent: dynamicContent
        };

        $api.UpdateDynamic(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("动态信息更新成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })




});


