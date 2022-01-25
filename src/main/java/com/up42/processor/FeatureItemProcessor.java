package com.up42.processor;

import com.up42.entity.Features;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author jalajchawla
 */
public class FeatureItemProcessor implements ItemProcessor<Features, Features> {
    @Override
    public Features process(Features item) throws Exception {
        return item;
    }

}
