package com.tianruan.bishe.utils.Enum;

public enum ResultCodeEnum {

    SUCCESS_CODE("SUCCESS_CODE","200"),
    NODATA_CODE("NODATA_CODE","500.1"),
    ERROE_CODE("ERROE_CODE","500.2"),
    NO_PERMISSION("NO_PERMISSION","111");;
    private String code;
    private String valueCode;
    private ResultCodeEnum(String code, String valueCode) {
        this.code = code;
        this.valueCode = valueCode;
    }

    public String getCode() {
        return code;
    }
    public String getValueCode() {
        return valueCode;
    }

}
