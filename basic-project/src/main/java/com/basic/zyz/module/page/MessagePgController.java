package com.basic.zyz.module.page;

import com.basic.zyz.module.pojo.Message;
import com.basic.zyz.module.service.MessageService;
import com.basic.zyz.common.basic.BaseController;
import com.basic.zyz.module.pojo.Message;
import com.basic.zyz.module.service.MessageService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 反馈管理Controller
 * @author zyz
 * @version 2018-12-22
 */
@Controller
@RequestMapping("/page/message")
public class MessagePgController  extends BaseController {

	@Autowired
	private MessageService messageService;

	 @RequestMapping(value = {"", "list"})
    public String userList(Message message, Model model) {
        PageInfo<Message> page = messageService.findPage(message);
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        return "home/message/messageList";
    }

	@RequestMapping("form")
	public String form(Message message, Model model) {
		if (StringUtils.isNotBlank(message.getId())){
			message = messageService.get(message.getId());
		}
		model.addAttribute("message", message);
		return "home/message/messageForm";
	}

	@RequestMapping("save")
	public String save(Message message, Model model, RedirectAttributes redirectAttributes) {
		message.setCreateBy(getUserInfo().getId());
		message.setUpdateBy(getUserInfo().getId());
		messageService.save(message);
		redirectAttributes.addFlashAttribute("msg", "保存反馈管理成功！");
		return "redirect:/page/message/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Message message, RedirectAttributes redirectAttributes) {
		messageService.delete(message);
		redirectAttributes.addFlashAttribute("msg", "删除反馈管理成功！");
		return "redirect:/page/message/?repage";
	}
	
}