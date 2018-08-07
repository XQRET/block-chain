/**
 * 设置Cookie
 * @param name 名称
 * @param value 值
 * @param Expires 过期时间，默认为null
 */
function setDomainCookie(name,value,strCookieDomain)
{
	var argv = setDomainCookie.arguments;
	var argc = setDomainCookie.arguments.length;
	var expires = (argc > 3) ? argv[3] : -10000; //Expires – 过期时间。
	setCookie(name, value, expires,"/",strCookieDomain);
}

/**
 * 设置Cookie
 * @param name 名称
 * @param value 值
 * @param Expires 过期时间，默认为null
 * @param Path 路径，默认为null,如果设为根目录，请用"/"
 * @param Domain 域，默认为null,
 * @param Secure 安全，默认为false
 */
function setCookie(name, value)
{
 var expdate = new Date();
 var argv = setCookie.arguments;
 var argc = setCookie.arguments.length;
 var expires = (argc > 2) ? argv[2] : null; //Expires – 过期时间。
 var path = (argc > 3) ? argv[3] : null;   //Path – 路径。指定与cookie关联的WEB页.
 var domain = (argc > 4) ? argv[4] : null; //Domain – 域。指定关联的WEB服务器或域。
 var secure = (argc > 5) ? argv[5] : false; //Secure – 安全。
 if(expires!=null)
 {
	 expdate.setTime(expdate.getTime() + expires);
 }
  document.cookie = name + "=" + escape (value) +((expires == null) ? "" : ("; expires="+ expdate.toGMTString()))
  +((path == null) ? "" : ("; path=" + path)) +((domain == null) ? "" : ("; domain=" + domain))
  +((secure == true) ? "; secure" : "");
}

/**
 * 获得Cookie的原始值,如果没有取到返回null
 * @param name 名称
 * @return
 */
function getCookie(name)
{
 var arg = name + "=";
 var alen = arg.length;
 var clen = document.cookie.length;
 var i = 0;
 while (i < clen){
  var j = i + alen;
  if (document.cookie.substring(i, j) == arg)
   return getCookieVal (j);
  i = document.cookie.indexOf(" ", i) + 1;
  if (i == 0) break;
 }
 return null;
}

//获得Cookie解码后的值
function getCookieVal(offset){
 var endstr = document.cookie.indexOf (";", offset);
 if (endstr == -1)
  endstr = document.cookie.length;
 return unescape(document.cookie.substring(offset, endstr));
}

/**
 * 删除Cookie的原始值
 * @param name 名称
 * @return
 */
function delCookie(name)
{
 var exp = -10000;
 var cval = getCookie(name);
 setJincinCookie(name,cval,exp);
}

function delHostCookie(name)
{
 var exp = -10000;
 var cval = getCookie(name);
 setHostCookie(name,cval,exp);
}

function setHostCookie(name,value)
{
	var argv = setHostCookie.arguments;
	var argc = setHostCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null; //Expires – 过期时间。
	var host=window.location.host;
	setCookie(name, value,expires,"/",host);
}