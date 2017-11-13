package com.qingshixun.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshixun.project.checkin.web.ResponseData;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.Profession;
import com.qingshixun.project.service.IProfessionService;

@Scope("prototype")
@RequestMapping("/profession")
@Controller
public class ProfessionController {
	
	@Autowired
	private IProfessionService professionService;
	
	/**
	 * 显示职业列表
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/list")
	public String findUser(Model model,@RequestParam(value="page",defaultValue="0") Integer page){
		PageBean<Profession> pageBean = professionService.getPageBean(5, page);
		model.addAttribute("pageBean", pageBean);
		return "profession/list";
	}
	
	/**
	 * 跳转到添加界面
	 * @return
	 */
	@RequestMapping(value={"add","edit"},method=RequestMethod.GET)
	public String add(Model model,Integer id){
		if(id != null){
			Profession profession = professionService.get(id);
			model.addAttribute("profession", profession);
		}
		return "profession/form";
	}
	
	/**
	 * 保存职业信息
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute Profession profession) {
		professionService.saveOrUpdate(profession);
		return "success";
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	@ResponseBody
	public ResponseData delete(Integer id) {
		ResponseData responseData = new ResponseData();
		try {
			professionService.delete(id);
		} catch (Exception e) {
			responseData.setError(e.getMessage());
		}
		
		return responseData;
	}
	
}
