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

        $api.GetComment(req,function (res) {
            var data = res.data;
            $("[name='commentName']").val(data.commentName);
            $("[name='dynamicId']").val(data.dynamicId);
            //初始化填充富文本编辑器
            ue.ready(function(){
                ue.setContent(data.commentDetails);
            });
            form.render();//重新绘制表单，让修改生效
        });
    }


    /**
     * 自动加载编译器
     */
    $(function () {
        var html = '<div id="myEditor" name="dynamicContent"></div>';
        $('#commentContent').append(html);
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
    form.on("submit(updateComment)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var commentName = data.field.commentName;
        var dynamicId = data.field.dynamicId;
        var commentDetails = UE.getEditor('myEditor').getContent();



        //请求
        var req = {
            id: id,
            commentName: commentName,
            dynamicId: dynamicId,
            commentDetails: commentDetails
        };

        $api.UpdateComment(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("评论信息更新成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })




});


