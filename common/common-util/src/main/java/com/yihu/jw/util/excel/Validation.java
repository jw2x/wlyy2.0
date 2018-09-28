package com.yihu.jw.util.excel;

import java.util.Map;
import java.util.Set;

public interface Validation {

    public int validate(Map<String, Set> repeatMap) throws Exception;
}
