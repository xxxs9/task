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
            , height: 415
            , url: $tool.getContext() + 'common/informationList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'},
                  {field: 'loginName', title: '发布者', width: '10%'}
                , {field: 'informationTitle', title: '标题', width: '10%'}
                , {field: 'isTop', title: '是否置顶', width: '10%',templet: '#top'}
                , {field: 'informationImg', title: '封面图', width: '10%',templet:'<div><img src="{{d.informationImg}}"></div>'}
                , {field: 'informationType', title: '资讯类型', width: '10%',templet: '#tmp'}
                , {field: 'createTime', title: '创建时间', width: '20%'}
                , {field: 'updateTime', title: '更新时间', width: '20%'}
                , {fixed: 'right', title: '操作', width: 300, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
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
                editUser(row.id);
            }else if(layEvent === 'initPwd'){//密码初始化
                initPwd(row.id);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryUser)", function (data) {
        var isTop = data.field.isTop;
        var loginName = data.field.loginName;
        var informationTitle = data.field.informationTitle;


        //表格重新加载
        tableIns.reload({
            where:{
                isTop:isTop,
                loginName:loginName,
                informationTitle:informationTitle
            }
        });

        return false;
    });

    //添加资讯
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "添加资讯",
            type: 2,
            content: "addInformation.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回资讯列表', '.layui-layer-setwin .layui-layer-close', {
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
    });

    //删除
    function delInformation(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DelInformation(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //初始化密码
    function initPwd(id){
        layer.confirm('确认初始化密码吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.InitPwd(req,function (res) {
                var pwd = res.data;
                layer.confirm("初始化成功，密码:"+pwd, function(index){
                    //do something
                    layer.close(index);
                });
            });
        });
    }

    //编辑
    function editUser(id){
        var index = layui.layer.open({
            title: "编辑资讯",
            type: 2,
            content: "editInformation.html?id="+id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回资讯列表', '.layui-layer-setwin .layui-layer-close', {
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