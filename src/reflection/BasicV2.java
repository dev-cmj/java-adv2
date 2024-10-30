package reflection;

import reflection.data.BasicData;

import java.util.Arrays;

public class BasicV2 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 클래스 메타데이터 조회 방법 3가지

        // 1. 클래스에서 찾기
        Class< BasicData > basicData = BasicData.class;

        System.out.println("basicData.getName() = " + basicData.getName());
        System.out.println("basicData.getSimpleName() = " + basicData.getSimpleName());
        System.out.println("basicData.getCanonicalName() = " + basicData.getCanonicalName());
        System.out.println("basicData.getTypeName() = " + basicData.getTypeName());
        System.out.println("basicData.getPackageName() = " + basicData.getPackageName());
        System.out.println("basicData.getSuperclass() = " + basicData.getSuperclass());
        System.out.println("basicData.getModifiers() = " + basicData.getModifiers());
        System.out.println("basicData.getInterfaces() = " + Arrays.toString(basicData.getInterfaces()));
        System.out.println("basicData.getSigners() = " + Arrays.toString(basicData.getSigners()));
        System.out.println("basicData.getClassLoader() = " + basicData.getClassLoader());
    }
}
