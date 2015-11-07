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

	String generateCountByQuery(String... byParam);

	String generateUpdateByQuery(String... byParam);

	String generateDeleteByQuery(String... byParams);

	String generateSelectByQuery(String... byParams);

	String generateSelectFieldByQuery(String... fields);

	String generateSelectFieldByQuery(List<String> fields, List<String> byParams);

}
