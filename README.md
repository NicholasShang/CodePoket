# CodePoket
Some good codes and accumulation..

# Java8.time

## JDK8 中对时间的一些处理以及测试类。

>  1、它提供了javax.time.ZoneId用来处理时区。
>  
>  2、它提供了LocalDate与LocalTime类
>  
>  3、Java 8中新的时间与日期API中的所有类都是不可变且线程安全的，这与之前的Date与Calendar API中的恰好相反，那里面像java.util.Date以及SimpleDateFormat这些关键的类都不是线程安全的。
>  
>  4、新的时间与日期API中很重要的一点是它定义清楚了基本的时间与日期的概念，比方说，瞬时时间，持续时间，日期，时间，时区以及时间段。它们都是基于ISO日历体系的。
>  
>  5、每个Java开发人员都应该至少了解这套新的API中的这五个类：
>      ①Instant 它代表的是时间戳，比如2014-01-14T02:20:13.592Z，这可以从java.time.Clock类中获取，像这样： Instant current = Clock.system(ZoneId.of(“Asia/Tokyo”)).instant();
>      ②LocalDate 它表示的是不带时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
>      ③LocalTime – 它表示的是不带日期的时间
>      ④LocalDateTime – 它包含了时间与日期，不过没有带时区的偏移量
>      ⑤ZonedDateTime – 这是一个带时区的完整时间，它根据UTC/格林威治时间来进行时区调整
>      
>  6、这个库的主包是java.time，里面包含了代表日期，时间，瞬时以及持续时间的类。它有两个子package，一个是java.time.foramt，这个是什么用途就很明显了，还有一个是java.time.temporal，它能从更低层面对各个字段进行访问。
>  
>  7、时区指的是地球上共享同一标准时间的地区。每个时区都有一个唯一标识符，同时还有一个地区/城市(Asia/Tokyo)的格式以及从格林威治时间开始的一个偏移时间。比如说，东京的偏移时间就是+09:00。
>  
>  8、OffsetDateTime类实际上包含了LocalDateTime与ZoneOffset。它用来表示一个包含格林威治时间偏移量（+/-小时：分，比如+06:00或者 -08：00）的完整的日期（年月日）及时间（时分秒，纳秒）。
>  
>  9、DateTimeFormatter类用于在Java中进行日期的格式化与解析。与SimpleDateFormat不同，它是不可变且线程安全的，如果需要的话，可以赋值给一个静态变量。DateTimeFormatter类提供了许多预定义的格式器，你也可以自定义自己想要的格式。当然了，根据约定，它还有一个parse()方法是用于将字符串转换成日期的，如果转换期间出现任何错误，它会抛出DateTimeParseException异常。类似的，DateFormatter类也有一个用于格式化日期的format()方法，它出错的话则会抛出DateTimeException异常。
>  
>  10、“MMM d yyyy”与“MMm dd yyyy”这两个日期格式也略有不同，前者能识别出”Jan 2 2014″与”Jan 14 2014″这两个串，而后者如果传进来的是”Jan 2 2014″则会报错，因为它期望月份处传进来的是两个字符。为了解决这个问题，在天为个位数的情况下，你得在前面补0，比如”Jan 2 2014″应该改为”Jan 02 2014″。
 
 
# patterns 设计模式的小例子
## patterns.factory 工厂模式

> 核心思想：通过反射来进行类的实例化，减少重复性的代码

``` java
public class Factory {
    public <T> T getShape(Class<? extends  T> clazz){
        if (clazz == null ) {
            return null;
        }
        T obj = null;
        try {
            //====================================================//
            obj = (T) Class.forName(clazz.getName()).newInstance();
            //====================================================//
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
```



## patterns.immutable 不可变模式

> 1、类的所有属性声明为private，去除掉所有的setter方法，防止外界直接对其进行修改
> 
> 2、类的声明采用final进行修饰，保证没有父类对其修改
> 
> 3、类的属性声明为final,如果对象类型为可变类型，应对其重新包装，重新new一个对象返回
> 
> ### 优点：
> 
> 1、因为是不可变的，所以不允许程序对其进行修改，避免了程序中修改数据带来的异常产生
> 
> 2、由于对象是不可变的，减少了线程同步带来的开销
> 
> ### 缺点：
> 
> 每次返回都创建新的对象，内存会有一定的开销，不容易被垃圾回收器回收，造成资源的浪费
> 
> ### 应用场景：
> 
> 1、不适合大对象、且创建频繁的场景，因为对象大且创建频繁会容易导致内存泄漏
> 
> 2、适合表示抽象数据类型（如数字、枚举类型或颜色）的值
> 
> 3、适合在多线程环境中进行同步而不需要考虑线程同步
>


# schedule 定时任务

一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。

 按顺序依次为

- >  **秒（0~59）**
- >  **分钟（0~59）**
- >  **小时（0~23**）
- >  **天（月）（0~31，但是你需要考虑你月的天数）**
- >  **月（0~11）**
- >  **天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）**
- >  **7.年份（1970－2099）**

>  其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),
>  一个列表(1,3,5),通配符。由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?.

> -  0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
> -  0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
> -  0 0 12 ? * WED 表示每个星期三中午12点
> -  0 0 12 * * ?" 每天中午12点触发
> -  0 15 10 ? * *" 每天上午10:15触发
> -  0 15 10 * * ?" 每天上午10:15触发
> -  0 15 10 * * ? *" 每天上午10:15触发
> -  "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
> -  0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
> -  0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
> -  0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
> -  0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
> -  0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
> -  0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
> -  0 15 10 15 * ?" 每月15日上午10:15触发
> -  0 15 10 L * ?" 每月最后一日的上午10:15触发
> -  0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
> -  0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
> -  0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发

 有些子表达式能包含一些范围或列表
 例如：子表达式（天（星期））可以为 “MON-FRI”，“MON，WED，FRI”，“MON-WED,SAT”
 “*”字符代表所有可能的值
 因此，“*”在子表达式（月）里表示每个月的含义，“*”在子表达式（天（星期））表示星期的每一天

>  **“/”字符用来指定数值的增量**
>  例如：在子表达式（分钟）里的“0/15”表示从第0分钟开始，每15分钟
>  在子表达式（分钟）里的“3/20”表示从第3分钟开始，每20分钟（它和“3，23，43”）的含义一样

>  **“？”字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值**
>  当2个子表达式其中之一被指定了值以后，为了避免冲突，需要将另一个子表达式的值设为“？”

>  **“L” 字符仅被用于天（月）和天（星期）两个子表达式，它是单词“last”的缩写**
>  但是它在两个子表达式里的含义是不同的。
>  在天（月）子表达式中，“L”表示一个月的最后一天
>  在天（星期）自表达式中，“L”表示一个星期的最后一天，也就是SAT
>  如果在“L”前有具体的内容，它就具有其他的含义了
>  例如：“6L”表示这个月的倒数第６天，“ＦＲＩＬ”表示这个月的最一个星期五
>  注意：在使用“L”参数时，不要指定列表或范围，因为这会导致问题

字段      | 允许值                  | 允许的特殊字符
---       |   ---                   |  ---
秒        |   0-59                  |   , - * /
秒        |   0-59                  |   , - * /
分        |   0-59	                |   , - * /
小时      |   0-23                  |   , - * /
日期      |   1-31                  |   , - * ? / L W C
月份      |   1-12 或者 JAN-DEC     |   , - * /
星期      |   1-7 或者 SUN-SAT      |   , - * ? / L C #
年（可选）|   留空, 1970-2099       |   , - * /

