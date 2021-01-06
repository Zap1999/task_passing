package com.sqlacademy.task_passing.service;

public interface SyntaxAnalysisService {
    boolean isValidSyntax(String analysisObject);

    SqlQueryType defineSqlQueryType(String query);
}
