package zx.soft.cases.management.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zx.soft.cases.management.domain.Cases;
import zx.soft.cases.management.domain.CasesData;
import zx.soft.cases.management.domain.CasesType;

@Service
public class CasesService {
	@Inject
	private CasesManager casesManager;

	public void insertCases(Cases cases) {
		casesManager.insertCases(cases);
	}

	public void insertCasesData(CasesData casesData) {
		casesManager.insertCasesData(casesData);
	}

	public void deleteData(String tableName, List<String> ids) {
		casesManager.deleteData(tableName, ids);
	}

	public void deleteSingleData(String tableName, String id) {
		casesManager.deleteSingleData(tableName, id);
	}

	public void insertCasesType(CasesType casesType) {
		casesManager.insertCasesType(casesType);
	}

	public void updateCasesType(CasesType casesType) {
		casesManager.updateCasesType(casesType);
	}

	public void deleteCasesType(String id) {
		casesManager.deleteCasesType(id);
	}
}
