package cn.inbs.blockchain.dao.vo.cockpit;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AssertVO {

	@JSONField(name = "y")
	private List<String> listAssert = new ArrayList<String>();

	@JSONField(name = "x")
	private List<BigDecimal> listMoney = new ArrayList<BigDecimal>();

	public List<String> getListAssert() {
		return listAssert;
	}

	public void setListAssert(List<String> listAssert) {
		this.listAssert = listAssert;
	}

	public List<BigDecimal> getListMoney() {
		return listMoney;
	}

	public void setListMoney(List<BigDecimal> listMoney) {
		this.listMoney = listMoney;
	}

}
