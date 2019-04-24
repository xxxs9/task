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
        var ue; //百度编译器
        upload = layui.upload;//上传组件

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

        $api.GetHeroDetail(req,function (res) {
            var data = res.data;
            $("[name='heroName']").val(data.heroName);
            $("[name='heroTitle']").val(data.heroTitle);
            $("[name='videoUrl']").val(data.videoUrl);
            $("[name='output']").val(data.output);
            $("[name='existence']").val(data.existence);
            $("[name='physics']").val(data.physics);
            $("[name='magic']").val(data.magic);
            $("[name='operation']").val(data.operation);
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 表单提交
     * */
    form.on("submit(updateInformation)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var heroName = data.field.heroName;
        var heroTitle = data.field.heroTitle;
        var videoUrl = data.field.videoUrl;
        var output = data.field.output;
        var existence = data.field.existence;
        var physics = data.field.physics;
        var magic = data.field.magic;
        var operation = data.field.operation;

        //请求
        var req = {
            id: id,
            heroName: heroName,
            heroTitle: heroTitle,
            videoUrl: videoUrl,
            output: output,
            existence: existence,
            physics: physics,
            magic: magic,
            operation: operation
        };

        $api.UpdateHeroDetail(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("详情更新成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


