package com.yihu.jw.healthyhouse.model.dict;

import java.io.Serializable;
import java.util.Objects;

/**
 * 字典项主键（联合主键）。
 */
public class DictEntryKey implements Serializable{
    String code;
    String dictId;

    public DictEntryKey(){
    }

    public DictEntryKey(String code, String dictId){
        this.code = code == null ? "" : code;
        this.dictId = dictId == null ? null : dictId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DictEntryKey){
            DictEntryKey pk = (DictEntryKey)obj;
            if(this.code==pk.getCode() && this.dictId==(pk.getDictId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, dictId);
    }


}
