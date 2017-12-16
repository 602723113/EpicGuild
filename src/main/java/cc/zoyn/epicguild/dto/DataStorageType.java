package cc.zoyn.epicguild.dto;

/**
 * 储存类型枚举
 *
 * @author Zoyn
 * @since 2017-11-19
 */
public enum DataStorageType {
    MySQL("mysql"), YAML("yaml");

    private String name = null;

    DataStorageType(String configName) {
        this.name = configName;
    }

    public static DataStorageType getByName(String name) {
        for (DataStorageType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return YAML;
    }

    public String getName() {
        return this.name;
    }
}
