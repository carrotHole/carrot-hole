package com.carrothole.carrot.config.mf;

import com.carrothole.carrot.entity.BaseUserTime;
import com.carrothole.carrot.entity.BaseUserTimeTenant;
import com.carrothole.carrot.util.SecurityUtil;
import com.mybatisflex.annotation.InsertListener;

import java.util.Date;

/**
 * Description:  <br>
 * Date: 2024/8/30 15:33 <br>
 *
 * @author moon
 * @since
 */
public class MfDefaultInsertListener implements InsertListener {
    @Override
    public void onInsert(Object o) {

        if (o instanceof BaseUserTime baseUserTime){
            baseUserTime.setCreatedBy(SecurityUtil.getPayLoad().getUsername());
            baseUserTime.setCreatedTime(new Date());
            return;
        }
        if (o instanceof BaseUserTimeTenant baseUserTimeTenant){
            baseUserTimeTenant.setCreatedBy(SecurityUtil.getPayLoad().getUsername());
            baseUserTimeTenant.setCreatedTime(new Date());

        }
    }
}
