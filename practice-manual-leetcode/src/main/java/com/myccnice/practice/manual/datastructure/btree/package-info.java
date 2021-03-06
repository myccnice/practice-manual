/**
 * 多路平衡搜索树。
 * #对B-Tree和B+Tree讲解的很好
 * https://www.cnblogs.com/vianzhang/p/7922426.html
 * (linux查看block大小: stat /boot/|grep "IO Block")
 * (linux查看page大小:  getconf PAGESIZE)
 * B-Tree是为磁盘等外存储设备设计的一种平衡查找树。因此在讲B-Tree之前先了解下磁盘的相关知识。
 * 系统从磁盘读取数据到内存时是以磁盘块（block）为基本单位的，位于同一个磁盘块中的数据会被一次性读取出来，而不是需要什么取什么。
 * InnoDB存储引擎中有页（Page）的概念，页是其磁盘管理的最小单位。InnoDB存储引擎中默认每个页的大小为16KB，可通过参数innodb_page_size将页的大小设置为4K、8K、16K，在MySQL中可通过如下命令查看页的大小：
 * mysql> show variables like 'innodb_page_size';
 * 而系统一个磁盘块的存储空间往往没有这么大，因此InnoDB每次申请磁盘空间时都会是若干地址连续磁盘块来达到页的大小16KB。InnoDB在把磁盘数据读入到磁盘时会以页为基本单位，在查询数据时如果一个页中的每条数据都能有助于定位数据记录的位置，这将会减少磁盘I/O次数，提高查询效率。
 * B-Tree结构的数据可以让系统高效的找到数据所在的磁盘块。为了描述B-Tree，首先定义一条记录为一个二元组[key, data] ，key为记录的键值，对应表中的主键值，data为一行记录中除主键外的数据。对于不同的记录，key值互不相同。
 *
 * #BTree和B+Tree详解，我是看这个文章才理解BTree的
 * https://blog.csdn.net/endlu/article/details/51720299
 *
 * @author 王鹏
 * @date 2018年12月7日
 */
package com.myccnice.practice.manual.datastructure.btree;