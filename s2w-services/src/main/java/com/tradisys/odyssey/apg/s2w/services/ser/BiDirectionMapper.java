package com.tradisys.odyssey.apg.s2w.services.ser;

import java.io.IOException;
import java.io.InputStream;

public interface BiDirectionMapper {
    <T> T mapAny(InputStream in, Class<T> valueType) throws IOException;
}