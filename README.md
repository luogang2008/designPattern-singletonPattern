# maven-singleton
## 单例模式的应用场景:
  SeverletContext, SeverContextConfig, Spring中ApplicationContext, 数据库连接池
  
## 1、每种单例写法的优、缺点总结：
  ### 1.饿汉式(如: HungrySingleton, HungrySingletonV2)
  
      类加载的时候就立即初始化, 创建单例对象，无线程安全问题
      
      没有synchronized(){...}同步代码块, 执行效率高
      
      类加载的时候就初始化, 占资源, 浪费内存，可能出现创建实例对象, 但一直不用这个对象的情况
      
      
  ### 2.懒汉式(如: LazySimpleSingleton, DoubleCheckLazySingleton, LazyInnerClassSingleton)
      LazySimpleSingleton: 简单的懒汉式，存在线程安全问题
      
      DoubleCheckLazySingleton: 双重校验锁懒汉式, 解决了LazySimpleSingleton的线程安全问题, 但有synchronized(){...}同步代码块, 执行效率低
     
      LazyInnerClassSingleton: 静态内部类懒汉式, 外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存,
      只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE, 第一次调用getInstance()方法会导致虚拟机加载LazyHolder类,
      不仅能确保线程安全,也能保证单例的唯一性, 同时也延迟了单例的实例化
      
      
  ### 3.注册式(如: EnumSingleton, ContainerSingleton)将每一个实例都登记到某一个地方, 使用唯一标示获取单例
     
     EnumSingleton: 反编译可以看到在静态代码块中就给INSTANCE赋值, 是饿汉式单例的实现, 在JDK层面使得枚举式单例不受反射和序列化攻击,
     是effectiveJava中推荐单例写法
      
     ContainerSingleton: 适用于创建实例非常多的情况, 便于管理
          

## 2、破坏单例模式的方式总结：
  ### 1. 反射破坏单例
        通过获取类的字节码, 反射获取构造, 并实例化对象
        
        应对: 在构造中判断实例是否为空, 不为空则抛exception, 如: LazyInnerClassSingleton中, 
        private LazyInnerClassSingleton(){
            if(LazyHolder.lazy!=null){//防止反射攻击
                throw new RuntimeException("不允许创建多个实例");
            }
        }
        
        
  ### 1. 序列化破坏单例
        将单例对象创建好后(s1), 将s1序列化到磁盘, 再从磁盘反序列化为内存对象(s2), s1!=s2
        
        应对: 添加 readResolve方法, 如: LazyInnerClassSingleton中,
         //防止序列化攻击
        private Object readResolve(){
            return LazyHolder.lazy;
        }
        
        
## 3、静态内部类的执行逻辑，以及时序图
    首先，我们先了解下类的加载时机:
    1.遇到new、getstatic、setstatic或者invikestatic这4个字节码指令时，对应的java代码场景为：
    new一个关键字或者一个实例化对象时、读取或设置一个静态字段时(final修饰、已在编译期把结果放入常量池的除外)、调用一个类的静态方法时
    
    2.使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没进行初始化，需要先调用其初始化方法进行初始化。
    
    3.当初始化一个类时，如果其父类还未进行初始化，会先触发其父类的初始化
    
    4.当虚拟机启动时，用户需要指定一个要执行的主类(包含main()方法的类)，虚拟机会先初始化这个类
    
    5.当使用JDK 1.7等动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化
    
    这5种情况被称为是类的主动引用，注意，这里《虚拟机规范》中使用的限定词是"有且仅有"，那么，除此之外的所有引用类都不会对类进行初始化，称为被动引用。静态内部类就属于被动引用的行列
    
    当getInstance()方法被调用时，LazyHolder才在LazyInnerClassSingleton的运行时常量池里，把符号引用替换为直接引用，这时静态对象INSTANCE也真正被创建，然后再被getInstance()方法返回出去

