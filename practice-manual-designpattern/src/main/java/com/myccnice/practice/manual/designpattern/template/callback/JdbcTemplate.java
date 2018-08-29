package com.myccnice.practice.manual.designpattern.template.callback;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用回调改造后的模板类
 *
 * create in 2017年9月27日
 * @author wangpeng
 */
public class JdbcTemplate {

    // 正在执行sql的方法
    private final Object execute(StatementCallback action) throws SQLException {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()){
            return action.doInStatement(stmt);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private Connection getConnection() {
        return null;
    }

    // 为了看着顺眼，我们来给他封装一层吧
    public Object query(StatementCallback stmt) throws SQLException{
        return execute(stmt);
    }  
}
