package com.myccnice.practice.manual.jdk.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;

import org.junit.Assert;
import org.junit.Test;

import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Person;
import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Student;

/**
 * 并发流相关方法测试
 *
 * @author 王鹏
 * @date 2018年11月23日
 */
public class ParallelStreamTest extends BaseTest {

    @Test
    public void peek() {
        List<Student> students = new ArrayList<>(persons.size());
        Object lock = new Object();
        Consumer<Person> action = p -> {
            Student student = Student.toStudent(p);
            synchronized (lock) {
                students.add(student);
            }
        };
        persons.parallelStream().peek(action).count();
        Assert.assertEquals(persons.size(), students.size());
    }

    @Test
    public void map() {
        // Function<Person, Student> mapper = person -> Student.toStudent(person);
        List<Student> students = persons.parallelStream().map(Student::toStudent).collect(toList());
        Assert.assertEquals(persons.size(), students.size());
    }
}
