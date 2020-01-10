package com.tudou.base.emp.service.impl;

import com.tudou.base.emp.entity.EidpEmp;
import com.tudou.base.emp.mapper.EidpEmpMapper;
import com.tudou.base.emp.service.IEidpEmpService;
import com.tudou.extra.system.AuthInfo;
import com.tudou.extra.system.UserInfoUtil;
import com.tudou.system.platform.base.annotation.BaseResource;
import com.tudou.system.platform.base.service.impl.BaseServiceImpl;
import com.tudou.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("eidpEmpService")
public class EidpEmpServiceImpl extends BaseServiceImpl<EidpEmp, String> implements IEidpEmpService {

    @Resource
    @BaseResource
    private EidpEmpMapper eidpEmpMapper;

    @Override
    public void valid(String userName, String password) throws Exception {
//        SecurityUtils.setSecurityManager(securityManager);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//        try {
//            subject.login(token);
//        } catch (UnknownAccountException e) {
//            throw new Exception("该账号不存在!");
//        } catch (IncorrectCredentialsException e) {
//            throw new Exception("密码错误!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e.getMessage());
//        }
    }

    @Override
    public String getCreateEidpEmpId() {
        Integer count = eidpEmpMapper.getEidpMaxCount();
        if (null == count) {
            count = 0;
        }
        return CommonUtil.transIdByCount(count);
    }

    @Override
    public EidpEmp save(EidpEmp eidpEmp) throws Exception {
        valide(eidpEmp);
        List<EidpEmp> eidpEmps = eidpEmpMapper.findEidpEmpByName(eidpEmp.getUsername());
        if (CollectionUtils.isNotEmpty(eidpEmps)) {
            throw new Exception("存在相同的姓名!【" + eidpEmp.getUsername() + "】");
        }
        // 需要修改
        //eidpEmp.setPassword(Base64.encodeToString("111111".getBytes()));
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        eidpEmp.setCreatetime(new Date());
        eidpEmp.setCreateEmpId(authInfo.getEmpId());
        eidpEmp.setCreateEmp(authInfo.getEmpName());
        super.save(eidpEmp);
        return eidpEmp;
    }

    @Override
    public void update(EidpEmp eidpEmp) throws Exception {
        valide(eidpEmp);
        List<EidpEmp> eidpEmps = eidpEmpMapper.findEidpEmpByNameAndNotId(eidpEmp.getUsername(), eidpEmp.getId());
        if (CollectionUtils.isNotEmpty(eidpEmps)) {
            throw new Exception("存在相同的姓名!【" + eidpEmp.getUsername() + "】");
        }
        AuthInfo authInfo = UserInfoUtil.getCurrentAuthInfo();
        eidpEmp.setModifytime(new Date());
        eidpEmp.setModifyEmpId(authInfo.getEmpId());
        eidpEmp.setModifyEmp(authInfo.getEmpName());
        eidpEmpMapper.updateByPrimaryKey(eidpEmp);
    }

    /**
     * 验证
     *
     * @param entity
     * @throws Exception
     */
    private void valide(EidpEmp entity) throws Exception {
        if (null == entity) {
            throw new Exception("需要保存的用户信息为空！");
        }
        if (StringUtils.isEmpty(entity.getId())) {
            throw new Exception("用户编号不能为空！");
        }
        if (StringUtils.isEmpty(entity.getUsername())) {
            throw new Exception("用户姓名不能为空！");
        }
        if (StringUtils.isEmpty(entity.getPassword())) {
            throw new Exception("用户密码不能为空！");
        }
    }


}