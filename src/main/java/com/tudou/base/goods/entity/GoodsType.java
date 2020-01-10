package com.tudou.base.goods.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "eidp_goods_type")
public class GoodsType {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String des;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父级主键
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 创建人id
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 修改日期
     */
    private Date modifytime;

    /**
     * 修改人id
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

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
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取描述
     *
     * @return des - 描述
     */
    public String getDes() {
        return des;
    }

    /**
     * 设置描述
     *
     * @param des 描述
     */
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
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
     * 获取父级主键
     *
     * @return parent_id - 父级主键
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级主键
     *
     * @param parentId 父级主键
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取创建日期
     *
     * @return createtime - 创建日期
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建日期
     *
     * @param createtime 创建日期
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取创建人id
     *
     * @return create_emp_id - 创建人id
     */
    public String getCreateEmpId() {
        return createEmpId;
    }

    /**
     * 设置创建人id
     *
     * @param createEmpId 创建人id
     */
    public void setCreateEmpId(String createEmpId) {
        this.createEmpId = createEmpId == null ? null : createEmpId.trim();
    }

    /**
     * 获取创建人
     *
     * @return create_emp - 创建人
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * 设置创建人
     *
     * @param createEmp 创建人
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * 获取修改日期
     *
     * @return modifytime - 修改日期
     */
    public Date getModifytime() {
        return modifytime;
    }

    /**
     * 设置修改日期
     *
     * @param modifytime 修改日期
     */
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * 获取修改人id
     *
     * @return modify_emp_id - 修改人id
     */
    public String getModifyEmpId() {
        return modifyEmpId;
    }

    /**
     * 设置修改人id
     *
     * @param modifyEmpId 修改人id
     */
    public void setModifyEmpId(String modifyEmpId) {
        this.modifyEmpId = modifyEmpId == null ? null : modifyEmpId.trim();
    }

    /**
     * 获取修改人
     *
     * @return modify_emp - 修改人
     */
    public String getModifyEmp() {
        return modifyEmp;
    }

    /**
     * 设置修改人
     *
     * @param modifyEmp 修改人
     */
    public void setModifyEmp(String modifyEmp) {
        this.modifyEmp = modifyEmp == null ? null : modifyEmp.trim();
    }
}