package com.myccnice.practice.manual.designpattern.template;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 在上面这个抽象类中，封装了JDBC API的主要流程:①获取连接②获取Statement；③执行查询；④遍历结果集
 * 在这个流程中只有在遍历ResultSet并封装成集合的这一步骤是可定制的，因为每张表都映射不同的java bean。
 * 所以这一步需要延迟到子类中，由子类负责实现，这是典型的模板方法模式。
 *
 * create in 2017年9月27日
 * @author wangpeng
 */
public abstract class JdbcTemplate {

    //template method
    public final Object execute(String sql) throws SQLException{
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            return doInStatement(rs);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    //implements in subclass
    protected abstract Object doInStatement(ResultSet rs);

    private Connection getConnection() {
        return null;
    }
}
