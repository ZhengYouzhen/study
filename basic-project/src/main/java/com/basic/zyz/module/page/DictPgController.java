package com.basic.zyz.module.page;

import com.basic.zyz.module.pojo.Dict;
import com.basic.zyz.module.service.DictService;
import com.basic.zyz.common.basic.BaseController;
import com.basic.zyz.common.constant.RedisEnum;
import com.basic.zyz.common.utils.RedisUtil;
import com.basic.zyz.module.pojo.Dict;
import com.basic.zyz.module.service.DictService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 字典管理Controller
 * @author zyz
 * @version 2019-01-07
 */
@Controller
@RequestMapping("/page/dict")
public class DictPgController  extends BaseController {

	@Autowired
	private DictService dictService;

	@Autowired
	private RedisUtil redisUtil;

	 @RequestMapping(value = {"", "list"})
    public String userList(Dict dict, Model model) {
        PageInfo<Dict> page = dictService.findPage(dict);
        model.addAttribute("page", page);
        model.addAttribute("dict", dict);
        return "home/dict/dictList";
    }

	@RequestMapping("form")
	public String form(Dict dict, Model model) {
		if (StringUtils.isNotBlank(dict.getId())){
			dict = dictService.get(dict.getId());
		}
		model.addAttribute("dict", dict);
		return "home/dict/dictForm";
	}

	@RequestMapping("save")
	public String save(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		//      更改菜单，把redis菜单结构清空清空
		redisUtil.del(RedisEnum.REDIS_DICT.getKey() + dict.getType());
		dict.setCreateBy(getUserInfo().getId());
		dict.setUpdateBy(getUserInfo().getId());
		dictService.save(dict);
		redirectAttributes.addFlashAttribute("msg", "保存字典管理成功！");
		return "redirect:/page/dict/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		dictService.delete(dict);
		redirectAttributes.addFlashAttribute("msg", "删除字典管理成功！");
		return "redirect:/page/dict/?repage";
	}
	
}