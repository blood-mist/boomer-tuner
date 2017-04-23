package root;

import utils.CategoryType;

import java.util.ArrayList;
import java.util.List;

public class RootModel {
    private boolean playlistMode = false;
    private boolean directorySelected = false;
    private CategoryType selectedCategory;
    private List<PlaylistModeListener> playlistListeners = new ArrayList<>();
    private List<SelectedCategoryListener> categoryListeners = new ArrayList<>();

    public boolean isPlaylistMode() {
        return playlistMode;
    }

    public void setPlaylistMode(boolean playlistMode) {
        this.playlistMode = playlistMode;
        playlistModeChanged();
    }
    public void setDirectorySelection(boolean selection){
        directorySelected = selection;
    }
    public boolean isDirectorySelected(){
        return directorySelected;
    }

    public void togglePlaylistMode() {
        setPlaylistMode(!isPlaylistMode());
    }

    public CategoryType getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryType selectedCategory) {
        this.selectedCategory = selectedCategory;
        categoryChanged();
    }

    public void addPlaylistModeListener(PlaylistModeListener listener) {
        playlistListeners.add(listener);
    }

    public void addSelectedCategoryListener(SelectedCategoryListener listener) {
        categoryListeners.add(listener);
    }

    private void categoryChanged() {
        for (SelectedCategoryListener listener : categoryListeners) {
            listener.selectedCategoryChanged(selectedCategory);
        }
    }

    private void playlistModeChanged() {
        for (PlaylistModeListener listener : playlistListeners) {
            listener.playlistModeChanged(playlistMode);
        }
    }
}
