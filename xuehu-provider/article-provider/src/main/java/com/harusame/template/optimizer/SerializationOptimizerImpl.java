package com.harusame.template.optimizer;

import com.harusame.template.domain.vo.ArticleVo;
import org.apache.dubbo.common.serialize.support.SerializationOptimizer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Component
public class SerializationOptimizerImpl implements SerializationOptimizer {
    @Override
    public Collection<Class<?>> getSerializableClasses() {
        List<Class<?>> classes = new LinkedList<>();
        classes.add(ArticleVo.class);
        return classes;
    }
}
