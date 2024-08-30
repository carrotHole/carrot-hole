package com.carrothole.carrot.config.validate;

/**
 * Description: 参数校验分组 <br>
 * Date: 2024/8/30 13:31 <br>
 *
 * @author moon
 * @since 0.0.1
 */
public interface ValidateGroup {

    Class<? extends GroupService> SAVE = ValidateSaveGroup.class;
    Class<? extends GroupService> UPDATE =  ValidateUpdateGroup.class;
    Class<?>[] SAVE_UPDATE = {ValidateSaveGroup.class, ValidateUpdateGroup.class};


    interface GroupService {}
    interface ValidateSaveGroup extends GroupService {}
    interface ValidateUpdateGroup extends GroupService {}

}
