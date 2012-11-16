package org.kambanaria.writebytecode.asm;

public class Version {

    private Integer _version;

    public Version(Integer version) {
        _version = version;
    }

    public Integer getVersion() {
        return _version;
    }

    @Override
    public String toString() {
        return "Version: " + _version;
    }
}
