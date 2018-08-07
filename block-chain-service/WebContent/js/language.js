$(function(){ 
      // do something 
    var script=document.createElement("script");  
    script.type="text/javascript";  
    script.src=getRootPath()+"/js/WidgetV3.ashx.js";  
    document.getElementsByTagName('head')[0].appendChild(script);  
    
    var value = sessionStorage.getItem("language");
    document.onreadystatechange = function () {
        if (document.readyState == 'complete') {
            if(value==="1"){
                Microsoft.Translator.Widget.Translate('zh-CHS', 'en', onProgress, onError, onComplete, onRestoreOriginal, 2000);
            }
        }
    }
    function onProgress(value) {
    }
    function onError(error) {
    }
    function onComplete() {
        $("#WidgetFloaterPanels").hide();
    }
    function onRestoreOriginal() { 
    }
});

function translate(){
    var value = sessionStorage.getItem("language");
    if(value==="1"){
    	sessionStorage.setItem('all_logoimg','images/logo.png');/*存入logo*/
    	sessionStorage.setItem('planChart','../images/notlogged/plan_pic.png');/*存入方案架构图*/
    	sessionStorage.setItem('banner2','./images/notlogged/banner_bg_02.png');/*存入首页banner图*/
    	sessionStorage.setItem('index_hy','./images/notlogged/index_hy_left.png');/*存入首页合约图*/
    	sessionStorage.setItem('profile','../images/notlogged/profile.png');/*存入关于我们页面架构图*/
    	sessionStorage.setItem("language", "0");
    }else{
        sessionStorage.setItem("language", "1");
    	sessionStorage.setItem('all_logoimg','images/logo_en.png');/*页面为英文时切换英文logo*/
    	sessionStorage.setItem('planChart','../images/notlogged/plan_pic_en.png');/*页面为英文时切换英文图*/
    	sessionStorage.setItem('banner2','./images/notlogged/banner_bg_02_en.png');/*页面为英文时切换英文banner图*/
    	sessionStorage.setItem('index_hy','./images/notlogged/index_hy_left_en.png');/*页面为英文时切换英文合约图*/
    	sessionStorage.setItem('profile','../images/notlogged/profile_en.png');/*页面为英文时切换英文架构图*/
    }
}