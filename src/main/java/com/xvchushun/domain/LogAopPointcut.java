package com.xvchushun.domain;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAopPointcut {
    public abstract String logModulename();
    public abstract String logDesc();
    public abstract log_Type logType();

    public static enum log_Type
    {
        SELECT,ADD,UPDATE,DELETE,EXPORT,IMPORT;

        private String logname;
        private String index;

        public String getLogname() {
            return this.name();
        }
        public String getIndex() {
            return String.valueOf(this.ordinal()+1);
        }

    }
}
