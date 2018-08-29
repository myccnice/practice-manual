package com.myccnice.practice.manual.designpattern;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myccnice.practice.manual.designpattern.template.callback.JdbcTemplate;
import com.myccnice.practice.manual.designpattern.template.callback.StatementCallback;
import com.myccnice.practice.manual.designpattern.vo.User;

/**
 * Unit test for simple App.
 */
public class AppTest {

    //内部类方式
    public Object innerClassQuery(final String sql) throws SQLException {
        class QueryStatementCallback implements StatementCallback {
            public Object doInStatement(Statement stmt) throws SQLException {
                ResultSet rs = stmt.executeQuery(sql);
                List<User> userList = new ArrayList<User>();
                User user = null;
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserName(rs.getString("userName"));
                    user.setBirth(rs.getDate("birth"));
                    user.setAge(rs.getInt("age"));
                    userList.add(user);
                }
                return userList;
            }
        }
        JdbcTemplate jt = new JdbcTemplate();
        return jt.query(new QueryStatementCallback());
    }

    //匿名类方式
    public Object anonymousClassQuery(final String sql) throws Exception {
        JdbcTemplate template = new JdbcTemplate();
        return template.query(new StatementCallback() {
            public Object doInStatement(Statement stmt) throws SQLException {
                ResultSet rs = stmt.executeQuery(sql);
                List<User> userList = new ArrayList<User>();
                User user = null;
                while (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserName(rs.getString("userName"));
                    user.setBirth(rs.getDate("birth"));
                    userList.add(user);
                }
                return userList;
            }
        });
    }
}
