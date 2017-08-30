package com.qingshixun.project.paging.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qingshixun.project.paging.model.Page;
import com.qingshixun.project.paging.model.TransactionModel;
import com.qingshixun.project.paging.service.TransactionJdbcService;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController {

	@Autowired
	private TransactionJdbcService transactionJdbcService;

	// 指定默认每页的显示记录条数
	private static final int DEFAULT_PAGE_SIZE = 5;

	// 分页显示的列表数据
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		int pageNo = 1;
		
		// 指定页数（第几页）
		String inputPageNo = request.getParameter("pageNo");
		if(!StringUtils.isEmpty(inputPageNo)){
			pageNo = Integer.parseInt(inputPageNo);
		}
		
		// 查询并返回分页数据
		Page<TransactionModel> transaction = transactionJdbcService.findTransactionList(pageNo, DEFAULT_PAGE_SIZE);
		
		model.addAttribute("transaction", transaction);
		return "index";
	}
}
