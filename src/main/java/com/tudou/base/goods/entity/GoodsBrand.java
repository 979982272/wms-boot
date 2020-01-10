package com.tudou.base.goods.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "eidp_goods_brand")
public class GoodsBrand {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 中文名称
     */
    @Column(name = "chn_name")
    private String chnName;

    /**
     * 英文名称
     */
    @Column(name = "eng_name")
    private String engName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private int serverFlag;


    public int getServerFlag() {
        return serverFlag;
    }

    public void setServerFlag(int serverFlag) {
        this.serverFlag = serverFlag;
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取中文名称
     *
     * @return chn_name - 中文名称
     */
    public String getChnName() {
        return chnName;
    }

    /**
     * 设置中文名称
     *
     * @param chnName 中文名称
     */
    public void setChnName(String chnName) {
        this.chnName = chnName == null ? null : chnName.trim();
    }

    /**
     * 获取英文名称
     *
     * @return eng_name - 英文名称
     */
    public String getEngName() {
        return engName;
    }

    /**
     * 设置英文名称
     *
     * @param engName 英文名称
     */
    public void setEngName(String engName) {
        this.engName = engName == null ? null : engName.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}