package com.tudou.base.menu.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "eidp_menu")
public class EidpMenu {
    @Id
    private Integer id;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 父级编码
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 地址
     */
    private String url;

    /**
     * 文本
     */
    private String text;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    @Transient
    private List<EidpMenu> childMenus;

    @Transient
    private EidpMenu parentMenu;

    public List<EidpMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<EidpMenu> childMenus) {
        this.childMenus = childMenus;
    }

    public EidpMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(EidpMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取等级
     *
     * @return level - 等级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置等级
     *
     * @param level 等级
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取父级编码
     *
     * @return parent_id - 父级编码
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级编码
     *
     * @param parentId 父级编码
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取地址
     *
     * @return url - 地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置地址
     *
     * @param url 地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取文本
     *
     * @return text - 文本
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文本
     *
     * @param text 文本
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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

    /**
     * 获取是否删除0：正常,1删除
     *
     * @return server_flag - 是否删除0：正常,1删除
     */
    public Integer getServerFlag() {
        return serverFlag;
    }

    /**
     * 设置是否删除0：正常,1删除
     *
     * @param serverFlag 是否删除0：正常,1删除
     */
    public void setServerFlag(Integer serverFlag) {
        this.serverFlag = serverFlag;
    }
}