package cn.inbs.blockchain.dao.vo.cockpit;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NumVO {

	/**
	 * X轴时间
	 */
	@JSONField(name = "t")
	private List<String> time = new ArrayList<String>(12);
	/**
	 * 数量
	 */
	@JSONField(name = "n")
	private List<Integer> number = new ArrayList<Integer>(12);
	/**
	 * 金额
	 */
	@JSONField(name = "m")
	private List<BigDecimal> money = new ArrayList<BigDecimal>(12);

	public List<String> getTime() {
		return time;
	}

	public void setTime(List<String> time) {
		this.time = time;
	}

	public List<Integer> getNumber() {
		return number;
	}

	public void setNumber(List<Integer> number) {
		this.number = number;
	}

	public List<BigDecimal> getMoney() {
		return money;
	}

	public void setMoney(List<BigDecimal> money) {
		this.money = money;
	}

}
