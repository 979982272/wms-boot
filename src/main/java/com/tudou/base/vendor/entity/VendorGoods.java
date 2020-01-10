package com.tudou.base.vendor.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eidp_vendor_goods")
public class VendorGoods {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     * 产品编码
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 产品类型编码
     */
    @Column(name = "goods_type_id")
    private String goodsTypeId;

    /**
     * 产品类型名称
     */
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    /**
     * 产品单位编码
     */
    @Column(name = "goods_unit_id")
    private String goodsUnitId;

    /**
     * 产品单位名称
     */
    @Column(name = "goods_unit_name")
    private String goodsUnitName;

    /**
     * 产品品牌编码
     */
    @Column(name = "goods_brand_id")
    private String goodsBrandId;

    /**
     * 产品品牌名称
     */
    @Column(name = "goods_brand_name")
    private String goodsBrandName;

    /**
     * 规格型号
     */
    @Column(name = "goods_model")
    private String goodsModel;

    /**
     * 供应商编码
     */
    @Column(name = "vendor_id")
    private String vendorId;

    /**
     * 供应商名称
     */
    @Column(name = "vendor_name")
    private String vendorName;

    /**
     * 供应价格
     */
    @Column(name = "normal_price")
    private BigDecimal normalPrice;

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

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
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
     * 获取产品类型编码
     *
     * @return goods_type_id - 产品类型编码
     */
    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    /**
     * 设置产品类型编码
     *
     * @param goodsTypeId 产品类型编码
     */
    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId == null ? null : goodsTypeId.trim();
    }

    /**
     * 获取产品类型名称
     *
     * @return goods_type_name - 产品类型名称
     */
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    /**
     * 设置产品类型名称
     *
     * @param goodsTypeName 产品类型名称
     */
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName == null ? null : goodsTypeName.trim();
    }

    /**
     * 获取产品单位编码
     *
     * @return goods_unit_id - 产品单位编码
     */
    public String getGoodsUnitId() {
        return goodsUnitId;
    }

    /**
     * 设置产品单位编码
     *
     * @param goodsUnitId 产品单位编码
     */
    public void setGoodsUnitId(String goodsUnitId) {
        this.goodsUnitId = goodsUnitId == null ? null : goodsUnitId.trim();
    }

    /**
     * 获取产品单位名称
     *
     * @return goods_unit_name - 产品单位名称
     */
    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    /**
     * 设置产品单位名称
     *
     * @param goodsUnitName 产品单位名称
     */
    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName == null ? null : goodsUnitName.trim();
    }

    /**
     * 获取产品品牌编码
     *
     * @return goods_brand_id - 产品品牌编码
     */
    public String getGoodsBrandId() {
        return goodsBrandId;
    }

    /**
     * 设置产品品牌编码
     *
     * @param goodsBrandId 产品品牌编码
     */
    public void setGoodsBrandId(String goodsBrandId) {
        this.goodsBrandId = goodsBrandId == null ? null : goodsBrandId.trim();
    }

    /**
     * 获取产品品牌名称
     *
     * @return goods_brand_name - 产品品牌名称
     */
    public String getGoodsBrandName() {
        return goodsBrandName;
    }

    /**
     * 设置产品品牌名称
     *
     * @param goodsBrandName 产品品牌名称
     */
    public void setGoodsBrandName(String goodsBrandName) {
        this.goodsBrandName = goodsBrandName == null ? null : goodsBrandName.trim();
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
     * 获取供应商编码
     *
     * @return vendor_id - 供应商编码
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * 设置供应商编码
     *
     * @param vendorId 供应商编码
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId == null ? null : vendorId.trim();
    }

    /**
     * 获取供应价格
     *
     * @return normal_price - 供应价格
     */
    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    /**
     * 设置供应价格
     *
     * @param normalPrice 供应价格
     */
    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
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