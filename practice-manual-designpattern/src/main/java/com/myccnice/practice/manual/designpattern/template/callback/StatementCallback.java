package com.myccnice.practice.manual.designpattern.template.callback;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 将子类需要延迟实现的方法声明为接口
 *
 * create in 2017年9月27日
 * @author wangpeng
 */
public interface StatementCallback {

    Object doInStatement(Statement stmt) throws SQLException; 
}
