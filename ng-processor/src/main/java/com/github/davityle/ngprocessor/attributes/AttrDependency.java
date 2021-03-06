package com.github.davityle.ngprocessor.attributes;

import java.util.Scanner;

public class AttrDependency {

    private final String className;
    private String[] dependencies;

    public AttrDependency(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    protected static String getResource(String resourcePath){
        try {
            return new Scanner(AttrDependency.class.getClassLoader().getResourceAsStream(resourcePath), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            return new Scanner(AttrDependency.class.getResourceAsStream(resourcePath), "UTF-8").useDelimiter("\\A").next();
        }
    }
}
