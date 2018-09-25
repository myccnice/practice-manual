package com.myccnice.practice.manual.mysql.theory;

/**
 * @see https://blog.csdn.net/zhanghongzheng3213/article/details/51722506
 * MySQL索引
 * 
 * 索引是对数据库表中一列或多列的值进行排序的一种结构。在关系数据库中，索引是一种与表有关的数据库结构，它可以使对应于表的SQL语句执行得更快。
 * 如果没有索引，要对表进行查询需要全表搜索，是将所有记录一一取出，和查询条件进行一一对比，然后返回满足条件的记录，如果表的数据量大会消耗大量数据库系统时间，
 * 并造成大量磁盘I/O操作（访问磁盘的成本大概是访问内存的十万倍左右）。如果在表中建立索引，则先在索引中找到符合查询条件的索引值，最后通过保存在索引中的ROWID
 * 快速找到表中对应的记录。
 */