package com.qingshixun.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qingshixun.project.checkin.web.ResponseData;
import com.qingshixun.project.model.Hobby;
import com.qingshixun.project.model.PageBean;
import com.qingshixun.project.model.Profession;
import com.qingshixun.project.model.User;
import com.qingshixun.project.model.UserBean;
import com.qingshixun.project.service.IHobbyService;
import com.qingshixun.project.service.IProfessionService;
import com.qingshixun.project.service.IUserService;

@Scope("prototype")
@RequestMapping("/user")
@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserService userService;

	@Autowired
	private IProfessionService professionService;
	
	@Autowired
	private IHobbyService hobbyService;
	/**
	 * 跳转到登录界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginUser", method = RequestMethod.GET)
	public String loginUser() {
		return "user/login";
	}

	/**
	 * 验证登录时是否满足，ajax异步刷新
	 * 
	 * @return
	 */
	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData validateLogin(UserBean user,HttpSession session) {

		ResponseData responseData = new ResponseData();
		UserBean user1 = userService.loginUser(user.getName(), user.getPassword());
		if (user1 != null) {
			responseData.setData(user1);
			
			session.setAttribute("userInfo", userService.get(user1.getId()));
		} else {
			responseData.setError(user1);
		}

		return responseData;
	}

	/**
	 * 登录成功后要跳转的页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loginResultUser")
	public String loginResultUser(User user) {
		return "main";
	}
	
	
	/**
	 * 根据分页来查找用户
	 * @return
	 */
	@RequestMapping("/list")
	public String findUser(Model model,@RequestParam(value="page",defaultValue="0") Integer page){
		PageBean<User> pageBean = userService.getPageBean(5, page);
		model.addAttribute("pageBean", pageBean);
		
		return "user/list";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping(value={"add","edit"},method=RequestMethod.GET)
	public String toAdd(Model model,Integer id){
		if(id != null){
			User user = userService.get(id);
			model.addAttribute("user", user);
		}
		List<Profession> professions = professionService.findAll();
		List<Hobby> hobbies = hobbyService.findAll();
		model.addAttribute("professions", professions);
		model.addAttribute("hobbies", hobbies);
		return "user/form";
	}
	
	/**
	 * 保存职业信息
	 * @param department
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request,@ModelAttribute User user,MultipartFile imageFile) throws IllegalStateException, IOException {	
		if(imageFile != null){
			String filePath = request.getServletContext().getRealPath("/") + "file/";
		    // 获取文件原始名称
		    String fileName = imageFile.getOriginalFilename();
		    // 创建文件目录
		    File file = new File(filePath + fileName);
		    file.mkdirs();
		    // 转存文件
		    imageFile.transferTo(file);
		    user.setAvatar(fileName);
		}
		for (Hobby hobby : user.getHobbies()) {
			System.out.println(hobby.getName());
		}
		userService.saveOrUpdate(user);
		return "success";
	}
	
	/**
	 * 上传头像
	 * @param request
	 * @param user
	 * @param imageFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, User user, MultipartFile imageFile) throws IllegalStateException, IOException {
	    String filePath = request.getServletContext().getRealPath("/") + "file/";
	    // 获取文件原始名称
	    String fileName = imageFile.getOriginalFilename();
	    // 创建文件目录
	    File file = new File(filePath + fileName);
	    file.mkdirs();
	    // 转存文件
	    imageFile.transferTo(file);
	    // 保存文件名
	   /* product.setImage(fileName);
	    productService.add(product);*/
	    // 重定向到 /product/list
	    System.out.println("文件名称："+fileName);
	    return "redirect:/user/list";
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData delete(Integer id) {
		ResponseData responseData = new ResponseData();
		try {
			userService.delete(id);
		} catch (Exception e) {
			responseData.setError(e.getMessage());
		}
		
		return responseData;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	@ResponseBody
	public ResponseData deletes(@RequestParam("userIds") Integer[] userIds) {
		ResponseData responseData = new ResponseData();
		System.out.println("---ids:"+userIds);
		try {
			userService.delete(userIds);
			
		} catch (Exception e) {
			responseData.setError(e.getMessage());
		}
		
		return responseData;
	}
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logoutUser",method=RequestMethod.GET)
	public String logoutUser(HttpSession session){
		Object user = session.getAttribute("userInfo");
		if (user != null) {
			session.removeAttribute("userInfo");
			/* session.invalidate(); */
		}
		return "user/login";
	}
	
}
