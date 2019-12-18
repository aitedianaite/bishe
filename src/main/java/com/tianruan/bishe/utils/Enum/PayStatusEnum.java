package com.tianruan.bishe.utils.Enum;

public enum PayStatusEnum {
    UN_PAY("UN_PAY",0),SUCCESS_PAY("SUCCESS_PAY",1);
    private String code;
    private Integer valueCode;
     PayStatusEnum(String code, Integer valueCode) {
        this.code = code;
        this.valueCode = valueCode;
    }

    public String getCode() {
        return code;
    }
    public Integer getValueCode() {
        return valueCode;
    }
}
