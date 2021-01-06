package com.sqlacademy.task_passing.service.impl;

import com.sqlacademy.task_passing.service.SqlQueryType;
import com.sqlacademy.task_passing.service.SyntaxAnalysisService;
import org.springframework.stereotype.Service;


@Service
public class SqlSyntaxAnalysisService implements SyntaxAnalysisService {
    @Override
    public boolean isValidSyntax(String analysisObject) {
        final var queryType = defineSqlQueryType(analysisObject);
        if (queryType == null) {
            return false;
        }

        return queryType.getPattern().matcher(analysisObject).matches();
    }

    @Override
    public SqlQueryType defineSqlQueryType(String query) {
        if (query.startsWith(SqlQueryType.ALTER.getStartsWith())) {
            return SqlQueryType.ALTER;
        } else if (query.startsWith(SqlQueryType.CREATE.getStartsWith())) {
            return SqlQueryType.CREATE;
        } else if (query.startsWith(SqlQueryType.DROP.getStartsWith())) {
            return SqlQueryType.DROP;
        } else if (query.startsWith(SqlQueryType.RENAME.getStartsWith())) {
            return SqlQueryType.RENAME;
        } else if (query.startsWith(SqlQueryType.DELETE.getStartsWith())) {
            return SqlQueryType.DELETE;
        } else if (query.startsWith(SqlQueryType.INSERT.getStartsWith())) {
            return SqlQueryType.INSERT;
        } else if (query.startsWith(SqlQueryType.SELECT.getStartsWith())) {
            return SqlQueryType.SELECT;
        } else if (query.startsWith(SqlQueryType.UPDATE.getStartsWith())) {
            return SqlQueryType.UPDATE;
        } else {
            return null;
        }
    }
}
