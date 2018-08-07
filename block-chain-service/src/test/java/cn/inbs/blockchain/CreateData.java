package cn.inbs.blockchain;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateData {
    private static Logger logger = LoggerFactory.getLogger(CreateData.class);

    public static void main(String[] args) {
        List<String> dataArray = new ArrayList<String>();
        dataArray.add("1GChC41hvt|239-245 callaghan road, Naragba, QLD 4504|别墅|{\"室\":\"3\",\"厅\":\"2\",\"卫\":\"2\",\"厨\":\"1\",\"阳台\":\"1\"}|441|4000000|2000");
        dataArray.add("6TgPt415re|15-19 Edgehill Avenue, Botany, NSW|别墅|{\"室\":\"2\",\"厅\":\"2\",\"卫\":\"1\",\"厨\":\"1\",\"阳台\":\"1\"}|238|4000000|2000");
        dataArray.add("hSg421s5wT|1 Young Street,Randwick NSW 2031|普通住宅|{\"室\":\"3\",\"厅\":\"1\",\"卫\":\"2\",\"厨\":\"1\",\"阳台\":\"1\"}|98|4000000|2000");
        dataArray.add("iv1ChiDCt4|7-23 Street Spencer ,Melbourne VIC 3000|别墅|{\"室\":\"3\",\"厅\":\"2\",\"卫\":\"1\",\"厨\":\"1\",\"阳台\":\"2\"}|180|4000000|2000");
        dataArray.add("iwiihC14h1|239-245 Callaghan Road, Naragba, QLD 4504|别墅|{\"室\":\"3\",\"厅\":\"1\",\"卫\":\"1\",\"厨\":\"1\",\"阳台\":\"3\"}|441|4000000|2000");
        dataArray.add("PD64s65sf1|1栋809|别墅|{\"室\":\"2\",\"厅\":\"2\",\"卫\":\"1\",\"厨\":\"1\",\"阳台\":\"1\"}|80|4000000|2000");
        dataArray.add("rwS61Pt42s|51-59 thistlethwaite street|别墅|{\"室\":\"2\",\"厅\":\"1\",\"卫\":\"1\",\"厨\":\"1\",\"阳台\":\"1\"}|189|4000000|2000");
        dataArray.add("w1va4DPgv9|47-49 Glendale Avenue Templestowe, VIC|别墅|{\"室\":\"2\",\"厅\":\"1\",\"卫\":\"2\",\"厨\":\"1\",\"阳台\":\"1\"}|122|4000000|2000");

        createDate(dataArray);
    }

    private static void createDate(List<String> dataArray) {

        for (String data : dataArray) {
            Map<String, String> dataMap = new HashMap<String, String>();
            String[] split = data.split("\\|");
            String contractId = split[0].trim();
            dataMap.put("houseAddress", split[1].trim());
            dataMap.put("houseApartments", split[2].trim());
            String houseTypeJson = split[3].trim();
            Map<String, String> parse = (Map<String, String>) JSON.parse(houseTypeJson);
            StringBuilder stringBuilder = new StringBuilder();
            if (null != parse.get("室")) {
                stringBuilder.append(parse.get("室")).append("室");
            }

            if (null != parse.get("厅")) {
                stringBuilder.append(parse.get("厅")).append("厅");
            }


            if (null != parse.get("卫")) {
                stringBuilder.append(parse.get("卫")).append("卫");
            }

            if (null != parse.get("厨")) {
                stringBuilder.append(parse.get("厨")).append("厨");
            }

            if (null != parse.get("阳台")) {
                stringBuilder.append(parse.get("阳台")).append("阳台");
            }


            dataMap.put("houseType", stringBuilder.toString());
            dataMap.put("houseSize", split[4].trim());
            dataMap.put("houseTotalAmount", split[5].trim());
            dataMap.put("numberOfHairstyles", split[6].trim());

            String s = JSON.toJSONString(dataMap);

            String sql = "update t_block_contract set attachInfoJson='" + s + "' where contractId='" + contractId + "';";
            System.err.println(sql);


        }
    }
}
