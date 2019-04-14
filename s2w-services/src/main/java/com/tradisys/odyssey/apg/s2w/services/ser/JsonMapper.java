package com.tradisys.odyssey.apg.s2w.services.ser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class JsonMapper implements BiDirectionMapper {

    @Autowired
    private ObjectMapper jackson;

    @Override
    public <T> T mapAny(InputStream in, Class<T> valueType) throws IOException {
        return jackson.readValue(in, valueType);
    }

    public ObjectMapper getJackson() {
        return jackson;
    }

    public void setJackson(ObjectMapper jackson) {
        this.jackson = jackson;
    }
}
