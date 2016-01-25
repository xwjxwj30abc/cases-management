package zx.soft.cases.management.createdata;

import zx.soft.cases.management.domain.Cases;
import zx.soft.cases.management.domain.Cases.CasesBuilder;
import zx.soft.cases.management.service.CasesManager;

public class CreateCases {

	public static void main(String[] args) {
		CasesManager manager = new CasesManager();
		String typeId = "1453529696877";
		CasesBuilder builder = new CasesBuilder(typeId + String.valueOf(System.currentTimeMillis()), typeId, "漏洞相关事件");
		builder.addCasesDetail("漏洞非紧急事件").addCreateTime(System.currentTimeMillis()).addCreateUser("员工3");
		Cases cases = builder.build();
		manager.insertCases(cases);
	}
}
