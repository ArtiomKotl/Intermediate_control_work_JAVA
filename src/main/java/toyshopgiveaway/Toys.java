package toyshopgiveaway;

public class Toys implements Comparable<Toys>{
    private String id;
    private String name;
    private String freqFall;

    public Toys(String id, String name, String freqFall) {
        this.id = id;
        this.name = name;
        this.freqFall = freqFall;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFreqFall() {
        return freqFall;
    }

    @Override
    public String toString() {
        return String.join(",", this.id, this.name, this.freqFall);
    }

    @Override
    public int compareTo(Toys other) {
        return Integer.compare(Integer.parseInt(this.freqFall), Integer.parseInt(other.freqFall));
    }
}
