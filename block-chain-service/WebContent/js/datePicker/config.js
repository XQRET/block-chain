if (!HDPT)
{
	var HDPT = {
		user : {},
		funlist:[]
	};
}
var langList = 
[
	{name:'en',	charset:'UTF-8'},
	{name:'zh-cn',	charset:'UTF-8'},
	{name:'zh-tw',	charset:'UTF-8'}
];

var skinList = 
[
	{name:'default',	charset:'UTF-8'},
	{name:'whyGreen',	charset:'UTF-8'}
];

HDPT.rollRoundExcelDataTimerDelay = 18000000;// 服务商车辆查询列表:导入导出轮询定时器间毫秒