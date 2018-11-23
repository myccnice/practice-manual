package com.myccnice.practice.manual.jdk.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;

import org.junit.Assert;
import org.junit.Test;

import com.myccnice.practice.manual.jdk.jdk8.stream.vo.Parent;
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
    public void filter() {
        // 筛选出能够上学的小朋友，7岁上学
        int ageOfSchool = 7;
        // jdk8之前的方法
        List<Person> beforeJdk8 = new ArrayList<>(persons.size());
        for (Person person : persons) {
            if (person.getAge() >= ageOfSchool) {
                beforeJdk8.add(person);
            }
        }
        List<Person> afterJdk8 = persons.stream().filter(p -> p.getAge() >= ageOfSchool).collect(toList());
        Assert.assertEquals(beforeJdk8.size(), afterJdk8.size());
    }

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

    @Test
    public void flatMap() {
        List<Parent> parents = students.stream().flatMap(Student::toParents).collect(toList());
        Assert.assertEquals(parents.size(), 2 * students.size());
    }
}
