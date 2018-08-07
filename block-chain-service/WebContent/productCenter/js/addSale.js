function loadImg(file, img) {
    //创建读取文件的对象
    var reader = new FileReader();
    //创建文件读取相关的变量
    var imgFile;
    //为文件读取成功设置事件
    reader.onload = function (e) {
        imgFile = e.target.result;
        console.log(imgFile);
        img.attr('src', imgFile);
    };
    //正式读取文件
    reader.readAsDataURL(file);
}


$(function () {

    $('input[type="file"]').change(function(e){
        var file = this.files[0];
        var img = $("#"+this.id+"Img");
        loadImg(file, img);

    })



})

function updateImg(id) {
    $("#"+id).click();
}

$(".addpro_btn").click(function(){
    var productType = $("#productType").val();
    var productName = $("#productName").val();
    var productBanner = $("#productBanner")[0].files[0];
    var productDetails = $("#productDetails")[0].files[0];

    var formData = new FormData();
    formData.append("productType", productType);
    formData.append("productName", productName);
    formData.append("productBanner", productBanner);
    formData.append("productDetails", productDetails);




    $.ajax({
        type: "POST",
        data: formData,
        url: strRootPath + "/product/addProductToSaleList.do",
        cache: false,
        contentType: false,
        processData: false,
        success: function (data) {
            if (data != null) {
                layer.open({
                    content:data.resultCode == 0?'操作成功':data.resultMessage
                    ,yes: function(index, layero){
                        layer.close(index);
                    }
                    ,end:function(){
                        window.location.href='saleList.htm';
                    }
                });
            }else{
                layer.open({
                    title: '温馨提示'
                    , content: data.resultMessage
                });
            }
        }
    });


});