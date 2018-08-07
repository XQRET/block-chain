function MultiChoiceTree(id,jsonData)
{
	if(jsonData==null)
	{
		jsonData=[];
	}
	this.id=id;

	this.jsonData=jsonData;

	var arrSelectedNodeIds=new Array();
	this.getSelectedNodeIds=function()
	{
		return arrSelectedNodeIds;
	}
	this.setSelectedNodeIds=function(ids)
	{
		arrSelectedNodeIds=ids;
	}

	var strIconPath="Icon/";
	var selectedElements=null;
	var bOnlyLeafNode=false;
	var bIsShrinked=false;
	var bIsReadOnly=false;

	var strIdName="id";
	var strNameName="name";
	var strChildrenName="children";

	//设置json对象id/name/children变量的实际变量名
	this.setVarName=function(varIdName,varNameName,varChildrenName)
	{
		strIdName=varIdName;
		strNameName=varNameName;
		strChildrenName=varChildrenName;
	}

	this.getIconPath=function()
	{
		return strIconPath;
	}
	this.setIconPath=function(pstrIconPath)
	{
		strIconPath=pstrIconPath;
	}

	this.show=function(bForLeafNode,nWidth,nHeight,bShrinked,bReadOnly)
	{
		//参数处理
		if(bForLeafNode!=null && bForLeafNode!=undefined)
		{
			bOnlyLeafNode=bForLeafNode;
		}
		if(bShrinked!=null && bShrinked!=undefined)
		{
			bIsShrinked=bShrinked;
		}
		if(bReadOnly!=null && bReadOnly!=undefined)
		{
			bIsReadOnly=bReadOnly;
		}
		var varWidth=200;
		var varHeight=200;
		if(nWidth!=null && nWidth!=undefined)
		{
			varWidth=nWidth;
		}
		if(nHeight!=null && nHeight!=undefined)
		{
			varHeight=nHeight;
		}
		//显示组件框架
		document.write("<style>.treeTD{padding:0px;cursor:default}.treeImageTD{padding:0px;width:20px;height:20px;align:left}</style>");
		if(varHeight==null || ""+varHeight=="0" || varHeight=="")
		{
			document.write("<div id='"+this.id+"' style='border:1px solid #dcdcdc; padding:25px; background-color:#FFFFFF ;width: "+varWidth+"px;';>");
		}
		else
		{
			document.write("<div id='"+this.id+"' style='border:1px solid #dcdcdc; padding:25px; background-color:#FFFFFF ;width: "+varWidth+"px; height:"+varHeight+"px;';>");
		}
		document.write("</div>");

		this.reinit(bForLeafNode,jsonData,arrSelectedNodeIds);
	}

	this.reinit=function(bForLeafNode,jsonData,selectedNodeIds)
	{
		this.jsonData=jsonData;
		arrSelectedNodeIds=selectedNodeIds;

		//生成树的HTML代码
		var strHTML=getTreeHTML(this.id,this.jsonData,1,0,arrSelectedNodeIds,bForLeafNode,"");
		document.getElementById(this.id).innerHTML=strHTML;
		document.getElementById(this.id).varTree=this;
		selectedElements=new Array();

		if(arrSelectedNodeIds!=null && arrSelectedNodeIds.length>0)
		{//设置选中节点
//			with(document.getElementById(this.id).firstChild)
//			{
				var all=allChildren(document.getElementById(this.id).firstChild);
				for(var i=0;i<all.length;i++)
				{
					if(all[i].tagName.toLowerCase()!="td")
					{
						continue;
					}
					if(all[i].getAttribute("value")==null || all[i].getAttribute("value")==undefined)
					{
						continue;
					}
					if(isIdSelected(all[i].getAttribute("value"),arrSelectedNodeIds)==true)
					{
						selectedElements.push(all[i].previousSibling.firstChild);
					}
				}
				for(var i=0;i<selectedElements.length;i++)
				{//依次打开父节点
					var trNode=selectedElements[i].parentNode.parentNode;
					while(trNode.parentNode.parentNode.parentNode.tagName.toLowerCase()!="div")
					{
						trNode.parentNode.parentNode.parentNode.parentNode.setAttribute("childflag",2);
						trNode.parentNode.parentNode.parentNode.parentNode.style.display="";
						//trNode.parentNode.parentNode.parentNode.parentNode.previousSibling.children(0).children(0).src=strIconPath+"OpenCross.png";
						trNode.parentNode.parentNode.parentNode.parentNode.previousSibling.firstChild.firstChild.src=strIconPath+"OpenCross.png";
						trNode=trNode.parentNode.parentNode.parentNode.parentNode;
					}
				}
			//}
		}

		with(document.getElementById(this.id))
		{
			for(var i=0;i<selectedElements.length;i++)
			{//依次打开父节点
				var trNode=selectedElements[i].parentNode.parentNode;
				while(trNode.parentNode.parentNode.parentNode.tagName.toLowerCase()!="div")
				{
					trNode.parentNode.parentNode.parentNode.parentNode.setAttribute("childflag",2);
					trNode.parentNode.parentNode.parentNode.parentNode.style.display="";
					//trNode.parentNode.parentNode.parentNode.parentNode.previousSibling.children(0).children(0).src=strIconPath+"OpenCross.png";
					trNode.parentNode.parentNode.parentNode.parentNode.previousSibling.firstChild.firstChild.src=strIconPath+"OpenCross.png";

					trNode=trNode.parentNode.parentNode.parentNode.parentNode;
				}
			}
		}
	}

	var getTreeHTML=function(id,treeNode,nSiblingCount,nIndex,selectedNodeIds,bForLeafNode,strPath)
	{
		if(strPath==null || strPath==undefined || strPath=="")
		{
			strPath="";
		}
		else
		{
			strPath+=">";
		}

		var strHTML="";
		strHTML+="<table style=\"font-size:9pt;padding:0;width:0px;border:0px;\" cellpadding=\"0\" cellspacing=\"0\">";

		strHTML+=getTreeNodeHTML(id,treeNode,nSiblingCount,nIndex,selectedNodeIds,bForLeafNode,strPath);

		strHTML+="</table>";

		return strHTML;
	}

	var getTreeNodeHTML=function(id,treeNode,nSiblingCount,nIndex,selectedNodeIds,bForLeafNode,strPath)
	{
		var strHTML="";

		if(treeNode.constructor!=window.Array)
		{//非数组
			if(eval("treeNode."+strChildrenName)==null || eval("treeNode."+strChildrenName).length==0)
			{//叶子节点
				strHTML+="<tr>";

				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td class=\"treeImageTD\"><img src=\""+strIconPath+"TConnector.png\" onclick=\"document.getElementById('"+id+"').varTree.onOpenClose(event)\" childflag=\"0\"/></td>";
				}
				else
				{
					strHTML+="<td class=\"treeImageTD\"><img src=\""+strIconPath+"LConnector.png\" onclick=\"document.getElementById('"+id+"').varTree.onOpenClose(event)\" childflag=\"0\"/></td>";
				}

				if(isIdSelected(eval("treeNode."+strIdName),selectedNodeIds)==false)
				{//未选中
					strHTML+="<td class=\"treeTD\" style=\"cursor:default\"><img src=\""+strIconPath+"Unselected.gif\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\" selectflag=\"1\"/></td>";
					strHTML+="<td class=\"treeTD\" style=\"cursor:default\" value=\""+eval("treeNode."+strIdName)+"\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\"><nobr path=\""+strPath+eval("treeNode."+strNameName)+"\">"+eval("treeNode."+strNameName)+"</nobr></td>";
				}
				else
				{//选中
					strHTML+="<td class=\"treeTD\" style=\"cursor:default\"><img src=\""+strIconPath+"Selected.gif\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\" selectflag=\"2\"/></td>";
					strHTML+="<td class=\"treeTD\" style=\"cursor:default;color:#FF0000\" value=\""+eval("treeNode."+strIdName)+"\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\"><nobr path=\""+strPath+eval("treeNode."+strNameName)+"\">"+eval("treeNode."+strNameName)+"</nobr></td>";
				}

				strHTML+="</tr>";

				return strHTML;
			}
			else
			{//非叶子节点
				strHTML+="<tr>";

				if(nIndex+1<nSiblingCount)
				{
					if(bIsShrinked==false)
					{
						strHTML+="<td class=\"treeImageTD\"><img src=\""+strIconPath+"OpenCross.png\" onclick=\"document.getElementById('"+id+"').varTree.onOpenClose(event)\" childflag=\"2\" lastchildflag=\"0\"/></td>";
					}
					else
					{
						strHTML+="<td class=\"treeImageTD\"><img src=\""+strIconPath+"CloseCross.png\" onclick=\"document.getElementById('"+id+"').varTree.onOpenClose(event)\" childflag=\"1\" lastchildflag=\"0\"/></td>";
					}
				}
				else
				{
					if(bIsShrinked==false)
					{
						strHTML+="<td class=\"treeImageTD\"><img src=\""+strIconPath+"LOpenCross.png\" onclick=\"document.getElementById('"+id+"').varTree.onOpenClose(event)\" childflag=\"2\" lastchildflag=\"1\"/></td>";
					}
					else
					{
						strHTML+="<td class=\"treeImageTD\"><img src=\""+strIconPath+"CloseCross.png\" onclick=\"document.getElementById('"+id+"').varTree.onOpenClose(event)\" childflag=\"1\" lastchildflag=\"1\"/></td>";
					}
				}

				if(bForLeafNode==false)
				{//所有节点都能选
					if(isIdSelected(eval("treeNode."+strIdName),selectedNodeIds)==false)
					{//未选中
						strHTML+="<td class=\"treeTD\" style=\"cursor:default\"><img src=\""+strIconPath+"Unselected.gif\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\" selectflag=\"1\"/></td>";
						strHTML+="<td class=\"treeTD\" style=\"cursor:default\" value=\""+eval("treeNode."+strIdName)+"\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\"><nobr path=\""+strPath+eval("treeNode."+strNameName)+"\">"+eval("treeNode."+strNameName)+"</nobr></td>";
					}
					else
					{//选中
						strHTML+="<td class=\"treeTD\" style=\"cursor:default\"><img src=\""+strIconPath+"Selected.gif\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\" selectflag=\"2\"/></td>";
						strHTML+="<td class=\"treeTD\" style=\"cursor:default;color:#FF0000\" value=\""+eval("treeNode."+strIdName)+"\" onclick=\"document.getElementById('"+id+"').varTree.onSelect(event)\"><nobr path=\""+strPath+eval("treeNode."+strNameName)+"\">"+eval("treeNode."+strNameName)+"</nobr></td>";
					}
				}
				else
				{//只能选择叶子节点
					strHTML+="<td class=\"treeTD\"><img src=\""+strIconPath+"Connector.png\"/></td>";
					strHTML+="<td class=\"treeTD\"><nobr path=\""+strPath+eval("treeNode."+strNameName)+"\">"+eval("treeNode."+strNameName)+"</nobr></td>";
				}
				strHTML+="</tr>";

				if(bIsShrinked==true)
				{
					strHTML+="<tr style='display:none'>";
				}
				else
				{
					strHTML+="<tr style=''>";
				}
				if(nIndex+1<nSiblingCount)
				{
					strHTML+="<td class=\"treeImageTD\" style=\"background-image:url('"+strIconPath+"IConnector.png')\"></td>";
				}
				else
				{
					strHTML+="<td></td>";
				}

				strHTML+="<td class=\"treeTD\" style=\"cursor:default\"></td>";
				strHTML+="<td class=\"treeTD\" style=\"cursor:default\">";

				for(var i=0;i<eval("treeNode."+strChildrenName).length;i++)
				{
					var node=eval("treeNode."+strChildrenName)[i];

					strHTML+=getTreeHTML(id,eval("treeNode."+strChildrenName)[i],eval("treeNode."+strChildrenName).length,i,selectedNodeIds,bForLeafNode,strPath+eval("treeNode."+strNameName));
				}
				strHTML+="</td>";
				strHTML+="</tr>";
			}
		}
		else
		{//数组
			for(var i=0;i<treeNode.length;i++)
			{
				strHTML+=getTreeNodeHTML(id,treeNode[i],treeNode.length,i,selectedNodeIds,bForLeafNode,strPath);
			}
		}

		return strHTML;
	}

	//根据Id查询对应节点的文本
	this.getNodeTitle=function(nodeId,treeNode)
	{
		if(treeNode==null)
		{
			treeNode=this.data;
		}

		if(treeNode.constructor!=window.Array)
		{
			if(eval("treeNode."+strIdName)==nodeId)
			{
				return eval("treeNode."+strNameName);
			}

			var nLength=0;
			if(eval("treeNode."+strChildrenName)!=null)
			{
				nLength=eval("treeNode."+strChildrenName).length;
			}
			if(nLength==0)
			{
				return null;
			}
			for(var i=0;i<nLength;i++)
			{
				var valueTemp=getNodeTitle(nodeId,eval("treeNode."+strChildrenName)[i]);
				if(valueTemp!=null)
				{
					return valueTemp;
				}
			}
		}
		else
		{
			var nLength=treeNode.length;

			for(var i=0;i<nLength;i++)
			{
				var valueTemp=this.getNodeTitle(nodeId,treeNode[i]);
				if(valueTemp!=null)
				{
					return valueTemp;
				}
			}
		}

		return null;
	}

	this.onOpenClose=function(evt)
	{
		var ev = evt||window.event;
		var e = ev.srcElement||ev.target;
		if(e.getAttribute("childflag")=="0")
		{//无子结点
		}
		else if(e.getAttribute("childflag")=="1")
		{//子结点当前隐藏
			e.parentNode.parentNode.nextSibling.style.display="";
			if(e.lastchildflag==0)
			{
				e.src=strIconPath+"OpenCross.png";
			}
			else
			{
				e.src=strIconPath+"LOpenCross.png";
			}
			e.setAttribute("childflag","2");
		}
		else
		{//子结点当前显示
			e.parentNode.parentNode.nextSibling.style.display="none";
			if(e.lastchildflag==0)
			{
				e.src=strIconPath+"CloseCross.png";
			}
			else
			{
				e.src=strIconPath+"LCloseCross.png";
			}
			e.setAttribute("childflag","1");
		}
	}

	this.onSelect=function(evt)
	{
		if(bIsReadOnly==true)
		{
			return;
		}
		var ev = evt?evt:(window.event?window.event:null);
		var e = ev.srcElement||ev.target;

		if(e.tagName.toLowerCase()=="nobr")
		{
			e=e.parentNode.previousSibling.firstChild;
		}
		else if(e.tagName.toLowerCase()=="td")
		{
			e=e.previousSibling.firstChild;
		}
		else if(e.tagName.toLowerCase()=="img")
		{//e不用修改
		}

		if(e.getAttribute("selectflag")=="1")
		{//当前节点未选中，选中它
			selectNode(e,true);
		}
		else
		{//当前已经选中，清除它
			clearNode(e);
		}
	}

	var selectNode=function(e,bDeep)
	{
		e.src=strIconPath+"Selected.gif";
		e.setAttribute("selectflag","2");
		e.parentNode.nextSibling.style.color="#FF0000";

		selectedElements.push(e);

		if(bOnlyLeafNode==true)
		{//只有叶子节点可以选，不用考虑父节点
			return;
		}

		//把该节点的所有父节点也选中
		var p=e.parentNode.parentNode.parentNode.parentNode.parentNode;
		if(p.tagName!="DIV")
		{//顶层节点了
			p=p.parentNode.previousSibling.children[1].firstChild;//现在是父节点的img标签对象
			if(p.getAttribute("selectflag")=="1")
			{//当前节选中
				selectNode(p,false);
			}
		}

		if(bDeep==true)
		{//把该节点的所有子节点也选中
			if(e.parentNode.parentNode.firstChild.firstChild.getAttribute("childflag")!=0)
			{//有子节点
				var tables=e.parentNode.parentNode.nextSibling.children[2].children;
				for(var i=0;i<tables.length;i++)
				{
					e=tables[i].firstChild.firstChild.children[1].firstChild;//现在是img对象
					selectNode(e,true);
				}
			}
		}
	}

	var clearNode=function(e)
	{
		for(var i=0;i<selectedElements.length;i++)
		{
			if(selectedElements[i]==e)
			{
				e.src=strIconPath+"Unselected.gif";
				e.setAttribute("selectflag","1");
				e.parentNode.nextSibling.style.color="#000000";

				selectedElements.splice(i,1);
				break;
			}
		}

		if(bOnlyLeafNode==true)
		{//只有叶子节点可以选，不用考虑子节点
			return;
		}

		if(e.parentNode.previousSibling.firstChild.getAttribute("childflag")=="0")
		{//当前是叶子节点
			return;
		}

		//把该节点的所有子节点的选中也清除掉
		var tables=e.parentNode.parentNode.nextSibling.children[2].children;
		for(var i=0;i<tables.length;i++)
		{
			e=tables[i].firstChild.firstChild.children[1].firstChild;//现在是img对象
			clearNode(e);
		}
	}

	function getAbsLeft(obj)
	{
		if(obj.offsetParent==null)
		{
			return obj.offsetLeft;
		}
		else
		{
			var leftParent=getAbsLeft(obj.offsetParent);
			return obj.offsetLeft+leftParent;
		}
	}

	function getAbsTop(obj)
	{
		if(obj.offsetParent==null)
		{
			return obj.offsetTop;
		}
		else
		{
			var topParent=getAbsTop(obj.offsetParent);
			return obj.offsetTop+topParent;
		}
	}

	//检查Id是否选中
	function isIdSelected(strId,selectedNodeIds)
	{
		if(selectedNodeIds==null)
		{
			return false;
		}
		for(var i=0;i<selectedNodeIds.length;i++)
		{
			if(selectedNodeIds[i]==strId)
			{
				return true;
			}
		}

		return false;
	}

	this.getSelectedNode=function(arrSelectedNodeIds,arrSelectedNodeTitles,arrSelectedNodePaths)
	{
		for(var i=0;i<selectedElements.length;i++)
		{
			if(arrSelectedNodeIds!=null)
			{
				arrSelectedNodeIds[i]=selectedElements[i].parentNode.nextSibling.getAttribute("value");
			}
			if(arrSelectedNodeTitles!=null)
			{
				arrSelectedNodeTitles[i]=selectedElements[i].parentNode.nextSibling.firstChild.innerText;
			}
			if(arrSelectedNodePaths!=null)
			{
				arrSelectedNodePaths[i]=selectedElements[i].parentNode.nextSibling.firstChild.getAttribute("path");
			}
		}
	}
}
