public abstract class Route {
    protected String name;
    NodeRoute head, temp, tail;
    public String getName() {
        return name;
    }
    public abstract void addStation(String stationName, Integer rates);
    public abstract NodeRoute move(Train t);
}
