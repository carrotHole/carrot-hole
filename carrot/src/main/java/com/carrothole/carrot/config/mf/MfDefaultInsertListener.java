package com.carrothole.carrot.config.mf;

import com.carrothole.carrot.entity.BaseUserTime;
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
        // todo 数据填充
        if (o instanceof BaseUserTime baseUserTime){
            baseUserTime.setCreatedBy("");
            baseUserTime.setCreatedTime(new Date());
            return;
        }
    }
}
