package com.polsri.union.app.util.core;

import java.util.List;

/**
 * Created by yakub andi panggabean on 11/05/2015 1:25 AM.
 */

public interface QueryDomainGenerator {

	String generateInsertQuery();

	String generateUpdateQuery();

	String generateDeleteQuery();

	String generateSelectAllQuery(boolean isPagination);

	String generateCountQuery();

	String generateCountByQuery(List<String> byParams);

	String generateUpdateByQuery(List<String> byParams);

	String generateDeleteByQuery(List<String> byParams);

	String generateSelectByQuery(List<String> byParams);

	String generateSelectFieldByQuery(List<String> fields);

	String generateSelectFieldByQuery(List<String> fields, List<String> byParams);

}
