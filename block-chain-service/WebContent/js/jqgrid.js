var BRK_DECORATION_ROW = null;
function selectThisRow(ele,lRowUniqId,func_callback){
    if(BRK_DECORATION_ROW != null){
        BRK_DECORATION_ROW.removeClass("select-row");
        BRK_DECORATION_ROW.attr("onmouseenter","overRow(this)");
        BRK_DECORATION_ROW.attr("onmouseleave","outRow(this)");
    }
    var row = $(ele);
    row.attr("onmouseenter","");
    row.attr("onmouseleave","");
    row.removeClass("on-row");
    row.addClass("select-row");
    BRK_DECORATION_ROW = row;
    g_lUserId = lRowUniqId;
    if(typeof func_callback != "undefined" && func_callback != null){
        func_callback(lRowUniqId);
    }
}

function selectThisElseRow(ele,lRowUniqId,func_callback){
	if(BRK_DECORATION_ROW != null){
        BRK_DECORATION_ROW.removeClass("select-row");
        BRK_DECORATION_ROW.attr("onmouseenter","overRow(this)");
        BRK_DECORATION_ROW.attr("onmouseleave","outRow(this)");
    }
    var row = $(ele);
    row.attr("onmouseenter","");
    row.attr("onmouseleave","");
    row.removeClass("on-row");
    //row.addClass("select-row");
    BRK_DECORATION_ROW = row;
    g_lUserId = lRowUniqId;
    if(typeof func_callback != "undefined" && func_callback != null){
        func_callback(lRowUniqId);
    }
}

  

function overRow(ele){
    $(ele).removeClass("leave-row");
    $(ele).addClass("on-row");
}

function outRow(ele) {
    $(ele).removeClass("on-row");
    $(ele).addClass("leave-row");
}