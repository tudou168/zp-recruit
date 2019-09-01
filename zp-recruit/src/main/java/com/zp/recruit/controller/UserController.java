package com.zp.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zp.recruit.entity.Tb_user;
import com.zp.recruit.service.ITb_userService;

/***
 * 用户信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user/")
@CrossOrigin
public class UserController extends BaseController{
	
	@Autowired
	private ITb_userService iTb_userService;
	
	/***
	 * 添加用户信息
	 * 微信获取的
	 * @param tb_user
	 * @return
	 */
	@RequestMapping(value = "addUserObject" , method = RequestMethod.POST)
	@ResponseBody
	public Object addUserObject(Tb_user tb_user) {
		if(null != tb_user.getU_open_id() || "" != tb_user.getU_open_id()) {
			return renderError("该用户已存在");
		}else {
			boolean bool = iTb_userService.insert(tb_user);
			return false != bool ? 
					renderSuccess("添加成功") : renderError("添加失败");
		}
	}
	
	/***
	 * 修改用户状态
	 * @param tb_user
	 * @return
	 */
	@RequestMapping(value = "modifyUserById" , method = RequestMethod.POST)
	@ResponseBody
	public Object modifyUserById(Tb_user tb_user) {
		boolean bool = iTb_userService.updateById(tb_user);
		return false != bool ?
				renderSuccess("修改成功") : renderError("修改失败");
	}

}