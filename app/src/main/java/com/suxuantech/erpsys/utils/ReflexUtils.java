package com.suxuantech.erpsys.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * ..................佛祖开光 ,永无BUG................
 *
 * @author Created by 李站旗 on 2017/11/2 20:30 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(反射工具)
 */

public class ReflexUtils {
    /**
     * 通过反射获取一个类的私有值
     * @param instance 反射类对象
     * @param fieldName 需要获取字段名字
     * @return  返回需要的字段值
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getValue(Object instance, String fieldName) throws IllegalAccessException,
            NoSuchFieldException {
        Field field = getField(instance.getClass(), fieldName);
        // 参数值为true，禁用访问控制检查(暴力反射,防止需要的值时私有的)
        field.setAccessible(true);
        Object object =  field.get(instance);
        field.setAccessible(false);
        return object;
    }

    /**
     * 通过目标类的.class方式进行反射
     * @param thisClass 目标类的.Class
     * @param fieldName 需要获取字段名字
     * @return 返回需要的字段值
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    public static Object getValue(Class thisClass, String fieldName) throws IllegalAccessException,
            NoSuchFieldException, InstantiationException {
        Field field = getField(thisClass, fieldName);
        // 参数值为true，禁用访问控制检查(暴力反射,防止需要的值时私有的)
        field.setAccessible(true);
        Object object =   field.get(thisClass.newInstance());
        field.setAccessible(false);
        return object;
    }



    /**
     *  通过反射获取一个公共字段对象
     * @param thisClass 字节码
     * @param fieldName 需要获取字段名字
     * @return  返回指定字段域对象
     * @throws NoSuchFieldException
     */
    public static Field getField(Class thisClass, String fieldName) throws NoSuchFieldException {
        if (fieldName == null) {
            throw new NoSuchFieldException("Error field !");
        }
        Field field = thisClass.getDeclaredField(fieldName);
        return field;
    }

    /**
     * 暴力设置值
     * @param obj
     * @param propertyName
     * @param value
     * @throws Exception
     */
    public static void setNotAccessibleProperty(Object obj, String propertyName, Object value) throws Exception
    {
        Class<?> clazz = obj.getClass();
        Field field = clazz.getDeclaredField(propertyName);
        //赋值前将该成员变量的访问权限打开
        field.setAccessible(true);
        field.set(obj, value);
        //赋值后将该成员变量的访问权限关闭
        field.setAccessible(false);
    }


















    /**
     * 设置字段值
     * @param t     对应实体
     * @param field     字段
     * @param fieldName     字段名称
     * @param value         字段值
     */
    public static<T> void setFieldValue(T t,Field field, String fieldName, String value){
        String name = field.getName();
        //判断该字段是否和目标字段相同
        if (!fieldName.equals(name)) {
            return;
        }
        //获取字段的类型
        Type type = field.getType();
        //获取字段的修饰符号码
        int typeCode = field.getModifiers();
        //获取字段类型的名称
        String typeName = type.toString();
        try {
            switch (typeName) {
                case "class java.lang.String":
                    if (Modifier.isPublic(typeCode)) {
                        field.set(t, value);
                    } else {
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName), String.class);
                        method.invoke(t, value);
                    }
                    break;
                case "double":
                    if(Modifier.isPublic(typeCode)){
                        field.setDouble(t, Double.valueOf(value));
                    }else{
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName),double.class);
                        method.invoke(t, Double.valueOf(value));
                    }
                    break;
                case "int":
                    if(Modifier.isPublic(typeCode)){
                        field.setInt(t, Integer.valueOf(value));
                    }else{
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName),int.class);
                        method.invoke(t, Integer.valueOf(value));
                    }
                    break;
                case "float":
                    if(Modifier.isPublic(typeCode)){
                        field.setFloat(t, Float.valueOf(value));
                    }else{
                        Method method = t.getClass().getMethod("set" + getMethodName(fieldName), float.class);
                        method.invoke(t, Float.valueOf(value));
                    }
                    break;
                default:
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把字段名称第一个字母换成大写
     * @param fieldName     字段名称
     * @return
     * @throws Exception    异常处理
     */
    private static String getMethodName(String fieldName) throws Exception{
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char)items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 根据字段名称获取指定Field字段
     * @param clazz        实体的字节码文件
     * @param filedName        字段的名称
     * @return 返回对应的字符按Field或者返回null
     */
    public static Field getFields(Class<?> clazz, String filedName){
        if (clazz == null || TextUtils.isEmpty(filedName)) {
            throw new IllegalArgumentException("params is illegal");
        }
        Field[] fields = clazz.getDeclaredFields();
        return getFieldByName(fields, filedName);
    }

    /**
     * 根据字段名称获取指定的Field
     * @param fields   字段集合
     * @param fieldName     字段名称
     * @return 返回对应的Field字段或者返回null
     */
    public static Field getFieldByName(Field[] fields, String fieldName){
        if (fields == null || fields.length == 0 || TextUtils.isEmpty(fieldName)) {
            throw new IllegalArgumentException("params is illegal");
        }
        for (Field field : fields) {
            String name = field.getName();
            //判断该字段是否和目标字段相同
            if (fieldName.equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 判断该字段是否为FieldName对应字段
     * @param field        Field字段
     * @param fieldName        目标字段
     * @return 是，返回true；否，返回false
     */
    public static boolean isFiledWithName(Field field, String fieldName){
        if(field == null || TextUtils.isEmpty(fieldName)){
            throw new IllegalArgumentException("params is illegal");
        }
        if (fieldName.equals(field.getName())) {
            return true;
        }
        return false;
    }






}
