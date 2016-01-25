package zx.soft.cases.management.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import zx.soft.cases.management.domain.Cases;
import zx.soft.cases.management.domain.CasesData;
import zx.soft.cases.management.domain.CasesType;
import zx.soft.cases.management.domain.Params;
import zx.soft.cases.management.domain.QueryParameters;
import zx.soft.cases.management.domain.QueryResult;
import zx.soft.cases.management.domain.Response;
import zx.soft.cases.management.service.CasesService;
import zx.soft.cases.management.service.ImpalaService;
import zx.soft.cases.management.utils.JsonUtils;

@Controller
@RequestMapping("/apt")
public class CasesController {

	private static Logger logger = LoggerFactory.getLogger(CasesController.class);
	@Inject
	private CasesService casesService;
	@Inject
	private ImpalaService impalaService;

	private static Map<String, String> type = new HashMap<>();
	static {
		type = ImpalaService.getAllNameTypeId();
	}

	//新增案件
	@RequestMapping(value = "/cases/insert", method = RequestMethod.POST)
	@ResponseBody
	public Response insertCases(@RequestBody Cases cases) {
		if (cases.getTypeId() != null) {
			cases.setCasesId(cases.getTypeId() + String.valueOf(System.currentTimeMillis()));
			casesService.insertCases(cases);
		}
		return new Response(0, "插入案件成功");
	}

	//更新案件
	@RequestMapping(value = "/cases/update", method = RequestMethod.POST)
	@ResponseBody
	public Response updataCases(@RequestBody Cases cases) {
		System.out.println(JsonUtils.toJsonWithoutPretty(cases));
		if (cases.getCasesId() == null) {
			return new Response(2, "请指定更新案件的ID");
		}
		Cases temp = impalaService.getSpecCases(cases.getCasesId());
		if (temp == null) {
			return new Response(1, "不存在该案件ID");
		}
		if (cases.getCasesName() != null) {
			temp.setCasesName(cases.getCasesName());
		}
		if (cases.getCasesDetail() != null) {
			temp.setCasesDetail(cases.getCasesDetail());
		}
		casesService.insertCases(temp);
		return new Response(0, "更新案件成功");
	}

	//删除某个案件所有数据
	@RequestMapping(value = "/cases/delete/{casesId}", method = RequestMethod.GET)
	@ResponseBody
	public Response deleteAllCasesData(@PathVariable("casesId") String casesId) {
		try {
			List<String> rowkeys = impalaService.getRowkeysByCasesId(casesId);
			logger.info("rowkey size=" + rowkeys.size());
			casesService.deleteData("casesData", rowkeys);
			casesService.deleteSingleData("cases", casesId);
		} catch (Exception e) {
			return new Response(1, "删除失败");
		}
		return new Response(0, "删除案件成功");
	}

	//查询单个案件信息
	@RequestMapping(value = "/cases/select", method = RequestMethod.GET)
	public @ResponseBody Cases getSpecCases(@RequestParam("casesId") String casesId) {
		Cases temp = impalaService.getSpecCases(casesId);
		if (temp.getTypeId() != null) {
			if (type.get(temp.getTypeId()) != null) {
				temp.setTypeId(type.get(temp.getTypeId()));
			} else {
				temp.setTypeId("默认分类");
			}
		}
		return temp;
	}

	//多条件查询案件信息
	@RequestMapping(value = "/cases/select", method = RequestMethod.POST)
	public @ResponseBody QueryResult getCasesList(@RequestBody Params p) throws SQLException {
		if (p.getOrder() == "") {
			p.setOrder("DESC");
		}
		if (p.getOrder_by() == "") {
			p.setOrder_by("createTime");
		}
		if (p.getPage_size() == 0) {
			p.setPage_size(20);
		}
		if (p.getQueryParameters().size() == 0) {
			p.getQueryParameters().add(new QueryParameters(1, "createTime", "0"));
		}
		List<Cases> lists = impalaService.getCasesList("apt.cases", p.getQueryParameters(), p.getOrder_by(),
				p.getOrder(), p.getPage_size(), p.getPage());
		int number = impalaService.getSum("apt.cases", p.getQueryParameters());
		for (Cases cases : lists) {
			if (type.get(cases.getTypeId()) != null) {
				cases.setTypeId(type.get(cases.getTypeId()));
			} else {
				cases.setTypeId("默认分类");
			}
		}
		return new QueryResult(number, lists);
	}

