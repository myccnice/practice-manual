package com.myccnice.practice.manual.jvm;

import org.junit.Test;

import com.myccnice.practice.manual.nibenjia.unsafe.PrivateClass;
import com.myccnice.practice.manual.nibenjia.unsafe.UnsafeUtil;

import static com.myccnice.practice.manual.nibenjia.unsafe.UnsafeUtil.getUnsafeInstance;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * https://www.jianshu.com/p/9819eb48716a
 *
 * @author 王鹏
 * @date 2019年3月13日
 */
public class UnsafeTest {

    /**
     * 修改和读取数组中的值
     * @throws Exception 
     */
    @Test
    public void readAndUpdateArray() throws Exception {
        Unsafe u = getUnsafeInstance();
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        // arrayBaseOffset:返回当前数组第一个元素地址相对于数组起始地址的偏移值，在本例中返回6。
        int b = u.arrayBaseOffset(int[].class);
        // arrayIndexScale: 返回当前数组一个元素占用的字节数,在本例中返回4。
        int s = u.arrayIndexScale(int[].class);
        // putInt(obj,offset,intval): 
        // 获取数组对象obj的起始地址，加上偏移值，得到对应元素的地址，将intval写入内存。
        u.putInt(arr, (long)b + s * 9, 1);
        for(int i=0;i<10;i++){
            // getInt(obj,offset): 
            // 获取数组对象obj的起始地址，加上偏移值，得到对应元素的地址，从而获得元素的值。
            // 偏移值: 数组元素偏移值 = arrayBaseOffset + arrayIndexScalse * i。
            int v = u.getInt(arr, (long)b+s*i);
            System.out.print(v + "");
        }
    }

    /**
     * 获取对象实例
     * allocateInstance: 在不执行构造方法的前提下，获取一个类的实例，即使这个类的构造方法是私有的。
     * @throws Exception 
     */
    @Test
    public void allocateInstance() throws Exception {
        Unsafe u = getUnsafeInstance();
        PrivateClass p = (PrivateClass)u.allocateInstance(PrivateClass.class);
        System.out.println(p.intfield);
    }

    /**
     * 修改静态变量和实例变量的值
     * 
     * 这里使用allocateInstance方法获取了一个Test类的实例，并且没有打印“constructor called”，说明构造方法没有调用。
     * 修改实例变量与修改数组的值类似，同样要获取地址偏移值，然后调用putInt方法。
     */
    @Test
    public void updateInstanceField() throws Exception {
        Unsafe u = getUnsafeInstance();
        PrivateClass t = (PrivateClass) u.allocateInstance(PrivateClass.class);
        //  objectFieldOffset:获取对象某个属性的地址偏移值
        long b1 = u.objectFieldOffset(PrivateClass.class.getDeclaredField("intfield"));
        u.putInt(t, b1, 2);
        System.out.println("intfield:" + t.intfield);
    }

    /**
     * 修改静态变量的值
     * 静态变量与实例变量不同之处在于，静态变量位于于方法区中，它的地址偏移值与类对象在方法区的地址相关，与类的实例无关。
     * @throws Exception
     */
    @Test
    public void updateStaticField() throws Exception {
        Unsafe u = getUnsafeInstance();
        Field staticIntField = PrivateClass.class.getDeclaredField("staticIntField");
        // staticFieldBase: 获取静态变量所属的类在方法区的首地址。可以看到，返回的对象就是PrivateClass.class。
        Object o = u.staticFieldBase(staticIntField);
        System.out.println(o == PrivateClass.class);
        // staticFieldOffset: 获取静态变量地址偏移值。
        Long b4 = u.staticFieldOffset(staticIntField);
        //因为是静态变量，传入的Object参数应为class对象
        u.putInt(o, b4, 10);
        System.out.println("staticIntField:"+u.getInt(Test.class, b4)); 
    }

    @Test
    public void molestString() throws Exception {
        String abc = "abc";
        UnsafeUtil.molestString(abc);
        System.out.println("abc");
    }

    /**
     * compareAndSwapObject方法的四个值：
     *  第一个值表示要进行操作的对象pc，
     *  第二个参数通过之前获取的主存偏移量intfieldOffset告诉方法将要比较的是pc对象中的哪个属性，
     *  第三个参数为预想的该属性的当前值，
     *  第四个参数为将要替换成的新值。
     */
    @Test
    public void cas() throws Exception {
        Unsafe u = getUnsafeInstance();
        PrivateClass pc = new PrivateClass(2);
        Field intfield = PrivateClass.class.getDeclaredField("intfield");
        long intfieldOffset = u.objectFieldOffset(intfield);
        u.compareAndSwapObject(pc, intfieldOffset, 2, 3);
    }

    /**
     * Unsafe.getAndAddInt(Object, long, int)
     * 获取对象属性当前值并增加相应的值
     * 第一个值表示要进行操作的对象pc，
     * 第二个参数通过之前获取的主存偏移量intfieldOffset告诉方法将要比较的是pc对象中的哪个属性，
     * 第三个参数为给属性增加的量
     */
    @Test
    public void getAndAdd() throws Exception {
        Unsafe u = getUnsafeInstance();
        PrivateClass pc = new PrivateClass(2);
        Field intfield = PrivateClass.class.getDeclaredField("intfield");
        long intfieldOffset = u.objectFieldOffset(intfield);
        int andAddInt = u.getAndAddInt(pc, intfieldOffset, 1);
        System.out.println(andAddInt);
    }
}