package com.tudou.crm.storage.inStorage.entity;

import com.tudou.system.platform.base.annotation.CreateFlag;
import com.tudou.system.platform.base.annotation.ModifyFlag;
import com.tudou.system.platform.base.annotation.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "crm_in_storage_work_part")
@CreateFlag
@ModifyFlag
public class InStorageWorkPart {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 入库单号
     */
    @Column(name = "in_storage_work")
    private String inStorageWork;

    /**
     * 来源明细编号
     */
    @Column(name = "from_order_part")
    @NotEmpty
    private String fromOrderPart;

    /**
     * 产品编码
     */
    @Column(name = "goods_id")
    @NotEmpty
    private String goodsId;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    @NotEmpty
    private String goodsName;

    /**
     * 单位编码
     */
    @Column(name = "goods_unit_id")
    @NotEmpty
    private String goodsUnitId;

    /**
     * 单位名称
     */
    @Column(name = "goods_unit_name")
    @NotEmpty
    private String goodsUnitName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 计划数量
     */
    @Column(name = "plan_amount")
    @NotEmpty
    private BigDecimal planAmount;

    /**
     * 入库数量
     */
    @Column(name = "receiving_amount")
    private BigDecimal receivingAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除0：正常,1删除
     */
    @Column(name = "server_flag")
    private Integer serverFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_emp")
    private String createEmp;

    /**
     * 创建人编号
     */
    @Column(name = "create_emp_id")
    private String createEmpId;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 修改人
     */
    @Column(name = "modify_emp")
    private String modifyEmp;

    /**
     * 修改人编号
     */
    @Column(name = "modify_emp_id")
    private String modifyEmpId;


    public String getInStorageWork() {
        return inStorageWork;
    }

    public void setInStorageWork(String inStorageWork) {
        this.inStorageWork = inStorageWork;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取来源明细编号
     *
     * @return from_order_part - 来源明细编号
     */
    public String getFromOrderPart() {
        return fromOrderPart;
    }

    /**
     * 设置来源明细编号
     *
     * @param fromOrderPart 来源明细编号
     */
    public void setFromOrderPart(String fromOrderPart) {
        this.fromOrderPart = fromOrderPart == null ? null : fromOrderPart.trim();
    }

    /**
     * 获取产品编码
     *
     * @return goods_id - 产品编码
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置产品编码
     *
     * @param goodsId 产品编码
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取产品名称
     *
     * @return goods_name - 产品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置产品名称
     *
     * @param goodsName 产品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取单位编码
     *
     * @return goods_unit_id - 单位编码
     */
    public String getGoodsUnitId() {
        return goodsUnitId;
    }

    /**
     * 设置单位编码
     *
     * @param goodsUnitId 单位编码
     */
    public void setGoodsUnitId(String goodsUnitId) {
        this.goodsUnitId = goodsUnitId == null ? null : goodsUnitId.trim();
    }

    /**
     * 获取单位名称
     *
     * @return goods_unit_name - 单位名称
     */
    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    /**
     * 设置单位名称
     *
     * @param goodsUnitName 单位名称
     */
    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName == null ? null : goodsUnitName.trim();
    }

    /**
     * 获取规格型号
     *
     * @return goods_model - 规格型号
     */
    public String getGoodsModel() {
        return goodsModel;
    }

    /**
     * 设置规格型号
     *
     * @param goodsModel 规格型号
     */
    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel == null ? null : goodsModel.trim();
    }

    /**
     * 获取计划数量
     *
     * @return plan_amount - 计划数量
     */
    public BigDecimal getPlanAmount() {
        return planAmount;
    }

    /**
     * 设置计划数量
     *
     * @param planAmount 计划数量
     */
    public void setPlanAmount(BigDecimal planAmount) {
        this.planAmount = planAmount;
    }

    /**
     * 获取入库数量
     *
     * @return receiving_amount - 入库数量
     */
    public BigDecimal getReceivingAmount() {
        return receivingAmount;
    }

    /**
     * 设置入库数量
     *
     * @param receivingAmount 入库数量
     */
    public void setReceivingAmount(BigDecimal receivingAmount) {
        this.receivingAmount = receivingAmount;
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

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 获取创建人编号
     *
     * @return create_emp_id - 创建人编号
     */
    public String getCreateEmpId() {
        return createEmpId;
    }

    /**
     * 设置创建人编号
     *
     * @param createEmpId 创建人编号
     */
    public void setCreateEmpId(String createEmpId) {
        this.createEmpId = createEmpId == null ? null : createEmpId.trim();
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    /**
     * 获取修改人编号
     *
     * @return modify_emp_id - 修改人编号
     */
    public String getModifyEmpId() {
        return modifyEmpId;
    }

    /**
     * 设置修改人编号
     *
     * @param modifyEmpId 修改人编号
     */
    public void setModifyEmpId(String modifyEmpId) {
        this.modifyEmpId = modifyEmpId == null ? null : modifyEmpId.trim();
    }
}