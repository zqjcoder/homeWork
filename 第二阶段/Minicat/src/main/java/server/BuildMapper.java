package server;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BuildMapper {

    // 缓存扫描到的class全限定类名
    static List<String> classNames = new ArrayList<>();

    public static void buildMapper(Mapper mapper, Map<String, HttpServlet> servletMap) {
        List<Host> hosts = mapper.getHosts();
        for (Host host : hosts) {
            doScan(host, host.getAppBase(), servletMap,true);
        }

    }


    /**
     * 扫描baseApp下的servlet
     */
    private static void doScan(Host host, String scanPackage, Map<String, HttpServlet> servletMap, boolean root) {
        String scanPackagePath = scanPackage.replaceAll("\\.", "/");
        File pack = new File(scanPackagePath);
        File[] files = pack.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                //如果是根目录下，封装Context
                if (root) {
                    //获取webapp根目录下的文件夹名
                    String name = file.getName();
                    Context context = new Context();
                    context.setName(name);
                    host.getContexts().add(context);
                }
                // 子package
                // 递归
                doScan(host, scanPackage + "." + file.getName(), servletMap,false);
            } else if (file.getName().endsWith(".class")) {
                String className = scanPackage + "." + file.getName().replaceAll(".class", "");
                for (Context context : host.getContexts()) {
                    //根据context的name分割路径包
                    String[] split = scanPackagePath.split(context.getName() + "/");
                    if(split != null && split.length > 1){
                        try {
                            Wrapper wrapper = new Wrapper();
                            //加载磁盘上指定目录的class文件并实例化
                            URL u = new URL("file://" + split[0] + context.getName() + "/");
                            URLClassLoader loader = new URLClassLoader(new URL[] {u});
                            //只用类的名称，如LagouServlet
                            String classNameStr = file.getName().replaceAll(".class", "");
                            //需要包名，如:server.LagouServlet
                            Class c = loader.loadClass(split[1] + "." + classNameStr);
                            //判断某个类是否是HttpServlet子类，且不是抽象类
                            if(HttpServlet.class.isAssignableFrom(c) && !Modifier.isAbstract(c.getModifiers())){
                                HttpServlet o = (HttpServlet)c.newInstance();
                                String servletUrl = lowerFirst(classNameStr.split("Servlet")[0]);
                                //将以例如/demo1/lagou的访问路径放到映射对象中
                                servletMap.put("/" + context.getName() + "/" + servletUrl, o);
                                //以自定义servlet前的单词为key存入wrapper的map中，如LagouServlet,就以lagou为key
                                wrapper.getServletMapper().put(servletUrl,o);
                                context.getWrappers().add(wrapper);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                classNames.add(className);
            }
        }
        System.out.println(classNames);
    }

    /**
     * 首字母小写方法
     */
    private static String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        if('A' <= chars[0] && chars[0] <= 'Z') {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

}
