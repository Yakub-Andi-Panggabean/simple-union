package com.polsri.union.app.util.core;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yakub andi panggabean on 11/05/2015 1:25 AM.
 */
public abstract class QueryDomainGeneratorBean implements QueryDomainGenerator {

	private static final String TABLE_NAME = " {table_name}";
	private static final String FIELDS_NAMES = " {field_name}";
	private static final String FIELDS_VALUES = " {field_value}";
	private static final String QUESTION_MARK = "?,";
	private static final String QUESTION_MARK_EQUAL = " = ?";
	private static final String PRIMARY_KEY = "{primary_key}";
	private static final String WHERE_CLAUSE = " WHERE ";
	private static final String AND_CLAUSE = " AND ";
	private static final String PRIMARY_KEY_WHERE = " WHERE " + PRIMARY_KEY + QUESTION_MARK_EQUAL;
	// query statement
	private static final String DELETE_STATEMENT_BLUEPRINT = "DELETE FROM " + TABLE_NAME + PRIMARY_KEY_WHERE;
	private static final String INSERT_STATEMENT_BLUEPRINT = "INSERT INTO " + TABLE_NAME + " (" + FIELDS_NAMES
			+ ") VALUES (" + FIELDS_VALUES + ")";
	private static final String UPDATE_STATEMENT_BLUEPRINT = "UPDATE " + TABLE_NAME + " SET " + FIELDS_NAMES
			+ PRIMARY_KEY_WHERE;
	private static final String SELECT_ALL_STATEMENT_BLUEPRINT = "SELECT * FROM " + TABLE_NAME;
	private static final String SELECT_FIELDS_STATEMENT_BLUEPRINT = "SELECT " + FIELDS_NAMES + " FROM " + TABLE_NAME;
	private static final String SELECT_BY_STATEMENT_BLUEPRINT = SELECT_ALL_STATEMENT_BLUEPRINT + WHERE_CLAUSE
			+ FIELDS_NAMES;
	private static final String SELECT_FIELDS_BY_STATEMENT_BLUEPRINT = SELECT_FIELDS_STATEMENT_BLUEPRINT + WHERE_CLAUSE
			+ FIELDS_VALUES;
	private static final String DELETE_BY_STATEMENT_BLUEPRINT = "DELETE FROM " + TABLE_NAME + WHERE_CLAUSE
			+ FIELDS_NAMES;
	private static final String UPDATE_BY_STATEMENT_BLUEPRINT = "UPDATE " + TABLE_NAME + " SET " + FIELDS_NAMES
			+ WHERE_CLAUSE + FIELDS_VALUES;
	private static final String COUNT_STATEMENT_BLUEPRINT = "SELECT COUNT(*) FROM " + TABLE_NAME;
	private static final String COUNT_BY_STATEMENT_BLUEPRINT = "SELECT COUNT(*) FROM " + TABLE_NAME + WHERE_CLAUSE
			+ FIELDS_NAMES;

	private static final Logger LOG = LoggerFactory.getLogger(QueryDomainGeneratorBean.class);

