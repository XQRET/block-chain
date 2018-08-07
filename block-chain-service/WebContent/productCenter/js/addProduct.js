var laypage = layui.laypage,layer = layui.layer,laydate = layui.laydate,layform = layui.form;

//根据合约id查询产品信息
$("#contractId").blur(function(){
    var contractId=$("#contractId").val();
    if(contractId!=null&&contractProductType!=null){
        $.post(""+strRootPath+"/contract/queryContractPresaleBasicInfo.do",{"contractId":contractId},function(data){
            if (data != null) {
                if(data.resultCode == 0){
                    $("#amount").val(data.resultDate.amount);
                    $("#term").val(data.resultDate.term);
                    $("#interestRate").val(data.resultDate.interestRate);
                }
            }else{

            }
        });
    }
});
$("#addProductForm .addpro_btn").click(function(){
    var addProductForm=$("#addProductForm").serialize();
    $.post(""+strRootPath+"/contract/addContractToPresaleList.do",addProductForm,function(data){
        if (data != null) {
            layer.open({
                content:data.resultCode == 0?'操作成功':data.resultMessage
                ,yes: function(index, layero){
                    layer.close(index);
                }
                ,end:function(){
                    closefbE();
                }
            });
        }else{
            layer.open({
                title: '温馨提示'
                , content: data.resultMessage
            });
        }
    });
});
