package zx.soft.cases.management.createdata;

import zx.soft.cases.management.domain.CasesData.CasesDataBuilder;
import zx.soft.cases.management.service.CasesManager;

public class CreateCasesData {

	public static void main(String[] args) {
		CasesManager manager = new CasesManager();
		String casesId = "14535296679251453530233133";
		CasesDataBuilder builder = new CasesDataBuilder(casesId + String.valueOf(System.currentTimeMillis()), casesId);
		builder.addCreateTime(System.currentTimeMillis());
		builder.addFileId(String.valueOf(System.currentTimeMillis()));
		manager.insertCasesData(builder.build());
	}
}
