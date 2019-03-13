# maven-singleton
## 单例模式的应用场景:
  severletContext, SeverContextConfig, Spring中ApplicationContext, 数据库连接池
## 1、每种单例写法的优、缺点总结：
  ### 1.饿汉式(如: HungrySingleton, HungrySingletonV2)
          类加载的时候就立即初始化, 创建单例对象，无线程安全问题
          没有synchronized(){...}同步代码块, 执行效率高
          类加载的时候就初始化, 占资源, 浪费内存，可能出现创建实例对象, 但一直不用这个对象的情况
  ### 2.懒汉式(如: LazySimpleSingleton, DoubleCheckLazySingleton, LazyInnerClassSingleton)
      LazySimpleSingleton: 简单的懒汉式，存在线程安全问题
      DoubleCheckLazySingleton: 双重校验锁懒汉式, 解决了LazySimpleSingleton的线程安全问题, 但有synchronized(){...}同步代码块, 执行效率低
      LazyInnerClassSingleton: 静态内部类懒汉式, 兼顾饿汉式内存浪费(只有第一次调用LazyInnerClassSingleton getInstace()的时候才会初始化内部类),
      也兼顾synchronized(){...}性能问题
  ### 3.注册式(如: EnumSingleton, ContainerSingleton)将每一个实例都登记到某一个地方, 使用唯一标示获取单例
      EnumSingleton: 反编译可以看到在静态代码块中就给INSTANCE赋值, 是饿汉式单例的实现, 在JDK层面使得枚举式单例不受反射和序列化攻击,
      是effectiveJava中推荐单例写法
      
      ContainerSingleton: 适用于创建实例非常多的情况, 便于管理
          

## 2、破坏单例模式的方式总结：
  ### 1. 反射破坏单例
        通过获取类的字节码, 反射获取构造, 并实例化对象, 
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
## 3、内部类的执行逻辑，以及时序图
