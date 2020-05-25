package com.lagou.edu.utils;

import com.lagou.edu.annotion.MyAutoWired;
import com.lagou.edu.annotion.MyService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Field;
public class MyAnnotationConfigApplicationContext {
    private String packageName;
    //声明bean容器
    private ConcurrentHashMap<String,Object> beans = null;
    public MyAnnotationConfigApplicationContext(String packageName) throws Exception{
        this.packageName = packageName;
        beans = new ConcurrentHashMap<>();
       //初始化bean操作
        initBeans();
        //完成bean的实例化及存入容器中
        initEntryField();
    }

    // 初始化属性
    private void initEntryField() throws Exception {
        // 1.遍历所有的bean容器对象
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            // 2.判断属性上面是否有加注解@MyREsource 自动注入
            Object bean = entry.getValue();
            attriAssign(bean);
        }

    }


    // 依赖注入并完成容易装配
    public void attriAssign(Object object) throws Exception {
        // 1.使用反射机制,获取当前类的所有属性
        Class<? extends Object> classInfo = object.getClass();
        Field[] declaredFields = classInfo.getDeclaredFields();

        // 2.判断当前类属性是否存在注解
        for (Field field : declaredFields) {
            //跟自定义注解相关联
            MyAutoWired extResource = field.getAnnotation(MyAutoWired.class);
            if (extResource != null) {
                // 获取属性名称
                String beanId = field.getName();
                Object bean = getBean(beanId);
                if (bean != null) {
                    // 3.默认使用属性名称，查找bean容器对象 1参数 当前对象 2参数给属性赋值
                    field.setAccessible(true); // 允许访问私有属性
                    field.set(object, bean);
                }
            }
        }
    }

    //初始化对象
    public void initBeans () throws Exception {
        //1.使用java反射机制扫包，获取当前包下所有类
        List<Class<?>> classes = ClassUtil.getClasses(packageName);
        System.out.println("初始化");
        //2.判断类上面是否有注解,返回一个map集合，里面包含了，所有带MyService注解的类的信息
        ConcurrentHashMap<String,Object>  classHasExtServiceAnnotation = findClassIsHasAnnotation(classes);
        if (classHasExtServiceAnnotation == null || classHasExtServiceAnnotation.isEmpty()){
            throw new Exception("该包下所有类都没有MyServiceAnnotation");
        }
    }
    public Object getBean (String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)){
            throw new Exception("bean Id 不能为空");
        }
        //从spring 容器初始化对像
        Object object = beans.get(beanId);
        if (object == null) {
            throw  new Exception("Class not found");
        }
        return object;
    }

    //通过反射解析对象
    private Object newInstance(Class<?> classInfo) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //    Class<?> name = Class.forName(className);
        return classInfo.newInstance();
    }
    /*
     * 参数：通过工具类扫描的改包下所有的类信息
     * 返回值：返回一个map集合，里面包含了，所有带ExtService注解的类的信息
     *
     * */
    public  ConcurrentHashMap<String,Object> findClassIsHasAnnotation (  List<Class<?>> classes) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (Class<?> classInfo:classes) {
            //判断类上是否有自定义ExtService注解
           MyService annotation = classInfo.getAnnotation(MyService.class);
            if (annotation !=null){
                //beans(类名小写,classInfo)
                //获取当前类名
                String className = classInfo.getSimpleName();
                //将类名首字母变为小写
                String beanID =toLowerCaseFirstOne(className);
                System.out.println("current beanInfo  = " + beanID);
                Object bean = newInstance(classInfo);
                //如果当前类上有ExtService注解，将该类的信息，添加到map集合
                beans.put(beanID,bean);
            }
        }
        return beans;
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}