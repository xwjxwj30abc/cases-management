package zx.soft.cases.management.createdata;

import zx.soft.cases.management.domain.CasesType;
import zx.soft.cases.management.domain.CasesType.CasesTypeBuilder;
import zx.soft.cases.management.service.CasesManager;

public class CreateCasesType {

	public static void main(String[] args) {
		CasesManager manager = new CasesManager();
		CasesTypeBuilder builder = new CasesTypeBuilder(String.valueOf(System.currentTimeMillis()), "非紧急类型");
		builder.addCreateTime(System.currentTimeMillis()).addCreateUser("员工3");
		CasesType casesType = builder.build();
		manager.insertCasesType(casesType);
	}
}
