package add.haslearntit.domain.entry;

public class EntryPopularity implements Comparable<EntryPopularity> {

	private String name;
	private Long count;

	public EntryPopularity(String name, long count) {
		this.name = name;
		this.count = count;
	}

	public String toString() {
		return name + "(" + count + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (count ^ (count >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntryPopularity other = (EntryPopularity) obj;
		if (count != other.count)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(EntryPopularity o) {
		return o.count.compareTo(this.count);
	}

	public void incrementCount() {
		count++;
	}

	public boolean isFor(Entry entry) {
		return entry.getName().equals(name);
	}

}
