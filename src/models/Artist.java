package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist {
	private final String name;
	private List<Album> albums = new ArrayList<>();

	public Artist(final String name) {
		this.name = name;
	}

	public void addSong(Song song) {
		albums.add(song.getAlbum());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Artist artist = (Artist) o;
		if (!Objects.equals(this.name, artist.getName()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + albums.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}
}
