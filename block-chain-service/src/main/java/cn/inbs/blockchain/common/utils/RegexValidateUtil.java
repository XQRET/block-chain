/**
 * @author tanglei
 * @data 2017-11-1
 */
package cn.inbs.blockchain.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * @author tanglei
 * @date 2017-11-1
 */
public class RegexValidateUtil {
	private static boolean flag = false;
	private static String regex = "";

	private static Logger logger = Logger.getLogger(RegexValidateUtil.class);

	public static boolean check(String str, String regex) {
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证非空
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkNotEmputy(String notEmputy) {
		regex = "^\\s*$";
		return check(notEmputy, regex) ? false : true;
	}

	/**
	 * 验证邮箱
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		String regex = "^\\w+[-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$ ";
		return check(email, regex);
	}

	/**
	 * 验证手机号码
	 *
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145
	 * 电信号码段:133、153、180、189
	 *
	 * @param cellphone
	 * @return
	 */
	public static boolean checkCellphone(String cellphone) {
		//String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))|(17[0-9])\\d{8}$";
		String regex = "^1[3578]\\d{9}|14[357]\\d{8}$";
		return check(cellphone, regex);
	}

	/**
	 * 验证密码
	* @param password
	* @return
	 */
	public static boolean checkPassword(String password){
		String regex = "^[a-zA-Z0-9]{6,16}$";
		return check(password, regex);
	}

	/**
	 * 验证固话号码
	 * @param telephone
	 * @return
	 */
	public static boolean checkTelephone(String telephone) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		return check(telephone, regex);
	}

	/**
	 * 验证传真号码
	 * @param fax
	 * @return
	 */
	public static boolean checkFax(String fax) {
		String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		return check(fax, regex);
	}

	/**
	 * 验证QQ号码
	 * @param QQ
	 * @return
	 */
	public static boolean checkQQ(String QQ) {
		String regex = "^[1-9][0-9]{4,} $";
		return check(QQ, regex);
	}
}