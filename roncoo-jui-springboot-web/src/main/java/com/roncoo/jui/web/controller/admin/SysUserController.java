package com.roncoo.jui.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.jui.common.enums.UserSexEnum;
import com.roncoo.jui.common.enums.UserStatusEnum;
import com.roncoo.jui.common.util.base.BaseController;
import com.roncoo.jui.web.bean.qo.SysUserQO;
import com.roncoo.jui.web.service.SysUserService;

/**
 * 后台用户信息 
 *
 * @author wujing
 * @since 2017-10-25
 */
@Controller
@RequestMapping(value = "/admin/sysUser")
public class SysUserController extends BaseController {

	private final static String TARGETID = "admin-sysUser";

	@Autowired
	private SysUserService service;
	
	@RequestMapping(value = "/list")
	public void list(@RequestParam(value = "pageNum", defaultValue = "1") int pageCurrent, @RequestParam(value = "numPerPage", defaultValue = "20") int pageSize, @ModelAttribute SysUserQO qo, ModelMap modelMap){
		modelMap.put("page", service.listForPage(pageCurrent, pageSize, qo));
		modelMap.put("pageCurrent", pageCurrent);
		modelMap.put("pageSize", pageSize);
		modelMap.put("bean", qo);
		modelMap.put("userSexEnums", UserSexEnum.values());
		modelMap.put("userStatusEnums", UserStatusEnum.values());
	}
	
	@RequestMapping(value = "/add")
	public void add(ModelMap modelMap){
		modelMap.put("userSexEnums", UserSexEnum.values());
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute SysUserQO qo){
		if (service.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id){
		if (service.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}
	
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", service.getById(id));
		modelMap.put("userSexEnums", UserSexEnum.values());
		modelMap.put("userStatusEnums", UserStatusEnum.values());
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute SysUserQO qo){
		if (service.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}
	
	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", service.getById(id));
		modelMap.put("userSexEnums", UserSexEnum.values());
		modelMap.put("userStatusEnums", UserStatusEnum.values());
	}
	
}
