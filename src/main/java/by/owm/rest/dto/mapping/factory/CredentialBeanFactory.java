package by.owm.rest.dto.mapping.factory;

import by.owm.rest.dto.CredentialsDto;
import org.dozer.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CredentialBeanFactory implements BeanFactory {

    @Override
    public Object createBean(final Object source,
                             final Class<?> sourceClass,
                             final String targetBeanId) {

        if (!(source instanceof CredentialsDto)) {
            throw new IllegalArgumentException("Source is null or doesn't instance of AttachmentDTO.");
        }

        final CredentialsDto attachmentDTO = (CredentialsDto) source;

        return null;
    }
}
