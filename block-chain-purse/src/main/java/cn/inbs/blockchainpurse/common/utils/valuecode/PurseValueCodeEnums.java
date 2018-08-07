package cn.inbs.blockchainpurse.common.utils.valuecode;

/**
 * @Description: 码值枚举类
 * @Package: cn.inbs.blockchainpurse.common.utils.valuecode
 * @ClassName: PurseValueCodeEnums
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/21 12:23
 * @Version: 1.0
 */
public enum PurseValueCodeEnums {
    ;
    private String description;
    private String[] codeArray;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCodeArray() {
        return codeArray;
    }

    public void setCodeArray(String[] codeArray) {
        this.codeArray = codeArray;
    }

    PurseValueCodeEnums(String description, String[] codeArray) {
        this.description = description;
        this.codeArray = codeArray;
    }
}
