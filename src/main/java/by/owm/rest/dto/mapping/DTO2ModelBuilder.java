package by.owm.rest.dto.mapping;

import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

@Component
public class DTO2ModelBuilder extends BeanMappingBuilder {

    @Override
    protected void configure() {

//        mapping(RequestDTO.class, type(RequestWrapper.class).beanFactory(RequestWrapperBeanFactory.class), TypeMappingOptions.oneWay());
//
//        mapping(CandidateDTO.class, type(Candidate.class).beanFactory(CandidateBeanFactory.class), TypeMappingOptions.oneWay());
//
//        mapping(AttachmentDTO.class, type(Attachment.class).beanFactory(AttachmentBeanFactory.class), TypeMappingOptions.oneWay());
//
//        mapping(SourceDTO.class, type(Source.class).beanFactory(SourceBeanFactory.class), TypeMappingOptions.oneWay());

    }
}
