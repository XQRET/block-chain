package cn.inbs.blockchain.dao.vo.cockpit;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IncomeVO {

	@JSONField(name = "t")
	private List<String> time = new ArrayList<String>(6);

	/**
	 * 金额
	 */
	@JSONField(name = "m")
	private List<BigDecimal> money = new ArrayList<BigDecimal>(6);

	public List<BigDecimal> getMoney() {
		return money;
	}

	public List<String> getTime() {
		return time;
	}

	public void setMoney(List<BigDecimal> money) {
		this.money = money;
	}

	public void setTime(List<String> time) {
		this.time = time;
	}
}
