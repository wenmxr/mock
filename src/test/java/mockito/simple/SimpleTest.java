package mockito.simple;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @Author qinwen
 * @Date 2021/7/22 5:52 下午
 */
public class SimpleTest {

    /**
     * 交互校验
     */
    @Test
    public void test0() {
        // mock creation
        List mockedList = mock(List.class);
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();
        //selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    /**
     * 模拟数据
     */
    @Test
    public void test1() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
        // 设置mock对象的行为 － 当调用其get方法获取第0个元素时，返回"first"
        when(mockedList.get(0)).thenReturn("first");
        // doReturn("first").when(mockedList).get(0);
        // the following prints "first"
        System.out.println(mockedList.get(0));
        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
    }

    /**
     * 校验某个行为没有发生
     */
    @Test
    public void test2() {
        List mockOne = mock(List.class);
        //using mocks - only mockOne is interacted
        mockOne.add("one");
        //ordinary verification
        verify(mockOne).add("one");
        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");
    }

    /**
     * Mock真实的对象
     */
    @Test
    public void test3() {
        List list = new LinkedList();
        List spy = spy(list);
        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);
        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");
        //prints "one" - the first element of a list
        System.out.println(spy.get(0));
        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());
        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    /**
     * 重置模拟
     */
    @Test
    public void test4() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);
        reset(mock);
    }
}
