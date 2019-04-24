layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', '$api','jquery', 'table', 'laypage','laytpl', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        laytpl = layui.laytpl,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 页面初始化
     * */
    function init() {
    }
    init();

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#information-data'
            , height: 'full-200'
            , url: $tool.getContext() + 'adminHeroWeekFree/list.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'},
                  {field: 'heroName', title: '英雄名称', width: '20%'}
                , {field: 'heroTitle', title: '英雄称号', width: '20%'}
                , {field: 'img', title: '图片', width: '40%'}
                , {field: 'des', title: '描述', width: '20%'}
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(userFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delInformation(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editHeroDetail(row.id);
            }else if(layEvent === 'initPwd'){//密码初始化
                initPwd(row.id);
            }
        });
    }
    defineTable();

    //查询
    form.on("submit(queryHero)", function (data) {
        var heroName = data.field.heroName;
        //表格重新加载
        tableIns.reload({
            where:{
                heroName:heroName,
            }
        });
        return false;
    });

    //爬取英雄详情
    form.on("submit(reptilHero)", function (data) {
        $api.ReptilHeroWeekFree(null,function (data) {
            // layer.msg("详情数据爬取成功！", {time: 1000}, function () {
            //     tableIns.reload();
            // });
        });
        return false;
    });

    //编辑
    function editHeroDetail(id){
        var index = layui.layer.open({
            title: "编辑英雄详情",
            type: 2,
            content: "editHeroDetail.html?id="+id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });

        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    }
});