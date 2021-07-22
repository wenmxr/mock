# mock
You can study some mock demo with this project.

## Mockito
Mockito 是一个强大的用于 Java 开发的模拟测试框架, 通过 Mockito 我们可以创建和配置 Mock 对象, 进而简化有外部依赖的类的测试。

简单来说，创建 mock 对象之后，这个对象里面的方法是怎么实现的可以不用去关心，我们只需要给这个方法定义一个输入、输出即可。

@Mock: 创建一个Mock.
   
@InjectMocks: 创建一个实例，简单的说是这个Mock可以调用真实代码的方法，其余用@Mock（或@Spy）注解创建的mock将被注入到用该实例中。
    
注意：必须使用@RunWith(MockitoJUnitRunner.class) 或 Mockito.initMocks(this)进行mocks的初始化和注入。
    
Mock=stub，只会调用mock方法，不会调用真实方法。

Spy=监视，可以选择调用真实方法还是mock方法。

不论是否是真实的方法调用都可以进行verify验证。

如果被测试的代码和被mock的代码在同一个类里的时候，需要使用spy。

mock可以使用doReturn和thenReturn两种形式，而spy只能使用doReturn。举例：

List list = Mockito.mock(ArrayList.class);

//以下两种形式都可以

Mockito.when(list.get(0)).thenReturn(new Object());

Mockito.doReturn(new Object()).when(list).get(0);

但是如果是List list = Mockito.spy(new ArrayList());

则只能使用Mockito.doReturn(new Object()).when(list).get(1);

如果使用thenReturn，因为调用时会去调用真实的get方法导致得不到预期的返回值（抛了异常）。

使用Mockito.verify验证参数的时候，如果参数是对象，需要用Matchers.eq。

//报错

Mockito.verify(obj, Mockito.times(1)).method("checked value");

//正确

Mockito.verify(obj, Mockito.times(1)).method(Matchers.eq("checked value"));

## PowerMock

要使用PowerMock的特性必须在类上加上注解

@RunWith(PowerMockRunner.class)

@PrepareForTest( { TestClass.class })//这里放需要被PowerMock处理的类（普通mockito处理的类不需要放进去）

PowerMock来mock静态方法的时候，可以用mockStatic(Class<?> type)，该方法忽略被mock的静态方法的public/private/protected关键字。

PowerMockito.mockStatic(MockedClass.class);

PowerMockito.when(MockedClass.method(Matchers.anyString())).thenReturn(retValue);

私有静态方法需要通过类似反射的形式

PowerMockito.when(MockedClass.class, "method", Matchers.anyString()).thenReturn(retValue);


静态方法所在的类要写在PrepareForTest里

@PrepareForTest( { TestClass.class })

否则mock不生效

