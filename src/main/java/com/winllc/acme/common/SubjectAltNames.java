package com.winllc.acme.common;

import java.util.*;

public class SubjectAltNames {

    public enum SubjAltNameType {
        DNS,
        IP
    }

    private Map<SubjAltNameType, List<String>> sans = new HashMap<>();

    public Map<SubjAltNameType, List<String>> getSans() {
        return sans;
    }

    public List<String> getValuesForType(SubjAltNameType type){
        if(sans.get(type) != null){
            return sans.get(type);
        }else{
            return new ArrayList<>();
        }
    }

    public void addValue(SubjectAltName san){
        addValue(SubjAltNameType.valueOf(san.getType()), san.getValue());
    }

    public void addValue(SubjAltNameType type, String value){
        sans.compute(type, (key, list) -> {
            if(list == null) list = new ArrayList<>();
            list.add(value);
            return list;
        });
    }


    public void addValues(SubjAltNameType type, Collection<String> values){
        for(String value : values){
            addValue(type, value);
        }
    }

}
