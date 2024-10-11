package project;

public class Class {
    private String ClassName;
    private group[] groups;
    public Class(String className, group[] groups) {
        this.ClassName = className;
        this.groups = groups;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        this.ClassName = className;
    }

    public group[] getGroups() {
        return groups;
    }

    public void setGroups(group[] groups) {
        this.groups = groups;
    }

    public String show() {
        String result = "Class{名称:'" + ClassName + "', 小组:[";
        for (int i = 0; i < groups.length; i++) {
            result += groups[i].show();
            if (i < groups.length - 1) {
                result += ", ";
            }
        }
        result += "]}";
        return result;
    }


}
