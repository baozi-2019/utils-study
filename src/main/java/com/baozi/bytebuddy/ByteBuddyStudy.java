package com.baozi.bytebuddy;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * 创建人：baozi
 * 创建日期：2023/4/30 09:52
 * 描述：
 */
public class ByteBuddyStudy {
    private static final String[] a = new String[]{"name", "password"};
    private static final Class<?>[] clazzs = new Class[]{String.class, Integer.class};
    private static final Class<? extends Annotation>[] ann = new Class[]{NotNull.class, NotBlank.class};
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ByteBuddy byteBuddy = new ByteBuddy();
//        DynamicType.Unloaded<Object> notNullUnloaded = byteBuddy.subclass(Object.class).implement(NotNull.class).name("com.baozi.annotions.NotNullImpl")
//                .method(named("annotationType")).intercept(FixedValue.value(NotNull.class))
//                .method(named("message")).intercept(FixedValue.value("name不能为空"))
//                .make();
//        Class<?> notNullImplClass = notNullUnloaded.load(MyTest.class.getClassLoader()).getLoaded();
//        AnnotationDescription annotationDescription = AnnotationDescription.Builder.ofType(NotNull.class)
//                .define("message", "name不能为空")
//                .build();


//        DynamicType.Unloaded<Object> unloaded = byteBuddy.subclass(Object.class).name("com.baozi.TestBean")
//                .defineField("name", String.class, Visibility.PRIVATE)
//                .annotateField(annotationDescription)
//                .defineMethod("getName", String.class, Visibility.PUBLIC).intercept(FieldAccessor.ofBeanProperty())
//                .defineMethod("setName", void.class, Visibility.PUBLIC).withParameters(String.class).intercept(FieldAccessor.ofBeanProperty())
//                .make();
        DynamicType.Builder<Object> test = byteBuddy.subclass(Object.class).name("com.baozi.TestBean");
        for (int i = 0; i < a.length; i++) {
            DynamicType.Builder.FieldDefinition.Optional.Valuable<Object> objectValuable = test.defineField(a[i], String.class, Visibility.PRIVATE);

            AnnotationDescription annotationDescription = AnnotationDescription.Builder.ofType(ann[i])
                    .define("message", "xx不能为空").build();
            DynamicType.Builder.FieldDefinition.Optional<Object> objectOptional = objectValuable.annotateField(annotationDescription);

            test = objectOptional.defineMethod("get" + capitalize(a[i]), String.class, Visibility.PUBLIC).intercept(FieldAccessor.ofBeanProperty())
                    .defineMethod("set" + capitalize(a[i]), String.class, Visibility.PUBLIC)
                    .withParameters(String.class).intercept(FieldAccessor.ofBeanProperty());

        }
        DynamicType.Unloaded<Object> unloaded = test.make();
        Class<?> testBanClass = unloaded.load(ByteBuddyStudy.class.getClassLoader()).getLoaded();
        Object o = testBanClass.getDeclaredConstructor().newInstance();
        System.out.println(o);
        Set<ConstraintViolation<Object>> validateSet = Validation.buildDefaultValidatorFactory().getValidator().validate(o);
        for (ConstraintViolation<Object> validate : validateSet) {
            System.out.println(validate.getMessage());
        }
    }

    public static String capitalize(String str) {
        char[] chars = str.toCharArray();
        if (97 <= chars[0] && chars[0] <= 122) {
            chars[0] ^= 32;
        }
        return String.valueOf(chars);
    }
}