	public String generateInsertQuery() {
		// TODO Auto-generated method stub
		Field fields[] = this.getClass().getDeclaredFields();
		StringBuilder fieldName = new StringBuilder();
		StringBuilder fieldValue = new StringBuilder();
		for (Field field : fields) {
			String fieldNames = mapTableField().get(field.getName());
			if (fieldNames != null) {
				fieldName.append(fieldNames);
				fieldName.append(",");
				fieldValue.append(QUESTION_MARK);
			}

		}
		String generatedFieldNames = fieldName.substring(0, fieldName.length() - 1);
		String generateFieldValue = fieldValue.substring(0, fieldValue.length() - 1);
		String generatedQuery = INSERT_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName())
				.replace(FIELDS_NAMES, generatedFieldNames).replace(FIELDS_VALUES, generateFieldValue);
		LOG.info("generateInsertQuery() :" + generatedQuery);
		return generatedQuery;
	}

	public String generateUpdateQuery() {
		// TODO Auto-generated method stub
		Field fields[] = this.getClass().getDeclaredFields();
		StringBuilder fieldName = new StringBuilder();
		for (Field field : fields) {
			String fieldNames = mapTableField().get(field.getName());
			if (fieldNames != null) {
				fieldName.append(mapTableField().get(field.getName()));
				fieldName.append(QUESTION_MARK_EQUAL);
				fieldName.append(",");
			}
		}
		String generatedFieldNames = fieldName.substring(0, fieldName.length() - 1);
		String generatedQuery = UPDATE_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName())
				.replace(FIELDS_NAMES, generatedFieldNames).replace(PRIMARY_KEY, findPrimaryKey());
		LOG.info("generateUpdateQuery() :" + generatedQuery);
		return generatedQuery;
	}

	public String generateDeleteQuery() {
		// TODO Auto-generated method stub
		String generatedQuery = DELETE_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName()).replace(PRIMARY_KEY,
				findPrimaryKey());
		try {
			LOG.info(" generateDeleteQuery() :" + generatedQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return generatedQuery;
	}

	public String generateSelectAllQuery() {
		// TODO Auto-generated method stub
		String generatedQuery = SELECT_ALL_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName());
		LOG.info("generateSelectAllQuery() :" + generatedQuery);
		return generatedQuery;
	}

	public String generateCountQuery() {
		String generatedQuery = COUNT_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName());
		LOG.info("generateCountQuery() :" + generatedQuery);
		return generatedQuery;
	}

	public String generateCountByQuery(List<String> byParams) {
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (String byParam : byParams) {
			params.append(byParam);
			params.append(QUESTION_MARK_EQUAL);
			if (byParams.size() > 1) {
				if (i < byParams.size() - 1) {
					params.append(AND_CLAUSE);
					i++;
				}
			}
		}
		String generatedQuery = COUNT_BY_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName()).replace(FIELDS_NAMES,
				params);
		LOG.info("generateCountByQuery(List<String> byParams) :" + generatedQuery);
		return generatedQuery;
	}

	public String generateUpdateByQuery(List<String> byParams) {
		// TODO Auto-generated method stub
		Field fields[] = this.getClass().getDeclaredFields();
		StringBuilder fieldName = new StringBuilder();
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (Field field : fields) {
			String fieldNames = mapTableField().get(field.getName());
			if (fieldNames != null) {
				fieldName.append(fieldNames);
				fieldName.append(QUESTION_MARK_EQUAL);
				fieldName.append(",");
			}
		}
		for (String byParam : byParams) {
			params.append(byParam);
			params.append(QUESTION_MARK_EQUAL);
			if (byParams.size() > 1) {
				if (i < byParams.size() - 1) {
					params.append(AND_CLAUSE);
					i++;
				}
			}
		}
		String generatedFieldNames = fieldName.substring(0, fieldName.length() - 1);
		String generatedQuery = UPDATE_BY_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName())
				.replace(FIELDS_NAMES, generatedFieldNames).replace(FIELDS_VALUES, params);
		LOG.info("generateUpdateByQuery(List<String> byParams) :" + generatedQuery);
		return generatedQuery;
	}

	public String generateDeleteByQuery(List<String> byParams) {
		// TODO Auto-generated method stub
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (String byParam : byParams) {
			params.append(byParam);
			params.append(QUESTION_MARK_EQUAL);
			if (byParams.size() > 1) {
				if (i < byParams.size() - 1) {
					params.append(AND_CLAUSE);
					i++;
				}
			}
		}
		String generatedQuery = DELETE_BY_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName()).replace(FIELDS_NAMES,
				params);
		LOG.info("generateDeleteByQuery(List<String> byParams) :" + generatedQuery);
		return generatedQuery;
	}

	public String generateSelectByQuery(List<String> byParams) {
		// TODO Auto-generated method stub
		StringBuilder params = new StringBuilder();
		int i = 0;
		for (String byParam : byParams) {
			params.append(byParam);
			params.append(QUESTION_MARK_EQUAL);
			if (byParams.size() > 1) {
				if (i < byParams.size() - 1) {
					params.append(AND_CLAUSE);
					i++;
				}
			}
		}
		String generatedQuery = SELECT_BY_STATEMENT_BLUEPRINT.replace(TABLE_NAME, findTableName()).replace(FIELDS_NAMES,
				params);
		LOG.info("generateSelectByQuery(List<String> byParams) :" + generatedQuery);
		return generatedQuery;
	}

	public String generateSelectFieldByQuery(List<String> fields, List<String> byParams) {
		StringBuilder paramsFields = new StringBuilder();
		StringBuilder params = new StringBuilder();
		int i = 0, x = 0;
		for (String field : fields) {
			paramsFields.append(field);
			if (fields.size() > 1) {
				if (i < fields.size() - 1) {
					paramsFields.append(",");
					i++;
				}
			}
		}

		for (String byParam : byParams) {
			params.append(byParam);
			params.append(QUESTION_MARK_EQUAL);
			if (byParams.size() > 1) {
				if (x < byParams.size() - 1) {
					params.append(AND_CLAUSE);
					x++;
				}
			}
		}
		String generatedQuery = SELECT_FIELDS_BY_STATEMENT_BLUEPRINT.replace(FIELDS_NAMES, paramsFields)
				.replace(TABLE_NAME, findTableName()).replace(FIELDS_VALUES, params);
		LOG.info("generateSelectFieldByQuery(List<String> fields, List<String> byParams) :" + generatedQuery);
		return generatedQuery;
	}

	public String generateSelectFieldByQuery(List<String> fields) {
		StringBuilder paramsFields = new StringBuilder();
		int i = 0;
		for (String byParam : fields) {
			paramsFields.append(byParam);
			if (fields.size() > 1) {
				if (i < fields.size() - 1) {
					paramsFields.append(",");
					i++;
				}
			}
		}
		String generatedQuery = SELECT_FIELDS_STATEMENT_BLUEPRINT.replace(FIELDS_NAMES, paramsFields)
				.replace(TABLE_NAME, findTableName());
		LOG.info(" generateSelectFieldByQuery(List<String> fields):" + generatedQuery);
		return generatedQuery;
	}

	public abstract String findPrimaryKey();

	public abstract String findTableName();

	public abstract Map<String, String> mapTableField();

}
