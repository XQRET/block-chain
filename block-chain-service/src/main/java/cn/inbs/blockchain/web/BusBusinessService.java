package cn.inbs.blockchain.web;
import com.cdoframework.cdolib.base.Return;
import com.cdoframework.cdolib.base.Utility;
import com.cdoframework.cdolib.business.App;
import com.cdoframework.cdolib.business.BusServiceClient;
import com.cdoframework.cdolib.data.cdo.CDO;
import com.cdoframework.cdolib.servicebus.ServiceBus;

public class BusBusinessService extends App
{
	private ServiceBus serviceBus;

	/**
	 * 服务调用--客户端对象
	 */
	private BusServiceClient busServiceClient;

	protected BusBusinessService()
	{
		this.busServiceClient=null;
	}


	public boolean isRunning()
	{
		return true;
	}

	//开始
	public Return start()
	{
		String strXML=Utility.readTextResource("servicebus.xml", "utf-8",this.getClass().getClassLoader());
		if(strXML==null)
		{
			return Return.valueOf(-1,"Read servicebus.xml failed");
		}

		this.serviceBus=new ServiceBus();
		Return ret=this.serviceBus.init(strXML,null);
		if(ret.getCode()!=0)
		{
			return ret;
		}
		ret=this.serviceBus.start();
		if(ret.getCode()!=0)
		{
			this.serviceBus.destroy();
			return ret;
		}
		//加载LocalCache
		String strLocalCacheXML = Utility.readTextResource("LocalCache.xml", "utf-8",this.getClass().getClassLoader());
		if (null == strLocalCacheXML) {
			this.busServiceClient = new BusServiceClient(this.serviceBus);
		} else {
			this.busServiceClient = new BusServiceClient(this.serviceBus, "LocalCache.xml", "utf-8", null);
		}
		ret = this.busServiceClient.start();
		return ret;
	}

	//停止
	public void stop()
	{
		this.busServiceClient.stop();
		this.serviceBus.stop();
		this.serviceBus.destroy();
	}


	//执行检查
	public Return checkToDo(CDO cdoRequest)
	{
		Return ret=this.serviceBus.checkToDo(cdoRequest);

		return ret;
	}

	//执行事务
	public Return handleTrans(CDO cdoRequest,CDO cdoResponse)
	{
		Return ret=this.busServiceClient.handleTrans(cdoRequest,cdoResponse);

		return ret;
	}

	//处理事件
	//add by brook.zhao
	public void handleEvent(CDO cdoEvent)
	{
		this.serviceBus.handleEvent(cdoEvent);
	}

	//触发事件
	public void raiseEvent(CDO cdoRequest)
	{
		this.serviceBus.raiseEvent(cdoRequest,0);
	}
}