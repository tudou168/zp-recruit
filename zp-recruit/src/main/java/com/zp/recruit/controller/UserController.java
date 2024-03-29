package com.zp.recruit.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
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
			Wrapper<Tb_user> wrapper = new EntityWrapper<Tb_user>();
			wrapper.where("u_open_id='"+ tb_user.getU_open_id()+"'");
			Tb_user tb_userObj = iTb_userService.selectOne(wrapper);
			if(tb_userObj != null) {
				return renderSuccess(tb_userObj.getU_id());
			}else {
				tb_user.setU_login_time(new Date());
				boolean bool = iTb_userService.insert(tb_user);
				return false != bool ? 
						renderSuccess(tb_user.getU_id()) : renderError("添加失败");
			}
		}else {
			return  renderError("openid不能为空");
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
	
	/***
	 * 
	 * @param size
	 * @param current
	 * @return
	 */
	@RequestMapping(value = "getUserList" , method = RequestMethod.POST)
	@ResponseBody
	public Object getUserList(Integer size, Integer current) {
		if (size == null) {
			size = 7;
		}
		if (current == null) {
			current = 1;
		}
		Page<Tb_user> pageo = new Page<Tb_user>(current, size);
		Page<Tb_user> page = iTb_userService.selectPage(pageo);
		return page.getSize() > 0 ? 
				renderSuccess(page) : renderError("暂无数据");
	}

}
