public class MyTestingClass {
    private String name;
    private int id;

    public MyTestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        for (int i = 0; i < name.length(); i++) {
            hash = hash * 31 + name.charAt(i);
        }

        // mix in the id
        hash = hash * 17 + id;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "MyTestingClass{name='" + name + "', id=" + id + "}";
    }
}
