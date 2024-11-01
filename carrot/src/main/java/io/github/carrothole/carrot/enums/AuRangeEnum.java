package io.github.carrothole.carrot.enums;

/**
 * @author moon
 * @since 0.0.1
 */
public enum AuRangeEnum {

    ALL(1,"全部数据"),
    CUSTOM(1<<1,"自定义组织机构"),
    SELF_DEPT(1<<2,"用户所在组织机构"),
    SELF_DEPT_HIGHER(1<<3 ,"用户所在组织机构的所有上级组织机构"),
    SELF_DEPT_LOWER(1<<4 ,"用户所在组织机构的所有下级组织机构"),

    ;



    private final Integer code;
    private final String desc;

    AuRangeEnum(Integer code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }

    public boolean hasMe(Integer code){
        return hasOneOf(this,code);
    }

    public static boolean hasOneOf(AuRangeEnum en, Integer code){
        return (code & en.getCode()) != 0;
    }


}
