package com.baozi.snakeyaml;

import com.baozi.snakeyaml.bean.ServerBean;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

/**
 * 作者：鲍庆洋
 * 时间：2023/6/9 10:37
 * 描述：
 */
public class SnakeYAMLStudy {
    public void load() {
        Yaml yaml = new Yaml(new Constructor(ServerBean.class, new LoaderOptions()));
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("test.yaml");
        ServerBean load = yaml.load(resourceAsStream);
        System.out.println(load.toString());
    }

    public static void main(String[] args) {
        SnakeYAMLStudy snakeYAMLStudy = new SnakeYAMLStudy();
        snakeYAMLStudy.load();
    }
}