	//插入案件数据
	@RequestMapping(value = "/cases_data/insert", method = RequestMethod.POST)
	@ResponseBody
	public Response insertCasesData(@RequestBody CasesData casesData) {
		try {
			if (casesData.getCasesId() != null) {
				casesData.setRowkey(casesData.getCasesId() + String.valueOf(System.currentTimeMillis()));
				if (casesData.getCreateTime() == 0) {
					casesData.setCreateTime(System.currentTimeMillis());
				}
				casesService.insertCasesData(casesData);
			}
		} catch (Exception e) {
			return new Response(1, "插入案件数据失败");
		}
		return new Response(0, "插入案件数据成功");
	}

	//删除数据
	@RequestMapping(value = "/cases_data/delete", method = RequestMethod.POST)
	@ResponseBody
	public Response deleteCases(@RequestBody List<String> rowkeys) {
		try {
			casesService.deleteData("casesData", rowkeys);
		} catch (Exception e) {
			return new Response(1, "删除失败");
		}
		return new Response(0, "删除案件数据成功");
	}

	//查询单个案件数据信息
	@RequestMapping(value = "/cases_data/select", method = RequestMethod.GET)
	public @ResponseBody CasesData getSpecCasesData(@RequestParam("rowkey") String rowkey) {
		return impalaService.getSpecCasesData(rowkey);
	}

	//多条件查询案件数据信息
	@RequestMapping(value = "/cases_data/select", method = RequestMethod.POST)
	public @ResponseBody QueryResult getCasesDataList(@RequestBody Params p) throws SQLException {
		if (p.getOrder() == "") {
			p.setOrder("DESC");
		}
		if (p.getOrder_by() == "") {
			p.setOrder_by("createTime");
		}
		if (p.getPage_size() == 0) {
			p.setPage_size(20);
		}
		if (p.getQueryParameters().size() == 0) {
			p.getQueryParameters().add(new QueryParameters(1, "createTime", "0"));
		}
		List<CasesData> lists = impalaService.getCasesDataList("apt.casesData", p.getQueryParameters(),
				p.getOrder_by(), p.getOrder(), p.getPage_size(), p.getPage());
		int number = impalaService.getSum("apt.casesData", p.getQueryParameters());
		return new QueryResult(number, lists);
	}

	@RequestMapping(value = "/cases_type/insert", method = RequestMethod.POST)
	@ResponseBody
	public Response insert(@RequestBody CasesType casesType) {
		//提示新建的案件类型已存在
		if (impalaService.getTypeIdByName(casesType.getName()) != null) {
			return new Response(2, "案件类型名称已存在.");
		}
		String typeId = String.valueOf(System.currentTimeMillis());
		casesType.setTypeId(typeId);
		try {
			casesService.insertCasesType(casesType);
			type.put(casesType.getTypeId(), casesType.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(1, "插入失败.");

		}
		return new Response(0, "插入成功.");
	}

	@RequestMapping(value = "/cases_type/update", method = RequestMethod.POST)
	@ResponseBody
	public Response update(@RequestBody CasesType casesType) {
		//提示更新的的案件类型名称已存在
		if (casesType.getName() != null) {
			if (impalaService.getTypeIdByName(casesType.getName()) != null) {
				return new Response(2, "案件类型名称已存在.");
			} else {
				try {
					casesService.updateCasesType(casesType);
					type.put(casesType.getTypeId(), casesType.getName());
				} catch (Exception e) {
					e.printStackTrace();
					return new Response(1, "更新失败.");
				}
			}
		}
		return new Response(0, "更新成功.");
	}

	@RequestMapping(value = "/cases_type/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Response delete(@PathVariable String id) {
		try {
			casesService.deleteCasesType(id);
			type.remove(id);
		} catch (Exception e) {
			return new Response(1, "删除出错");
		}
		return new Response(0, "成功删除");
	}

	@RequestMapping(value = "/cases_type/select", method = RequestMethod.GET)
	@ResponseBody
	public CasesType selectCasesType(@RequestParam("name") String name) throws UnsupportedEncodingException {
		return impalaService.getSpecCasesType(name);
	}

	@RequestMapping(value = "/cases_type/select", method = RequestMethod.POST)
	public @ResponseBody QueryResult getCasesTypeList(@RequestBody Params p) throws SQLException {
		if (p.getOrder() == "") {
			p.setOrder("DESC");
		}
		if (p.getOrder_by() == "") {
			p.setOrder_by("createTime");
		}
		if (p.getPage_size() == 0) {
			p.setPage_size(20);
		}
		if (p.getQueryParameters().size() == 0) {
			p.getQueryParameters().add(new QueryParameters(1, "createTime", "0"));
		}
		List<CasesType> lists = impalaService.getCasesTypeList("apt.casesType", p.getQueryParameters(),
				p.getOrder_by(), p.getOrder(), p.getPage_size(), p.getPage());
		int number = impalaService.getSum("apt.casesType", p.getQueryParameters());
		return new QueryResult(number, lists);
	}

}
