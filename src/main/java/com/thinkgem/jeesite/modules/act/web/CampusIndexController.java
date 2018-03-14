package com.thinkgem.jeesite.modules.act.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.dao.Msg;
import com.thinkgem.jeesite.modules.act.entity.LsAddress;
import com.thinkgem.jeesite.modules.act.entity.LsIp;
import com.thinkgem.jeesite.modules.act.entity.LsOffice;
import com.thinkgem.jeesite.modules.act.service.IpService;
import com.thinkgem.jeesite.modules.act.service.NetworkService;
import com.thinkgem.jeesite.modules.act.service.OfficeSchoolService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 校区索引
 *
 * @author lfy
 */
@Controller
@RequestMapping(value = "/a/")
public class CampusIndexController {

    @Autowired
    private OfficeSchoolService officeSchoolService;




    @RequestMapping("/campusIndex")
//	@ResponseBody
    public String getOffice(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        return "modules/act/actCampusIndex";
    }

    /**
     * 返回所有的校区信息，给下拉使用
     */
    @RequestMapping("/getAllCampus")
    @ResponseBody
    public Msg getDepts(@RequestParam(value = "campus_search_input", defaultValue = "") String campus_search_input) {
        //查出的所有校区信息
        List<LsOffice> list =new ArrayList<LsOffice>();
        if(campus_search_input==null||"".equals(campus_search_input)){
            list =  officeSchoolService.getAll();
        }
        else {
            list = officeSchoolService.getAllWithCondition(campus_search_input);
        }
        return Msg.success().add("offices", list);
    }
    public static void main(String[] args) throws Exception {
        String str = "你好世界";
        PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK); // nǐ,hǎo,shì,jiè
        PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER); // ni3,hao3,shi4,jie4
        PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE); // ni,hao,shi,jie
        PinyinHelper.getShortPinyin(str); // nhsj
//        PinyinHelper.addPinyinDict("user.dict");  // 添加用户自定义字典
        System.out.println(PinyinHelper.getShortPinyin("崇文门65464"));
    }

}
