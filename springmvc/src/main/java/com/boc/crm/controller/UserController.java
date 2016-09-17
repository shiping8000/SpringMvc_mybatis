package com.boc.crm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;  

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  

import com.boc.crm.service.UserService;
  
@Controller  
public class UserController {  
    
	@Resource  
    private UserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        //int userId = Integer.parseInt(request.getParameter("id"));  
        
        System.out.println("----0");
        //User user = this.userService.getUserById(userId);  
        //model.addAttribute("user", user);  
        return "user/showUser";  
    } 
   
    
}  