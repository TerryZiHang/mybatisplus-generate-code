package com.szh.bean.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Terry
 * @since 2021-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_contact_info")
public class TContactInfoPO extends Model<TContactInfoPO> {

    private static final long serialVersionUID = 1L;

    private String markId;

    private String name;

    private String phone;

    private String email;

    private String position;


    @Override
    protected Serializable pkVal() {
        return this.markId;
    }

}
